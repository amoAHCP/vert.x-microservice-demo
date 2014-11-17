package org.jacpfx.service;


import org.jacpfx.common.OperationType;
import org.jacpfx.common.Type;
import org.jacpfx.model.Employee;
import org.jacpfx.vertx.services.ServiceVerticle;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;

import javax.ws.rs.*;

/**
 * Created by amo on 29.10.14.
 */
@ApplicationPath("/service-REST-GET")
public class RESTGetVerticleService extends ServiceVerticle {




    @Path("/testEmployeeOne")
    @OperationType(Type.REST_GET)
    @Produces("application/json")
    public void getTestEmployeeOne(@QueryParam("name") String name, @QueryParam("lastname") String lastname, Message message) {
        message.reply(name + ":" + lastname);
    }


    @Path("/testEmployeeTwo")
    @OperationType(Type.REST_GET)
    @Produces("application/json")
    public JsonObject getTestEmployeeTwo(@PathParam("id") String id) {
        return new JsonObject().putString("id",id);
    }


    @Path("/testEmployeeThree/:id")
    @OperationType(Type.REST_GET)
    public void getTestEmployeeByPathParameterOne(@PathParam("id") String id, Message message) {
        message.reply(id);
    }


    @Path("/testEmployeeThree/:id/:name")
    @OperationType(Type.REST_GET)
    public void getTestEmployeeByPathParameterTwo(Message message, @PathParam("id") String id, @PathParam("name") String name) {
        message.reply(id + ":" + name);
    }


    @Path("/testEmployeeFour/:id/employee/:name")
    @OperationType(Type.REST_GET)
    public void getTestEmployeeByPathParameterThree(Message message, @PathParam("id") String id, @PathParam("name") String name) {
        message.reply(id + ":" + name);
    }

    @Path("/testEmployeeFive")
    @OperationType(Type.REST_GET)
    @Produces("application/json")
    public Employee getTestEmployeeFive(@PathParam("id") String id) {
        return new Employee(id,"dfg",null,"dfg","fdg","dfg");
    }


}
