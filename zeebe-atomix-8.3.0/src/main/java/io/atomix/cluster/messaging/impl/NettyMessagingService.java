package io.atomix.cluster.messaging.impl;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

import io.atomix.utils.net.Address;
import io.netty.channel.ChannelHandlerContext;

@Weave
public abstract class NettyMessagingService {
	
	@Trace(dispatcher = true)
	public CompletableFuture<byte[]> sendAndReceive(Address address, String type, byte[] payload, boolean keepAlive, Duration timeout, Executor executor) {
		CompletableFuture<byte[]>  result = Weaver.callOriginal();
		return result;
	}

	@Trace(dispatcher = true)
	public CompletableFuture<Void> sendAsync(Address address, String type, byte[] payload, boolean keepAlive) {
		return Weaver.callOriginal();
	}
	
	@Weave
	private static class MessageDispatcher<M extends ProtocolMessage> {
		
		@Trace
		protected void channelRead0(final ChannelHandlerContext ctx, final Object message) {
			Weaver.callOriginal();
		}
	}
}
