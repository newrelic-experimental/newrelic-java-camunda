package io.camunda.zeebe.gateway.protocol;

import java.util.Iterator;

import com.google.common.util.concurrent.ListenableFuture;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TracedMethod;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.grpc.stub.StreamObserver;

@Weave
public class GatewayGrpc {


	@Weave
	public static class GatewayBlockingStub {

		@Trace(dispatcher = true)
		public Iterator<GatewayOuterClass.ActivateJobsResponse> activateJobs(GatewayOuterClass.ActivateJobsRequest request) {
			TracedMethod traced = NewRelic.getAgent().getTracedMethod();
			String worker = request.getWorker();
			if(worker != null) {
				traced.addCustomAttribute("Worker", worker);
			}
			String type = request.getType();
			if(type != null) {
				traced.addCustomAttribute("Type", type);
			}

			return Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public GatewayOuterClass.CancelProcessInstanceResponse cancelProcessInstance(GatewayOuterClass.CancelProcessInstanceRequest request) {
			return Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public GatewayOuterClass.CreateProcessInstanceResponse createProcessInstance(GatewayOuterClass.CreateProcessInstanceRequest request) {
			TracedMethod traced = NewRelic.getAgent().getTracedMethod();
			traced.addCustomAttribute("BpmnProcessId",request.getBpmnProcessId());
			return Weaver.callOriginal();			
		}

		@Trace(dispatcher = true)
		public GatewayOuterClass.CreateProcessInstanceWithResultResponse createProcessInstanceWithResult(GatewayOuterClass.CreateProcessInstanceWithResultRequest request) {
			return Weaver.callOriginal();			
		}

		@Trace(dispatcher = true)
		public GatewayOuterClass.FailJobResponse failJob(GatewayOuterClass.FailJobRequest request) {
			TracedMethod traced = NewRelic.getAgent().getTracedMethod();
			traced.addCustomAttribute("ErrorMessage", request.getErrorMessage());
			traced.addCustomAttribute("Retries",request.getRetries());
			return Weaver.callOriginal();			
		}

		@Trace(dispatcher = true)
		public GatewayOuterClass.PublishMessageResponse publishMessage(GatewayOuterClass.PublishMessageRequest request) {
			TracedMethod traced = NewRelic.getAgent().getTracedMethod();
			traced.addCustomAttribute("CorrelationKey", request.getCorrelationKey());
			traced.addCustomAttribute("Name",request.getName());
			traced.addCustomAttribute("MessageId",request.getMessageId());
			return Weaver.callOriginal();			
		}

		@Trace(dispatcher = true)
		public GatewayOuterClass.ThrowErrorResponse throwError(GatewayOuterClass.ThrowErrorRequest request) {
			TracedMethod traced = NewRelic.getAgent().getTracedMethod();
			traced.addCustomAttribute("ErrorCode", request.getErrorCode());
			traced.addCustomAttribute("Worker",request.getErrorMessage());
			return Weaver.callOriginal();			
		}

	}

	@Weave
	public static class GatewayFutureStub {

		@Trace
		public ListenableFuture<GatewayOuterClass.CancelProcessInstanceResponse> cancelProcessInstance(GatewayOuterClass.CancelProcessInstanceRequest request) {
			ListenableFuture<GatewayOuterClass.CancelProcessInstanceResponse> f = Weaver.callOriginal();

			return f;
		}

		@Trace
		public ListenableFuture<GatewayOuterClass.CompleteJobResponse> completeJob(GatewayOuterClass.CompleteJobRequest request) {
			return Weaver.callOriginal();
		}

		@Trace
		public ListenableFuture<GatewayOuterClass.CreateProcessInstanceResponse> createProcessInstance(GatewayOuterClass.CreateProcessInstanceRequest request) {
			return Weaver.callOriginal();
		}

		@Trace
		public ListenableFuture<GatewayOuterClass.CreateProcessInstanceWithResultResponse> createProcessInstanceWithResult(GatewayOuterClass.CreateProcessInstanceWithResultRequest request) {
			return Weaver.callOriginal();
		}

		@Trace
		public ListenableFuture<GatewayOuterClass.DeployResourceResponse> deployResource(GatewayOuterClass.DeployResourceRequest request) {
			return Weaver.callOriginal();
		}

		@Trace
		public ListenableFuture<GatewayOuterClass.FailJobResponse> failJob(GatewayOuterClass.FailJobRequest request) {
			return Weaver.callOriginal();
		}

		@Trace
		public ListenableFuture<GatewayOuterClass.ThrowErrorResponse> throwError(GatewayOuterClass.ThrowErrorRequest request) {
			return Weaver.callOriginal();
		}

		@Trace
		public ListenableFuture<GatewayOuterClass.PublishMessageResponse> publishMessage(GatewayOuterClass.PublishMessageRequest request) {
			return Weaver.callOriginal();
		}

		@Trace
		public ListenableFuture<GatewayOuterClass.ResolveIncidentResponse> resolveIncident(GatewayOuterClass.ResolveIncidentRequest request) {
			return Weaver.callOriginal();
		}

		@Trace
		public ListenableFuture<GatewayOuterClass.SetVariablesResponse> setVariables(GatewayOuterClass.SetVariablesRequest request) {
			return Weaver.callOriginal();
		}

		@Trace
		public ListenableFuture<GatewayOuterClass.TopologyResponse> topology(GatewayOuterClass.TopologyRequest request) {
			return Weaver.callOriginal();
		}

		@Trace
		public ListenableFuture<GatewayOuterClass.UpdateJobRetriesResponse> updateJobRetries(GatewayOuterClass.UpdateJobRetriesRequest request) {
			return Weaver.callOriginal();
		}

	}

	@Weave
	public static class GatewayStub {

		@Trace(dispatcher = true)
		public void activateJobs(GatewayOuterClass.ActivateJobsRequest request,StreamObserver<GatewayOuterClass.ActivateJobsResponse> responseObserver) {
			Weaver.callOriginal();
		}


		@Trace(dispatcher = true)
		public void cancelProcessInstance(GatewayOuterClass.CancelProcessInstanceRequest request,StreamObserver<GatewayOuterClass.CancelProcessInstanceResponse> responseObserver) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void completeJob(GatewayOuterClass.CompleteJobRequest request,StreamObserver<GatewayOuterClass.CompleteJobResponse> responseObserver) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void createProcessInstance(GatewayOuterClass.CreateProcessInstanceRequest request,StreamObserver<GatewayOuterClass.CreateProcessInstanceResponse> responseObserver) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void createProcessInstanceWithResult(GatewayOuterClass.CreateProcessInstanceWithResultRequest request,StreamObserver<GatewayOuterClass.CreateProcessInstanceWithResultResponse> responseObserver) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void deployResource(GatewayOuterClass.DeployResourceRequest request,StreamObserver<GatewayOuterClass.DeployResourceResponse> responseObserver) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void failJob(GatewayOuterClass.FailJobRequest request,StreamObserver<GatewayOuterClass.FailJobResponse> responseObserver) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void throwError(GatewayOuterClass.ThrowErrorRequest request,StreamObserver<GatewayOuterClass.ThrowErrorResponse> responseObserver) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void publishMessage(GatewayOuterClass.PublishMessageRequest request,StreamObserver<GatewayOuterClass.PublishMessageResponse> responseObserver) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void resolveIncident(GatewayOuterClass.ResolveIncidentRequest request,StreamObserver<GatewayOuterClass.ResolveIncidentResponse> responseObserver) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void setVariables(GatewayOuterClass.SetVariablesRequest request,StreamObserver<GatewayOuterClass.SetVariablesResponse> responseObserver) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void topology(GatewayOuterClass.TopologyRequest request,StreamObserver<GatewayOuterClass.TopologyResponse> responseObserver) {
			Weaver.callOriginal();
		}

		@Trace(dispatcher = true)
		public void updateJobRetries(GatewayOuterClass.UpdateJobRetriesRequest request,StreamObserver<GatewayOuterClass.UpdateJobRetriesResponse> responseObserver) {
			Weaver.callOriginal();
		}

	}
}
