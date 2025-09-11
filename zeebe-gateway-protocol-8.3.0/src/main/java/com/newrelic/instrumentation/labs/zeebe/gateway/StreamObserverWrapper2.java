package com.newrelic.instrumentation.labs.zeebe.gateway;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;

import io.grpc.stub.StreamObserver;

public class StreamObserverWrapper2<V> implements StreamObserver<V> {

	private StreamObserver<V> delegate = null;
	private static boolean isTransformed = false;

	public StreamObserverWrapper2(StreamObserver<V> d){
		delegate = d;
		if(!isTransformed) {
			AgentBridge.instrumentation.retransformUninstrumentedClass(getClass());
			isTransformed = true;
		}
	}

	@Override
	@Trace(dispatcher = true)
	public void onNext(V value) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","StreamObserver","onNext",value.getClass().getSimpleName());
		if(delegate != null) {
			delegate.onNext(value);
		}
	}

	@Override
	public void onError(Throwable t) {
		NewRelic.noticeError(t);
		if(delegate != null) {
			delegate.onError(t);
		}
	}

	@Override
	public void onCompleted() {
		if(delegate != null) {
			delegate.onCompleted();
		}
	}

}
