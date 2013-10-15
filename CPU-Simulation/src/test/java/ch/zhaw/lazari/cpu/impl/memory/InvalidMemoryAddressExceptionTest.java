/*
 * File: 		InvalidMemoryAddressExceptionTest.java
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

/**
 * Responsibility:
 */
public class InvalidMemoryAddressExceptionTest {

	@Test
	public void testGetMessage() {
		final InvalidMemoryAddressException exception = new InvalidMemoryAddressException(7, 3);
		assertTrue(exception.getMessage().contains("7"));
		assertTrue(exception.getMessage().contains("3"));
	}

}
