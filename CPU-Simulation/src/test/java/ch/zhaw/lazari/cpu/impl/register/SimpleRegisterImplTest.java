/*
 * File: 		SimpleRegisterImplTest.java
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.zhaw.lazari.cpu.api.Register;

/**
 * Responsibility:
 */
public class SimpleRegisterImplTest {

	private final static boolean[] WORD = {true, true, false, false, true, true, false, true};

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl#SimpleRegisterImpl(int)}.
	 */
	@Test
	public void testSimpleRegisterImplInt() {
		final Register register = new SimpleRegisterImpl(WORD.length, 0);
		assertEquals(8, register.getSize());
		int trueCount = 0;
		for(final boolean bit : register.get()) {
			trueCount += (bit) ? 1 : 0;
		}
		assertTrue(trueCount > 0);
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl#set(byte[])}.
	 */
	@Test
	public void testSetGet() {
		final Register register = new SimpleRegisterImpl(WORD.length, 0);
		register.set(WORD);
		for(int index = 0; index < WORD.length; ++index) {
			assertEquals(WORD[index], register.get()[index]);	
		}
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl#set(byte[])}.
	 */
	@Test(expected = InvalidRegisterAccessException.class)
	public void testSetToLong() {
		final Register register = new SimpleRegisterImpl(WORD.length, 0);
		final boolean[] word = new boolean[WORD.length + 1];
		register.set(word);
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl#set(byte[])}.
	 */
	@Test(expected = InvalidRegisterAccessException.class)
	public void testSetToShort() {
		final Register register = new SimpleRegisterImpl(WORD.length - 1, 0);
		final boolean[] word = new boolean[WORD.length];
		register.set(word);
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl#clear()}.
	 */
	@Test
	public void testSetClear() {
		final Register register = new SimpleRegisterImpl(WORD.length, 0);
		register.clear();		
		for(int index = 0; index < WORD.length; ++index) {
			assertFalse(register.get()[index]);
		}
	}	
}
