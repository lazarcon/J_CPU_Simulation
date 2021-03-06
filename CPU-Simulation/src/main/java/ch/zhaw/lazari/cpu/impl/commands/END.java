/*
 * File: 		End2ByteImpl.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.commands;

import ch.zhaw.lazari.cpu.api.CPU;



/**
 * End Instruction
 */
public class END extends AbstractCommand {
	
	private final CPU cpu;
	
	public END(final CPU cpu) {
		this.cpu = cpu;
	}
	
	/* (non-Javadoc)
	 * @see ch.zhaw.lazari.cpu.api.Command#execute()
	 */
	@Override
	public void execute() {
		log("Telling CPU to stop.");
		cpu.stop();
	}
	
	@Override
	public String toString() {
		return "END";
	}
}
