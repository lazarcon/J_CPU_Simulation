/*
 * File: 		AbstractAccumulatorCommand.java
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

/**
 * Provides the accumulator
 */
public abstract class AbstractAccumulatorCommand extends AbstractCommand {

	private final ArithmeticLogicalAccumulator accu;
	
	/**
	 * Creates a template command, knowning the accu
	 * @param accu Accumulator to use
	 */
	public AbstractAccumulatorCommand(final ArithmeticLogicalAccumulator accu) {
		this.accu = accu;
	}
	
	protected ArithmeticLogicalAccumulator getAccu() {
		return accu;
	}
}
