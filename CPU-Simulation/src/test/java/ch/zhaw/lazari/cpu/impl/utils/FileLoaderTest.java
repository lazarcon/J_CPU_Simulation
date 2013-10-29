/*
 * File: 		FileLoaderTest.java
 * Date: 		Oct 29, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.utils;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

/**
 * Responsibility:
 */
public class FileLoaderTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.FileLoader#load(java.lang.String)}.
	 */
	@Test
	public void testLoad() {
		final FileLoader loader = new FileLoader();
		final List<String> lines = loader.load("src/test/resources/test.txt");
		assertEquals("First line", lines.get(0));
		assertEquals("Second line", lines.get(1));
		assertEquals("", lines.get(2));
		assertEquals("Fourth line", lines.get(3));
	}

}
