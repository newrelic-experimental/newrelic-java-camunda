package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.EvaluateDecisionResponse;

@Weave
public class EvaluateDecisionCommandImpl {

	@Trace
	public ZeebeFuture<EvaluateDecisionResponse> send() {
		ZeebeFuture<EvaluateDecisionResponse> f = Weaver.callOriginal();
		return (ZeebeFuture<EvaluateDecisionResponse>) f.whenComplete(new NRCompletionWrapper<EvaluateDecisionResponse>("EvaluateDecisionCommandImpl", NewRelic.getAgent().getTransaction().startSegment("EvaluateDecision")));
	}
}
