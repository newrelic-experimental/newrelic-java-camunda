package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRActivatedCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.ActivateJobsResponse;

@Weave
public class ActivateJobsCommandImpl {

	@Trace
	public ZeebeFuture<ActivateJobsResponse> send() {
		ZeebeFuture<ActivateJobsResponse> f = Weaver.callOriginal();
		
		return (ZeebeFuture<ActivateJobsResponse>) f.whenComplete(new NRActivatedCompletionWrapper("ActivateJobs", NewRelic.getAgent().getTransaction().startSegment("ActivateJobs")));
	}
}
