/*
 * File: 		NOT.java
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

/**
 * Responsibility:
 */
public class NOT extends AbstractAccumulatorCommand {

	/**
	 * @param accu
	 */
	public NOT(final Accumulator accu) {
		super(accu);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log.trace("Executing not operation on accumulator");
		accu.not();
	}

}
