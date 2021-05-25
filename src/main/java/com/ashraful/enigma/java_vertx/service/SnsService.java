/*
 * @author: Md Ashraful Alam
 * @On: 5/25/21, 2:59 PM
 */

package com.ashraful.enigma.java_vertx.service;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

public class SnsService {
    public static final String LOCAL_SNS_HOST = "http://localhost:4575";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SnsAsyncClient snsAsyncClient;

    public SnsService() {
      this.snsAsyncClient = SnsAsyncClient.builder().endpointOverride(URI.create(LOCAL_SNS_HOST)).region(Region.US_EAST_1).build();
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

}
