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

/**
 * Responsibility:
 */
public enum InstructionSet2ByteWord {

	CLR(InstructionGroup.REGISTER, "(0000)([01]{2})(101[01]{7})", "(CLR R)([0-3]{1})(*)"),
	ADD(InstructionGroup.ACCU, "(0000)([01]{2})(111[01]{7})", "(ADD R)([0-3]{1})(*)"),
	ADDD(InstructionGroup.ACCU, "(1)([01]{15})", "(ADDD )(-{0,1}[0-9]{5})(*)"),
	INC(InstructionGroup.ACCU, "(00000001)([01]{4})([01]{4})", "(INC)(*)"),
	DEC(InstructionGroup.ACCU, "(00000100)([01]{4})([01]{4})", "(DEC)(*)"),
	LWDD(InstructionGroup.MEMORY, "(010[01]{1})([01]{2})([01]{10})", "(LWDD R)([0-3]{1})( ){1}[0-9]{4})(*)"),
	SWDD(InstructionGroup.MEMORY, "(011[01]{1})([01]{2})([01]{10})", "(SWDD R)([0-3]{1})( ){1}[0-9]{4})(*)"),
	SRA(InstructionGroup.ARITHMETIC, "(00000101)([01]{4})([01]{4})", "(SRA)(*)"),
	SLA(InstructionGroup.ARITHMETIC, "(00001000)([01]{4})([01]{4})", "(SLA)(*)"),
	SRL(InstructionGroup.ARITHMETIC, "(00001001)([01]{4})([01]{4})", "(SRL)(*)"),
	SLL(InstructionGroup.ARITHMETIC, "(00001100)([01]{4})([01]{4})", "(SLL)(*)"),
	AND(InstructionGroup.LOGIC, "(0000)([01]{2})(100[01]{7})", "(AND R)([0-3]{1})(*)"),
	OR(InstructionGroup.LOGIC, "(0000)([01]{2})(110[01]{7})", "(OR R)([0-3]{1})(*)"),
	NOT(InstructionGroup.LOGIC, "(000000001)([01]{3})([01]{4})", "(NOT)(*)"),
	BZ(InstructionGroup.PROGRAM_COUNTER, "(0001)([01]{2})(10[01]{8})", "(BZ R)([0-3]{1})(*)"),
	BNZ(InstructionGroup.PROGRAM_COUNTER, "(0001)([01]{2})(01[01]{8})", "(BNZ R)([0-3]{1})(*)"),
	BC(InstructionGroup.PROGRAM_COUNTER, "(0001)([01]{2})(11[01]{8})", "(BC R)([0-3]{1})(*)"),
	B(InstructionGroup.PROGRAM_COUNTER, "(0001)([01]{2})(00[01]{8})", "(B R)([0-3]{1})(*)"),
	BZD(InstructionGroup.PROGRAM_COUNTER, "(00110)([01])([01]{10})", "(BZD )([0-9]{4})(*)"),
	BNZD(InstructionGroup.PROGRAM_COUNTER, "(00101)([01])([01]{10})", "(BNZD )([0-9]{4})(*)"),
	BCD(InstructionGroup.PROGRAM_COUNTER, "(00111)([01])([01]{10})", "(BCD )([0-9]{4})(*)"),
	BD(InstructionGroup.PROGRAM_COUNTER, "(00100)([01])([01]{10})", "(BD )([0-9]{4})(*)"),
	END(InstructionGroup.CPU, "(0000000000000000)", "(END)(*)");
	

	private final InstructionGroup group;
	
	private final String bitPattern;
	
	private final String mnemonicPattern;
	
	private InstructionSet2ByteWord(final InstructionGroup group, final String bitPattern, final String mnemonicPattern) {
		this.group = group;
		this.bitPattern = bitPattern;
		this.mnemonicPattern = mnemonicPattern;
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
		return mnemonicPattern;
	}

	public InstructionGroup getGroup() {
		return group;
	}
	
	public int getRegisterId(final String word) {
		return Integer.parseInt(word.replaceAll(bitPattern, "$2"));
	}
	
	public int getAddress(final String word) {
		return Integer.parseInt(word.replaceAll(bitPattern, "$3"));
	}
	
	public String getSecondWord(final String word) {
		return word.replaceAll(bitPattern, "$2");
	}

}
