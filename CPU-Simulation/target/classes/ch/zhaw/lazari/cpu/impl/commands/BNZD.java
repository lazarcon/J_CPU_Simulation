/*
 * File: 		BNZD.java
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
import ch.zhaw.lazari.cpu.impl.ByteArrayUtils;

/**
 * Responsibility:
 */
public class BNZD extends AbstractConditionalProgramCounterCommandAddress {

	/**
	 * @param programCounter
	 * @param accu
	 * @param address
	 */
	public BNZD(ProgramCounter programCounter, ArithmeticLogicalAccumulator accu, int address) {
		super(programCounter, accu, address);
	}

	@Override
	protected boolean shouldJump() {
		return ByteArrayUtils.toInt(getAccu().get()) != 0;
	}

}
