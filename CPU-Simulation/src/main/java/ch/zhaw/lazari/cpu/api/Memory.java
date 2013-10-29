/*
 * File: 		Memory.java
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
 * Represents the RAM memory of a computer. This is actually not part of the CPU.
 */
public interface Memory {

	/** The default size for memory elements in bytes */
	int DEFAULT_SIZE = 1024;
	
	/**
	 * Stores the given byte
	 * @param address where to store the byte
	 * @param value the byte to store
	 */
	void store(int address, boolean[] bits);
	
	/**
	 * Returns the byte stored at address
	 * @param address the address where the byte is locate
	 * @return byte stored at that address
	 */
	boolean[] load(int address);
}
