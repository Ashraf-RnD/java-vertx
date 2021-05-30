/*
 * @author: Md Ashraful Alam
 * @On: 5/30/21, 12:33 PM
 */

package com.ashraful.enigma.java_vertx.model;

import lombok.*;

import java.io.Serializable;


public class CreateSnsTopicRequest implements Serializable {
  private String topicName;

  @Override
  public String toString() {
    return "CreateSnsTopicRequest{" +
      "topicName='" + topicName + '\'' +
      '}';
  }

  public String getTopicName() {
    return topicName;
  }

  public void setTopicName(String topicName) {
    this.topicName = topicName;
  }
}
