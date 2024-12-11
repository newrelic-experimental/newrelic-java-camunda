package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.ModifyProcessInstanceResponse;

@Weave
public class ModifyProcessInstanceCommandImpl {

	public ZeebeFuture<ModifyProcessInstanceResponse> send() {
		ZeebeFuture<ModifyProcessInstanceResponse> f = Weaver.callOriginal();
		
		return (ZeebeFuture<ModifyProcessInstanceResponse>) f.whenComplete(new NRCompletionWrapper<ModifyProcessInstanceResponse>("ModifyProcessInstanceCommandImpl", NewRelic.getAgent().getTransaction().startSegment("ModifyProcessInstance")));
	}
}
