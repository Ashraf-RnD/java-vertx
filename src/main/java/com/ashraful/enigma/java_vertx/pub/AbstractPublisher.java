/*
 * @author: Md Ashraful Alam
 * @On: 3/11/21, 12:03 PM
 */

package com.ashraful.enigma.java_vertx.pub;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;

public class AbstractPublisher extends AbstractVerticle {

  protected void listenVertxServer(Router router, int port) {
    vertx.createHttpServer()
      .requestHandler(router)
      .listen(port);
  }
}
