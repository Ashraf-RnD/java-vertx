package com.ashraful.enigma.java_vertx.configuration;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import java.net.URI;

public class DynamoDbClientConfig {
    private static final String DYNAMO_DB_ENDPOINT = "http://localhost:7569";

    public static DynamoDbEnhancedAsyncClient dynamoDbEnhancedAsyncClient() {
        return DynamoDbEnhancedAsyncClient.builder()
                .dynamoDbClient(DynamoDbAsyncClient.builder()
                        .endpointOverride(URI.create(DYNAMO_DB_ENDPOINT))
                        .build())
                .build();
    }
}
