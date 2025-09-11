package io.camunda.zeebe.scheduler;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
public abstract class ActorTask {

	public abstract String getName();
	
	@Trace
	public boolean execute(ActorThread runner) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","ActorTask",getName(),"execute");
		return Weaver.callOriginal();
	}
	
	public void fail(Throwable error) {
		NewRelic.noticeError(error);
		Weaver.callOriginal();
	}
	
	@Trace
	public void resubmit() {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","ActorTask",getName(),"resubmit");
		Weaver.callOriginal();
	}
	
	@Trace
	public void submit(ActorJob job) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","ActorTask",getName(),"submit");
		Weaver.callOriginal();
	}
}
