package io.camunda.zeebe.scheduler;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public class ActorThread {
	
	ActorTask currentTask = Weaver.callOriginal();
	
	@Trace(dispatcher = true)
	private void executeCurrentTask() {
		if(currentTask != null) {
			String taskName = currentTask.getName();
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, false, "ActorThread", "Actor","ExecuteTask",taskName);
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","ActorThread","executeTask",taskName);
		}
		Weaver.callOriginal();
	}
}
