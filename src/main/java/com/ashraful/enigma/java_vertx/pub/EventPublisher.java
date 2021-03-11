/*
 * @author: Md Ashraful Alam
 * @On: 3/11/21, 10:52 AM
 */

package com.ashraful.enigma.java_vertx.pub;

import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import static com.ashraful.enigma.java_vertx.commons.Constants.*;

public class EventPublisher extends AbstractPublisher {
  Logger log = LoggerFactory.getLogger(this.getClass());
  Gson gson = new Gson();

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    Router router = Router.router(vertx);

    router.route("/*")
      .handler(BodyHandler.create());

    router.route("/messageEvent")
      .handler(routingContext -> {
        log.info(this.getClass().getName() + " :: message: " + routingContext.getBodyAsString());
        var msgObject = new JsonObject();
        msgObject.put("from", "EventPublisher");
        msgObject.put("body", routingContext.getBodyAsJson());

        vertx.eventBus()
          .publish(ADDRESS_EVENT_PUBLISHER, msgObject);

        var response = routingContext.response();
        response.putHeader(CONTENT_TYPE, TEXT_PLAIN);
        response.end("Published event from EventPublisher");
      });

    listenVertxServer(router, 8870);
  }

}
