/*
 * File: 		ByteArrayUtilsTest.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Responsibility:
 */
public class ByteArrayUtilsTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils#toBinaryString(byte[])}.
	 */
	@Test
	public void testToBinaryStringFromByteArray() {
		final byte[] word = {1, 0};
		assertEquals("0000000100000000", ByteArrayUtils.toBinaryString(word));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils#toInt(byte[])}.
	 */
	@Test
	public void testToIntFromByteArrayPositive() {
		final byte[] word = {1, 0};
		assertEquals(256, ByteArrayUtils.toInt(word));
		assertEquals(Short.MAX_VALUE, ByteArrayUtils.toInt(new byte[]{Byte.MAX_VALUE, Byte.MIN_VALUE}));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils#toInt(byte[])}.
	 */
	@Test
	public void testToIntFromByteArrayNegative() {
		assertEquals(-512, ByteArrayUtils.toInt(new byte[] {-1, 0}));
		assertEquals(254, ByteArrayUtils.toInt(new byte[] {0, -1}));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils#fromInt(int, int)}.
	 */
	@Test
	public void testFromIntPositive() {
		final byte[] word = ByteArrayUtils.fromInt(Short.MAX_VALUE, 2);
		assertEquals(Byte.MAX_VALUE, word[0]);
		assertEquals(Byte.MIN_VALUE, word[1]);
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils#fromInt(int, int)}.
	 */
	@Test
	public void testFromIntNegative() {
		final byte[] word = ByteArrayUtils.fromInt(-256, 2);
		assertEquals(-1, word[0]);
		assertEquals(0, word[1]);
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils#not(byte[])}.
	 */
	@Test
	public void testNot() {
		final byte[] word = {1, 0};
		final byte[] expected = {~1, ~0};
		final byte[] result = ByteArrayUtils.not(word);
		assertEquals(expected[0], result[0]);
		assertEquals(expected[1], result[1]);
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils#and(byte[], byte[])}.
	 */
	@Test
	public void testAnd() {
		final byte[] first = {1, 0};
		final byte[] second = {~1, ~0};
		final byte[] result = ByteArrayUtils.and(first, second);
		assertEquals(0, result[0]);
		assertEquals(0, result[1]);
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils#or(byte[], byte[])}.
	 */
	@Test
	public void testOr() {
		final byte[] first = {1, 0};
		final byte[] second = {~1, ~0};
		final byte[] result = ByteArrayUtils.or(first, second);
		assertEquals(-1, result[0]);
		assertEquals(-1, result[1]);
	}
	
	@Test 
	public void testGetRange() {
		final int[] range = ByteArrayUtils.getRange(Short.SIZE / Byte.SIZE);
		assertEquals(Short.MIN_VALUE, range[0]);
		assertEquals(Short.MAX_VALUE, range[1]);
	}

	@Test
	public void testToBinaryStringFromPositive() {
		assertEquals("0000000000101010", ByteArrayUtils.toBinaryString(42, 16));
		assertEquals("0111111111111111", ByteArrayUtils.toBinaryString(Short.MAX_VALUE, 16));
	}

	@Test
	public void testToBinaryStringFromNegative() {
		assertEquals("1111111111010101", ByteArrayUtils.toBinaryString(-42, 16));
		assertEquals("1111111111111111", ByteArrayUtils.toBinaryString(Short.MIN_VALUE, 16));
	}
	
	@Test
	public void testConvert() {
		assertEquals("00101010", ByteArrayUtils.convert("00101010"));
		assertEquals("-0101010", ByteArrayUtils.convert("11010101"));
	}

	@Test
	public void testParseByteInRange() {
		assertEquals(42, ByteArrayUtils.parseFirstByte("00101010"));
		assertEquals(-42, ByteArrayUtils.parseFirstByte("11010101"));
		assertEquals(Byte.MIN_VALUE, ByteArrayUtils.parseFirstByte("11111111"));
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testParseByteNotInRange() {
		ByteArrayUtils.parseFirstByte("111111111");
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testParseIntInvalid() {
		ByteArrayUtils.parseInt("111111111111111111111111111111111");
	}
	
	@Test
	public void testParseIntValid() {
		assertEquals(0, ByteArrayUtils.parseInt("00000000"));
		assertEquals(1, ByteArrayUtils.parseInt("00000001"));
		assertEquals(-1, ByteArrayUtils.parseInt("11111111"));
		assertEquals(2, ByteArrayUtils.parseInt("00000010"));
		assertEquals(Short.MAX_VALUE, ByteArrayUtils.parseInt("0111111111111111"));
		assertEquals(Short.MIN_VALUE, ByteArrayUtils.parseInt("1000000000000000"));
	}
}
