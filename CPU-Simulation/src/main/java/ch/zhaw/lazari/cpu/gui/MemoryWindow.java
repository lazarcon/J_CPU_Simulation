package ch.zhaw.lazari.cpu.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ch.zhaw.lazari.cpu.api.Memory;
import ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils;

public class MemoryWindow extends JPanel implements TickablePanel{

	private static final long serialVersionUID = 1L;
	protected static final String[] HEADERS = {"Adresse", "Bits1", "Bits2", "Dezimal"};
	protected JTable table;
	protected final Memory memory;
	protected final int min;
	protected final int max;


	/**
	 * Create the frame.
	 */
	public MemoryWindow(final Memory memory, int min, int max) {
		this.memory = memory;
		this.min = min;
		this.max = max;
		table = getTable();

		JScrollPane scroll = new JScrollPane(table);


		scroll.setPreferredSize(new Dimension(300,10000));

		setLayout(new BorderLayout());
		add(new JLabel(String.format("Memory (%d - %d)", min, max)),BorderLayout.NORTH);

		add(scroll, BorderLayout.CENTER);

	}

	@Override
	public void tick() {
		int row = 0;
		for(int index = min; index < max; index +=2) {
			Object[] data = getRow(index);


			table.setValueAt(data[1], row, 1);
			table.setValueAt(data[2], row, 2);
			table.setValueAt(data[3], row, 3);
			row++;
		}	
		table.repaint();
	}

	protected JTable getTable() 
	{
		final Object[][] data = new Object[max-min][4];
		int row = 0;
		for(int index = min; index < max; index += 2) {
			data[row++] = getRow(index);
		}
		return new JTable(data, HEADERS);
	}

	protected Object[] getRow(int index) 
	{

		String word1 = BooleanArrayUtils.toBinaryString(memory.load(index));
		String word2 = BooleanArrayUtils.toBinaryString(memory.load(index+1));
		final Object[] row = new Object[4];
		row[0] = index;
		row[1] = word1;
		row[2] = word2;

		row[3] = BooleanArrayUtils.toInt(word1+word2);

		return row;
	}


}
