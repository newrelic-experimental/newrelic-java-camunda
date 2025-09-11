package com.newrelic.instrumentation.labs.zeebe.atomix;

import java.util.function.BiConsumer;

public class NRBiConsumer<T> implements BiConsumer<T, Throwable> {

	@Override
	public void accept(T t, Throwable u) {
		
	}

}
