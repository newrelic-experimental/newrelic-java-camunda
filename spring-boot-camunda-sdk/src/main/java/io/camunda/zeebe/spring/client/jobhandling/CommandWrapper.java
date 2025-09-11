package io.camunda.zeebe.spring.client.jobhandling;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class CommandWrapper {

	@Trace
	public void executeAsync() {
		Weaver.callOriginal();
	}
	
	@Trace
	public void executeAsyncWithMetrics(String metricName, String action, String type) {
		Weaver.callOriginal();
	}
}
