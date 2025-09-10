package sv.com.jsoft.security;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import sv.com.jsoft.model.Contribuyente;

import java.util.List;

public class SecurityService {

    @Inject
    public SecurityIdentity identity;

    public Response getContribuyente() {
        List<Contribuyente> contribuyentes = Contribuyente.find("usuario", identity.getPrincipal().getName()).list();
        if (contribuyentes.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response
                .status(Response.Status.OK)
                .entity(contribuyentes.get(0))
                .build();
    }
}
