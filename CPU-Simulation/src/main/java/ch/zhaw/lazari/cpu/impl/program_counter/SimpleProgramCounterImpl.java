/*
 * File: 		SimpleProgramCounterImpl.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.program_counter;

import ch.zhaw.lazari.cpu.api.ProgramCounter;

/**
 * Responsibility:
 */
public class SimpleProgramCounterImpl implements ProgramCounter{


	
	private final int stepSize;

	private int address;
	
	/**
	 * Creates a new ProgrammCounter with the given offset and stepsize
	 * @param offset Memory offset, where the programm is stored
	 * @param stepSize increment for the next address
	 */
	public SimpleProgramCounterImpl(int initialAdress, final int stepSize) {
	
		this.stepSize = stepSize;
		this.address = initialAdress;
	}
	
	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.ProgramCounter#set(int)
	 */
	@Override
	public void set(int address) {
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.ProgramCounter#get()
	 */
	@Override
	public int get() {
		return address;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.ProgramCounter#next()
	 */
	@Override
	public void next() {
		address += stepSize;
	}

}
