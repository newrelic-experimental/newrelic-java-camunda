package com.newrelic.instrumentation.labs.zeebe.gateway;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;

import io.grpc.stub.StreamObserver;

public class StreamObserverWrapper<V> implements StreamObserver<V> {
	
	private StreamObserver<V> delegate = null;
	private static boolean isTransformed = false;
	private Token token = null;
	
	public StreamObserverWrapper(StreamObserver<V> d, Token t) {
		delegate = d;
		token = t;
		if(!isTransformed) {
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
			isTransformed = true;
		}
	}

	@Override
	@Trace(async = true)
	public void onNext(V value) {
		if(token != null) {
			token.link();
		}
		if(delegate != null) {
			delegate.onNext(value);
		}
	}

	@Override
	@Trace(async = true)
	public void onError(Throwable t) {
		NewRelic.noticeError(t);
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(delegate != null) {
			delegate.onError(t);
		}
	}

	@Override
	@Trace(async = true)
	public void onCompleted() {
		if(token != null) {
			token.linkAndExpire();
			token = null;
		}
		if(delegate != null) {
			delegate.onCompleted();
		}
	}

}
