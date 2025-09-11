package io.atomix.cluster.messaging.impl;

import java.util.Optional;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
abstract class ServerConnection {

	@Trace(dispatcher = true)
	public void reply(long messageId, ProtocolReply.Status status, Optional<byte[]> payload) {
		NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","Atomix","ServerConnection",getClass().getSimpleName(),"reply");
		Weaver.callOriginal();
	}
}
