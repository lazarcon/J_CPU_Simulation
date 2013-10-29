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

import org.junit.Test;

import ch.zhaw.lazari.cpu.api.LogicalAccumulator;

/**
 * Responsibility:
 */
public class LogicalAccumulatorImplTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.LogicalAccumulatorImpl#and(byte[])}.
	 */
	@Test
	public void testAnd() {
		final LogicalAccumulator accu = new LogicalAccumulatorImpl(3);
		final boolean[] in = {true, true, false};
		final boolean[] and = {true, false, false};
		final boolean[] expected = {true, false, false};
		accu.set(in);
		accu.and(and);
		boolean[] out = accu.get();
		for(int index = 0; index < out.length; ++index) {
			assertEquals(expected[index], out[index]);
		}
	}
	
	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.LogicalAccumulatorImpl#or(byte[])}.
	 */
	@Test
	public void testOr() {
		final LogicalAccumulator accu = new LogicalAccumulatorImpl(3);
		final boolean[] in = {true, true, false};
		final boolean[] or = {true, false, false};
		final boolean[] expected = {true, true, false};
		accu.set(in);
		accu.or(or);
		boolean[] out = accu.get();
		for(int index = 0; index < out.length; ++index) {
			assertEquals(expected[index], out[index]);
		}
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.LogicalAccumulatorImpl#not()}.
	 */
	@Test
	public void testNot() {
		final LogicalAccumulator accu = new LogicalAccumulatorImpl(2);
		final boolean[] in = {true, false};
		final boolean[] expected = {false, true};
		accu.set(in);
		accu.not();
		boolean[] out = accu.get();
		for(int index = 0; index < out.length; ++index) {
			assertEquals(expected[index], out[index]);
		}
	}

}
