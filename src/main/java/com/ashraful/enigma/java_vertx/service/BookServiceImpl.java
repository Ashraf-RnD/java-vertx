package com.ashraful.enigma.java_vertx.service;

import com.ashraful.enigma.java_vertx.constants.DynamoDbTableName;
import com.ashraful.enigma.java_vertx.entity.BookData;
import com.ashraful.enigma.java_vertx.repository.DynamoDbReactiveRepo;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final DynamoDbReactiveRepo dynamoDbReactiveRepo;

    @Override
    public Mono<CompletableFuture<Void>> saveBook(BookData bookData) {
        return Mono.fromCompletionStage(CompletableFuture.supplyAsync(
                () -> dynamoDbReactiveRepo.putData(bookData, BookData.class, DynamoDbTableName.BOOK_DATA.getValue())));
    }

    @Override
    public Mono<BookData> getBook(String bookId) {
        return Mono.fromCompletionStage(
                CompletableFuture.supplyAsync(
                        () -> dynamoDbReactiveRepo.getData(bookId, BookData.class, DynamoDbTableName.BOOK_DATA.getValue())));
    }

    @Override
    public Flux<BookData> getAllBooks() {
        return Flux.from(dynamoDbReactiveRepo.scanAll(BookData.class, DynamoDbTableName.BOOK_DATA.getValue()))
                .flatMapIterable(Page::items);
    }
}
