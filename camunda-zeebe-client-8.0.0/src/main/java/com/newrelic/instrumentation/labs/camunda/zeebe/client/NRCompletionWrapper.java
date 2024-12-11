package com.newrelic.instrumentation.labs.camunda.zeebe.client;

import java.util.function.BiConsumer;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;


public class NRCompletionWrapper<T> implements BiConsumer<T, Throwable> {
	
	private String name = null;
	private Segment segment = null;
	
	public NRCompletionWrapper(String n, Segment s) {
		name = n;
		segment = s;
	}
	

	@Override
	public void accept(T t, Throwable u) {
		if(u != null) {
			NewRelic.noticeError(u);
		}
		if(segment != null) {
			segment.addCustomAttribute("Commamd", name);
			segment.end();
			segment = null;
		}
	}

}
