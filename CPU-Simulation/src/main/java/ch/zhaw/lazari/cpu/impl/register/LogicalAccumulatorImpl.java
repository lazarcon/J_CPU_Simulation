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

import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toBinaryString;
import ch.zhaw.lazari.cpu.api.LogicalAccumulator;

/**
 * Simple Implementation of an Accumulator
 */
public class LogicalAccumulatorImpl extends SimpleRegisterImpl implements LogicalAccumulator{

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
	public void and(boolean[] bits) {
		checkSize(bits.clone(), "AND"); 
		final boolean[] result = new boolean[getSize()];
		for(int index = 0; index < bits.length; ++index) {
			result[index] = get()[index] & bits[index];
		}
		log(String.format("Executed (content, passed argument): '%s' AND '%s' = '%s'", 
				toBinaryString(get()), toBinaryString(bits), toBinaryString(result)));
		set(result);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#or(byte[])
	 */
	@Override
	public void or(boolean[] bits) {
		checkSize(bits.clone(), "OR");
		final boolean[] result = new boolean[getSize()];
		for(int index = 0; index < bits.length; ++index) {
			result[index] = get()[index] | bits[index];
		}
		log(String.format("Executed (content, passed argument): '%s' OR '%s' = '%s'", 
				toBinaryString(get()), toBinaryString(bits), toBinaryString(result)));
		set(result);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#not()
	 */
	@Override
	public void not() {
		final boolean[] result = new boolean[getSize()];
		for(int index = 0; index < getSize(); ++index) {
			result[index] = !get()[index];
		}
		log(String.format("Executed (content, passed argument): NOT '%s' = '%s'", 
				toBinaryString(get()), toBinaryString(result)));
		set(result);
	}
}
