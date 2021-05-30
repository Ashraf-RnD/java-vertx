/*
 * @author: Md Ashraful Alam
 * @On: 5/30/21, 1:29 PM
 */

package com.ashraful.enigma.java_vertx.model;

import java.io.Serializable;

public class SubscribeSnsTopicRequest implements Serializable {
  private String vendorSubscriptionUrl;
  private String topicArn;

  @Override
  public String toString() {
    return "SubscribeSnsTopicRequest{" +
      "vendorSubscriptionUrl='" + vendorSubscriptionUrl + '\'' +
      ", topicArn='" + topicArn + '\'' +
      '}';
  }

  public String getVendorSubscriptionUrl() {
    return vendorSubscriptionUrl;
  }

  public void setVendorSubscriptionUrl(String vendorSubscriptionUrl) {
    this.vendorSubscriptionUrl = vendorSubscriptionUrl;
  }

  public String getTopicArn() {
    return topicArn;
  }

  public void setTopicArn(String topicArn) {
    this.topicArn = topicArn;
  }
}
