package com.admin.business.rest_resource;

import com.admin.Employee;
import com.admin.Room;
import com.admin.business.registered_rest_client.EmployeeServiceRest;
import com.admin.business.registered_rest_client.RoomServiceRest;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/employees")
public class EmployeeResource {

    @Inject
    @RestClient
    EmployeeServiceRest employeeService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        employees.forEach(System.out::println);
        return employees;
    }
}
