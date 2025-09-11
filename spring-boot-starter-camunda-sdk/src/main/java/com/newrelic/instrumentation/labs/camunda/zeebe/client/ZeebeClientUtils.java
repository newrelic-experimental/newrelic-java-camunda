package com.newrelic.instrumentation.labs.camunda.zeebe.client;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import com.newrelic.agent.bridge.AgentBridge;
import com.newrelic.api.agent.NewRelic;

public class ZeebeClientUtils {
	
	private static final Set<Method> instrumented = new HashSet<Method>();
	
	public static boolean isInstrumented(Method method) {
		return instrumented.contains(method);
	}
	
	public static void instrument(Method method) {
		AgentBridge.instrumentation.instrument(method, "Custom/Camunda/JobWorker");
		NewRelic.getAgent().getLogger().log(Level.FINE, "Instrumented JobWorker Method: {0}", method);
		instrumented.add(method);
	}
}
