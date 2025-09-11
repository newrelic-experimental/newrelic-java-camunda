package io.camunda.zeebe.spring.client.jobhandling;

import java.util.HashMap;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.CamundaUtils;

import io.camunda.zeebe.client.api.response.ActivatedJob;

@Weave
public abstract class CommandWrapper {

	private ActivatedJob job = Weaver.callOriginal();

	@Trace(dispatcher = true)
	public void executeAsync() {
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		CamundaUtils.addActivatedJob(attributes, job);
		Weaver.callOriginal();
	}
}
