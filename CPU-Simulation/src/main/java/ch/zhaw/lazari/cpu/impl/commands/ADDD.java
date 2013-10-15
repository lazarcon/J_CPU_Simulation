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

import ch.zhaw.lazari.cpu.api.Accumulator;

/**
 * Responsibility:
 */
public class ADDD extends AbstractAccumulatorCommand {

	private final byte[] word;
	
	/**
	 * @param accu
	 */
	public ADDD(final Accumulator accu, final byte[] word) {
		super(accu);
		this.word = word;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log.trace("Adding direct");
		// TODO handle 15 bits rule
		accu.add(word);
	}

}
