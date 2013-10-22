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
package ch.zhaw.lazari.cpu.impl.utils;


/**
 * Provides all necessary byte operations
 */
public final class ByteArrayUtils {
	
	public static final int RADIX_BINARY = 2;
	
	private ByteArrayUtils() {
		// Avoid instantiation
	}

	/**
	 * Creates a string representation of the word
	 * @param word word that should be "stringed"
	 * @return String representation of the word
	 */
	public static String toBinaryString(final byte[] word) {
		final StringBuilder builder = new StringBuilder();
		for(final byte aByte : word) {
			final String binary = Integer.toBinaryString(aByte);
			if(binary.length() < Byte.SIZE) {
				builder.append(String.format("%08d", Integer.parseInt(binary)));
			} else {
				builder.append(binary.substring(binary.length() - Byte.SIZE));
			}
		}
		return builder.toString();
	}

	/**
	 * Converts the byte array to an integer
	 * @param word array of byte representing the value
	 * @return value represented by the word
	 */
	public static int toInt(final byte[] word) {
		final StringBuilder bits = new StringBuilder(word.length * Byte.SIZE);
		for(int index = 0; index < word.length; ++index) {
			bits.append(toBinaryString(word[index], Byte.SIZE));
		}
		return parseInt(bits.toString());
	}
	
	/**
	 * Replacement for <code>Integer.parseInt(String, int)</code> because
	 * it doesn't yield the expected result
	 * @param bits
	 * @return
	 */
	public static int parseInt(final String bits) {
		if(bits.length() > Integer.SIZE) {
			throw new InvalidArgumentException(String.format("Passed argument '%s' can not be represented by an integer.", bits.toString()));
		} 
		int result = 0;
		final boolean isNegative = bits.charAt(0) == '1';
		for(int index = 1; index < bits.length(); ++index) {
			int bit;
			if(isNegative) {
				bit = (bits.charAt(index) == '0') ? 1 : 0;
			} else {
				bit = (bits.charAt(index) == '0') ? 0 : 1;
			}
			// Minus 1 because last power must be 0
			final int power = bits.length() - index - 1;
			final int part = bit * pow(2, power);
			result += part;
		}
		return (isNegative) ? -(result + 1) : result;
	}
	
	/**
	 * Converts the given integer to a byte array. Strips off anything,
	 * that does not fit in the given length
	 * @param value The integer value to convert
	 * @param length of the returned byte array
	 * @return byte array representing the int as good as possible
	 */
	public static byte[] fromInt(final int value, final int length) {
		final byte[] result = new byte[length];
		final String bits = toBinaryString(value, length * Byte.SIZE);
		int offset = 0;
		for(int index = 0; index < length; ++index) {
			final String sub = bits.substring(offset, offset + Byte.SIZE);
			result[index] = parseByte(sub);
			offset += Byte.SIZE;
		}		
		return result;
	}
	
	/**
	 * Replacement for <code>Byte.parseByte(String, int)</code>, because it does not work on 2s complement
	 * @param bits String containing the bits to parse in 2s complement
	 * @return byte represented by bits
	 */
	public static byte parseByte(final String bits) {
		if(bits.length() != Byte.SIZE) {
			throw new InvalidArgumentException(String.format("'%s' is not a valid bit-string", bits));
		}
		return Byte.parseByte(convert(bits), RADIX_BINARY);
	}

	/** 
	 * Converts a bit-string, so that it can be used by <code>Byte.parseByte(String, int)</code>
	 * @param bits String to convert
	 * @return converted String (if conversion is required
	 */
	public static String convert (final String bits) {
		if(bits.startsWith("1")) {
			final StringBuilder builder = new StringBuilder(Byte.SIZE);
			builder.append("-");
			for(int index = 1; index < Byte.SIZE; ++index) {
				builder.append((bits.charAt(index) == '1') ? '0' : '1');
			}
			return builder.toString();
		} else {
			return bits;
		}
	}
	
	/**
	 * Converts an integer to a String of bits.
	 * @param value The value to convert
	 * @param length The length of the result
	 * @return String of the given length containting 0 and 1 representing the value in 2s complement
	 */
	public static String toBinaryString(final int value, final int length) {
		final StringBuilder builder = new StringBuilder();
		int divResult = (value < 0) ? -value : value;
		// Calc value
		while(divResult > 0) {
			final int remains = divResult % 2;
			if(value < 0) {
				builder.append(remains == 0 ? 1 : 0);
			} else {
				builder.append(remains == 0 ? 0 : 1);
			}
			divResult /= 2;			
		}
		// Check size
		if((value > 0 && builder.length() > length - 1) || builder.length() > length) {
			throw new InvalidArgumentException(String.format("The passed value '%d' does not fit in the range '%d'.", value, length));
		}
		// Fill
		while(builder.length() < length - 1) {
			if(value < 0) {
				builder.append(1);
			} else {
				builder.append(0);
			}
		}
		// Append sign if not already in
		if(builder.length() < length) {
			builder.append((value < 0) ? "1" : "0");
		} else {
			builder.replace(builder.length() - 1, builder.length(), "1");
		}
		builder.reverse();
		return builder.toString();
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
	
	public static int[] getRange(final int wordLength) {
		final int possibilities = pow(RADIX_BINARY, (Byte.SIZE * wordLength) - 1);
		return new int[]{-possibilities, possibilities - 1};
	}
	
	private static int pow(final int base, final int exponent) {
		if(exponent < 0) {
			throw new InvalidArgumentException(String.format("pow is not defined for negative values like %d", exponent));
		} 
		int result = 1;
		for(int index = 0; index < exponent; ++index) {
			result *= base;
		}
		return result;
	}

}
