/*
 * File: 		InvalidMemoryAddressException.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.memory;

/**
 * Indicates that an invalid memory address was accessed
 */
public class InvalidMemoryAddressException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final int address;
	
	private final int maxAddress;
	
	/**
	 * Creates a new InvalidMemoryAccessException
	 * @param address The address that was accessed
	 * @param maxAddress The max address allowed.
	 */
	public InvalidMemoryAddressException(final int address, final int maxAddress){
		this.address = address;
		this.maxAddress = maxAddress;
	}
	
	@Override
	public String getMessage() {
		return String.format("%d is not a valid memory address. Range is: 0 - %d%n", address, maxAddress);
	}
}
