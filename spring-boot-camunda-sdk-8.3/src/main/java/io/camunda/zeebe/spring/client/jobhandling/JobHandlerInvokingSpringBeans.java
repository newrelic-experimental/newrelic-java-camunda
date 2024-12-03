package io.camunda.zeebe.spring.client.jobhandling;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.CamundaUtils;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.value.ZeebeWorkerValue;
import io.camunda.zeebe.spring.client.bean.MethodInfo;

@Weave
public abstract class JobHandlerInvokingSpringBeans {
	
	private final ZeebeWorkerValue workerValue = Weaver.callOriginal();

	@Trace(dispatcher = true)
	public void handle(JobClient jobClient, ActivatedJob job) {
		if(workerValue != null) {
			String name = workerValue.getName();
			String type = workerValue.getType();
			boolean enabled = workerValue.getEnabled();
			MethodInfo info = workerValue.getMethodInfo();
			String beanName = info.getBeanName();
			String methodName = info.getMethodName();
			HashMap<String, Object> attributes = new HashMap<String, Object>();
			CamundaUtils.addAttribute(attributes, "JobWorkerName", name);
			CamundaUtils.addAttribute(attributes, "JobWorkerType", type);
			CamundaUtils.addAttribute(attributes, "Worker-Enabled", enabled);
			CamundaUtils.addAttribute(attributes, "BeanName", beanName);
			CamundaUtils.addAttribute(attributes, "Method", methodName);
			
			TracedMethod traced = NewRelic.getAgent().getTracedMethod();
			traced.addCustomAttributes(attributes);
			traced.setMetricName("Custom","Camunda","JobHandlerInvokingSpringBeans","handle");
			
			Transaction transaction = NewRelic.getAgent().getTransaction();
			if(beanName != null && methodName != null) {
				String beanComponent = "Bean(" + beanName + ")";
				String methodComponent = "Method(" + methodName + ")";
				transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "Camunda-JobWorker", "Camunda","Jobworker",beanComponent,methodComponent);
			} else if(name != null || type != null) {
				String nameComponent = "WorkerName(" + (name !=null ? name : "") + ")";
				String typeComponent = "WorkerType(" + (type !=null ? type : "") + ")";
				transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_HIGH, false, "Camunda-JobWorker", "Camunda","Jobworker",nameComponent,typeComponent);
			}
			
		}
		Weaver.callOriginal();
	}
}
