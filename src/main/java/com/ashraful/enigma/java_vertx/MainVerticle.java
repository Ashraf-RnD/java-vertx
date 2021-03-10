package com.ashraful.enigma.java_vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req ->
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello Vert.x! Enigma")
    ).listen(8888);
  }
}
