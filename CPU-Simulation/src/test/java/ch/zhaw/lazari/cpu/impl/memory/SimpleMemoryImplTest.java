/*
 * File: 		SimpleMemoryImplTest.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.memory;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.zhaw.lazari.cpu.api.Memory;
import ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils;
import ch.zhaw.lazari.cpu.impl.utils.InvalidArgumentException;

/**
 * Responsibility:
 */
public class SimpleMemoryImplTest {
	
	private final static boolean[] STORE = {true, false, false, true, true, false, true, false};
	
	private final static int ADDRESS = Memory.DEFAULT_SIZE - 1;
	
	@Test
	public void testConstructor() {
		final int testSize = 10;
		final Memory memory = new SimpleMemoryImpl(testSize);
		int nonZeros = 0;
		for(int index = 0; index < testSize; ++index) {
			nonZeros += (BooleanArrayUtils.toInt(memory.load(index)) == 0) ? 0 : 1; 
		}
		assertTrue(nonZeros > 0);
	}
	
	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.memory.SimpleMemoryImpl#store(int, byte)}.
	 */
	@Test
	public void testStoreLoadValid() {
		final Memory memory = new SimpleMemoryImpl();
		memory.store(ADDRESS, STORE);
		assertEqualArray(STORE, memory.load(ADDRESS));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.memory.SimpleMemoryImpl#store(int, byte)}.
	 */
	@Test(expected = InvalidMemoryAddressException.class)
	public void testStoreInvalidNegative() {
		final Memory memory = new SimpleMemoryImpl();
		memory.store(-1, STORE);
	}

	@Test(expected = InvalidMemoryAddressException.class)
	public void testStoreInvalidMax() {
		final Memory memory = new SimpleMemoryImpl();
		memory.store(Memory.DEFAULT_SIZE, STORE);
	}

	@Test(expected = InvalidArgumentException.class)
	public void testStoreInvalidBits() {
		final Memory memory = new SimpleMemoryImpl();
		memory.store(ADDRESS, new boolean[]{true});
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.memory.SimpleMemoryImpl#load(int)}.
	 */
	@Test(expected = InvalidMemoryAddressException.class)
	public void testLoadInvalidNegative() {
		final Memory memory = new SimpleMemoryImpl();
		memory.load(-1);
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.memory.SimpleMemoryImpl#load(int)}.
	 */
	@Test(expected = InvalidMemoryAddressException.class)
	public void testLoadInvalidMax() {
		final Memory memory = new SimpleMemoryImpl();
		memory.load(Memory.DEFAULT_SIZE);
	}

	private static void assertEqualArray(final boolean[] expected, final boolean[] got) {
		assertTrue(expected.length == got.length);
		for(int index = 0; index < expected.length; ++index) {
			assertTrue(expected[index] == got[index]);
		}
	}
}
