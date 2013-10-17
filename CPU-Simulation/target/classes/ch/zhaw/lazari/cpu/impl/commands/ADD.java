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

import ch.zhaw.lazari.cpu.api.Accumulator;
import ch.zhaw.lazari.cpu.api.Register;

/**
 * Responsibility:
 */
public class ADD extends AbstractAccumulatorCommand {

	private final Register register;
	
	public ADD(final Accumulator accu, final Register register) {
		super(accu);
		this.register = register;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log.trace("Adding value from register to accumulator");
		accu.add(register.get());
	}

}