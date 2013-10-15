/*
 * File: 		Swdd.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import ch.zhaw.lazari.cpu.api.Memory;
import ch.zhaw.lazari.cpu.api.Register;

/**
 * Responsibility:
 */
public class SWDD extends AbstractMemoryCommand {

	private final Register register;
	
	private int address;
	
	/**
	 * @param memory
	 */
	public SWDD(final Memory memory, final Register register, final int address) {
		super(memory);
		this.register = register;
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log.trace("Storing from register to memory");
		for(byte toStore : register.get()) {
			memory.store(address++, toStore);
		}
	}

}
