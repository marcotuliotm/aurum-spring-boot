package com.aurum.process.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import com.aurum.process.dto.CaseDTO;


@Configuration
public class ReceiverConfiguration {

	private static final String SUBSCRIPTION_NAME = "save-cases-subscription";

	@Bean
	public DirectChannel pubSubInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public PubSubInboundChannelAdapter messageChannelAdapter(@Qualifier("pubSubInputChannel") MessageChannel inputChannel, PubSubTemplate pubSubTemplate) {
		final PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(pubSubTemplate, SUBSCRIPTION_NAME);
		adapter.setOutputChannel(inputChannel);
		adapter.setAckMode(AckMode.MANUAL);
		adapter.setPayloadType(CaseDTO.class);
		return adapter;
	}
}
