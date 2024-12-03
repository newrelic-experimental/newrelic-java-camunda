package io.camunda.zeebe.client.impl.worker;

import java.util.HashMap;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.CamundaClientUtils;

import io.camunda.zeebe.client.api.response.ActivatedJob;

@Weave
public abstract class JobRunnableFactoryImpl {

	@Trace(dispatcher = true)
	private void executeJob(ActivatedJob job, Runnable doneCallback) {
		Runnable wrapper = CamundaClientUtils.getWrapper(doneCallback);
		if(wrapper != null) {
			doneCallback = wrapper;
		}
		HashMap<String, Object> attributes = new HashMap<String, Object>();
		CamundaClientUtils.addActivatedJob(attributes, job);
		NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		Weaver.callOriginal();
	}
}
