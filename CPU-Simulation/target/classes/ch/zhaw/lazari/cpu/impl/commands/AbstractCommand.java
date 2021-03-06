/*
 * File: 		AbstractCommand.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.zhaw.lazari.cpu.api.Command;

/**
 * Provide logging for all child classes
 */
public abstract class AbstractCommand implements Command {

	private static final String FORMAT = "\t\t%s";
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	protected void log(final String message) {
		log.trace(String.format(FORMAT, message));
	}
	
}
