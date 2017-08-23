package org.jacpfx.service;


import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.jacpfx.common.*;
import org.jacpfx.model.Employee;
import org.jacpfx.vertx.services.ServiceVerticle;

import javax.ws.rs.*;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * Created by amo on 29.10.14.
 */
@ApplicationPath("/service-REST-GET")
@Selfhosted(port = 9090)
public class RESTGetVerticleService extends ServiceVerticle {


    @Path("/wsEndpintOne")
    @OperationType(Type.WEBSOCKET)
    @Consumes("application/json")
    public void wsEndpointOne(String name, WSResponse reply) {
        vertx.fileSystem().readFile("employeeAll.json", ar -> {
            if (ar.succeeded()) {
                final Buffer result = ar.result();

                reply.reply(() -> {
                            try {
                                return new String(result.getBytes(), "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                );

            }

        });

    }

    @Path("/testSimpleString")
    @OperationType(Type.WEBSOCKET)
    @Consumes("application/json")
    @Produces("application/json")
    public void testSimpleString(String name, WSResponse reply) {
        reply.reply(() -> name);
    }

    @Path("/wsEndpintTwo")
    @OperationType(Type.WEBSOCKET)
    public void wsEndpointTwo(String name, WSResponse reply) {
        reply.reply(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + "-3" + Thread.currentThread();
        });
        reply.reply(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + "-4" + Thread.currentThread();
        });
        reply.reply(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + "-5" + Thread.currentThread();
        });
        reply.reply(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + "-6" + Thread.currentThread();
        });
        System.out.println("Message-2: " + name + "   :::" + this);
    }

    @Path("/wsEndpintThree")
    @OperationType(Type.WEBSOCKET)
    public void wsEndpointThreeReplyToAll(String name, WSResponse reply) {
        reply.replyToAll(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + "-3" + Thread.currentThread();
        });
        reply.replyToAll(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + "-4" + Thread.currentThread();
        });
        reply.replyToAll(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + "-5" + Thread.currentThread();
        });
        reply.replyToAll(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + "-6" + Thread.currentThread();
        });
        System.out.println("Message-2: " + name + "   :::" + this);
    }


    @Path("/wsEndpintFour")
    @OperationType(Type.WEBSOCKET)
    public void wsEndpointThreeReplyToAllTwo(String name, WSResponse reply) {
        reply.replyToAll(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return name + "-3" + Thread.currentThread();
        });
        System.out.println("Message-4: " + name + "   :::" + this);
    }

    @Path("/hello")
    @OperationType(Type.WEBSOCKET)
    public void wsEndpointHello(String name, WSResponse reply) {

        reply.reply(() -> name + "-2");
        System.out.println("Message-1: " + name + "   :::" + this);
    }

    @Path("/asyncReply")
    @OperationType(Type.WEBSOCKET)
    public void wsEndpointAsyncReply(String name, WSResponse reply) {

        reply.reply(() -> name + "-2");
        System.out.println("Message-1: " + name + "   :::" + this);
    }


    @Path("/testEmployeeOne")
    @OperationType(Type.REST_GET)
    @Produces("application/json")
    public void getTestEmployeeOne(@QueryParam("name") String name, @QueryParam("lastname") String lastname, Message message, final RoutingContext routingContext) {
        if (message != null) {
            message.reply(name + ":" + lastname);
        } else {
            routingContext.response().end(name + ":" + lastname);
        }
    }


    @Path("/testEmployeeTwo")
    @OperationType(Type.REST_GET)
    @Produces("application/json")
    public JsonObject getTestEmployeeTwo(@PathParam("id") String id) {
        return new JsonObject().put("id", id);
    }


    @Path("/testEmployeeThree/:id")
    @OperationType(Type.REST_GET)
    public void getTestEmployeeByPathParameterOne(@PathParam("id") String id, Message message, final RoutingContext routingContext) {
        if (message != null) {
            message.reply(id);
        } else {
            routingContext.response().end(id);
        }
    }


    @Path("/testEmployeeThree/:id/:name")
    @OperationType(Type.REST_GET)
    public void getTestEmployeeByPathParameterTwo(Message message, final RoutingContext routingContext, @PathParam("id") String id, @PathParam("name") String name) {
        if (message != null) {
            message.reply(id + ":" + name);
        } else {
            routingContext.response().end(id + ":" + name);
        }
    }


    @Path("/testEmployeeFour/:id/employee/:name")
    @OperationType(Type.REST_GET)
    public void getTestEmployeeByPathParameterThree(Message message, final RoutingContext routingContext, @PathParam("id") String id, @PathParam("name") String name) {
        if (message != null) {
            message.reply(id + ":" + name);
        } else {
            routingContext.response().end(id + ":" + name);
        }
    }

    @Path("/testEmployeeFive")
    @OperationType(Type.REST_GET)
    @Produces("application/json")
    public Employee getTestEmployeeFive(@PathParam("id") String id) {
        return new Employee(id, "dfg", null, "dfg", "fdg", "dfg");
    }


    @Path("/getAll")
    @OperationType(Type.REST_GET)
    @Produces({"text/json", "application/json"})
    public void getAll(@QueryParam("name") String name, final Message message, final RoutingContext routingContext) {

        vertx.fileSystem().readFile("employeeAll.json", ar -> {
            if (ar.succeeded()) {
                final Buffer result = ar.result();
                try {
                    if (message != null) message.reply(new String(result.getBytes(), "UTF-8"));
                    if (routingContext != null) routingContext.response().end(new String(result.getBytes(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @Path("/testSimpleJsonString")
    @OperationType(Type.EVENTBUS)
    @Consumes("application/json")
    public void testSimpleJsonString(String name, EBMessageReply reply) {
        System.out.println(name);
        reply.reply("ass");
    }


}
