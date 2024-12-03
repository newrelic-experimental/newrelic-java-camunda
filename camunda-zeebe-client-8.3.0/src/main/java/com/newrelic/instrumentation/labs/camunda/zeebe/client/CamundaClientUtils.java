package com.newrelic.instrumentation.labs.camunda.zeebe.client;

import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;

import io.camunda.zeebe.client.api.response.ActivatedJob;

public class CamundaClientUtils {

	public static Runnable getWrapper(Runnable runnable) {
		if(runnable instanceof CamundaRunnableWrapper) {
			return null;
		}
		
		Token token = NewRelic.getAgent().getTransaction().getToken();
		if(token == null || !token.isActive()) {
			return null;
		}
		
		return new CamundaRunnableWrapper(runnable, token);
	}
	
	public static void addActivatedJob(Map<String,Object> attributes, ActivatedJob job) {
		if(job != null) {
			addAttribute(attributes,"Job-BpmnProcessId", job.getBpmnProcessId());
			addAttribute(attributes,"Job-ElementId", job.getElementId());
			addAttribute(attributes,"Job-TenantId", job.getTenantId());
			addAttribute(attributes,"Job-Type", job.getType());
		}
	}
	public static void addAttribute(Map<String, Object> attributes, String key, Object value) {
		if(value != null && attributes != null && key != null && !key.isEmpty()) {
			attributes.put(key, value);
		}
	}
}
