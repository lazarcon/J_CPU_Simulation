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

/**
 * Responsibility:
 */
public class BD extends AbstractProgramCounterCommand {

	private final int address;
	
	/**
	 * @param programCounter
	 */
	public BD(final ProgramCounter programCounter, final int address) {
		super(programCounter);
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log(String.format("Telling program counter to set its value to '%d'", address));
		getProgramCounter().set(address);
	}

	@Override
	public String toString() {
		return String.format("BD #%d", address);
	}

}
