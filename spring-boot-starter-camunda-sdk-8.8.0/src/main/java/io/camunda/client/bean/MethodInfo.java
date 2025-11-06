package io.camunda.client.bean;

import java.lang.reflect.Method;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.ZeebeClientUtils;

@Weave(type = MatchType.Interface)
public abstract class MethodInfo {
	
	public abstract BeanInfo getBeanInfo();
	public abstract Method getMethod();
	public abstract String getMethodName();
	
	@Trace
	public Object invoke(Object... args) {
		Method method = getMethod();
		if(method != null && !ZeebeClientUtils.isInstrumented(method)) {
			ZeebeClientUtils.instrument(method);
		}
		String bean = getBeanInfo().getBeanName();
		String mName = getMethodName();
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","MethodInfo","invoke",bean != null ? bean : "UndefinedBean",mName != null ? mName : "UndefinedMethod");
		NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "Camunda-Worker", "Camunda","JobWorker",bean,mName);
		return Weaver.callOriginal();
	}

}
