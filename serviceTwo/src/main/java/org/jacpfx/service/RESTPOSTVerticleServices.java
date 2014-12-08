package org.jacpfx.service;


import io.vertx.core.eventbus.Message;
import org.jacpfx.common.OperationType;
import org.jacpfx.common.Type;
import org.jacpfx.vertx.services.ServiceVerticle;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by amo on 29.10.14.
 */
@ApplicationPath("/service-post")
public class RESTPOSTVerticleServices extends ServiceVerticle {


    @Path("/updateForm")
    @OperationType(Type.REST_POST)
    @Produces({"text/json","application/json"})
    public void updateForm(@FormParam("id") String id,
                           @FormParam("summary") String summary,
                           @FormParam("description") String description, final Message message) {

        message.reply("{id: "+id+", summary:"+summary+", description:"+description+"}");
    }




}
