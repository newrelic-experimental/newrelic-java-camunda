package io.atomix.cluster.messaging.impl;

import java.util.function.BiConsumer;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Token;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave
abstract class HandlerRegistry {

	void register(String type, BiConsumer<ProtocolRequest, ServerConnection> handler) {
		Token token = NewRelic.getAgent().getTransaction().getToken();
		if(token != null && token.isActive()) {
			HandlerWrapper wrapper = new HandlerWrapper(handler, token, type);
			handler = wrapper;
		} else if(token != null) {
			token.expire();
			token = null;
		}
		Weaver.callOriginal();
	}
}
