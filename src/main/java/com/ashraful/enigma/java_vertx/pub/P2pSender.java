/*
 * @author: Md Ashraful Alam
 * @On: 3/11/21, 12:52 PM
 */

package com.ashraful.enigma.java_vertx.pub;

import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import static com.ashraful.enigma.java_vertx.commons.Constants.*;

public class P2pSender extends AbstractPublisher{
  Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    Router router = Router.router(vertx);

    router.route("/*")
      .handler(BodyHandler.create());

    router.route("/messageP2p")
      .handler(routingContext -> {
        log.info(this.getClass().getName() + " :: message: " + routingContext.getBodyAsString());
        var msgObject = getEntries(this.getClass().getName(),routingContext.getBodyAsJson());

        vertx.eventBus()
          .request(ADDRESS_P2P_SENDER, msgObject);

        prepareResponse(routingContext);
      });

    listenVertxServer(router, 8871);
  }


}
