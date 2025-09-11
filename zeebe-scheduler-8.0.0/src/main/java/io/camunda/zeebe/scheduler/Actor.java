package io.camunda.zeebe.scheduler;

import java.util.function.BiConsumer;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.scheduler.CamundaBiConsumerWrapper;

import io.camunda.zeebe.scheduler.future.ActorFuture;

@Weave(type = MatchType.BaseClass)
public abstract class Actor {
	
	public abstract String getName();

	@Trace(dispatcher = true)
	public void run(final Runnable action) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","Scheduler","Actor",getName(),"run");
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public <T> void runOnCompletion(ActorFuture<T> future, BiConsumer<T, Throwable> callback) {
		if(!(callback instanceof CamundaBiConsumerWrapper)) {
			Token token = NewRelic.getAgent().getTransaction().getToken();
			if(token != null && token.isActive()) {
				callback = new CamundaBiConsumerWrapper<T>(callback, token);
			} else if(token != null) {
				token.expire();
				token = null;
			}
		}
			
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","Scheduler","Actor",getName(),"runOnCompletion");
		Weaver.callOriginal();
	}
}
