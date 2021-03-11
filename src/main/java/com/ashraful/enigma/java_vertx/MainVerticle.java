package com.ashraful.enigma.java_vertx;

import com.ashraful.enigma.java_vertx.pub.EventPublisher;
import com.ashraful.enigma.java_vertx.sub.AlphaSubscriber;
import com.ashraful.enigma.java_vertx.sub.DeltaSubscriber;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.deployVerticle(new EventPublisher());
    vertx.deployVerticle(new AlphaSubscriber());
    vertx.deployVerticle(new DeltaSubscriber());
  }
}
