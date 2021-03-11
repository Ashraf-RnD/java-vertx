/*
 * @author: Md Ashraful Alam
 * @On: 3/11/21, 11:50 AM
 */

package com.ashraful.enigma.java_vertx.sub;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.json.JsonObject;

import static com.ashraful.enigma.java_vertx.commons.Constants.ADDRESS_EVENT_PUBLISHER;

public abstract class AbstractSubscriber extends AbstractVerticle {

  protected void eventConsumer(Logger log){
    vertx.eventBus()
      .consumer(ADDRESS_EVENT_PUBLISHER, msg -> {

        var msgBody = msg.body();
        log.info("msgBody = " + this.getClass().getName() + " :: " + msgBody);

        var obj = new JsonObject();
        obj.put("from", this.getClass().getName());
        msg.reply(obj);
      });
  }
}
