/*
 * File: 		AbstractMemoryCommand.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import ch.zhaw.lazari.cpu.api.Memory;

/**
 * Provides the memory
 */
public abstract class AbstractMemoryCommand extends AbstractCommand {

	protected final Memory memory;
	
	public AbstractMemoryCommand(final Memory memory) {
		this.memory = memory;
	}
}
