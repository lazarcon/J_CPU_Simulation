/*
 * File: 		SimpleCPUImplTest.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import ch.zhaw.lazari.cpu.api.CPU;
import ch.zhaw.lazari.cpu.api.Memory;


/**
 * Responsibility:
 */
public class SimpleCPUImplTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.SimpleCPUImpl#SimpleCPUImpl(ch.zhaw.lazari.cpu.api.Memory)}.
	 */
	@Test
	public void testSimpleCPUImpl() {
		final Memory memory = mock(Memory.class);
		when(memory.load(anyInt())).thenReturn((byte) 0);
		final CPU cpu = new SimpleCPUImpl(memory);
		assertTrue(cpu.isFinished());
		cpu.tick();
		assertTrue(cpu.isFinished());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.SimpleCPUImpl#tick()}.
	 */
	@Test
	public void testTick() {
		final Memory memory = mock(Memory.class);
		when(memory.load(anyInt())).thenReturn((byte) 1);
		final CPU cpu = new SimpleCPUImpl(memory);
		cpu.start();
		cpu.tick();
		assertFalse(cpu.isFinished());
		when(memory.load(anyInt())).thenReturn((byte) 0);
		cpu.tick();
		assertTrue(cpu.isFinished());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.SimpleCPUImpl#getRegister(int)}.
	 */
	@Test
	public void testGetRegister() {
		final Memory memory = mock(Memory.class);
		final CPU cpu = new SimpleCPUImpl(memory);
		for(int index = 0; index < 4; ++index) {
			assertNotNull(cpu.getRegister(index));
		}
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.SimpleCPUImpl#getMemory()}.
	 */
	@Test
	public void testGetMemory() {
		final Memory memory = mock(Memory.class);
		final CPU cpu = new SimpleCPUImpl(memory);
		assertEquals(memory, cpu.getMemory());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.SimpleCPUImpl#getProgramCounter()}.
	 */
	@Test
	public void testGetProgramCounter() {
		final Memory memory = mock(Memory.class);
		final CPU cpu = new SimpleCPUImpl(memory);
		assertNotNull(cpu.getProgramCounter());
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.SimpleCPUImpl#getAccumulator()}.
	 */
	@Test
	public void testGetAccumulator() {
		final Memory memory = mock(Memory.class);
		final CPU cpu = new SimpleCPUImpl(memory);
		assertNotNull(cpu.getAccumulator());
	}

}
