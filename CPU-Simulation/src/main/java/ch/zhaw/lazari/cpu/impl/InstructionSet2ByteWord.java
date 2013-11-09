/*
 * File: 		RegexInterpreterImpl.java
 * Date: 		Oct 17, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl;

import ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils;

/**
 * Responsibility:
 */
public enum InstructionSet2ByteWord {
	
	CLR(InstructionGroup.REGISTER, "(0000)([01]{2})(101[01]{7})", "(CLR R)([0-3]{1})", "0000%s1010000000"),
	ADD(InstructionGroup.ACCU, "(0000)([01]{2})(111[01]{7})", "(ADD R)([0-3]{1})", "0000%s1110000000"),
	ADDD(InstructionGroup.ACCU, "(1)([01]{15})", "(ADDD )(-{0,1}[0-9]{1,5})", "1%s"),
	INC(InstructionGroup.ACCU, "(00000001)([01]{4})([01]{4})", "(INC)", "0000000100000000"),
	DEC(InstructionGroup.ACCU, "(00000100)([01]{4})([01]{4})", "(DEC)", "0000010000000000"),
	LWDD(InstructionGroup.MEMORY, "(010[01]{1})([01]{2})([01]{10})", "(LWDD R)([0-3]{1})( #)([0-9]{3})", "0100%s%s"),
	SWDD(InstructionGroup.MEMORY, "(011[01]{1})([01]{2})([01]{10})", "(SWDD R)([0-3]{1})( #)([0-9]{3})", "0110%s%s"),
	SRA(InstructionGroup.ARITHMETIC, "(00000101)([01]{4})([01]{4})", "(SRA)", "0000010100000000"),
	SLA(InstructionGroup.ARITHMETIC, "(00001000)([01]{4})([01]{4})", "(SLA)", "0000100000000000"),
	SRL(InstructionGroup.ARITHMETIC, "(00001001)([01]{4})([01]{4})", "(SRL)", "0000100100000000"),
	SLL(InstructionGroup.ARITHMETIC, "(00001100)([01]{4})([01]{4})", "(SLL)", "0000110000000000"),
	AND(InstructionGroup.LOGIC, "(0000)([01]{2})(100[01]{7})", "(AND R)([0-3]{1})", "0000%s1000000000"),
	OR(InstructionGroup.LOGIC, "(0000)([01]{2})(110[01]{7})", "(OR R)([0-3]{1})", "0000%s1100000000"),
	NOT(InstructionGroup.LOGIC, "(000000001)([01]{3})([01]{4})", "(NOT)", "0000000010000000"),
	BZ(InstructionGroup.PROGRAM_COUNTER, "(0001)([01]{2})(10[01]{8})", "(BZ R)([0-3]{1})", "0001%s1000000000"),
	BNZ(InstructionGroup.PROGRAM_COUNTER, "(0001)([01]{2})(01[01]{8})", "(BNZ R)([0-3]{1})", "0001%s0100000000"),
	BC(InstructionGroup.PROGRAM_COUNTER, "(0001)([01]{2})(11[01]{8})", "(BC R)([0-3]{1})", "0001%s1100000000"),
	B(InstructionGroup.PROGRAM_COUNTER, "(0001)([01]{2})(00[01]{8})", "(B R)([0-3]{1})", "0001%s0000000000"),
	BZD(InstructionGroup.PROGRAM_COUNTER, "(00110)([01])([01]{10})", "(BZD #)({1}[0-9]{3})", "001100%s"),
	BNZD(InstructionGroup.PROGRAM_COUNTER, "(00101)([01])([01]{10})", "(BNZD #)({1}[0-9]{3})", "001010%s"),
	BCD(InstructionGroup.PROGRAM_COUNTER, "(00111)([01])([01]{10})", "(BCD #)({1}[0-9]{3})", "001110%s"),
	BD(InstructionGroup.PROGRAM_COUNTER, "(00100)([01])([01]{10})", "(BD #)({1}[0-9]{3})", "001000%s"),
	END(InstructionGroup.CPU, "(0000000000000000)", "(END)", "0000000000000000");
	
	private static final String LINE_NUMBER_REGEX = "([0-9]{3})( )";

	private static final String COMMENT_REGEX = "(.*)";
	
	private final InstructionGroup group;
	
	private final String bitPattern;
	
	private final String mnemonicPattern;
	
	private final String format;
	
	private InstructionSet2ByteWord(final InstructionGroup group, final String bitPattern, final String mnemonicPattern, final String format) {
		this.group = group;
		this.bitPattern = bitPattern;
		this.mnemonicPattern = mnemonicPattern;
		this.format = format;
	}
		
	public static InstructionSet2ByteWord createFromBits(final String word) {
		for(final InstructionSet2ByteWord instruction : InstructionSet2ByteWord.values()) {
			if(word.matches(instruction.getBitPattern())) {
				return instruction;
			}
		}
		throw new UnknownCommandException(word);
	}

	public static InstructionSet2ByteWord createFromMnemonic(final String word) {
		for(final InstructionSet2ByteWord instruction : InstructionSet2ByteWord.values()) {
			if(word.matches(instruction.getMnemonicPattern())) {
				return instruction;
			}
		}
		throw new UnknownCommandException(word);
	}

	
	private String getBitPattern() {
		return bitPattern;
	}

	private String getMnemonicPattern() {
		final StringBuilder builder = new StringBuilder(LINE_NUMBER_REGEX);
		builder.append(mnemonicPattern);
		builder.append(COMMENT_REGEX);
		return builder.toString();
	}

	public InstructionGroup getGroup() {
		return group;
	}
	
	public int getRegisterId(final String word) {
		return BooleanArrayUtils.toInt("0"+word.replaceAll(bitPattern, "$2"));
	}

	public int getMnemonicFirst(final String word) {
		return Integer.parseInt(word.replaceAll(getMnemonicPattern(), "$4"));
	}

	public int getMnemonicSecond(final String line) {
		return Integer.parseInt(line.replaceAll(getMnemonicPattern(), "$6"));		
	}

	public String getAddress(final String word) {
		return "0" + word.replaceAll(bitPattern, "$3");
	}
	
	public String getSecondWord(final String word) {
		return word.replaceAll(bitPattern, "$2");
	}
	
	public int getLineNumber(String line) {
		return Integer.parseInt(line.replaceAll(getMnemonicPattern(), "$1"));
	}
	
	public String getFormat() {
		return format;
	}
}
