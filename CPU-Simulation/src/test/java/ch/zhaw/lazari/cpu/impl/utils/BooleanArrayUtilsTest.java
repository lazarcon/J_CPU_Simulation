/*
 * File: 		BooleanArrayUtilsTest.java
 * Date: 		Oct 29, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.utils;

import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.fromDigit;
import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.fromInt;
import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toBinaryString;
import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toDigit;
import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toInt;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Responsibility:
 */
public class BooleanArrayUtilsTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils#toBinaryString(boolean[])}.
	 */
	@Test
	public void testToBinaryString() {
		assertEquals("1001",toBinaryString(new boolean[] {true, false, false, true}));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils#toDigit(boolean)}.
	 */
	@Test
	public void testToDigit() {
		assertEquals(1, toDigit(true));
		assertEquals(0, toDigit(false));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils#fromDigit(int)}.
	 */
	@Test
	public void testFromDigit() {
		assertTrue(fromDigit(1));
		assertFalse(fromDigit(0));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils#toInt(boolean[])}.
	 */
	@Test
	public void testToIntBooleanArray() {
		assertEquals(0, toInt(new boolean[]{false}));
		assertEquals(1, toInt(new boolean[]{false, true}));
		assertEquals(3, toInt(new boolean[]{false, true, true}));
		assertEquals(-3, toInt(new boolean[]{true, false, true}));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils#fromInt(int, int)}.
	 */
	@Test
	public void testFromInt() {
		assertEqualArray(new boolean[]{false}, fromInt(0, 1));
		assertEqualArray(new boolean[]{false, true}, fromInt(1, 2));
		assertEqualArray(new boolean[]{false, true, true}, fromInt(3, 3));
		assertEqualArray(new boolean[]{true, false, false}, fromInt(-3, 3));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils#toInt(java.lang.String)}.
	 */
	@Test
	public void testToIntString() {
		assertEquals(0, toInt("0"));
		assertEquals(1, toInt("01"));
		assertEquals(3, toInt("011"));
		assertEquals(-3, toInt("101"));
		assertEquals(-1, toInt("111"));
		assertEquals(-15, toInt("10001"));
	}
	
	private static void assertEqualArray(final boolean[] expected, final boolean[] got) {
		assertTrue(expected.length == got.length);
		for(int index = 0; index < expected.length; ++index) {
			assertTrue(expected[index] == got[index]);
		}
	}
	
	@Test
	public void testInc() {
		boolean[] in = {true, true, false};
		boolean[] expected = {true, true, true};
		assertEqualArray(expected, BooleanArrayUtils.inc(in));
		
		boolean[] in2 = {false, true, true};
		boolean[] expected2 = {true, false, false};
		assertEqualArray(expected2, BooleanArrayUtils.inc(in2));

	}
}
