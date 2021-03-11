package com.ashraful.enigma.java_vertx.verticle;

import com.ashraful.enigma.java_vertx.entity.BookData;
import com.ashraful.enigma.java_vertx.model.Book;
import com.ashraful.enigma.java_vertx.repository.DynamoDbReactiveRepo;
import com.ashraful.enigma.java_vertx.service.BookServiceImpl;
import com.google.gson.Gson;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class LibraryVerticle extends AbstractVerticle {

  private final BookServiceImpl bookService;

  public LibraryVerticle() {
    this.bookService = new BookServiceImpl(new DynamoDbReactiveRepo());
  }

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
    var book = gson.fromJson(String.valueOf(jsonObject), Book.class);
    BookData bookData = BookData.builder()
      .bookId(String.valueOf(System.currentTimeMillis()))
      .bookName(book.getBookName())
      .bookPrice(book.getBookPrice())
      .bookType(book.getBookType())
      .bookLanguage(book.getBookLanguage())
      .build();
    System.out.println("bookData = " + bookData);
    bookService.saveBook(bookData)
      .doOnSuccess(b -> {
        System.out.println("b = " + b);
        routingContext
          .response()
          .setStatusCode(201)
          .putHeader("content-type", "application/json")
          .end(gson.toJson(b));
      });


  }


}
