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

import ch.zhaw.lazari.cpu.api.Accumulator;
import ch.zhaw.lazari.cpu.impl.ByteArrayUtils;

/**
 * Simple Implementation of an Accumulator
 */
public class SimpleAccumulatorImpl extends SimpleRegisterImpl implements Accumulator{

	private final static Logger LOG = LoggerFactory.getLogger(Accumulator.class);
	
	private final int carryFlag = 0;
	
	/**
	 * Creates a new accumulator with a default word length
	 */
	public SimpleAccumulatorImpl() {
		super();
	}

	/**
	 * Creates a new accumulator with the given wordSize
	 * @param wordSize number of bytes per word
	 */
	public SimpleAccumulatorImpl(final int wordSize) {
		super(wordSize);
	}
	
	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#getCarryFlag()
	 */
	@Override
	public int getCarryFlag() {
		return carryFlag;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#add(byte[])
	 */
	@Override
	public void add(byte[] bytes) {
		LOG.trace("Adding word");
		// TODO create content
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#increment()
	 */
	@Override
	public void increment() {
		LOG.trace("Incrementing");
		add(new byte[]{0, 1});
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#decrement()
	 */
	@Override
	public void decrement() {
		LOG.trace("Decrementing");
		add(new byte[]{0, -1});
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftRightArithmetic()
	 */
	@Override
	public void shiftRightArithmetic() {
		LOG.trace("Executing arithmetic right shift");
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftLeftArithmetic()
	 */
	@Override
	public void shiftLeftArithmetic() {
		LOG.trace("Executing arithmetic left shift");
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftRightLogical()
	 */
	@Override
	public void shiftRightLogical() {
		LOG.trace("Executing logical right shift");
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftLeftLogical()
	 */
	@Override
	public void shiftLeftLogical() {
		LOG.trace("Executing logical left shift");
		// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#and(byte[])
	 */
	@Override
	public void and(byte[] bytes) {
		set(ByteArrayUtils.and(get(), bytes));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#or(byte[])
	 */
	@Override
	public void or(byte[] bytes) {
		set(ByteArrayUtils.or(get(), bytes));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#not()
	 */
	@Override
	public void not() {
		set(ByteArrayUtils.not(get()));
	}
}
