/*
 * File: 		Lwdd.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toBinaryString;
import ch.zhaw.lazari.cpu.api.Memory;
import ch.zhaw.lazari.cpu.api.Register;

/**
 * Responsibility:
 */
public class LWDD extends AbstractMemoryCommand {

	private final Register register;
	
	private int address;
	
	/**
	 * @param memory
	 */
	public LWDD(final Memory memory, final Register register, final int address) {
		super(memory);
		this.register = register;
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		final boolean[] word = new boolean[register.getSize()];
		int read = 0;
		while(read < word.length) {
			final boolean[] stored = getMemory().load(address++);
			for(final boolean bit : stored) {
				word[read++] = bit;
			}
		}
		log(String.format("Telling register to set its value to '%s' (from memory)", toBinaryString(word)));
		register.set(word);
	}

}
