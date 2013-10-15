/*
 * File: 		Accumulator.java
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
 * Represents the Accumulator of a processor
 */
public interface Accumulator extends Register {

	/**
	 * @return status of the carry flag
	 */
	int getCarryFlag();
	
	/**
	 * Adds the bytes passed
	 * @param bytes
	 */
	void add(byte[] bytes);
		
	/**
	 * Adds one to the content of the accumulator.
	 * Sets overflow to true, if there was an overflow
	 */
	void increment();
	
	/**
	 * subtracts one from the content. Sets the overflow to true, if there was an overflow
	 */
	void decrement();
	
	/**
	 * Divides the content by 2. 
	 */
	void shiftRightArithmetic();
	
	/**
	 * Multiplies the content with 2
	 */
	void shiftLeftArithmetic();
	
	/**
	 * Divides the content by 2
	 */
	void shiftRightLogical();
	
	/**
	 * Multiplies the content with 2
	 */
	void shiftLeftLogical();
	
	/**
	 * Performs a logical and operation between the content and the given word
	 * @param bytes word to perform the operation with
	 */
	void and(final byte[] bytes);

	/**
	 * Performs a logical or operation between the content and the given word
	 * @param bytes word to perform the operation with
	 */
	void or(final byte[] bytes);

	/**
	 * Inverts the stored content
	 */
	void not();
}
