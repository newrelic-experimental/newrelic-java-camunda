package io.camunda.zeebe.client.api.worker;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.CamundaClientUtils;

import io.camunda.zeebe.client.api.response.ActivatedJob;

@Weave(type = MatchType.Interface)
public class JobHandler {

	@Trace(dispatcher = true)
	public void handle(JobClient client, ActivatedJob job) {
		TracedMethod traced = NewRelic.getAgent().getTracedMethod();
		traced.setMetricName("Custom","Camunda","ZeebeClient","JobHandler",getClass().getSimpleName(),"handle");
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		CamundaClientUtils.addActivatedJob(attributes, job);
		traced.addCustomAttributes(attributes);
		Weaver.callOriginal();
	}
}
