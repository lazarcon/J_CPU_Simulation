/*
 * File: 		BD.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import ch.zhaw.lazari.cpu.api.ProgramCounter;
import ch.zhaw.lazari.cpu.impl.ByteArrayUtils;

/**
 * Responsibility:
 */
public class BD extends AbstractProgramCounterCommand {

	private final byte[] address;
	
	/**
	 * @param programCounter
	 */
	public BD(final ProgramCounter programCounter, final byte[] address) {
		super(programCounter);
		this.address = address.clone();
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log.trace("Executing unconditional direct jump");
		programCounter.set(ByteArrayUtils.toInt(address));
	}

}
