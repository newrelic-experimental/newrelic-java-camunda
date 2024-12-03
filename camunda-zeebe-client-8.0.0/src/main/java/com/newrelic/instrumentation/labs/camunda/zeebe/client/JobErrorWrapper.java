package com.newrelic.instrumentation.labs.camunda.zeebe.client;

import java.util.function.Consumer;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

public class JobErrorWrapper implements Consumer<Throwable> {
	
	private Consumer<Throwable> delegate = null;
	private static boolean isTransformed = false;
	private Token token = null;
	
	public JobErrorWrapper(Consumer<Throwable> d) {
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
	public void accept(Throwable t) {
		NewRelic.noticeError(t);
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(delegate != null) {
			delegate.accept(t);
		}
	}

}
