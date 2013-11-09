package ch.zhaw.lazari.cpu.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import ch.zhaw.lazari.cpu.api.Memory;
import ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils;

public class MemoryWindow extends JPanel implements TickablePanel{

	private static final long serialVersionUID = 1L;
	private static final String[] HEADERS = {"Adresse", "Bits", "Dezimal"};
	private JTable table;
	private final Memory memory;
	private final int min;
	private final int max;
	
	/**
	 * Create the frame.
	 */
	public MemoryWindow(final Memory memory, int min, int max) {
		this.memory = memory;
		this.min = min;
		this.max = max;
		table = getTable();
		add(new JLabel(String.format("Memory (%d - %d)", min, max)));
		add(table, BorderLayout.CENTER);
	}

	@Override
	public void tick() {
		int row = 0;
		for(int index = min; index < max; ++index) {
			Object[] data = getRow(index);
			table.setValueAt(data[1], row, 1);
			table.setValueAt(data[2], row++, 2);
		}	
		table.repaint();
	}
	
	private JTable getTable() 
	{
		final Object[][] data = new Object[max-min][3];
		int row = 0;
		for(int index = min; index < max; ++index) {
			data[row++] = getRow(index);
		}
		return new JTable(data, HEADERS);
	}
	
	private Object[] getRow(int index) 
	{
		final Object[] row = new Object[3];
		row[0] = index;
		row[1] = BooleanArrayUtils.toBinaryString(memory.load(index));
		row[2] = BooleanArrayUtils.toInt(memory.load(index));
		return row;
	}

	
}
