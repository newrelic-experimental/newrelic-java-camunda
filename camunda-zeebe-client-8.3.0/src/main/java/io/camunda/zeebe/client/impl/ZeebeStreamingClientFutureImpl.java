package io.camunda.zeebe.client.impl;

import java.util.function.Consumer;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.NewField;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.BaseClass)
public class ZeebeStreamingClientFutureImpl<ClientResponse, BrokerResponse> {

	@NewField
	public Segment segment = null;
	
	public ZeebeStreamingClientFutureImpl(ClientResponse response, Consumer<BrokerResponse> collector) {
		
	}
	
	public void onCompleted() {
		if(segment != null) {
			segment.end();
			segment = null;
		}
		Weaver.callOriginal();
	}
	
	public void onError(Throwable throwable) {
		NewRelic.noticeError(throwable);
		if(segment != null) {
			segment.end();
			segment = null;
		}
		Weaver.callOriginal();
	}
}
