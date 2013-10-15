/*
 * File: 		ByteUtils.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl;

/**
 * Provides all necessary byte operations
 */
public final class ByteArrayUtils {

	private static final int BITS_PER_BYTE = 8;
	
	private static final int ZEROS = 0xFF;
	
	private ByteArrayUtils() {
		// Avoid instantiation
	}

	/**
	 * Creates a string representation of the word
	 * @param word word that should be "stringed"
	 * @return String representation of the word
	 */
	public static String toString(final byte[] word) {
		final StringBuilder builder = new StringBuilder();
		for(final byte aByte : word) {
			builder.append(String.format("%08d", Integer.parseInt(Integer.toBinaryString(aByte))));
		}
		return builder.toString();
	}

	/**
	 * Converts the byte array to an integer
	 * @param word array of byte representing the value
	 * @return value represented by the word
	 */
	public static int toInt(final byte[] word) {
		int result = 0;
		int offset = 0;
		for(int index = word.length - 1; index >= 0; --index) {
			result |= (word[index] & ZEROS) << offset;
			offset += BITS_PER_BYTE;
		}
		return result;
	}
	
	/**
	 * Converts the given int to a byte array. Strips off anything,
	 * that does not fit in the given length
	 * @param value The integer value to convert
	 * @param length of the returned byte array
	 * @return byte array representing the int as good as possible
	 */
	public static byte[] fromInt(final int value, final int length) {
		final byte[] result = new byte[length];
		int offset = (length - 1) * BITS_PER_BYTE;
		for(int index = 0; index < length; ++index) {
			result[index] = (byte) ((value >> offset) & ZEROS);
			offset -= BITS_PER_BYTE;
		}
		return result;
	}
		
	/**
	 * Creates the complement of word
	 * @param word byte array which is to be inverted
	 * @return inverted word
	 */
	public static byte[] not(final byte[] word) {
		final byte[] complement = new byte[word.length];
		for(int index = 0; index < word.length; ++index) {
			complement[index] = (byte) ~word[index];
		}
		return complement;
	}
	
	/**
	 * Combines first and second to a new word, resulting from logical and
	 * @param first byteArray to use
	 * @param second byteArray to use
	 * @return Logical And combined word
	 */
	public static byte[] and(final byte[] first, final byte[] second) {
		if(first.length != second.length) {
			throw new InvalidArgumentException("the passed word should be of same length");
		}
		final byte[] and = new byte[first.length];
		for(int index = 0; index < first.length; ++index) {
			and[index] = (byte) (first[index] & second[index]);
		}
		return and;
	}
	
	/**
	 * Combines first and second to a new word, resulting from logical or
	 * @param first byteArray to use
	 * @param second byteArray to use
	 * @return Logical And combined word
	 */
	public static byte[] or(final byte[] first, final byte[] second) {
		if(first.length != second.length) {
			throw new InvalidArgumentException("the passed word should be of same length");
		}
		final byte[] or = new byte[first.length];
		for(int index = 0; index < first.length; ++index) {
			or[index] = (byte) (first[index] | second[index]);
		}
		return or;
	}

}
