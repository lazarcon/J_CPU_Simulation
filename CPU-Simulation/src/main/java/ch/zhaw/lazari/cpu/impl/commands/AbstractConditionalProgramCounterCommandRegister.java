/*
 * File: 		AbstractConditionalProgramCounterCommandRegister.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import ch.zhaw.lazari.cpu.api.*;

/**
 * Responsibility:
 */
public abstract class AbstractConditionalProgramCounterCommandRegister extends AbstractConditionalProgramCounterCommand {

	protected final Register register;
	
	/**
	 * @param programCounter
	 * @param accu
	 */
	public AbstractConditionalProgramCounterCommandRegister(
			final ProgramCounter programCounter, final Accumulator accu, final Register register) {
		super(programCounter, accu);
		this.register = register;
	}

	@Override
	protected final byte[] getAddress() {
		return register.get();
	}
	
}
