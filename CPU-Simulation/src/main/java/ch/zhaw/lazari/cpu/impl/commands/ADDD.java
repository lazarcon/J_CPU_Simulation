/*
 * File: 		AddDirect.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.fromInt;
import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toBinaryString;
import ch.zhaw.lazari.cpu.api.ArithmeticLogicalAccumulator;

/**
 * Adds a Word to the Accumulator
 */
public class ADDD extends AbstractAccumulatorCommand {
	
	private final boolean[] word;
	
	/**
	 * Creates a new ADDD Command using the accumulator and word
	 * @param accu The Accumulator to use
	 * @param word the word to add
	 */
	public ADDD(final ArithmeticLogicalAccumulator accu, final boolean[] word) {
		super(accu);
		this.word = word.clone();
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		final String shortend = toBinaryString(word).substring(1);
		final String processed = getProcessed(shortend);
		log(String.format("Telling Accu to add value '%s' (direct).", processed));
		getAccu().add(fromInt(Integer.parseInt(processed, 2), word.length));
	}

	private String getProcessed(final String toProcess) {
		final int value = Integer.parseInt(toProcess, 2);
		return (value < 0) ? "1" + toProcess : "0" + toProcess;
	}
}
