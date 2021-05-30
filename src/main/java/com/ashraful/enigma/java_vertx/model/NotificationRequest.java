/*
 * @author: Md Ashraful Alam
 * @On: 5/30/21, 1:51 PM
 */

package com.ashraful.enigma.java_vertx.model;

import java.io.Serializable;

public class NotificationRequest implements Serializable {
  private String notificationMessage;
  private String topicArn;

  @Override
  public String toString() {
    return "NotificationRequest{" +
      "notificationMessage='" + notificationMessage + '\'' +
      ", topicArn='" + topicArn + '\'' +
      '}';
  }

  public String getNotificationMessage() {
    return notificationMessage;
  }

  public void setNotificationMessage(String notificationMessage) {
    this.notificationMessage = notificationMessage;
  }

  public String getTopicArn() {
    return topicArn;
  }

  public void setTopicArn(String topicArn) {
    this.topicArn = topicArn;
  }
}
