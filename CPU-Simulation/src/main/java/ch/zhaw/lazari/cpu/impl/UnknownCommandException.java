/*
 * File: 		UnknownCommandException.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl;


/**
 * Represents an unknown command
 */
public class UnknownCommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String word;
	
	public UnknownCommandException(final String word) {
		super();
		this.word = word;
	}
	
	@Override
	public String getMessage() {
		return String.format("The command '%s' could not be interpreted.", word);
	}
}
