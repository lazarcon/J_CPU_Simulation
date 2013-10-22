/*
 * File: 		OR.java
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
import ch.zhaw.lazari.cpu.impl.utils.ByteArrayUtils;

/**
 * Responsibility:
 */
public class OR extends AbstractAccumulatorCommand {

	private final Register register;
	
	/**
	 * @param accu
	 */
	public OR(final ArithmeticLogicalAccumulator accu, final Register register) {
		super(accu);
		this.register = register;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log(String.format("Telling accumulator to perform OR operation with value '%s'", ByteArrayUtils.toBinaryString(register.get())));
		getAccu().or(register.get());
	}

}
