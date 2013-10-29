/*
 * File: 		ProgramLoader2ByteInstructionSetTest.java
 * Date: 		Oct 29, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl;

import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.fromInt;
import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import ch.zhaw.lazari.cpu.api.Memory;
import ch.zhaw.lazari.cpu.api.ProgramLoader;
import ch.zhaw.lazari.cpu.impl.utils.ProgramLoader2ByteInstructionSet;

/**
 * Responsibility:
 */
public class ProgramLoader2ByteInstructionSetTest {

	/**
	 * Test method for {@link ch.zhaw.lazari.cpu.impl.utils.ProgramLoader2ByteInstructionSet#load(java.util.List)}.
	 */
	@Test
	public void testLoad() {
		final Memory memory = mock(Memory.class);
		final ProgramLoader loader = new ProgramLoader2ByteInstructionSet();
		final List<String> lines = new LinkedList<String>();
		lines.add("100 CLR R0");
		lines.add("102 CLR R1");
		lines.add("104 CLR R2 some comment");
		lines.add("106 CLR R3");
		lines.add("");
		lines.add("108 ADD R0");
		lines.add("110 ADDD -24; comment");
		lines.add("112 INC");
		lines.add("114 DEC");
		lines.add("116 SWDD R1 #200");
		lines.add("118 LWDD R2 #300");
		lines.add("120 SRA");
		lines.add("122 SLA");
		lines.add("124 SRL");
		lines.add("126 SLL");
		lines.add("128 AND R3");
		lines.add("130 OR R2");
		lines.add("134 NOT");
		lines.add("136 BZ R1");
		lines.add("138 BNZ R2");
		lines.add("140 BC R3");
		lines.add("142 B R0");
		lines.add("144 BZD #200");
		lines.add("146 BNZD #202");
		lines.add("148 BCD #204");
		lines.add("150 BD #206");
		lines.add("152 END");
		loader.load(lines, memory);
		verify(memory).store(100, toByte("00000010"));
		verify(memory).store(101, toByte("10000000"));
	}

	private boolean[] toByte(final String bits) {
		return fromInt(toInt(bits), Byte.SIZE);
	}
}
