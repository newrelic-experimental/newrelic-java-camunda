package com.newrelic.instrumentation.labs.camunda.zeebe.client;

import java.util.Map;

public class CamundaUtils {

	
	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(value != null && attributes != null && key != null && !key.isEmpty()) {
			attributes.put(key, value);
		}
	}
}
