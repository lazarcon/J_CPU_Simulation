package ch.zhaw.lazari.cpu.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.zhaw.lazari.cpu.api.Register;
import ch.zhaw.lazari.cpu.impl.utils.BooleanArrayUtils;

public class RegisterWindow extends JPanel implements TickablePanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	private Register register;

	public void tick() {
		textField.setText(
				String.format("%s - %d", 
						BooleanArrayUtils.toBinaryString(register.get()), 
						BooleanArrayUtils.toInt(register.get()))) ;

	}

	/**
	 * Create the frame.
	 * 
	 * @param programCounter
	 */
	public RegisterWindow(Register register) {
		this.register = register;
		add(new JLabel(String.format("Register %d", register.getId())));
		textField = new JTextField();
		textField.setColumns(10);
		add(textField);
	}

}
