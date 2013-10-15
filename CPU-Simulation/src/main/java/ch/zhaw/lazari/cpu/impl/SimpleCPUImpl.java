/*
 * File: 		SimpleCPUImpl.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.zhaw.lazari.cpu.api.*;
import ch.zhaw.lazari.cpu.impl.commands.END;
import ch.zhaw.lazari.cpu.impl.commands.INC;
import ch.zhaw.lazari.cpu.impl.program_counter.SimpleProgramCounterImpl;
import ch.zhaw.lazari.cpu.impl.register.SimpleAccumulatorImpl;
import ch.zhaw.lazari.cpu.impl.register.SimpleRegisterImpl;

/**
 * Simple Implementation of a central processing unit
 */
public class SimpleCPUImpl implements CPU {

	private static final Logger LOG = LoggerFactory.getLogger(CPU.class);
	
	private static final int DEFAULT_WORD_LENGTH = 2;
	
	private static final int REGISTERS = 3;
	
	private final Accumulator accu = new SimpleAccumulatorImpl(DEFAULT_WORD_LENGTH);
	
	private final Register[] registers = new Register[REGISTERS + 1];
	
	private final ProgramCounter programCounter = new SimpleProgramCounterImpl(0, DEFAULT_WORD_LENGTH);;
	
	private final Memory memory;

	private Command instruction;
	
	/**
	 * Creates a new CPU using the passed components
	 * @param memory The memory where program code and data is stored
	 */
	public SimpleCPUImpl(
			final Memory memory) {
		this.memory = memory;
		this.registers[0] = accu; 
		for(int index = 1; index < registers.length; ++index) {
			registers[index] = new SimpleRegisterImpl(DEFAULT_WORD_LENGTH);
		}
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.CPU#tick()
	 */
	@Override
	public void tick() {
		LOG.trace("recieved tick ... executing instructions");
		final byte[] command = getCommandWord();
		instruction = interpret(command);
		instruction.execute();
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.CPU#isFinished()
	 */
	@Override
	public boolean isFinished() {
		return (instruction == null || instruction instanceof END);
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.CPU#getRegister(int)
	 */
	@Override
	public Register getRegister(final int index) {
		return registers[index];
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.CPU#getMemory()
	 */
	@Override
	public Memory getMemory() {
		return memory;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.CPU#getProgramCounter()
	 */
	@Override
	public ProgramCounter getProgramCounter() {
		return programCounter;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.CPU#getAccumulator()
	 */
	@Override
	public Accumulator getAccumulator() {
		return accu;
	}
	
	private byte[] getCommandWord() {
		final byte[] word = new byte[DEFAULT_WORD_LENGTH];
		int address = programCounter.get();
		for(int index = 0; index < DEFAULT_WORD_LENGTH; ++index) {
			word[index] = memory.load(address++);	
		}
		return word;
	}
		
	private Command interpret(final byte[] word) {
		if(word[0] == 1) {
			return new INC(accu);
		} else if(word[0] == 0 && word[1] == 0) {
			return new END();
		} else {
			throw new UnknownCommandException(word);
		}
	}
}
