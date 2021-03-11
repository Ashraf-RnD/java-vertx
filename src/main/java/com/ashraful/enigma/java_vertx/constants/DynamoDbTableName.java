package com.ashraful.enigma.java_vertx.constants;

public enum DynamoDbTableName {
    BOOK_DATA("book-data");

    private final String value;

    DynamoDbTableName(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
