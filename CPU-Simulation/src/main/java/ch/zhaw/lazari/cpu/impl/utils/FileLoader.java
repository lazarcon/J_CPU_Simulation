/*
 * File: 		FileLoader.java
 * Date: 		Oct 29, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.impl.utils;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads a file into memory
 */
public class FileLoader {
	
	private static final Logger LOG = LoggerFactory.getLogger(FileLoader.class);
	
	public List<String> load(final String filename) {
		final List<String> lines = new LinkedList<String>();
		try{
			final FileInputStream fstream = new FileInputStream(filename);
			final DataInputStream in = new DataInputStream(fstream);
			final BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null)   {
				lines.add(line);
			}
			in.close();
		} catch (final Exception exception){
			LOG.error(String.format("Could not load file %s. Reason", filename), exception);
		}
		return lines;
	}
}
