package org.jacpfx.service;


import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import org.jacpfx.common.MessageReply;
import org.jacpfx.common.OperationType;
import org.jacpfx.common.Type;
import org.jacpfx.model.Employee;
import org.jacpfx.vertx.services.ServiceVerticle;

import javax.ws.rs.*;
import java.io.UnsupportedEncodingException;

/**
 * Created by amo on 29.10.14.
 */
@ApplicationPath("/service-REST-GET")
public class RESTGetVerticleService extends ServiceVerticle {


    @Path("/wsEndpintOne")
    @OperationType(Type.WEBSOCKET)
    public void wsEndpointOne(String name,MessageReply reply) {

    }

    @Path("/hello")
    @OperationType(Type.WEBSOCKET)
    public void wsEndpointHello(String name,MessageReply reply) {

          reply.send(name);
        reply.send(name + "-1");
        reply.send(name+"-2");
        System.out.println("Message-1: "+name+"   :::"+this);
    }


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
        return new JsonObject().put("id",id);
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

    @Path("/getAll")
    @OperationType(Type.REST_GET)
    @Produces({"text/json","application/json"})
    public void getAll(@QueryParam("name") String name, final Message message) {

        vertx.fileSystem().readFile("employeeAll.json", ar ->{
            if (ar.succeeded()) {
                final Buffer result = ar.result();
                try {
                    message.reply(new String(result.getBytes(),"UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
