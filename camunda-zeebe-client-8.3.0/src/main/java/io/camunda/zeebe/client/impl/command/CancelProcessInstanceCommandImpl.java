package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.CancelProcessInstanceResponse;
import io.camunda.zeebe.client.impl.ZeebeStreamingClientFutureImpl;

@Weave
public class CancelProcessInstanceCommandImpl {

	@Trace
	public ZeebeFuture<CancelProcessInstanceResponse> send() {
		ZeebeFuture<CancelProcessInstanceResponse> f = Weaver.callOriginal();
		if(f instanceof ZeebeStreamingClientFutureImpl) {
			Segment segment = NewRelic.getAgent().getTransaction().startSegment("CancelProcessInstance");
			((ZeebeStreamingClientFutureImpl<?,?>) f).segment = segment;
		}

		return f;
		
	}
}
