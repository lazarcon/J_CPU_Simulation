/*
 * File: 		Swdd.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import static ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils.toBinaryString;
import ch.zhaw.lazari.cpu.api.Memory;
import ch.zhaw.lazari.cpu.api.Register;

/**
 * Responsibility:
 */
public class SWDD extends AbstractMemoryCommand {

	private final Register register;
	
	private int address;
	
	/**
	 * @param memory
	 */
	public SWDD(final Memory memory, final Register register, final int address) {
		super(memory);
		this.register = register;
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log(String.format("Telling memory to store '%s' at address %d", toBinaryString(register.get()), address));
		final int memoryWords = register.getSize() / Memory.WORD_SIZE;
		final boolean[] registerWord = register.get();
		for(int word = 0; word < memoryWords; ++word) {
			final boolean[] memoryWord = new boolean[Memory.WORD_SIZE];
			for(int index = 0; index < memoryWord.length; ++index) {
				memoryWord[index] = registerWord[word * Memory.WORD_SIZE + index];
			}
			getMemory().store(address++, memoryWord);
		}
	}
	
	@Override
	public String toString() {
		return String.format("SWDD R%d #%d", register.getId(), address);
	}


}
