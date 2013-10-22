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

import ch.zhaw.lazari.cpu.api.ArithmeticLogicalAccumulator;
import ch.zhaw.lazari.cpu.api.ProgramCounter;

/**
 * Provide template <code>execute</code> method for conditional jump commands
 */
public abstract class AbstractConditionalProgramCounterCommand extends AbstractProgramCounterCommand{

	private final ArithmeticLogicalAccumulator accu;
	
	public AbstractConditionalProgramCounterCommand(
			final ProgramCounter programCounter, final ArithmeticLogicalAccumulator accu) {
		super(programCounter);
		this.accu = accu;
	}
	
	protected ArithmeticLogicalAccumulator getAccu() {
		return accu;
	}
	
	@Override
	public final void execute() {
		log(String.format("Executing conditional jump to address '%d'", getAddress()));
		if(shouldJump()) {
			jump(getAddress());
		}
	}
	
	private void jump(int address) {
		getProgramCounter().set(address);
	}
	
	protected abstract boolean shouldJump();
	
	protected abstract int getAddress();

}
