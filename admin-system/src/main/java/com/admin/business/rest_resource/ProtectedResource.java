//package com.admin.business.rest_resource;
//
//import io.quarkus.security.ForbiddenException;
//import io.quarkus.security.identity.SecurityIdentity;
//import io.smallrye.mutiny.Uni;
//import org.jboss.logging.Logger;
//import org.keycloak.authorization.client.AuthzClient;
//
//import javax.inject.Inject;
//import javax.security.auth.AuthPermission;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import java.security.Permission;
//import java.util.List;
//
//@Path("/api/protected")
//public class ProtectedResource {
//
//    @Inject
//    SecurityIdentity identity;
//
//    @Inject
//    AuthzClient authzClient;
//
//    @GET
//    public Uni<List<Permission>> get() {
//        System.out.println("identity.getPrincipal() = " + identity.getPrincipal().getName());
//        System.out.println("identity.getAttribute(\"permissions\") = " + identity.getAttribute("permissions"));
//        Logger.getLogger(this.getClass()).info("Permissions => : "+identity.getAttribute("permissions"));
//
//
//        return identity.checkPermission(new AuthPermission("{resource_name}")).onItem()
//                .transform(granted -> {
//                    if (granted) {
//                        return identity.getAttribute("permissions");
//                    }
//                    throw new ForbiddenException();
//                });
//    }
//}