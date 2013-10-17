/*
 * File: 		AddRegister.java
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
import ch.zhaw.lazari.cpu.api.Register;
import ch.zhaw.lazari.cpu.impl.ByteArrayUtils;

/**
 * Adds Register Content to Accumulator
 */
public final class ADD extends AbstractAccumulatorCommand {

	private final Register register;
	
	/**
	 * Creates a new Add Command for Accumulator, using the register
	 * @param accu Accumulator to use
	 * @param register Register to use
	 */
	public ADD(final ArithmeticLogicalAccumulator accu, final Register register) {
		super(accu);
		this.register = register;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		getLog().trace(String.format("\t\tAdding value '%s' (from register) to accumulator.", ByteArrayUtils.toString(register.get())));
		getAccu().add(register.get());
	}

}
