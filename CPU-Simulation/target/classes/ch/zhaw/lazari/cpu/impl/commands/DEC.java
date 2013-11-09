/*
 * File: 		Dec2ByteImpl.java
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
 * Decrements the accumulator
 */
public class DEC extends AbstractAccumulatorCommand {
	
	public DEC(final ArithmeticLogicalAccumulator accu) {
		super(accu);
	}
	
	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log("Telling accu to decrement");
		getAccu().decrement();
	}

	@Override
	public String toString() {
		return "DEC";
	}

}
