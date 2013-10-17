/*
 * File: 		Register.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.api;

/**
 * Represents a register component of the processor.
 * 
 * The register has a word length of 2 bytes
 */
public interface Register {

	/** Default Word length of the register */
	static int DEFAULT_WORD_LENGTH = 2;
	
	/**
	 * Sets the content of the register to the given word
	 * @param word the word to store in the register
	 */
	void set(final byte[] word);
	
	/**
	 * @return content of the register
	 */
	byte[] get();
	
	/**
	 * Clears the content of the register
	 */
	void clear();
	
	/** Returns number of bytes used by the register */
	int getSize();
}
