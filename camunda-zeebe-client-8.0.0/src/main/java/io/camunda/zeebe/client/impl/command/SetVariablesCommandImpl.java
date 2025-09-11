package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.SetVariablesResponse;

@Weave
public class SetVariablesCommandImpl {

	@Trace
	public ZeebeFuture<SetVariablesResponse> send() {
		ZeebeFuture<SetVariablesResponse> f = Weaver.callOriginal();
		return (ZeebeFuture<SetVariablesResponse>) f.whenComplete(new NRCompletionWrapper<SetVariablesResponse>("SetVariablesCommandImpl", NewRelic.getAgent().getTransaction().startSegment("SetVariablesCommand")));
	}
}
