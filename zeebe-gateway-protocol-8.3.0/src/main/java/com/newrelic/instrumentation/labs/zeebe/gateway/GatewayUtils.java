package com.newrelic.instrumentation.labs.zeebe.gateway;

import java.util.HashMap;
import java.util.Map;

import com.newrelic.api.agent.TracedMethod;

import io.camunda.zeebe.gateway.protocol.GatewayOuterClass;

public class GatewayUtils {

	public static void processGatewayResponse(Object object, TracedMethod traced) {
		if(object instanceof GatewayOuterClass.ActivateJobsResponse) {
			GatewayOuterClass.ActivateJobsResponse response = (GatewayOuterClass.ActivateJobsResponse)object;
			HashMap<String, Object> attributes = new HashMap<String, Object>();
			addAttribute(attributes, "ActivateJobsResponse-JobCount", response.getJobsCount());
			addAttribute(attributes, "ActivateJobsResponse-Descriptor", response.getDescriptorForType().getFullName());
		}
	}

	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(value != null && attributes != null && key != null && !key.isEmpty()) {
			attributes.put(key, value);
		}
	}
}
