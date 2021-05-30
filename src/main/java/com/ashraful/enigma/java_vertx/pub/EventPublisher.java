/*
 * @author: Md Ashraful Alam
 * @On: 3/11/21, 10:52 AM
 */

package com.ashraful.enigma.java_vertx.pub;

import com.ashraful.enigma.java_vertx.model.CreateSnsTopicRequest;
import com.ashraful.enigma.java_vertx.model.NotificationRequest;
import com.ashraful.enigma.java_vertx.model.SubscribeSnsTopicRequest;
import com.ashraful.enigma.java_vertx.service.SnsAsyncService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.vertx.core.Promise;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import static com.ashraful.enigma.java_vertx.commons.Constants.ADDRESS_EVENT_PUBLISHER;

public class EventPublisher extends AbstractPublisher {
  Logger log = LoggerFactory.getLogger(this.getClass());

  private final Gson gson;
  private final SnsAsyncService snsAsyncService;

  public EventPublisher() {
    this.snsAsyncService = new SnsAsyncService();
    this.gson = new Gson();
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    Router router = Router.router(vertx);

    router.route("/*")
      .handler(BodyHandler.create());

    router.route("/messageEvent")
      .handler(routingContext -> {
        log.info(this.getClass().getName() + " :: message: " + routingContext.getBodyAsString());
        var msgObject = getEntries(this.getClass().getName(), routingContext.getBodyAsJson());

        vertx.eventBus()
          .publish(ADDRESS_EVENT_PUBLISHER, msgObject);

        prepareResponse(routingContext);
      });

    router.route("/createTopic")
      .handler(routingContext -> {
        log.info("createTopic :: message: " + routingContext.getBodyAsString());
        var createTopicRequest = getRequest(routingContext, CreateSnsTopicRequest.class);
        log.info("createTopic :: CreateTopicRequest: " + createTopicRequest.toString());

        snsAsyncService.createTopic(createTopicRequest.getTopicName())
          .thenAccept(s -> {
            var response = new HashMap<String,String>();
            response.put("topicArn",s);
            prepareResponse(routingContext, gson.toJson(response));
          });
      });

    router.route("/subscribeUrl")
      .handler(routingContext -> {
        log.info("subscribeUrl :: message: " + routingContext.getBodyAsString());
        var subscribeSnsTopicRequest = getRequest(routingContext, SubscribeSnsTopicRequest.class);

        snsAsyncService.subscribeVendorUrl(subscribeSnsTopicRequest.getVendorSubscriptionUrl(),
          subscribeSnsTopicRequest.getTopicArn(),
          "http")
          .thenAccept(s -> {
            var response = new HashMap<String,String>();
            response.put("subscriptionArn",s);
            prepareResponse(routingContext, gson.toJson(response));
          });

//        prepareResponse(routingContext, "Subscribed");
      });

    router.route("/publishMsg")
      .handler(routingContext -> {
        log.info("subscribeUrl :: message: " + routingContext.getBodyAsString());
        var notificationRequest = getRequest(routingContext, NotificationRequest.class);

        snsAsyncService.sendNotification(
          notificationRequest.getNotificationMessage(),
          notificationRequest.getTopicArn());

        prepareResponse(routingContext, "published");
      });

    listenVertxServer(router, 8870);
  }

  private <T> T getRequest(RoutingContext routingContext, Class<T> tClass) {
    String bodyAsString = routingContext.getBodyAsString();
    return gson.fromJson(bodyAsString, tClass);
  }

}
