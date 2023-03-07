package com.admin.security.keycloak.authorization;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.cache.NoCache;

import io.quarkus.security.identity.SecurityIdentity;

import java.util.Map;

@Path("/api/users")
public class UsersResource {
    @Inject
    Logger logger;
    @Inject
    SecurityIdentity identity;

    @GET
    @Path("/me")
    @NoCache
    public User me() {
        logger.info("http://localhost:8080/api/users/me api launched");

        return new User(identity);
    }

    public static class User {

        private final String userName;

        User(SecurityIdentity identity) {
            System.out.println("identity.getPrincipal() = " + identity.getPrincipal());
            System.out.println("Map.of(\"issuedFor\",identity.getPrincipal()) = " + Map.of("issuedFor", identity.getPrincipal()));
            this.userName = identity.getPrincipal().getName();
        }

        public String getUserName() {
            return userName;
        }
    }
}