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
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.zhaw.lazari.cpu.api.ArithmeticLogicalAccumulator;
import ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils;

/**
 * Responsibility:
 */
public class ArithmeticLogicalAccumulatorImplTest {

	private static final int LENGTH = 2;
	
	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#add(byte[])}.
	 */
	@Test
	public void testAddPositiveNoOverflow() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH);
		accu.set(ByteArrayUtils.fromInt(300, LENGTH));
		accu.add(ByteArrayUtils.fromInt(200, LENGTH));
		final byte[] result = accu.get();
		assertEquals(500, ByteArrayUtils.toInt(result));
		assertEquals(0, accu.getCarryFlag());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#add(byte[])}.
	 */
	@Test
	public void testAddPositiveOverflow() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH);
		accu.set(ByteArrayUtils.fromInt(65_535, LENGTH));
		accu.add(ByteArrayUtils.fromInt(1, LENGTH));
		assertEquals(1, accu.getCarryFlag());
		final byte[] result = accu.get();
		assertEquals(0, ByteArrayUtils.toInt(result));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#add(byte[])}.
	 */
	@Test
	public void testAddNegativNoOverflow() {
		final ArithmeticLogicalAccumulator accu = new ArithmeticLogicalAccumulatorImpl(LENGTH);
		accu.set(ByteArrayUtils.fromInt(300, LENGTH));
		accu.add(ByteArrayUtils.fromInt(-200, LENGTH));
		final byte[] result = accu.get();
		assertEquals(100, ByteArrayUtils.toInt(result));
		assertEquals(0, accu.getCarryFlag());
	}


	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#increment()}.
	 */
	@Test
	public void testIncrement() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#decrement()}.
	 */
	@Test
	public void testDecrement() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#shiftRightArithmetic()}.
	 */
	@Test
	public void testShiftRightArithmetic() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#shiftLeftArithmetic()}.
	 */
	@Test
	public void testShiftLeftArithmetic() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#shiftRightLogical()}.
	 */
	@Test
	public void testShiftRightLogical() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.ArithmeticLogicalAccumulatorImpl#shiftLeftLogical()}.
	 */
	@Test
	public void testShiftLeftLogical() {
		fail("Not yet implemented");
	}

}
