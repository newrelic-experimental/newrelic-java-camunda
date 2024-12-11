package com.newrelic.instrumentation.labs.camunda.zeebe.client;

import java.util.function.BiConsumer;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Segment;

import io.camunda.zeebe.client.api.response.ActivateJobsResponse;


public class NRActivatedCompletionWrapper implements BiConsumer<ActivateJobsResponse, Throwable> {
	
	private String name = null;
	private Segment segment = null;
	
	public NRActivatedCompletionWrapper(String n, Segment s) {
		name = n;
		segment = s;
	}
	

	@Override
	public void accept(ActivateJobsResponse t, Throwable u) {
		if(u != null) {
			NewRelic.noticeError(u);
		}
		if(segment != null) {
			if(t != null) {
				if(t.getJobs().isEmpty()) {
					segment.ignore();
					segment = null;
				} else {
					segment.addCustomAttribute("Commamd", name);
					
					segment.end();
					segment = null;
				}
			} else {
				segment.addCustomAttribute("Commamd", name);
				
				segment.end();
				segment = null;
			}
		}
	}

}
