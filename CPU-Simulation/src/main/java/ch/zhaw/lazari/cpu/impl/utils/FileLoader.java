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
	
	/**
	 * Loads the contents of <code>filename</code> into a list of strings, where each entry represents a line in the file
	 * @param filename name of file to load
	 * @return List of Strings contained in that file
	 * The list will be empty, if the file could not be read.
	 */
	public List<String> load(final String filename) {
		final List<String> lines = new LinkedList<String>();
		DataInputStream in = null;
		BufferedReader br = null;
		try{
			FileInputStream fstream = new FileInputStream(filename);
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = br.readLine()) != null)   {
				lines.add(line);
			}
			in.close();
		} catch (final Exception exception){
			LOG.error(String.format("Could not load file %s. Reason", filename), exception);
		} finally {
			if(br != null) {
				try {
				br.close();
				} catch (final Exception exception) {
					LOG.error("Could not close buffered reader", exception);
				}
			}
		}
		return lines;
	}
}
