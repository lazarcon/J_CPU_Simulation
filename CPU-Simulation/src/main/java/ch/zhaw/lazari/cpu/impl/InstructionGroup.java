/*
 * File: 		InstructionGroup.java
 * Date: 		Oct 17, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl;

/**
 * Defines available instruction groups
 */
public enum InstructionGroup {

	REGISTER,
	ACCU,
	MEMORY,
	ARITHMETIC,
	LOGIC,
	PROGRAM_COUNTER,
	CPU
}
