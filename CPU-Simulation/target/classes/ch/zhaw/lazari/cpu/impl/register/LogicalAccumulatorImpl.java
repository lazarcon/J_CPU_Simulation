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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.zhaw.lazari.cpu.api.LogicalAccumulator;
import ch.zhaw.lazari.cpu.impl.ByteArrayUtils;

/**
 * Simple Implementation of an Accumulator
 */
public class LogicalAccumulatorImpl extends SimpleRegisterImpl implements LogicalAccumulator{

	private static final Logger LOG = LoggerFactory.getLogger(LogicalAccumulator.class);
	
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
		LOG.trace("Executing AND");
		set(ByteArrayUtils.and(get(), bytes));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#or(byte[])
	 */
	@Override
	public void or(byte[] bytes) {
		LOG.trace("Executing OR");
		set(ByteArrayUtils.or(get(), bytes));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#not()
	 */
	@Override
	public void not() {
		LOG.trace("Executing NOT");
		set(ByteArrayUtils.not(get()));
	}
}
