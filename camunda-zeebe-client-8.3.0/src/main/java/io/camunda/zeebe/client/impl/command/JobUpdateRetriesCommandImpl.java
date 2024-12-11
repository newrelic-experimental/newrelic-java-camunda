package io.camunda.zeebe.client.impl.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.NRCompletionWrapper;

import io.camunda.zeebe.client.api.ZeebeFuture;
import io.camunda.zeebe.client.api.response.UpdateRetriesJobResponse;

@Weave
public class JobUpdateRetriesCommandImpl {

	public ZeebeFuture<UpdateRetriesJobResponse> send() {
		ZeebeFuture<UpdateRetriesJobResponse> f = Weaver.callOriginal();
		return (ZeebeFuture<UpdateRetriesJobResponse>) f.whenComplete(new NRCompletionWrapper<UpdateRetriesJobResponse>("JobUpdateRetriesCommandImpl", NewRelic.getAgent().getTransaction().startSegment("JobUpdateRetries")));
	}
}
