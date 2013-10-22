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

import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toBinaryString;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.zhaw.lazari.cpu.api.Register;

/**
 * Simple implementation of a register component
 */
public class SimpleRegisterImpl implements Register {

	private static final String FORMAT = "\t\t\t%s";
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private boolean[] bits;
		
	/**
	 * Creates a new SimpleRegister for words of the given length
	 * Default stored bits are random
	 * @param wordLength number of bits per word
	 */
	public SimpleRegisterImpl(final int wordLength) {
		bits = new boolean[wordLength];
		for(int index = 0; index < wordLength; ++index) {
			bits[index] = new Random().nextBoolean();
		}
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Register#set(byte[])
	 */
	@Override
	public void set(boolean[] bits) {
		if(isValid(bits)) {
			log(String.format("Storing '%s'.", toBinaryString(bits.clone())));
			this.bits = bits.clone();
		} else {
			log.error("Was told to store word of invalid length.");
			throw new InvalidRegisterAccessException(bits.length, getSize());
		}		
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Register#get()
	 */
	@Override
	public boolean[] get() {
		log(String.format("Returning '%s'.", toBinaryString(bits.clone())));
		return this.bits.clone();
	}
	
	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Register#clear()
	 */
	@Override
	public void clear() {
		log("Cleared contents.");
		this.bits = new boolean[bits.length];
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Register#getSize()
	 */
	@Override
	public int getSize() {
		return bits.length;
	}
	
	protected boolean isValid(final boolean[] bits) {
		return (this.bits.length == bits.length);
	}
		
	protected void log(final String message) {
		log.trace(String.format(FORMAT, message));
	}
}
