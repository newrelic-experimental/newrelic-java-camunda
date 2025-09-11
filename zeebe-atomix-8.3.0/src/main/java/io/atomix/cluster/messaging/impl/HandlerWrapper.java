package io.atomix.cluster.messaging.impl;

import java.util.function.BiConsumer;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

public class HandlerWrapper implements BiConsumer<ProtocolRequest, ServerConnection> {
	
	private BiConsumer<ProtocolRequest, ServerConnection>  delegate = null;
	private Token token = null;
	private String type = null;
	private static boolean isTransformed = false;
	
	public HandlerWrapper(BiConsumer<ProtocolRequest, ServerConnection> d, Token t, String t1) {
		delegate = d;
		token = t;
		type = t1;
		if(!isTransformed) {
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
			isTransformed = true;
		}
	}

	@Override
	@Trace(async = true)
	public void accept(ProtocolRequest t, ServerConnection u) {
		if(type != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","Atomix","Handler",type);
		}
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(delegate != null) {
			delegate.accept(t, u);
		}
	}

}
