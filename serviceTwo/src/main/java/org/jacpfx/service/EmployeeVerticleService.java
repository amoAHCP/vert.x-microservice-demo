package org.jacpfx.service;


import org.jacpfx.common.OperationType;
import org.jacpfx.common.Type;
import org.jacpfx.vertx.services.ServiceVerticle;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.UnsupportedEncodingException;

/**
 * Created by amo on 29.10.14.
 */
@ApplicationPath("/service-employee")
public class EmployeeVerticleService extends ServiceVerticle {



    @Path("/getAll")
    @OperationType(Type.REST_GET)
   // @Produces("application/json")
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
