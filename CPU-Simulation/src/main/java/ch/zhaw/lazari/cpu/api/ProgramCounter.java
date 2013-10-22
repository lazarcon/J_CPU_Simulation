/*
 * File: 		ProgramCounter.java
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
 * Represents the program counter component of a processor
 */
public interface ProgramCounter {

	/** Default memory offset */
	int DEFAULT_OFFSET = 0;
	
	/**
	 * Sets the address of the next command 
	 * @param address relative address of the next command
	 */
	void set(final int address);
	
	/**
	 * Will return the currently stored address
	 * @return address of the current command
	 */
	int get();
	
	/** Increments the program counter */
	void next();
	
}
