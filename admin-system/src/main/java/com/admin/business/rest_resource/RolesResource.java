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
    Keycloak keycloakByKeycloakBuilder;

    @PostConstruct
    public void initKeycloak() {
        keycloakByKeycloakBuilder = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8543")
                .realm("quarkus")
                .clientId("backend-service")
                .clientSecret("RtWFIpgFl0VtHdj4pwV5kvJSXfQ0Nytf")
                .username("salahtobok")
                .password("root")
                .grantType(PASSWORD)
                .build();
        }


    @GET
    @Path("/roles")
    public List<RoleRepresentation> getRoles() {
        try {
            System.out.println("keycloak.tokenManager().grantToken().getToken() = " + keycloak.tokenManager().grantToken().getToken());
        }catch (Exception exception){
            System.out.println("exception.getMessage() keycloak = " + exception.getMessage());
        }


        try {
            System.out.println("keycloakByKeycloakBuilder.tokenManager().grantToken().getToken() = " + keycloakByKeycloakBuilder.tokenManager().grantToken().getToken());
        }catch (Exception exception){
            System.out.println("exception.getMessage() keycloakByKeycloakBuilder = " + exception.getMessage());
        }
        return keycloak.realm("quarkus").roles().list();
    }

}