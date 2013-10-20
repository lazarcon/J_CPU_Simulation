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

import static ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils.fromInt;
import static ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils.getRange;
import static ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils.toInt;
import ch.zhaw.lazari.cpu.api.ArithmeticLogicalAccumulator;
import ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils;

/**
 * Responsibility:
 */
public class ArithmeticLogicalAccumulatorImpl extends LogicalAccumulatorImpl implements ArithmeticLogicalAccumulator {
		
	private int carryFlag = 0;
	
	private final int[] range;
	
	public ArithmeticLogicalAccumulatorImpl() {
		this(DEFAULT_WORD_LENGTH);
	}
	
	public ArithmeticLogicalAccumulatorImpl(final int wordLength) {
		super(wordLength);
		range = getRange(wordLength);
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
		final int stored = toInt(get());
		final int toAdd = toInt(bytes);
		final int result = stored + toAdd;
		if(isOverflow(result)) {
			carryFlag = 1;
		} else {
			carryFlag = 0;
		}
		log(String.format("Executing add (in decimals): %d + %d = %d, carryFlag = %d", stored, toAdd, result, carryFlag));
		set(result);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#increment()
	 */
	@Override
	public void increment() {
		add(new byte[]{0, 1});
		log(String.format("After incremention new content ist '%s' and carryFlag is %d.", ByteArrayUtils.toString(get()), carryFlag));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#decrement()
	 */
	@Override
	public void decrement() {
		add(new byte[]{0, -1});
		log(String.format("After decremention new content ist '%s' and carryFlag is %d.", ByteArrayUtils.toString(get()), carryFlag));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftRightArithmetic()
	 */
	@Override
	public void shiftRightArithmetic() {
		final int before = toInt(get());
		set(toInt(get()) >> 1);
		final int after = toInt(get());
		log(String.format("Executed arithmetical right shift. %d --> %d (carryFlag is %d).", before, after, carryFlag));
		carryFlag = 0;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftLeftArithmetic()
	 */
	@Override
	public void shiftLeftArithmetic() {
		final int before = toInt(get());
		final int value = toInt(get());
		final int newValue = value << 1;
		if(isOverflow(newValue)) {
			carryFlag = 1;
		} else {
			carryFlag = 0;
		}
		set(newValue);
		final int after = toInt(get());
		log(String.format("Executed arithmetical left shift. %d --> %d (carryFlag is %d).", before, after, carryFlag));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftRightLogical()
	 */
	@Override
	public void shiftRightLogical() {
		final int before = toInt(get());
		set(toInt(get()) >>> 1); 
		final int after = toInt(get());
		carryFlag = 0;
		log(String.format("Executed logical right shift. %d --> %d (carryFlag is %d).", before, after, carryFlag));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftLeftLogical()
	 */
	@Override
	public void shiftLeftLogical() {
		final int before = toInt(get());
		final String word = ByteArrayUtils.toString(get());
		carryFlag = Integer.parseInt(word.substring(0, 1));
		final int value = toInt(get());
		set(value * 2);
		final int after = toInt(get());
		log(String.format("Executed logical left shift. %d --> %d (carryFlag is %d).", before, after, carryFlag));
	}

	private void set(final int value) {
		set(fromInt(value, get().length));
	}
	
	private boolean isOverflow(final int value) {
		return (value < range[0]) || (value > range[1]);
	}
}
