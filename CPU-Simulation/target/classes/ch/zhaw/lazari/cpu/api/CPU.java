/*
 * File: 		CPU.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.api;


/**
 * Represents a central processing unit
 */
public interface CPU {
	
	/** Tells the cpu that it should start processing */
	void start();
	
	/** Tells the cpu that it should stop finishing */
	void stop();
	
	/** Tells the CPU to execute the current instruction */
	void tick();
	
	/** Returns true, if there are no more instructions to execute */
	boolean isFinished();
	
	/** Returns the register with the given index */
	Register getRegister(final int index);
	
	Register[] getRegisters();
	
	/** Returns the memory used by the CPU */
	Memory getMemory();
	
	/** Returns the program counter used by the cpu */
	ProgramCounter getProgramCounter();
	
	/** Returns the accumulator used by the cpu */
	LogicalAccumulator getAccumulator();
	
	Command getCurrentCommand();

	String getCommandWord();
}
