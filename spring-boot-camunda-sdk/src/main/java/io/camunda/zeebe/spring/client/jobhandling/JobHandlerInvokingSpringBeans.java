package io.camunda.zeebe.spring.client.jobhandling;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;

@Weave
public abstract class JobHandlerInvokingSpringBeans {

	@Trace(dispatcher = true)
	public void handle(JobClient jobClient, ActivatedJob job) {
		Weaver.callOriginal();
	}
}
