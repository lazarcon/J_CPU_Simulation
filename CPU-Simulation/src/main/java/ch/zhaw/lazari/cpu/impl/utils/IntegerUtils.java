/*
 * File: 		IntegerUtils.java
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
 * Responsibility:
 */
public final class IntegerUtils {

	private IntegerUtils() {
		// Avoid instantiation
	}
	
	public static int pow(final int base, final int exponent) {
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
