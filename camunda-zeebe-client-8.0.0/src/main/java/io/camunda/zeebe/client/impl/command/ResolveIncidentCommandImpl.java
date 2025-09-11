package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.ResolveIncidentResponse;

@Weave
public class ResolveIncidentCommandImpl {

	public ZeebeFuture<ResolveIncidentResponse> send() {
		ZeebeFuture<ResolveIncidentResponse> f = Weaver.callOriginal();
		return (ZeebeFuture<ResolveIncidentResponse>) f.whenComplete(new NRCompletionWrapper<ResolveIncidentResponse>("ResolveIncidentCommandImpl", NewRelic.getAgent().getTransaction().startSegment("ResolveIncident")));
	}
}
