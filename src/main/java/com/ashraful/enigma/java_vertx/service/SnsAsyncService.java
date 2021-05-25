/*
 * @author: Md Ashraful Alam
 * @On: 5/25/21, 2:59 PM
 */

package com.ashraful.enigma.java_vertx.service;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class SnsAsyncService {
    public static final String LOCAL_SNS_HOST = "http://localhost:4575";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SnsAsyncClient snsAsyncClient;

    public SnsAsyncService() {
      SdkAsyncHttpClient sdkAsyncHttpClient = NettyNioAsyncHttpClient.builder()
        .connectionTimeout(Duration.ofSeconds(10))
        .build();

      this.snsAsyncClient = SnsAsyncClient.builder()
        .httpClient(sdkAsyncHttpClient)
        .endpointOverride(URI.create(LOCAL_SNS_HOST))
        .region(Region.AP_SOUTHEAST_1)
        .build();

    }

    public CompletableFuture<String> createTopic(String topicName) {

        var createTopicRequest = CreateTopicRequest.builder().name(topicName).build();

        return snsAsyncClient.createTopic(createTopicRequest)
            .thenApply(CreateTopicResponse::topicArn);
    }

    public CompletableFuture<String> subscribeVendorUrl(String vendorSubscriptionUrl, String topicArn, String protocol) {
        var subscribeRequest = SubscribeRequest.builder()
            .topicArn(topicArn)
            .endpoint(vendorSubscriptionUrl)
            .protocol(protocol)
            .build();

        return snsAsyncClient.subscribe(subscribeRequest)
            .thenApply(subscribeResponse -> {
                logger.info("SNS SubscriptionResponse: " + subscribeResponse.toString());
                return subscribeResponse.subscriptionArn();
            });
    }

    public void sendNotification(String msg, String topicArn){
      var publishRequest = PublishRequest.builder().message(msg).topicArn(topicArn).build();

      snsAsyncClient.publish(publishRequest)
        .thenAccept(publishResponse -> logger.info("publishResponse:: "+publishResponse.toString()));
    }

}
