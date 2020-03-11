package com.aurum.process.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.aurum.process.dto.CaseDTO;
import com.aurum.process.dto.CaseLot;
import com.aurum.process.pubsub.PubSubCaseGateway;
import com.aurum.process.service.CaseService;

@Service
public class CaseListener {
	@Autowired
	private CaseService service;

	@Autowired
	private PubSubCaseGateway pubSubCaseGateway;

	@ServiceActivator(inputChannel = "pubSubInputChannel")
	public void messageReceiver(CaseDTO payload,
								@Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
		service.create(payload);
		message.ack();
	}

	public void sendCases(CaseLot caseLot) {
		caseLot.getCases()
				.forEach(caseDTO -> pubSubCaseGateway.sendCaseToPubSub(caseDTO));
	}
}
