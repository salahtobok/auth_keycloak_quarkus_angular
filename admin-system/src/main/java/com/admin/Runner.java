//package com.admin;
//
//import com.admin.Employee;
//import com.admin.Room;
//import com.admin.business.registered_rest_client.EmployeeServiceRest;
//import com.admin.business.registered_rest_client.RoomServiceRest;
//import io.quarkus.runtime.QuarkusApplication;
//import io.quarkus.runtime.annotations.QuarkusMain;
//import org.eclipse.microprofile.rest.client.inject.RestClient;
//
//import javax.inject.Inject;
//import java.util.List;
//
//@QuarkusMain
//public class Runner implements QuarkusApplication {
//
//    @Inject
//    @RestClient
//    RoomServiceRest roomService;
//
//    @Inject
//    @RestClient
//    EmployeeServiceRest employeeService;
//
//    @Override
//    public int run(String... args) throws Exception {
//        List<Room> rooms = roomService.getAllRooms();
//        rooms.forEach(System.out::println);
//
//        List<Employee> employees = employeeService.getAllEmployees();
//        employees.forEach(System.out::println);
//        return 0;
//    }
//}
