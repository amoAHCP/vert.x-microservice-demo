package org.jacpfx.service;

import io.vertx.core.AbstractVerticle;

/**
 * Created by Andy Moncsek on 11.02.15.
 */
public class DemoVerticleA extends AbstractVerticle {

    @Override
    public void start(io.vertx.core.Future<Void> startFuture) throws Exception {
        System.out.println("Demo verticle: " + Thread.currentThread()+" in classloader: "+this.getClass().getClassLoader());
    }
}
