/*
 * @author: Md Ashraful Alam
 * @On: 3/11/21, 12:03 PM
 */

package com.ashraful.enigma.java_vertx.pub;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import static com.ashraful.enigma.java_vertx.commons.Constants.*;

public class AbstractPublisher extends AbstractVerticle {

  protected void listenVertxServer(Router router, int port) {
    vertx.createHttpServer()
      .requestHandler(router)
      .listen(port);
  }
  protected JsonObject getEntries(String sourceName, JsonObject msgBody) {
    var msgObject = new JsonObject();
    msgObject.put("from", sourceName);
    msgObject.put("body", msgBody);
    return msgObject;
  }


  protected void prepareResponse(RoutingContext routingContext) {
    var response = routingContext.response();
    response.putHeader(CONTENT_TYPE, TEXT_PLAIN);
    response.end("Response event from "+this.getClass().getName());
  }
  protected void prepareResponse(RoutingContext routingContext,String responseString) {
    var response = routingContext.response();
    response.putHeader(CONTENT_TYPE, APPLICATION_JSON);
    response.end(responseString);
  }
}
