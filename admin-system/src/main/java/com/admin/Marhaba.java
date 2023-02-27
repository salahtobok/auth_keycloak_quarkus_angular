package com.admin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("marhaba")
public class Marhaba {



    @GET
    @Produces("text/plain")
    public String fetchGreeting(){
        return "Ahlan Wasahla";
    }

}
