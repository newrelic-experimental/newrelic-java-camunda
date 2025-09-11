package io.atomix.cluster.messaging.impl;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.zeebe.atomix.AtomixUtils;

@Weave(type = MatchType.Interface)
abstract class ClientConnection {

	@Trace(dispatcher = true)
	public CompletableFuture<Void> sendAsync(ProtocolRequest message) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","Camunda","Zeebe","Atomix","ClientConnection",getClass().getSimpleName(),"sendAsync");
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		AtomixUtils.addProtocolRequest(attributes, message);
		traced.addCustomAttributes(attributes);
		CompletableFuture<Void> result = Weaver.callOriginal();
		return result;
	}
	
	@Trace(dispatcher = true)
	public CompletableFuture<byte[]> sendAndReceive(ProtocolRequest message) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","Camunda","Zeebe","Atomix","ClientConnection",getClass().getSimpleName(),"sendAndReceive");
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		AtomixUtils.addProtocolRequest(attributes, message);
		traced.addCustomAttributes(attributes);
		return Weaver.callOriginal();
	}
}
