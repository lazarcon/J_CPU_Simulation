/*
 * File: 		UnknownCommandExceptionTest.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 * Responsibility:
 */
public class UnknownCommandExceptionTest {

	@Test
	public void test() {
		final UnknownCommandException exception = new UnknownCommandException(new byte[]{7, 42});
		assertFalse(exception.getMessage().contains("7"));
		assertFalse(exception.getMessage().contains("42"));
	}

}
