package io.camunda.zeebe.spring.client.annotation;

import java.util.logging.Level;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.WeaveIntoAllMethods;
import com.newrelic.api.agent.weaver.WeaveWithAnnotation;
import com.newrelic.api.agent.weaver.Weaver;

public class JobWorker_instrumentation {
	
	@WeaveWithAnnotation( annotationClasses = {"io.camunda.zeebe.spring.client.annotation.JobWorker"})
	@WeaveIntoAllMethods
	@Trace(dispatcher = true)
	private static void instrumentation() {
		Exception e = new Exception("In JobWorker method");
		NewRelic.getAgent().getLogger().log(Level.FINE, e, "Call to a method annotated with @JobWorker");
		JobWorker jobWorker = Weaver.getMethodAnnotation(JobWorker.class);
		StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        StackTraceElement first = traces[1];
        String methodName = first.getMethodName();
        String classname = first.getClassName();
		NewRelic.getAgent().getLogger().log(Level.FINE, "Using method name {0} and class {1}", methodName, classname);
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		NewRelic.getAgent().getLogger().log(Level.FINE, "TracedMethod is {0}",traced);
		Transaction transaction = NewRelic.getAgent().getTransaction();
		NewRelic.getAgent().getLogger().log(Level.FINE, "Transaction is {0}",transaction);
		
		transaction.setTransactionName(TransactionNamePriority.CUSTOM_LOW, true, "Camunda", "Camunda","JobWorker",classname,methodName);
        traced.setMetricName("Custom","Camunda","JobWorker",classname,methodName);
		
		if(jobWorker != null) {
			String jobName = jobWorker.name();
    		NewRelic.getAgent().getLogger().log(Level.FINE, "JobName is {0}", jobName);
			String jobtype = jobWorker.type();
    		NewRelic.getAgent().getLogger().log(Level.FINE, "JobType is {0}", jobtype);
            if(jobName != null && !jobName.isEmpty()) {
            	traced.addCustomAttribute("JobWorker-Name", jobName);
            }
            if(jobtype != null && !jobtype.isEmpty()) {
            	traced.addCustomAttribute("JobWorker-Type", jobtype);
            }
		}
	}

}
