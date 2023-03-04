package keycloak.admin.client;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/api/admin")
public class RolesResource {

    @Inject
    Keycloak keycloak;

    @GET
    @Path("/roles")
    public List<RoleRepresentation> getRoles() {
        return keycloak.realm("quarkus").roles().list();
    }

}