/*
 * @author: Md Ashraful Alam
 * @On: 3/11/21, 11:13 AM
 */

package com.ashraful.enigma.java_vertx.sub;

import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;

import static com.ashraful.enigma.java_vertx.commons.Constants.ADDRESS_P2P_SENDER;

public class DeltaSubscriber extends AbstractSubscriber {
  Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    eventConsumer(log,ADDRESS_P2P_SENDER);

  }
}
