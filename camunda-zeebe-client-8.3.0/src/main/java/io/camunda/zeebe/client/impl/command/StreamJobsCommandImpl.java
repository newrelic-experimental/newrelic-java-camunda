package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.StreamJobsResponse;

@Weave
public class StreamJobsCommandImpl {

	@Trace
	public ZeebeFuture<StreamJobsResponse> send() {
		ZeebeFuture<StreamJobsResponse> f = Weaver.callOriginal();
		return (ZeebeFuture<StreamJobsResponse>) f.whenComplete(new NRCompletionWrapper<StreamJobsResponse>("StreamJobsCommandImpl", NewRelic.getAgent().getTransaction().startSegment("SteamJobs")));
	}
}
