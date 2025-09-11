package io.atomix.cluster.messaging.impl;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.atomix.cluster.messaging.impl.ProtocolMessage.Type;

@Weave(type = MatchType.Interface)
class Connection<M extends ProtocolMessage> {

	@Trace(dispatcher = true)
	public void dispatch(M message) {
		Type type = message.type();
		if(type != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","Zeebe","Atomix","Connection","dispatch",type.name());
		} else {
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom","Camunda","Zeebe","Atomix","Connection","dispatch");
		}
		Weaver.callOriginal();
	}
}
