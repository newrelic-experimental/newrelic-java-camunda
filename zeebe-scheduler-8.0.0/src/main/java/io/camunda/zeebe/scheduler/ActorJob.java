package io.camunda.zeebe.scheduler;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class ActorJob {

	public abstract ActorTask getTask();
	
	@Trace(dispatcher = true)
	void execute(ActorThread runner) {
		ActorTask task = getTask();
		if(task != null) {
			String taskName = task.getName();
			if(taskName != null) {
				NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","ActorJob","execute",taskName);
			}
		}
		Weaver.callOriginal();
	}
}
