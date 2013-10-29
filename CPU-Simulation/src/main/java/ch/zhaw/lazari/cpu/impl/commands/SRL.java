/*
 * File: 		SRL.java
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
 * Responsibility:
 */
public class SRL extends AbstractAccumulatorCommand {

	/**
	 * @param accu
	 */
	public SRL(final ArithmeticLogicalAccumulator accu) {
		super(accu);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log("Telling accumulator to logical right shift its content.");
		getAccu().shiftRightLogical();
	}
	
	@Override
	public String toString() {
		return "SRL";
	}

}
