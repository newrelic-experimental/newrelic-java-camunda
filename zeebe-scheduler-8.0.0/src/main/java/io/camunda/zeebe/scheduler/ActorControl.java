package io.camunda.zeebe.scheduler;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.camunda.zeebe.scheduler.future.ActorFuture;

@Weave
public abstract class ActorControl {

	@Trace(dispatcher = true)
	public <T> ActorFuture<T> call(Callable<T> callable) {
		
		return Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public void run(Runnable action) {
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public <T> void runOnCompletion(ActorFuture<T> future, BiConsumer<T, Throwable> callback) {
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public <T> void runOnCompletion(Collection<ActorFuture<T>> futures, Consumer<Throwable> callback) {
		Weaver.callOriginal();
	}
	
	@Trace(dispatcher = true)
	public void submit(Runnable action) {
		Weaver.callOriginal();
	}
}
