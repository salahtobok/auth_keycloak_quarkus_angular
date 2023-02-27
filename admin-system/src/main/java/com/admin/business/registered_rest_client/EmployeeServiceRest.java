package com.admin.business.registered_rest_client;

import com.admin.Employee;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/employees")
@ApplicationScoped
@RegisterRestClient(configKey="employees-api")
public interface EmployeeServiceRest {

    @GET
    List<Employee> getAllEmployees();
}
