package com.ashraful.enigma.java_vertx.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.io.Serializable;

@DynamoDbBean
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookData implements Serializable{

    private String bookId;
    private String bookName;
    private String bookPrice;
    private String bookType;
    private String bookLanguage;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("bookId")
    public String getBookId() {
        return bookId;
    }
}
