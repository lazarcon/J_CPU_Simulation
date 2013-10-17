/*
 * File: 		InvalidArgumentExceptionTest.java
 * Date: 		Oct 17, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.utils;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Responsibility:
 */
public class InvalidArgumentExceptionTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.InvalidArgumentException#InvalidArgumentException(java.lang.String)}.
	 */
	@Test
	public void testInvalidArgumentException() {
		final String word = "anyWord will do";
		final InvalidArgumentException exception = new InvalidArgumentException(word);
		assertTrue(exception.getMessage().contains(word));
	}

}
