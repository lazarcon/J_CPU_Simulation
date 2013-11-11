package ch.zhaw.lazari.cpu.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.zhaw.lazari.cpu.api.CPU;
import ch.zhaw.lazari.cpu.api.ProgramCounter;

public class ProgramCounterWindow extends JPanel implements TickablePanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	private JTextField textField_1;
	private int counter = 0;
	private CPU cpu;
	private JTextField currentCommandTextField;
	private JTextField currentCommandBitsTextField;

	public void tick() {
		currentCommandBitsTextField.setText(cpu.getCommandWord());
		currentCommandTextField.setText(cpu.getCurrentCommand().toString());
		textField.setText(Integer.toString(cpu.getProgramCounter().get()));
		textField_1.setText(Integer.toString(counter));
		counter++;
	}

	/**
	 * Create the frame.
	 * 
	 * @param programCounter
	 */
	public ProgramCounterWindow(CPU cpu) {
		this.cpu = cpu;
		add(new JLabel("ProgrammCounter:"));
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(3);
		
		JLabel label = new JLabel("Next Command:");
		add(label);
		currentCommandTextField =  new JTextField();
		currentCommandTextField.setColumns(10);
		add(currentCommandTextField);
		
		label = new JLabel("Command Bits:");
		add(label);
		currentCommandBitsTextField =  new JTextField();
		currentCommandBitsTextField.setColumns(11);
		add(currentCommandBitsTextField);


		label = new JLabel("Counter:");
		add(label);

		textField_1 = new JTextField();
		textField_1.setColumns(5);
		add(textField_1);
	}

}
