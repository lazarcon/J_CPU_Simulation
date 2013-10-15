/*
 * File: 		InvalidRegisterAccessException.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.register;

/**
 * Indicates that the register was accessed in an invalid way
 * This means that a word of invalid length was passed.
 */
public class InvalidRegisterAccessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private final int given;
	
	private final int expected;
	
	/**
	 * Creates a new InvalidRegisterAccessException
	 * @param given The word length that was passed to the register
	 * @param expected The word length expected by the register
	 */
	public InvalidRegisterAccessException(final int given, final int expected) {
		this.given = given;
		this.expected = expected;
	}
	
	@Override
	public String getMessage(){
		return String.format("A word of length %d was passed to the register, but words are expected to have a length of %d.%n", given, expected);
	}
}
