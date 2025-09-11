package com.newrelic.instrumentation.labs.camunda.zeebe.client;

import java.util.Map;

import io.camunda.zeebe.client.api.response.ActivatedJob;

public class CamundaUtils {

	
	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(value != null && attributes != null && key != null && !key.isEmpty()) {
			attributes.put(key, value);
		}
	}
	
	public static void addActivatedJob(Map<String, Object> attributes, ActivatedJob job) {
		if(job != null) {
			addAttribute(attributes, "BPMNProcessId", job.getBpmnProcessId());
			addAttribute(attributes, "ElementId", job.getElementId());
			addAttribute(attributes, "Type", job.getType());
			addAttribute(attributes, "TenantId", job.getTenantId());
			addAttribute(attributes, "Worker", job.getWorker());
		}
	}
}
