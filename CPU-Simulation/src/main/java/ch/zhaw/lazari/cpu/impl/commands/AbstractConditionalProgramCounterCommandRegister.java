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
import ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils;

/**
 * Responsibility:
 */
public abstract class AbstractConditionalProgramCounterCommandRegister extends AbstractConditionalProgramCounterCommand {

	private final Register register;
	
	/**
	 * @param programCounter
	 * @param accu
	 */
	public AbstractConditionalProgramCounterCommandRegister(
			final ProgramCounter programCounter, final ArithmeticLogicalAccumulator accu, final Register register) {
		super(programCounter, accu);
		this.register = register;
	}

	protected Register getRegister() {
		return register;
	}
	
	@Override
	protected final int getAddress() {
		return ByteArrayUtils.toInt(register.get());
	}
	
}
