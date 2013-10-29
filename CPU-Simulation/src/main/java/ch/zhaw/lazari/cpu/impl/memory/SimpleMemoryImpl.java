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
import ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils;
import ch.zhaw.lazari.cpu.impl.utils.InvalidArgumentException;

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
	public void store(int address, boolean[] bits) {
		if(!isValid(address)) {
			throw new InvalidMemoryAddressException(address, storage.length);
		} else if (bits.length != WORD_SIZE){
			throw new InvalidArgumentException(String.format("Bits to store (%d) are of invalid length (valid = %d)", bits.length, Byte.SIZE));
		}
		storage[address] = (byte) BooleanArrayUtils.toInt(bits);

	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Memory#load(int)
	 */
	@Override
	public boolean[] load(int address) {
		if(isValid(address)) {
			return BooleanArrayUtils.fromInt(storage[address], WORD_SIZE);
		} else {
			throw new InvalidMemoryAddressException(address, storage.length);			
		}
	}

	private boolean isValid(final int address) {
		return (address >= 0) && (address < storage.length);
	}
}
