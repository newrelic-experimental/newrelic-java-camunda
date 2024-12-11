package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.DeploymentEvent;

@Weave
public class DeployProcessCommandImpl {

	@Trace
	public ZeebeFuture<DeploymentEvent> send() {
		ZeebeFuture<DeploymentEvent>  f = Weaver.callOriginal();
		
		return (ZeebeFuture<DeploymentEvent>) f.whenComplete(new NRCompletionWrapper<DeploymentEvent>("DeployProcessCommandImpl", NewRelic.getAgent().getTransaction().startSegment("DeployProcess")));
	}
}
