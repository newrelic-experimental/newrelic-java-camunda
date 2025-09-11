package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.Topology;

@Weave
public class TopologyRequestImpl {

	@Trace
	public ZeebeFuture<Topology> send() {
		ZeebeFuture<Topology> f = Weaver.callOriginal();
		return (ZeebeFuture<Topology>) f.whenComplete(new NRCompletionWrapper<Topology>("TopologyRequestImpl", NewRelic.getAgent().getTransaction().startSegment("TopologyRequest")));
	}
}
