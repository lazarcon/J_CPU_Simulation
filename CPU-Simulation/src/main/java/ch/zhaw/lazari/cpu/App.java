/*
 * File: 		App.java
 * Date: 		Oct 15, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.cli.*;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.zhaw.lazari.cpu.api.*;
import ch.zhaw.lazari.cpu.gui.CPUSimulation;
import ch.zhaw.lazari.cpu.impl.ProgramLoader2ByteInstructionSet;
import ch.zhaw.lazari.cpu.impl.SimpleCPUImpl;
import ch.zhaw.lazari.cpu.impl.memory.SimpleMemoryImpl;
import ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils;
import ch.zhaw.lazari.cpu.impl.utils.FileLoader;

/**
 * Simulates a CPU
 */
public final class App {
	
	private static final Logger LOG = LoggerFactory.getLogger(App.class);
	
	private App() {
		// Avoid instantiation
	}
	
    public static void main( String[] args ) {
    	final CommandLineParser parser = new BasicParser();
    	try {
			final CommandLine cmd = parser.parse(createOptions(), args);
			if(cmd.hasOption('v')) {
				LogManager.getRootLogger().setLevel(Level.TRACE);
			}
			
			if(!cmd.hasOption('f')) {
				LOG.error("ERROR: A filename is required");
				System.exit(0);
			}

			int resultAddress = 0;
			if(cmd.hasOption('r')) {
				try {
					resultAddress = Integer.parseInt(cmd.getOptionValue('r'));
				} catch (final Exception exception) {
					LOG.error("could not interpret result address", exception);
					System.exit(0);
				}
			}
			
			final FileLoader loader = new FileLoader();
			final Memory memory = new SimpleMemoryImpl();
			final List<String> code = loader.load(cmd.getOptionValue('f'));
			final ProgramLoader programLoader = new ProgramLoader2ByteInstructionSet();
			programLoader.load(code, memory);
			
			
			final CPU cpu = new SimpleCPUImpl(memory);
			final CPUSimulation simulation = new CPUSimulation(cpu);
			
			if(cmd.hasOption('s')) {
			//	runStepwise(cpu);
			} else {
		//		runThrough(cpu);
			}

			if(cmd.hasOption('r')) {
				final boolean[] left = memory.load(resultAddress);
				final boolean[] right = memory.load(resultAddress + 1);
				final boolean[] combined = new boolean[left.length + right.length];
				for(int index = 0; index < left.length; ++index) {
					combined[index] = left[index];
					combined[index + Byte.SIZE] = right[index];
				}
				LOG.info(String.format("value in memory %d is: %d", resultAddress, BooleanArrayUtils.toInt(combined)));
			}
			//System.exit(0);
			
		} catch (ParseException e) {
			System.out.println("Exception while parsing arguments:");
			e.printStackTrace();
		}
    	
    	// load file from arguments
    	// create array of bytes from file
    	// execute file
    }
    
    private static Options createOptions() {
    	final Options options = new Options();
    	options.addOption("f", true, "name of menomic file to run");
    	options.addOption("s", false, "run in step mode");
    	options.addOption("v", false, "run in verbose mode");
    	options.addOption("r", true, "address where the result is expected");
    	return options;
    }
    
    private static void runStepwise(final CPU cpu) {
    	LOG.info("Running Stepwise");
    	cpu.start();
    	@SuppressWarnings("resource")
		final Scanner keyboard = new Scanner(System.in);
    	while(!cpu.isFinished()) {
    		keyboard.hasNextLine();
    		cpu.tick();
    		
    	}
    }
    
    private static void runThrough(final CPU cpu) {
    	LOG.info("Running through");
    	cpu.start();
    	while(!cpu.isFinished()) {
    		cpu.tick();
    	}
    }
}
