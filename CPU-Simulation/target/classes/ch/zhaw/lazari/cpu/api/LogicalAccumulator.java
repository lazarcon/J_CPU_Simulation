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
public interface LogicalAccumulator extends Register {
	
	/**
	 * Performs a logical and operation between the content and the given word
	 * @param bytes word to perform the operation with
	 */
	void and(final boolean[] bytes);

	/**
	 * Performs a logical or operation between the content and the given word
	 * @param bytes word to perform the operation with
	 */
	void or(final boolean[] bytes);

	/**
	 * Inverts the stored content
	 */
	void not();
}
