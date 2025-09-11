package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.impl.ZeebeStreamingClientFutureImpl;

@Weave
public class ThrowErrorCommandImpl {

	@Trace
	public ZeebeFuture<Void> send() {
		ZeebeFuture<Void> f = Weaver.callOriginal();
		if(f instanceof ZeebeStreamingClientFutureImpl) {
			Segment segment = NewRelic.getAgent().getTransaction().startSegment("ThrowError");
			((ZeebeStreamingClientFutureImpl<?,?>) f).segment = segment;
		}

		return f;
	}
}
