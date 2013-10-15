/*
 * File: 		AbstractConditionalProgramCounterCommandAddress.java
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

/**
 * Responsibility:
 */
public abstract class AbstractConditionalProgramCounterCommandAddress extends AbstractConditionalProgramCounterCommand {

	private final byte[] address;
	
	/**
	 * @param programCounter
	 * @param accu
	 */
	public AbstractConditionalProgramCounterCommandAddress(
			final ProgramCounter programCounter, final Accumulator accu, final byte[] address) {
		super(programCounter, accu);
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.impl.commands.AbstractConditionalProgramCounterCommand#getAddress()
	 */
	@Override
	protected final byte[] getAddress() {
		return address;
	}

}
