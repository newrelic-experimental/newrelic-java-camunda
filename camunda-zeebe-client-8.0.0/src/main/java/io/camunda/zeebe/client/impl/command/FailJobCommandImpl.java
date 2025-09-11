package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.FailJobResponse;

@Weave
public class FailJobCommandImpl {

	@Trace
	public ZeebeFuture<FailJobResponse> send() {
		ZeebeFuture<FailJobResponse> f = Weaver.callOriginal();
		
		return (ZeebeFuture<FailJobResponse>) f.whenComplete(new NRCompletionWrapper<FailJobResponse>("FailJobCommandImpl", NewRelic.getAgent().getTransaction().startSegment("FailJob")));
	}
	
}
