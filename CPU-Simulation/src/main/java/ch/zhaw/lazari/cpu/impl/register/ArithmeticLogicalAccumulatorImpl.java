/*
 * File: 		ArithmeticLogicalAccumulator.java
 * Date: 		Oct 17, 2013
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

import ch.zhaw.lazari.cpu.api.ArithmeticLogicalAccumulator;
import ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils;

/**
 * Responsibility:
 */
public class ArithmeticLogicalAccumulatorImpl extends LogicalAccumulatorImpl implements ArithmeticLogicalAccumulator {

	private static final Logger LOG = LoggerFactory.getLogger(ArithmeticLogicalAccumulator.class);
	
	private final int min;
	
	private final int max;
	
	private int carryFlag = 0;
	
	public ArithmeticLogicalAccumulatorImpl() {
		this(DEFAULT_WORD_LENGTH);
	}
	
	public ArithmeticLogicalAccumulatorImpl(final int wordLength) {
		super(wordLength);
		final int value = pow(ByteArrayUtils.RADIX_BINARY, (ByteArrayUtils.BITS_PER_BYTE * get().length) - 1);
		min = -value;
		max = value - 1;
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
		final int stored = ByteArrayUtils.toInt(get());
		final int toAdd = ByteArrayUtils.toInt(bytes);
		final int result = stored + toAdd;
		LOG.trace(String.format("%d + %d = %d", stored, toAdd, result));
		if(isOverflow(result)) {
			carryFlag = 1;
		} else {
			carryFlag = 0;
		}
		set(result);
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
		set(ByteArrayUtils.toInt(get()) >> 1);
		carryFlag = 0;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftLeftArithmetic()
	 */
	@Override
	public void shiftLeftArithmetic() {
		LOG.trace("Executing arithmetic left shift");
		final int value = ByteArrayUtils.toInt(get());
		final int newValue = value << 1;
		if(isOverflow(newValue)) {
			carryFlag = 1;
		} else {
			carryFlag = 0;
		}
		set(newValue);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftRightLogical()
	 */
	@Override
	public void shiftRightLogical() {
		LOG.trace("Executing logical right shift");
		set(ByteArrayUtils.toInt(get()) >>> 1); 
		carryFlag = 0;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftLeftLogical()
	 */
	@Override
	public void shiftLeftLogical() {
		LOG.trace("Executing logical left shift");
		final String word = ByteArrayUtils.toString(get());
		carryFlag = Integer.parseInt(word.substring(0, 1));
		final int value = ByteArrayUtils.toInt(get());
		set(value * 2);
	}

	private void set(final int value) {
		set(ByteArrayUtils.fromInt(value, get().length));
	}
	
	private boolean isOverflow(final int value) {
		return (value > max) || (value < min);
	}
	
	private int pow(final int base, final int exponent) {
		if(exponent == 0) {
			return 0;
		} else {
			int result = base;
			for(int index = 0; index < exponent; ++index) {
			result *= base;
		}
		return result;
		}
	}
}
