package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.DeleteResourceResponse;

@Weave
public class DeleteResourceCommandImpl {

	public ZeebeFuture<DeleteResourceResponse> send() { 
		ZeebeFuture<DeleteResourceResponse> f = Weaver.callOriginal();
		
		return (ZeebeFuture<DeleteResourceResponse>) f.whenComplete(new NRCompletionWrapper<DeleteResourceResponse>("DeleteResourceCommandImpl", NewRelic.getAgent().getTransaction().startSegment("DeleteResource")));
	}
}
