package sv.com.jsoft.util;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperRunManager;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.Map;

@ApplicationScoped
public class JasperReportUtil {

    public byte[] getReportPDF(Map<String, Object> map, String reportName, Path context, Connection conn) {
        byte[] bytes = null;
        try {
            File reportFile = compile(reportName, context);
            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), map, conn);
        } catch (Exception e) {
            Log.error("SE PRESENTO EL SIGUIENTE ERROR getReportPDF: ", e);
        }
        return bytes;
    }

    /**
     * metodo que compila
     *
     * @param path
     * @return
     * @throws Exception
     * @author wcanas
     */
    private File compile(String reportName, Path path) throws JRException {
        //se obtiene reporte compilado como jasper
        var reportFile = new File(path + File.separator + reportName + ".jasper");
        if (!reportFile.exists()) {//si no existe se compila
            String strReportSourceFile = path + File.separator + reportName + ".jrxml";
            //se compila reporte jrxml como jasper
            JasperCompileManager.compileReportToFile(strReportSourceFile, reportFile.toString());
        }
        return reportFile;
    }
}
