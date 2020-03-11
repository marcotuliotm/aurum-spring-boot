package com.aurum.process.pubsub;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Component;

import com.aurum.process.dto.CaseDTO;

@Component
@MessagingGateway(defaultRequestChannel = "pubSubOutputChannel")
public interface PubSubCaseGateway {
	void sendCaseToPubSub(CaseDTO caseDTO);
}
