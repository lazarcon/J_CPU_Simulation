/*
 * File: 		BZD.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import ch.zhaw.lazari.cpu.api.Accumulator;
import ch.zhaw.lazari.cpu.api.ProgramCounter;
import ch.zhaw.lazari.cpu.impl.ByteArrayUtils;

/**
 * Responsibility:
 */
public class BZD extends AbstractConditionalProgramCounterCommandAddress {

	/**
	 * @param programCounter
	 * @param accu
	 * @param address
	 */
	public BZD(final ProgramCounter programCounter, final Accumulator accu, final byte[] address) {
		super(programCounter, accu, address);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.impl.commands.AbstractConditionalProgramCounterCommand#shouldJump()
	 */
	@Override
	protected boolean shouldJump() {
		return ByteArrayUtils.toInt(accu.get()) == 0;
	}

}