/*
 * File: 		SimpleMemoryImpl.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.memory;

import java.util.Random;

import ch.zhaw.lazari.cpu.api.Memory;

/**
 * Simple Memory Implementation
 */
public class SimpleMemoryImpl implements Memory {
	
	private final byte[] storage;
	
	/** Creates a new memory with the default size */
	public SimpleMemoryImpl() {
		this(DEFAULT_SIZE);
	}
	
	/**
	 * Creates a new memory filled with random bytes in the given size
	 * @param sizeInBytes
	 */
	public SimpleMemoryImpl(final int sizeInBytes) {
		storage = new byte[sizeInBytes];
		new Random().nextBytes(storage);
	}
	
	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Memory#store(int, byte)
	 */
	@Override
	public void store(int address, byte value) {
		if(isValid(address)) {
			storage[address] = value;
		} else {
			throw new InvalidMemoryAddressException(address, storage.length);
		}
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Memory#load(int)
	 */
	@Override
	public byte load(int address) {
		if(isValid(address)) {
			return storage[address];
		} else {
			throw new InvalidMemoryAddressException(address, storage.length);			
		}
	}

	private boolean isValid(final int address) {
		return (address >= 0) && (address < storage.length);
	}
}
