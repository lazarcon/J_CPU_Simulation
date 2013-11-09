package ch.zhaw.lazari.cpu.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.zhaw.lazari.cpu.api.ProgramCounter;

public class ProgramCounterWindow extends JPanel implements TickablePanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private ProgramCounter programCounter;

	public void tick() {
		textField.setText(Integer.toString(programCounter.get()));
	}

	/**
	 * Create the frame.
	 * @param programCounter 
	 */
	public ProgramCounterWindow(ProgramCounter programCounter) {
		this.programCounter = programCounter;
		add(new JLabel("ProgrammCounter"));
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
	}

}
