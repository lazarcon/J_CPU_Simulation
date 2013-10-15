/*
 * File: 		SimpleAccumulatorImplTest.java
 * Date: 		Oct 15, 2013
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

import ch.zhaw.lazari.cpu.api.Accumulator;
import ch.zhaw.lazari.cpu.api.Register;

/**
 * Responsibility:
 */
public class SimpleAccumulatorImplTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#SimpleAccumulatorImpl()}.
	 */
	@Test
	public void testSimpleAccumulatorImpl() {
		final Accumulator accu = new SimpleAccumulatorImpl();
		assertEquals(Register.DEFAULT_WORD_LENGTH, accu.get().length);
		for(byte aByte : accu.get()) {
			if(aByte == 0) {
				fail("Accumulator should have random values by default");
			}
		}
		assertEquals(0, accu.getCarryFlag());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#add(byte[])}.
	 */
	@Test
	public void testAddWithoutCarry() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#add(byte[])}.
	 */
	@Test
	public void testAddWithCarry() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#increment()}.
	 */
	@Test
	public void testIncrement() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#decrement()}.
	 */
	@Test
	public void testDecrement() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#shiftRightArithmetic()}.
	 */
	@Test
	public void testShiftRightArithmetic() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#shiftLeftArithmetic()}.
	 */
	@Test
	public void testShiftLeftArithmetic() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#shiftRightLogical()}.
	 */
	@Test
	public void testShiftRightLogical() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#shiftLeftLogical()}.
	 */
	@Test
	public void testShiftLeftLogical() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#and(byte[])}.
	 */
	@Test
	public void testAnd() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#or(byte[])}.
	 */
	@Test
	public void testOr() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl#not()}.
	 */
	@Test
	public void testNot() {
		final Accumulator accu = new SimpleAccumulatorImpl();
		final byte[] in = {0, 1};
		final byte[] expected = {~0, ~1};
		accu.set(in);
		accu.not();
		byte[] out = accu.get();
		for(int index = 0; index < out.length; ++index) {
			assertEquals(expected[index], out[index]);
		}
	}

}
