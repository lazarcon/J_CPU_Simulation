package ch.zhaw.lazari.cpu.gui;

import java.awt.Rectangle;

import ch.zhaw.lazari.cpu.api.Memory;
import ch.zhaw.lazari.cpu.api.ProgramCounter;
import ch.zhaw.lazari.cpu.impl.InstructionSet2ByteWord;
import ch.zhaw.lazari.cpu.impl.UnknownCommandException;
import ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils;

public class CommandMemoryWindow extends MemoryWindow {

	private ProgramCounter programCounter;

	public CommandMemoryWindow(Memory memory, ProgramCounter programCounter,
			int min, int max) {
		super(memory, min, max);
		this.programCounter = programCounter;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void tick() {
		int row = 0;
		for (int index = min; index < max; index += 2) {
			Object[] data = getRow(index);

			int adress = (int) data[0];
			if (adress == programCounter.get()) {
				table.setRowSelectionInterval(row, row);
				table.getSelectionModel().setSelectionInterval(row, row);
				table.scrollRectToVisible(new Rectangle(table.getCellRect(row,
						0, true)));
			}
			table.setValueAt(data[1], row, 1);
			table.setValueAt(data[2], row, 2);
			table.setValueAt(data[3], row, 3);
			row++;
		}
		table.repaint();
	}

	protected Object[] getRow(int index) {

		String word1 = BooleanArrayUtils.toBinaryString(memory.load(index));
		String word2 = BooleanArrayUtils.toBinaryString(memory.load(index + 1));
		final Object[] row = new Object[4];
		row[0] = index;
		row[1] = word1;
		row[2] = word2;

		try {
			row[3] = InstructionSet2ByteWord.createFromBits(word1 + word2);
		} catch (UnknownCommandException e) {
			row[3] = "???";
		}

		return row;
	}

}
