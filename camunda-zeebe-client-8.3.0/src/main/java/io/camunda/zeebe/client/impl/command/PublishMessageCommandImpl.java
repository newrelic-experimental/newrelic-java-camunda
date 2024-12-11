package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.PublishMessageResponse;

@Weave
public class PublishMessageCommandImpl {

	 public ZeebeFuture<PublishMessageResponse> send() {
		 ZeebeFuture<PublishMessageResponse>  f = Weaver.callOriginal();
		 return (ZeebeFuture<PublishMessageResponse>) f.whenComplete(new NRCompletionWrapper<PublishMessageResponse>("PublishMessageCommandImpl", NewRelic.getAgent().getTransaction().startSegment("PublishMessage")));
	 }
}
