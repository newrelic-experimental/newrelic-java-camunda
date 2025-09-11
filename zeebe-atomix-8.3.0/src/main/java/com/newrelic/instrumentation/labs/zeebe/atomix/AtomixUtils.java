package com.newrelic.instrumentation.labs.zeebe.atomix;

import java.util.Map;

import io.atomix.cluster.messaging.impl.ProtocolMessage;
import io.atomix.cluster.messaging.impl.ProtocolRequest;

public class AtomixUtils {
	
	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(value != null && attributes != null && key != null && !key.isEmpty()) {
			attributes.put(key, value);
		}
	}
	
	public static void addProtocolMessage(Map<String,Object> attributes, ProtocolMessage message) {
		if(message != null) {
			addAttribute(attributes, "MessageType", message.type().toString());
			addAttribute(attributes, "MessageID", message.id());
		}
	}
	
	public static void addProtocolRequest(Map<String,Object> attributes, ProtocolRequest request) {
		if(request != null) {
			addAttribute(attributes, "Subject", request.subject());
			addAttribute(attributes, "Address", request.sender().toString());
			addProtocolMessage(attributes, request);
		}
	}

}
