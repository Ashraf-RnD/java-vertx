package com.ashraful.enigma.java_vertx.verticle;

import com.ashraful.enigma.java_vertx.model.Book;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;

public class LibraryVerticle extends AbstractVerticle {


  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    var router = Router.router(vertx);

    router
      .route("/*")
      .handler(BodyHandler.create());


    router
      .route(HttpMethod.POST, "/saveBook")
      .handler(this::saveBook);

    vertx
      .createHttpServer()
      .requestHandler(router)
      .listen(8878);
  }

  private void saveBook(RoutingContext routingContext) {

    Gson gson = new Gson();

    System.out.println("routingContext = " + routingContext.getBodyAsString());
    var jsonObject = routingContext.getBodyAsJson();
    var book = gson.fromJson(String.valueOf(jsonObject),Book.class);
    System.out.println("book = " + book);

    routingContext
      .response()
      .setStatusCode(201)
      .putHeader("content-type", "application/json")
      .end(gson.toJson(book));
  }


}
