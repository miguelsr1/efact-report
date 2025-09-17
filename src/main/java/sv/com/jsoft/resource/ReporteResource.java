package sv.com.jsoft.resource;

import com.google.gson.JsonObject;
import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import sv.com.jsoft.security.SecurityService;
import sv.com.jsoft.service.ReporteService;

@Path("/secured/dte/report")
public class ReporteResource extends SecurityService {

    @Inject
    ReporteService reporteService;

    @GET
    @Path("/{idFactura}")
    public Response getRpt(@PathParam("idFactura") Long idFactura) {
        Log.info("GET /: " + idFactura + " - " + identity.getPrincipal().getName());
        JsonObject jsonPdf = reporteService.getReportDte(idFactura, identity.getPrincipal().getName());
        if (jsonPdf.isEmpty()) {
            Log.info("GET /: " + idFactura + " - " + identity.getPrincipal().getName() + " NOT_FOUND");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            Log.info("GET /: " + idFactura + " - " + identity.getPrincipal().getName() + " OK");
            return Response.ok(jsonPdf.toString()).build();
        }
    }

    @GET
    @Path("/pdf/{idFactura}")
    @RolesAllowed("ROLE_EMISOR")
    public Response getPdfDte(@PathParam("idFactura") Long idFactura) {
        Log.info("GET /pdf/" + idFactura + " - " + identity.getPrincipal().getName());
        JsonObject jsonPdf = reporteService.findPdfDte(idFactura, identity.getPrincipal().getName());
        if (jsonPdf.isEmpty()) {
            Log.info("GET /pdf/" + idFactura + " - " + identity.getPrincipal().getName() + " NOT_FOUND");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            Log.info("GET /pdf/" + idFactura + " - " + identity.getPrincipal().getName() + " OK");
            return Response.ok(jsonPdf.toString()).build();
        }
    }

    @GET
    @Path("/json/{idFactura}")
    @RolesAllowed("ROLE_EMISOR")
    public Response getJsonDte(@PathParam("idFactura") Long idFactura) {
        Log.info("GET /json/" + idFactura + " - " + identity.getPrincipal().getName());
        JsonObject jsonPdf = reporteService.findJsonDte(idFactura, identity.getPrincipal().getName());
        if (jsonPdf.isEmpty()) {
            Log.info("GET /json/" + idFactura + " - " + identity.getPrincipal().getName() + " NOT_FOUND");
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            Log.info("GET /json/" + idFactura + " - " + identity.getPrincipal().getName() + " OK");
            return Response.ok(jsonPdf.toString()).build();
        }
    }
}
