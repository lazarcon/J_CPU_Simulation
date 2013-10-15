/*
 * File: 		AbstractProgramCounterCommand.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import ch.zhaw.lazari.cpu.api.ProgramCounter;

/**
 * Responsibility:
 */
public abstract class AbstractProgramCounterCommand extends AbstractCommand{

	protected final ProgramCounter programCounter;
	
	public AbstractProgramCounterCommand(final ProgramCounter programCounter) {
		this.programCounter = programCounter;
	}

}
