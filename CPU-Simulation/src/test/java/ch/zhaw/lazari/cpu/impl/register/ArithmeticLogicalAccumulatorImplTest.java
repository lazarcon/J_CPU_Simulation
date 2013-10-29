/*
 * File: 		ArithmeticLogicalAccumulatorImplTest.java
 * Date: 		Oct 17, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.register;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.zhaw.lazari.cpu.api.ArithmeticLogicalAccumulator;
import ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils;

/**
 * Responsibility:
 */
public class ArithmeticLogicalAccumulatorImplTest {

	private static final int LENGTH = 16;
	
	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#add(byte[])}.
	 */
	@Test
	public void testAddPositiveNoOverflow() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH, 0);
		accu.set(BooleanArrayUtils.fromInt(300, LENGTH));
		accu.add(BooleanArrayUtils.fromInt(200, LENGTH));
		final boolean[] result = accu.get();
		assertEquals(500, BooleanArrayUtils.toInt(result));
		assertEquals(0, accu.getCarryFlag());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#add(byte[])}.
	 */
	@Test
	public void testAddPositiveOverflow() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH, 0);
		accu.set(BooleanArrayUtils.fromInt(Short.MAX_VALUE, LENGTH));
		accu.add(BooleanArrayUtils.fromInt(1, LENGTH));
		assertEquals(1, accu.getCarryFlag());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#add(byte[])}.
	 */
	@Test
	public void testAddNegativNoOverflow() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH, 0);
		accu.set(BooleanArrayUtils.fromInt(300, LENGTH));
		accu.add(BooleanArrayUtils.fromInt(-200, LENGTH));
		final boolean[] result = accu.get();
		assertEquals(100, BooleanArrayUtils.toInt(result));
		assertEquals(0, accu.getCarryFlag());
	}


	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#increment()}.
	 */
	@Test
	public void testIncrement() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH, 0);
		accu.set(BooleanArrayUtils.fromInt(300, LENGTH));
		accu.increment();
		assertEquals(301, BooleanArrayUtils.toInt(accu.get()));
		assertEquals(0, accu.getCarryFlag());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#decrement()}.
	 */
	@Test
	public void testDecrement() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH, 0);
		accu.set(BooleanArrayUtils.fromInt(300, LENGTH));
		accu.decrement();
		assertEquals(299, BooleanArrayUtils.toInt(accu.get()));
		assertEquals(0, accu.getCarryFlag());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#shiftRightArithmetic()}.
	 */
	@Test
	public void testShiftRightArithmetic() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH, 0);
		accu.set(BooleanArrayUtils.fromInt(100, LENGTH));
		accu.shiftRightArithmetic();
		assertEquals(50, BooleanArrayUtils.toInt(accu.get()));
		assertEquals(0, accu.getCarryFlag());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#shiftLeftArithmetic()}.
	 */
	@Test
	public void testShiftLeftArithmetic() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH, 0);
		accu.set(BooleanArrayUtils.fromInt(100, LENGTH));
		accu.shiftLeftArithmetic();
		assertEquals(200, BooleanArrayUtils.toInt(accu.get()));
		assertEquals(0, accu.getCarryFlag());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#shiftRightLogical()}.
	 */
	@Test
	public void testShiftRightLogical() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH, 0);
		accu.set(BooleanArrayUtils.fromInt(100, LENGTH));
		accu.shiftRightLogical();
		assertEquals(50, BooleanArrayUtils.toInt(accu.get()));
		assertEquals(0, accu.getCarryFlag());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#shiftLeftLogical()}.
	 */
	@Test
	public void testShiftLeftLogical() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH, 0);
		accu.set(BooleanArrayUtils.fromInt(100, LENGTH));
		accu.shiftLeftLogical();
		assertEquals(200, BooleanArrayUtils.toInt(accu.get()));
		assertEquals(0, accu.getCarryFlag());
	}

}
