package io.camunda.zeebe.client.impl.worker;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.JobConsumerWrapper;
import com.newrelic.instrumentation.labs.camunda.zeebe.client.JobErrorWrapper;

import io.camunda.zeebe.client.api.response.ActivatedJob;

@Weave
public class JobPoller {


	public void poll(int maxJobsToActivate, Consumer<ActivatedJob> jobConsumer, IntConsumer doneCallback, Consumer<Throwable> errorCallback, BooleanSupplier openSupplier) { 

		if(!(jobConsumer instanceof JobConsumerWrapper)) {
			JobConsumerWrapper wrapper = new JobConsumerWrapper(jobConsumer);
			jobConsumer = wrapper;
		}
		if(!(errorCallback instanceof JobErrorWrapper)) {
			JobErrorWrapper errorWrapper = new JobErrorWrapper(errorCallback);
			errorCallback = errorWrapper;
		}
		Weaver.callOriginal();
	}
}
