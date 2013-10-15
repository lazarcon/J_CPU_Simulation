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
import static org.junit.Assert.fail;

import org.junit.Test;

import ch.zhaw.lazari.cpu.api.Register;

/**
 * Responsibility:
 */
public class SimpleRegisterImplTest {

	private final static byte[] WORD = {(byte) 7, (byte) 42};

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl#SimpleRegisterImpl()}.
	 */
	@Test
	public void testSimpleRegisterImpl() {
		final Register register = new SimpleRegisterImpl();
		assertEquals(Register.DEFAULT_WORD_LENGTH, register.get().length);
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl#SimpleRegisterImpl(int)}.
	 */
	@Test
	public void testSimpleRegisterImplInt() {
		final Register register = new SimpleRegisterImpl(Register.DEFAULT_WORD_LENGTH + 1);
		assertEquals(Register.DEFAULT_WORD_LENGTH + 1, register.get().length);
		for(final byte theByte : register.get()) {
			if(theByte == 0) {
				fail("The initial word is not a random value.");
			}
		}
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl#set(byte[])}.
	 */
	@Test
	public void testSetGet() {
		final Register register = new SimpleRegisterImpl();
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
		final byte[] word = new byte[Register.DEFAULT_WORD_LENGTH + 1];
		final Register register = new SimpleRegisterImpl();
		register.set(word);
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl#set(byte[])}.
	 */
	@Test(expected = InvalidRegisterAccessException.class)
	public void testSetToShort() {
		final byte[] word = new byte[Register.DEFAULT_WORD_LENGTH - 1];
		final Register register = new SimpleRegisterImpl();
		register.set(word);
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl#clear()}.
	 */
	@Test
	public void testSetClear() {
		final Register register = new SimpleRegisterImpl();
		register.clear();		
		for(int index = 0; index < Register.DEFAULT_WORD_LENGTH; ++index) {
			assertEquals(0, register.get()[index]);
		}
	}
	
	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl#getSize()}.
	 */
	@Test
	public void testGetSize() {
		final Register register = new SimpleRegisterImpl();
		assertEquals(Register.DEFAULT_WORD_LENGTH, register.getSize());
	}
}
