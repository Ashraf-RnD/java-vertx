package com.ashraful.enigma.java_vertx.repository;

import com.ashraful.enigma.java_vertx.configuration.DynamoDbClientConfig;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbAsyncTable;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PagePublisher;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

import java.util.concurrent.CompletableFuture;


public class DynamoDbReactiveRepo {
    private final DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient;

  public DynamoDbReactiveRepo() {
    this.dynamoDbEnhancedAsyncClient = DynamoDbClientConfig.dynamoDbEnhancedAsyncClient();
  }

  public <T> CompletableFuture<Void> putData(T t, Class<T> tClass, String tableName){
        DynamoDbAsyncTable<T> mappedTable = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(tClass));
        return mappedTable.putItem(t);
    }

    public <T> T getData(String partitionKey, Class<T> tClass, String tableName) {
        DynamoDbAsyncTable<T> mappedTable = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(tClass));
        Key key = Key.builder()
                .partitionValue(partitionKey)
                .build();
        return (T) mappedTable.getItem(key);
    }

    public <T> T getData(String partitionKey, String sortKey, Class<T> tClass, String tableName) {
        DynamoDbAsyncTable<T> mappedTable = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(tClass));
        Key key = Key.builder()
                .partitionValue(partitionKey)
                .sortValue(sortKey)
                .build();
        return (T) mappedTable.getItem(key);
    }

    public <T> CompletableFuture<T> deleteItem(T t,Class<T> tClass, String tableName) {
        var mappedTable = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(tClass));
        return mappedTable.deleteItem(t);
    }

    public <T> PagePublisher<T> scanAll(Class<T> tClass, String tableName){
        var mappedTable = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(tClass));
        return mappedTable.scan();
    }

    public <T> PagePublisher<T> findAllItems(Class<T> tClass, String tableName) {
        var mappedTable = dynamoDbEnhancedAsyncClient.table(tableName, TableSchema.fromBean(tClass));
        return mappedTable.scan(ScanEnhancedRequest.builder().consistentRead(true).build());
    }
}
