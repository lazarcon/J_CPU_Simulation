/*
 * File: 		Inc2ByteImpl.java
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
 * Increments the accumulator by 1
 */
public class INC extends AbstractAccumulatorCommand {
	
	public INC(final Accumulator accu) {
		super(accu);
	}
	
	@Override
	public void execute() {
		log.trace("Incrementing accumlator");
		accu.increment();
	}

}