package com.aurum.process.config;

import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Configuration
public class SenderConfiguration {

	private static final String TOPIC_NAME = "save-cases";

	@Bean
	public DirectChannel pubSubOutputChannel() {
		return new DirectChannel();
	}

	@Bean
	@ServiceActivator(inputChannel = "pubSubOutputChannel")
	public MessageHandler messageSender(PubSubTemplate pubSubTemplate) {
		final PubSubMessageHandler adapter = new PubSubMessageHandler(pubSubTemplate, TOPIC_NAME);
		adapter.setPublishCallback(new ListenableFutureCallback<>() {
			@Override
			public void onFailure(Throwable ex) {

			}

			@Override
			public void onSuccess(String result) {

			}
		});

		return adapter;
	}
}
