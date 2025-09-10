package sv.com.jsoft.util;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import sv.com.jsoft.model.Contribuyente;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@ApplicationScoped
public class UtilContribuyente {

    public Contribuyente findContribuyenteByUser(String username) {
        List<Contribuyente> contribuyentes = Contribuyente.find("usuario", username).list();

        if (contribuyentes.isEmpty()) {
            return null;
        }

        return contribuyentes.get(0);
    }

    /**
     * Helper para centralizar la búsqueda de contribuyente y el manejo de respuesta 404.
     */
    public Response withContribuyente(String username, Function<Contribuyente, Response> action) {
        Optional<Contribuyente> contribuyenteOpt = Contribuyente.find("usuario", username).firstResultOptional();
        return contribuyenteOpt
                .map(action)
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }
}
