/*
 * @author: Md Ashraful Alam
 * @On: 3/11/21, 10:52 AM
 */

package com.ashraful.enigma.java_vertx.pub;

import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import static com.ashraful.enigma.java_vertx.commons.Constants.ADDRESS_EVENT_PUBLISHER;

public class EventPublisher extends AbstractPublisher {
  Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    Router router = Router.router(vertx);

    router.route("/*")
      .handler(BodyHandler.create());

    router.route("/messageEvent")
      .handler(routingContext -> {
        log.info(this.getClass().getName() + " :: message: " + routingContext.getBodyAsString());
        var msgObject = getEntries(this.getClass().getName(),routingContext.getBodyAsJson());

        vertx.eventBus()
          .publish(ADDRESS_EVENT_PUBLISHER, msgObject);

        prepareResponse(routingContext);
      });

    listenVertxServer(router, 8870);
  }

}
