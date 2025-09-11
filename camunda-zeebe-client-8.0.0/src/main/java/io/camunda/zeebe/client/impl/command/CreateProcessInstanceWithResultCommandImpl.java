package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;

@Weave
public class CreateProcessInstanceWithResultCommandImpl {

	@Trace
	public ZeebeFuture<ProcessInstanceResult> send() {
		ZeebeFuture<ProcessInstanceResult> f = Weaver.callOriginal();
		
		return (ZeebeFuture<ProcessInstanceResult>) f.whenComplete(new NRCompletionWrapper<ProcessInstanceResult>("CreateProcessInstanceWithResultCommandImpl", NewRelic.getAgent().getTransaction().startSegment("CreateProcessInstanceWithResult")));
	}
}
