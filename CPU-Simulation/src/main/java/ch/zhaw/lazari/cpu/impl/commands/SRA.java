/*
 * File: 		SRA.java
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
public class SRA extends AbstractAccumulatorCommand {

	/**
	 * @param accu
	 */
	public SRA(final ArithmeticLogicalAccumulator accu) {
		super(accu);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log("Telling accumulator to arithmetical right shift its content.");
		getAccu().shiftRightArithmetic();
	}

	@Override
	public String toString() {
		return "SRA";
	}
}
