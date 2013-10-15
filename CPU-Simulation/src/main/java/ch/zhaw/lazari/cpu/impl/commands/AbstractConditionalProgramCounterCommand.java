/*
 * File: 		AbstractConditionalProgramCounterCommand.java
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
public abstract class AbstractConditionalProgramCounterCommand extends AbstractProgramCounterCommand{

	protected final Accumulator accu;
	
	/**
	 * @param programCounter
	 */
	public AbstractConditionalProgramCounterCommand(
			final ProgramCounter programCounter, final Accumulator accu) {
		super(programCounter);
		this.accu = accu;
	}
	
	@Override
	public final void execute() {
		log.trace("Executing conditional jump");
		if(shouldJump()) {
			jump(getAddress());
		} else {
			programCounter.next();
		}
	}
	
	private void jump(byte[] address) {
		programCounter.set(ByteArrayUtils.toInt(address));
	}
	
	protected abstract boolean shouldJump();
	
	protected abstract byte[] getAddress();

}
