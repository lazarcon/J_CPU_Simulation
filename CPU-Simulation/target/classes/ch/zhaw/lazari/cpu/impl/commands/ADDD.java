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

import ch.zhaw.lazari.cpu.api.ArithmeticLogicalAccumulator;
import ch.zhaw.lazari.cpu.impl.ByteArrayUtils;

/**
 * Adds a Word to the Accumulator
 */
public class ADDD extends AbstractAccumulatorCommand {
	
	private final byte[] word;
	
	/**
	 * Creates a new ADDD Command using the accumulator and word
	 * @param accu The Accumulator to use
	 * @param word the word to add
	 */
	public ADDD(final ArithmeticLogicalAccumulator accu, final byte[] word) {
		super(accu);
		this.word = word.clone();
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		final String shortend = ByteArrayUtils.toString(word).substring(1);
		final String processed = getProcessed(shortend);
		getLog().trace(String.format("Adding value '%s' (direct) to accumulator.", processed));
		getAccu().add(ByteArrayUtils.fromInt(Integer.parseInt(processed, ByteArrayUtils.RADIX_BINARY), word.length));
	}

	private String getProcessed(final String toProcess) {
		final int value = Integer.parseInt(toProcess, ByteArrayUtils.RADIX_BINARY);
		return (value < 0) ? "1" + toProcess : "0" + toProcess;
	}
}
