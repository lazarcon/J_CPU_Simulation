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

	CLR("(0000)([01]{2})(101[01]{7})"),
	ADD("(0000)([01]{2})(111[01]{7})"),
	ADDD("(1)([01]{15})"),
	INC("(00000001)([01]{4})([01]{4})"),
	DEC("(00000100)([01]{4})([01]{4})"),
	LWDD("(010[01]{1})([01]{2})([01]{10})"),
	SWDD("(011[01]{1})([01]{2})([01]{10})"),
	SRA("(00000101)([01]{4})([01]{4})"),
	SLA("(00001000)([01]{4})([01]{4})"),
	SRL("(00001001)([01]{4})([01]{4})"),
	SLL("(00001100)([01]{4})([01]{4})"),
	AND("(0000)([01]{2})(100[01]{7})"),
	OR("(0000)([01]{2})(110[01]{7})"),
	NOT("(000000001)([01]{3})([01]{4}"),
	BZ("(0001)([01]{2})(10[01]{8})"),
	BNZ("(0001)([01]{2})(01[01]{8})"),
	BC("(0001)([01]{2})(11[01]{8})"),
	B("(0001)([01]{2})(00[01]{8})"),
	BZD("(00110)([01])([01]{10})"),
	BNZD("(00101)([01])([01]{10})"),
	BCD("(00111)([01])([01]{10})"),
	BD("(00100)([01])([01]{10})"),
	END("(0000000000000000)");
	

	private final String pattern;
	
	private InstructionSet2ByteWord(final String pattern) {
		this.pattern = pattern;
	}
		
	public static InstructionSet2ByteWord create(final String word) {
		for(final InstructionSet2ByteWord instruction : InstructionSet2ByteWord.values()) {
			if(word.matches(instruction.getPattern())) {
				return instruction;
			}
		}
		throw new UnknownCommandException(word);
	}
	
	private String getPattern() {
		return pattern;
	}
	
	public int getRegisterId(final String word) {
		return Integer.parseInt(word.replaceAll(pattern, "$2"));
	}
	
	public int getAddress(final String word) {
		return Integer.parseInt(word.replaceAll(pattern, "$3"));
	}
	
	public String getSecondWord(final String word) {
		return word.replaceAll(pattern, "$2");
	}

}
