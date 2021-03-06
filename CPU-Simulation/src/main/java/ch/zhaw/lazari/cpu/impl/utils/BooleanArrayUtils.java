/*
 * File: 		BooleanArrayUtils.java
 * Date: 		Oct 22, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.utils;

/**
 * Provides utilities for arrays of boolean
 */
public final class BooleanArrayUtils {

	private static final int TRUE = 1;

	private static final int FALSE = 0;

	private BooleanArrayUtils() {
		// Avoid instantiation
	}

	/**
	 * Converts an array of boolean into a string using the toDigit method.
	 * 
	 * @param bits
	 *            Array of booleans to interpret
	 * @return String, containing binary digits that represent the array
	 */
	public static String toBinaryString(final boolean[] bits) {
		final StringBuilder builder = new StringBuilder(bits.length);
		for (final boolean bit : bits) {
			builder.append(toDigit(bit));
		}
		return builder.toString();
	}

	/**
	 * Converts a boolean into a digit.
	 * 
	 * @param bit
	 *            the boolean to interpret
	 * @return 1, if bit was <code>true</code>, 0 otherwise
	 */
	public static int toDigit(final boolean bit) {
		return (bit) ? TRUE : FALSE;
	}

	/**
	 * Converts a digit into a binary.
	 * 
	 * @param digit
	 *            the digit to convert
	 * @return boolean matching the digit
	 */
	public static boolean fromDigit(final int digit) {
		if (digit != TRUE && digit != FALSE) {
			throw new InvalidArgumentException(String.format(
					"%d is not a valid digit to convert. Valid are 0 and 1.",
					digit));
		}
		return digit == TRUE;
	}

	/**
	 * Converts a boolean array to an integer
	 * 
	 * @param bits
	 *            bits to convert
	 * @return integer representing the bits
	 */
	public static int toInt(final boolean[] bits) {
		if (bits.length > Integer.SIZE) {
			throw new InvalidArgumentException(
					String.format(
							"Cannot convert '%s' to integer, because its to long (%d/%d)",
							toBinaryString(bits), bits.length, Integer.SIZE));
		}
		int result = 0;
		for (int index = 1; index < bits.length; ++index) {
			if (bits[0]) {
				// bits[0] = MSB, if true, than the whole is negative
				result += toDigit(!bits[index])
						* IntegerUtils.pow(2, bits.length - index - 1);
			} else {
				result += toDigit(bits[index])
						* IntegerUtils.pow(2, bits.length - index - 1);
			}
		}
		return bits[0] ? -result -1 : result;
	}

	public static boolean[] fromInt(final int value, final int length) {
		boolean[] result = new boolean[length];
		final boolean isNegative = (value < 0);
	
		int divResult = isNegative ? -value : value;
		int index = length - 1;
		// Calc value
		while (divResult > 0) {
			final int remains = divResult % 2;
				result[index--] = (remains == 1);
		
			divResult /= 2;
		}
		if(isNegative) {
			result = invert(result);
			result = inc(result);
		}
	
		return result;
	}

	private static  boolean[] invert(boolean[] result) {
		for(int i=0;i<result.length;i++)
			result[i] = !result[i];
		return result;
	}

	public static boolean[] inc(boolean[] input) {
		boolean[] result = new boolean[input.length];
		// boolean flag = input[input.length-1];
		boolean add = true;
		for (int i = input.length - 1; i >= 0; i--) {
			result[i] = input[i] ^ add; // XOR
			add = input[i] && add;

		}
		return result;
	}

	public static int toInt(final String word) {
		final boolean[] bits = new boolean[word.length()];
		int index = 0;
		for (char aChar : word.toCharArray()) {
			bits[index++] = fromDigit(Integer.parseInt("" + aChar));
		}
		return toInt(bits);
	}

	public static boolean[] fromString(String s) {
		boolean[] result = new boolean[s.length()];
		for (int i = 0; i < s.length(); i++){
		    char c = s.charAt(i);        
		    result[i] = c == '1';
		}
		return result;
	}
}
