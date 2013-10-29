/*
 * File: 		IntegerUtilsTest.java
 * Date: 		Oct 29, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.utils;

import static ch.zhaw.lazari.cpu.impl.utils.IntegerUtils.pow;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Responsibility:
 */
public class IntegerUtilsTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.IntegerUtils#pow(int, int)}.
	 */
	@Test
	public void testPow() {
		assertEquals(1, pow(2, 0));
		assertEquals(2, pow(2, 1));
		assertEquals(8, pow(2, 3));
	}

}
