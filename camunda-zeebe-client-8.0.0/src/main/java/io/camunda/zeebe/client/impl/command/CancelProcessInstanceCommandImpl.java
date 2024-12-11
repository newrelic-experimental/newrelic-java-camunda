package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.CancelProcessInstanceResponse;

@Weave
public class CancelProcessInstanceCommandImpl {

	@Trace
	public ZeebeFuture<CancelProcessInstanceResponse> send() {
		ZeebeFuture<CancelProcessInstanceResponse> f = Weaver.callOriginal();
		
		return (ZeebeFuture<CancelProcessInstanceResponse>) f.whenComplete(new NRCompletionWrapper<CancelProcessInstanceResponse>("CancelProcessInstanceCommandImpl", NewRelic.getAgent().getTransaction().startSegment("CancelProcessInstance")));
	}
}
