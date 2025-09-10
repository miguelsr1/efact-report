package sv.com.jsoft.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.agroal.api.AgroalDataSource;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import sv.com.jsoft.model.BitacoraMh;
import sv.com.jsoft.model.Contribuyente;
import sv.com.jsoft.model.Factura;
import sv.com.jsoft.util.JasperReportUtil;
import sv.com.jsoft.util.UtilContribuyente;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ReporteService {

    @Inject
    AgroalDataSource dataSource;

    @Inject
    UtilContribuyente utilContribuyente;

    @ConfigProperty(name = "greeting.rpt.path")
    String pathRpt;


    public JsonObject getReportDte(Long idFactura, String username) {

        //Contribuyente contribuyente = utilContribuyente.findContribuyenteByUser(username);
        Factura factura = Factura.findById(idFactura);
        Contribuyente contribuyente = Contribuyente.findById(factura.idContribuyente);
        if (factura == null) {
            return new JsonObject();
        }

        JsonObject json = new JsonObject();

        byte[] reporte = makeReport(factura);
        JsonObject jsonDte = getJsonDte(contribuyente, factura, username);

        json.addProperty("pdf", Base64.getEncoder().encodeToString(reporte));
        json.add("json", jsonDte);

        return json;
    }

    public JsonObject findPdfDte(Long idFactura, String username) {
        Contribuyente contribuyente = utilContribuyente.findContribuyenteByUser(username);
        Factura factura = Factura.findById(idFactura);

        byte[] reporte = makeReport(factura);
        JsonObject json = new JsonObject();
        json.addProperty("pdf", Base64.getEncoder().encodeToString(reporte));

        return json;
    }

    public JsonObject findJsonDte(Long idFactura, String username) {
        Contribuyente contribuyente = utilContribuyente.findContribuyenteByUser(username);
        Factura factura = Factura.findById(idFactura);
        JsonObject json = new JsonObject();
        JsonObject jsonDte = getJsonDte(contribuyente, factura, username);
        json.add("json", jsonDte);

        return json;
    }

    public JsonObject getJsonDte(Contribuyente contribuyente, Factura factura, String username) {
        //DocumentoElectronico dte = dteService.makeDtoDte(contribuyente, factura, username);
        String jsonStr = ((BitacoraMh) BitacoraMh.find("idFactura", factura.idFactura).firstResult()).jsonDte; /*new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create().toJson(dte);*/
        return new Gson().fromJson(jsonStr, JsonObject.class);
    }

    public byte[] makeReport(Factura factura) {

        byte[] reporte = null;
        Map<String, Object> hm = new HashMap<>();
        JasperReportUtil jasperReportUtil = new JasperReportUtil();

        hm.put("PID_FACTURA", factura.idFactura);
        hm.put("P_PATH_IMG", pathRpt + File.separator + "images" + File.separator + "logos" + File.separator + "logo-dte.jpg");

        try (Connection conn = dataSource.getConnection()) {
            reporte = jasperReportUtil.getReportPDF(hm,
                    "dte-efact",
                    Path.of(pathRpt),
                    conn);

        } catch (SQLException e) {
            Log.error("ERROR GENERANDO EL PDF DE LA FACTURA " + factura.idFactura, e);
        }

        return reporte;
    }
}