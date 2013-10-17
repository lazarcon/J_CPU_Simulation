/*
 * File: 		RegexInterpreterImplTest.java
 * Date: 		Oct 17, 2013
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

/**
 * Responsibility:
 */
public class InstructionSet2ByteWordTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.InstructionSet2ByteWord#contains(java.lang.String)}.
	 */
	@Test
	public void testClear00() {
		final String word = "0000001010000000";
		final InstructionSet2ByteWord instruction = InstructionSet2ByteWord.createFromBits(word);
		assertEquals(InstructionSet2ByteWord.CLR, instruction);
		assertEquals(0, instruction.getRegisterId(word));
	}

	
}
