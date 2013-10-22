/*
 * File: 		AND.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toBinaryString;
import ch.zhaw.lazari.cpu.api.ArithmeticLogicalAccumulator;
import ch.zhaw.lazari.cpu.api.Register;

/**
 * Represents the AND Operation
 */
public class AND extends AbstractAccumulatorCommand {

	private final Register register;
	
	/**
	 * Creates a new AND Operation using Accumulator and Register
	 * @param accu Accumulator to use
	 * @param register Register to use
	 */
	public AND(final ArithmeticLogicalAccumulator accu, final Register register) {
		super(accu);
		this.register = register;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log(String.format("Telling accu to perform AND operation with value '%s'", toBinaryString(register.get())));
		getAccu().and(register.get());
	}

}
