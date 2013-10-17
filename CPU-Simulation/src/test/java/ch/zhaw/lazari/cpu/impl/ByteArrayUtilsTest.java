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
package ch.zhaw.lazari.cpu.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils;

/**
 * Responsibility:
 */
public class ByteArrayUtilsTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils#toString(byte[])}.
	 */
	@Test
	public void testToStringByteArray() {
		final byte[] word = {1, 0};
		assertEquals("0000000100000000", ByteArrayUtils.toString(word));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils#toInt(byte[])}.
	 */
	@Test
	public void testToInt() {
		final byte[] word = {1, 0};
		assertEquals(256, ByteArrayUtils.toInt(word));
	}

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils#fromInt(int, int)}.
	 */
	@Test
	public void testFromInt() {
		final byte[] word = ByteArrayUtils.fromInt(256, 2);
		assertEquals(1, word[0]);
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

}
