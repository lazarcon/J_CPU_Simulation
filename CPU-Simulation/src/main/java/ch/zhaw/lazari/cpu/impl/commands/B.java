/*
 * File: 		B.java
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
import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toInt;
import ch.zhaw.lazari.cpu.api.ProgramCounter;
import ch.zhaw.lazari.cpu.api.Register;

/**
 * Responsibility:
 */
public class B extends AbstractProgramCounterCommand {

	private final Register register;
		
	/**
	 * @param programCounter
	 */
	public B(final ProgramCounter programCounter, final Register register) {
		super(programCounter);
		this.register = register;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log(String.format("Telling program counter to set its value to '%s'.", toBinaryString(register.get())));
		getProgramCounter().set(toInt(register.get()));
	}

	@Override
	public String toString() {
		return String.format("B R%d", register.getId());
	}

}
