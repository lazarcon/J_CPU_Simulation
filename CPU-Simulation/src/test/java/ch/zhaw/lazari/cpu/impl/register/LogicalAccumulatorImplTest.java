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

import ch.zhaw.lazari.cpu.api.LogicalAccumulator;
import ch.zhaw.lazari.cpu.api.Register;

/**
 * Responsibility:
 */
public class LogicalAccumulatorImplTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.LogicalAccumulatorImpl#SimpleAccumulatorImpl()}.
	 */
	@Test
	public void testSimpleAccumulatorImpl() {
		final LogicalAccumulator accu = new LogicalAccumulatorImpl();
		assertEquals(Register.DEFAULT_WORD_LENGTH, accu.get().length);
		for(byte aByte : accu.get()) {
			if(aByte == 0) {
				fail("Accumulator should have random values by default");
			}
		}
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.LogicalAccumulatorImpl#and(byte[])}.
	 */
	@Test
	public void testAnd() {
		final LogicalAccumulator accu = new LogicalAccumulatorImpl();
		final byte[] in = {0, 1};
		final byte[] and = {~0, ~1};
		final byte[] expected = {0, 0};
		accu.set(in);
		accu.or(and);
		byte[] out = accu.get();
		for(int index = 0; index < out.length; ++index) {
			assertEquals(expected[index], out[index]);
		}
	}
	
	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.LogicalAccumulatorImpl#or(byte[])}.
	 */
	@Test
	public void testOr() {
		final LogicalAccumulator accu = new LogicalAccumulatorImpl();
		final byte[] in = {0, 1};
		final byte[] or = {~0, ~1};
		final byte[] expected = {1, 1};
		accu.set(in);
		accu.or(or);
		byte[] out = accu.get();
		for(int index = 0; index < out.length; ++index) {
			assertEquals(expected[index], out[index]);
		}
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.LogicalAccumulatorImpl#not()}.
	 */
	@Test
	public void testNot() {
		final LogicalAccumulator accu = new LogicalAccumulatorImpl();
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
