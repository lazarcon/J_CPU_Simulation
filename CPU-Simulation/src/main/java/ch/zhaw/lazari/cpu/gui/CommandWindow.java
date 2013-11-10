package ch.zhaw.lazari.cpu.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CommandWindow extends JPanel {

	private static final long serialVersionUID = 1L;

	private CPUSimulation simulation;

	boolean running = false;

	/**
	 * Create the frame.
	 */
	public CommandWindow(CPUSimulation simulation) {
		this.simulation = simulation;		
		final JButton btnTick = new JButton("Tick");
		btnTick.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CommandWindow.this.simulation.tick();
				if(CommandWindow.this.simulation.isFinished())
					btnTick.setEnabled(false);
				
			}
		});
		
		JButton btnSlow = new JButton("slow");
		add(btnSlow);
		btnSlow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CommandWindow.this.simulation.runSlow();
				
			}
		});
		JButton btnFast = new JButton("fast");
		add(btnFast);
		btnFast.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CommandWindow.this.simulation.runFast();
				
			}
		});
		add(btnTick, BorderLayout.NORTH);
	}
	


}
