/*
 * File: 		SimpleRegisterImpl.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.register;

import java.util.Random;

import ch.zhaw.lazari.cpu.api.Register;

/**
 * Simple implementation of a register component
 */
public class SimpleRegisterImpl implements Register {

	private byte[] word;
	
	/**
	 * Creates a new SimpleRegisterImpl with a random stored word
	 * and the default word length
	 */
	public SimpleRegisterImpl() {
		this(DEFAULT_WORD_LENGTH);
	}
	
	/**
	 * Creates a new SimpleRegister for words of the given length
	 * @param wordLength number of bytes per word
	 */
	public SimpleRegisterImpl(final int wordLength) {
		word = new byte[wordLength];
		new Random().nextBytes(word);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Register#set(byte[])
	 */
	@Override
	public void set(byte[] word) {
		if(isValid(word)) {
			this.word = word.clone();
		} else {
			throw new InvalidRegisterAccessException(word.length, this.word.length);
		}		
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Register#get()
	 */
	@Override
	public byte[] get() {
		return this.word.clone();
	}
	
	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Register#clear()
	 */
	@Override
	public void clear() {
		this.word = new byte[this.word.length];
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Register#getSize()
	 */
	@Override
	public int getSize() {
		return word.length;
	}
	
	private boolean isValid(final byte[] word) {
		return (this.word.length == word.length);
	}
}