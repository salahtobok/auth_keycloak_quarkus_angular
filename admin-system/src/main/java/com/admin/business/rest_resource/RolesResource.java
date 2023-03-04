package com.admin.business.rest_resource;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RoleRepresentation;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;
import static org.keycloak.OAuth2Constants.PASSWORD;

@Path("/api/admin")
public class RolesResource {

    @Inject
    Keycloak keycloak;
    Keycloak keycloak1;

    @PostConstruct
    public void initKeycloak() {

/*        keycloak1 = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8543")
                .realm("quarkus")
                .clientId("backend-service")
                .clientSecret("76e5X5DjYZWUX0a4TsP7njBYVVfrlT6p")
                .username("salahtobok")
                .password("root")
                .grantType(PASSWORD)
                .build();*/


        keycloak1 = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8543")
                .realm("quarkus")
                .clientId("backend-service")
//                .clientSecret("76e5X5DjYZWUX0a4TsP7njBYVVfrlT6p")
                .username("salahtobok")
                .password("root")
//                .grantType(CLIENT_CREDENTIALS)
                .build();


/*                worked when client is public
        .serverUrl("http://localhost:8543")
                .realm("quarkus")
                .clientId("backend-service")
                .username("salahtobok")
                .password("root")
                .build();*/
        }


    @GET
    @Path("/roles")
    public List<RoleRepresentation> getRoles() {
        try {
            System.out.println("keycloak.realm(\"quarkus\").roles().list() = " + keycloak.tokenManager().grantToken().getToken());
        }catch (Exception exception){
            System.out.println("exception.getMessage() keycloak1 = " + exception.getMessage());
        }


        try {
            System.out.println("keycloak1.realm(\"quarkus\").roles().list() = " + keycloak1.tokenManager().grantToken().getToken());
        }catch (Exception exception){
            System.out.println("exception.getMessage() keycloak1 = " + exception.getMessage());
        }
        return keycloak.realm("quarkus").roles().list();
    }

}