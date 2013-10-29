/*
 * File: 		ProgramLoader.java
 * Date: 		Oct 29, 2013
 *
 * Copyright 2013 Constantin Lazari. All rights reserved.
 *
 * Unless required by applicable law or agreed to in writing, this software
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.
 */
package ch.zhaw.lazari.cpu.api;

import java.util.List;

/**
 * Responsibility:
 */
public interface ProgramLoader {

	/** Loads the menomic program-lines into memory */
	void load(final List<String> lines, final Memory memory);
}