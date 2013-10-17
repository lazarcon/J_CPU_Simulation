/*
 * File: 		BC.java
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
public class BC extends AbstractConditionalProgramCounterCommandRegister {

	/**
	 * @param programCounter
	 * @param accu
	 */
	public BC(final ProgramCounter programCounter, final ArithmeticLogicalAccumulator accu, final Register register) {
		super(programCounter, accu, register);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.impl.commands.AbstractConditionalProgramCounterCommand#shouldJump()
	 */
	@Override
	protected boolean shouldJump() {
		return (getAccu().getCarryFlag() == 1);
	}

}
