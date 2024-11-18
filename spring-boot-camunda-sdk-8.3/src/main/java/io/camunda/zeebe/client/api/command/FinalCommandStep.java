package io.camunda.zeebe.client.api.command;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.camunda.zeebe.client.api.ZeebeFuture;

@Weave(type = MatchType.Interface)
public abstract class FinalCommandStep<T> {

	@Trace
	public ZeebeFuture<T> send() {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","FinalCommandStep",getClass().getSimpleName(),"send");
		return Weaver.callOriginal();
	}
}
