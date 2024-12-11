package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;

@Weave
public class CreateProcessInstanceCommandImpl {

	@Trace
	public ZeebeFuture<ProcessInstanceEvent> send() {
		ZeebeFuture<ProcessInstanceEvent> f = Weaver.callOriginal();
		
		return (ZeebeFuture<ProcessInstanceEvent>) f.whenComplete(new NRCompletionWrapper<ProcessInstanceEvent>("CreateProcessInstanceCommandImpl", NewRelic.getAgent().getTransaction().startSegment("CreateProcessInstance")));
	}
}
