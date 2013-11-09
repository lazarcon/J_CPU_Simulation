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

import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.fromInt;
import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toBinaryString;
import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toInt;

import java.util.BitSet;

import ch.zhaw.lazari.cpu.api.ArithmeticLogicalAccumulator;
import ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils;
import ch.zhaw.lazari.cpu.impl.utils.IntegerUtils;

/**
 * Responsibility:
 */
public class ArithmeticLogicalAccumulatorImpl extends LogicalAccumulatorImpl implements ArithmeticLogicalAccumulator {
		
	private boolean carryFlag = false;
	
	private final Range range;
	
	public ArithmeticLogicalAccumulatorImpl(final int wordLength, final int id) {
		super(wordLength, id);
		range = new Range(wordLength);
	}
	
	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#getCarryFlag()
	 */
	@Override
	public boolean getCarryFlag() {
		return carryFlag;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#add(byte[])
	 */
	@Override
	public void add(boolean[] bits) {
		final int stored = toInt(get());
		final int toAdd = toInt(bits);
		final int result = stored + toAdd;
		if(isOverflow(result)) {
			carryFlag = true;
		} else {
			carryFlag = false;
		}
		log(String.format("Executing add (in decimals): %d + %d = %d, carryFlag = %b", stored, toAdd, result, carryFlag));
		set(result);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#increment()
	 */
	@Override
	public void increment() {
		add(fromInt(1, getSize()));
		log(String.format("After incremention new content ist '%s' and carryFlag is %b.", 
				toBinaryString(get()), carryFlag));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#decrement()
	 */
	@Override
	public void decrement() {
		add(fromInt(-1, getSize()));
		log(String.format("After decremention new content ist '%s' and carryFlag is %b.", 
				toBinaryString(get()), carryFlag));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftRightArithmetic()
	 */
	@Override
	public void shiftRightArithmetic() {
		boolean[] before = get();
		boolean[] after = new boolean[getSize()];
		after[0] = before[0];
		after[1] = before[1];
		after[2] = false;
		for(int i=2;i<getSize()-1;i++)
		{
			after[i+1] = before[i];
		}
		set(after);
		log(String.format("Executed arithmetical right shift. %d --> %d (carryFlag is %B).", BooleanArrayUtils.toInt(before), BooleanArrayUtils.toInt(after), carryFlag));
		carryFlag = before[getSize()-1];
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftLeftArithmetic()
	 */
	@Override
	public void shiftLeftArithmetic() {
		boolean[] before = get();
		boolean[] after = new boolean[getSize()];
		after[0] = before[0];
		
		after[getSize()-1] = false;
		for(int i=1;i<getSize();i++)
		{
			after[i] = before[i+1];
		}
		set(after);
		log(String.format("Executed arithmetical left shift. %d --> %d (carryFlag is %B).", BooleanArrayUtils.toInt(before), BooleanArrayUtils.toInt(after), carryFlag));
		carryFlag = before[1];
		}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftRightLogical()
	 */
	@Override
	public void shiftRightLogical() {
		final int before = toInt(get());
		set(toInt(get()) >>> 1); 
		final int after = toInt(get());
		//carryFlag = before % 2;
		log(String.format("Executed logical right shift. %d --> %d (carryFlag is %b).", before, after, carryFlag));
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Accumulator#shiftLeftLogical()
	 */
	@Override
	public void shiftLeftLogical() {
		final int before = toInt(get());
		final String word = toBinaryString(get());
		//carryFlag = Integer.parseInt(word.substring(0, 1));
		final int value = toInt(get());
		set(value * 2);
		final int after = toInt(get());
		log(String.format("Executed logical left shift. %d --> %d (carryFlag is %b).", before, after, carryFlag));
	}

	private void set(final int value) {
		set(fromInt(value, getSize()));
	}
	
	private boolean isOverflow(final int value) {
		return (value < range.min) || (value > range.max);
	}
	
	private static final class Range {
		private final int min;
		private final int max;
		private Range(final int wordLength) {
			final int pow = IntegerUtils.pow(2, wordLength - 1);
			min = -pow ;
			max = pow - 1;
		}
	}
}
