package com.newrelic.instrumentation.labs.camunda.zeebe.client;

import java.util.function.Consumer;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.camunda.zeebe.client.api.response.ActivatedJob;

public class JobConsumerWrapper implements Consumer<ActivatedJob> {
	
	private Consumer<ActivatedJob> delegate = null;
	private static boolean isTransformed = false;
	private Token token = null;
	
	public JobConsumerWrapper(Consumer<ActivatedJob> d) {
		delegate = d;
		Token t = NewRelic.getAgent().getTransaction().getToken();
		if(t != null && t.isActive()) {
			token = t;
		} else if(t != null) {
			t.expire();
			t = null;
		}
		if(!isTransformed) {
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
			isTransformed = true;
		}
	}

	@Override
	@Trace(async = true)
	public void accept(ActivatedJob t) {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(delegate != null) {
			delegate.accept(t);
		}
	}

}
