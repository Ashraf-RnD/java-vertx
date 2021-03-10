package com.ashraful.enigma.java_vertx;

import com.ashraful.enigma.java_vertx.verticle.LibraryVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.deployVerticle(new LibraryVerticle());
  }
}
