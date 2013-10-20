/*
 * File: 		SimpleAccumulatorImpl.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.register;

import ch.zhaw.lazari.cpu.api.LogicalAccumulator;
import ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils;

/**
 * Simple Implementation of an Accumulator
 */
public class LogicalAccumulatorImpl extends SimpleRegisterImpl implements LogicalAccumulator{
	
	/**
	 * Creates a new accumulator with a default word length
	 */
	public LogicalAccumulatorImpl() {
		super();
	}

	/**
	 * Creates a new accumulator with the given wordSize
	 * @param wordSize number of bytes per word
	 */
	public LogicalAccumulatorImpl(final int wordSize) {
		super(wordSize);
	}
	

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#and(byte[])
	 */
	@Override
	public void and(byte[] bytes) {
		final String current = ByteArrayUtils.toBinaryString(get());
		set(ByteArrayUtils.and(get(), bytes));
		log(String.format("Executed (content, passed argument): '%s' AND '%s' = '%s'", 
				current, ByteArrayUtils.toBinaryString(bytes), ByteArrayUtils.toBinaryString(get())));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#or(byte[])
	 */
	@Override
	public void or(byte[] bytes) {
		final String current = ByteArrayUtils.toBinaryString(get());
		set(ByteArrayUtils.or(get(), bytes));
		log(String.format("Executed (content, passed argument): '%s' OR '%s' = '%s'", 
				current, ByteArrayUtils.toBinaryString(bytes), ByteArrayUtils.toBinaryString(get())));

	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#not()
	 */
	@Override
	public void not() {
		final String current = ByteArrayUtils.toBinaryString(get());
		set(ByteArrayUtils.not(get()));
		log(String.format("Executed NOT '%s' = '%s'", 
				current, ByteArrayUtils.toBinaryString(get())));
	}
}
