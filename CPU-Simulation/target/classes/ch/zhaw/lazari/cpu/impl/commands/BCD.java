/*
 * File: 		BCD.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import ch.zhaw.lazari.cpu.api.ArithmeticLogicalAccumulator;
import ch.zhaw.lazari.cpu.api.ProgramCounter;

/**
 * Responsibility:
 */
public class BCD extends AbstractConditionalProgramCounterCommandAddress {

	/**
	 * @param programCounter
	 * @param accu
	 * @param address
	 */
	public BCD(ProgramCounter programCounter, ArithmeticLogicalAccumulator accu, int address) {
		super(programCounter, accu, address);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.impl.commands.AbstractConditionalProgramCounterCommand#shouldJump()
	 */
	@Override
	protected boolean shouldJump() {
		return getAccu().getCarryFlag() == 1;
	}

	@Override
	public String toString() {
		return String.format("BCD #", getAddress());
	}

}
