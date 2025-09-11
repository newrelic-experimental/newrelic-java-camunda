package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.DeploymentEvent;
import io.camunda.zeebe.client.impl.ZeebeStreamingClientFutureImpl;

@Weave
public class DeployResourceCommandImpl {

	@Trace
	public ZeebeFuture<DeploymentEvent> send() {
		ZeebeFuture<DeploymentEvent> f = Weaver.callOriginal();
		if(f instanceof ZeebeStreamingClientFutureImpl) {
			Segment segment = NewRelic.getAgent().getTransaction().startSegment("DeployResource");
			((ZeebeStreamingClientFutureImpl<?,?>) f).segment = segment;
		}

		return f;
	}
}
