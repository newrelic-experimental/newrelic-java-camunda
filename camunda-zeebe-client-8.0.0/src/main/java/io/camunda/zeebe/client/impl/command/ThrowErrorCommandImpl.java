package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;

@Weave
public class ThrowErrorCommandImpl {

	@Trace
	 public ZeebeFuture<Void> send() {
		 ZeebeFuture<Void> f = Weaver.callOriginal();
		 return (ZeebeFuture<Void>) f.whenComplete(new NRCompletionWrapper<Void>("ThrowErrorCommandImpl", NewRelic.getAgent().getTransaction().startSegment("ThrowError")));
	 }
}
