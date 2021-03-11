package com.ashraful.enigma.java_vertx.service;

import com.ashraful.enigma.java_vertx.entity.BookData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public interface BookService {
    Mono<CompletableFuture<Void>> saveBook(BookData bookData);
    Mono<BookData> getBook(String bookId);
    Flux<BookData> getAllBooks();
}
