package io.camunda.zeebe.scheduler;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.camunda.zeebe.scheduler.future.ActorFuture;

@Weave
public abstract class ActorExecutor {

	@Trace
	private ActorFuture<Void> submitTask(final ActorTask task, final ActorThreadGroup threadGroup) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","ActorExecutor","submitTask", task.getName());
		return Weaver.callOriginal();
	}
}
