package com.ashraful.enigma.java_vertx.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

  private String bookName;
  private String bookType;
  private String bookPrice;
  private String bookLanguage;
}
