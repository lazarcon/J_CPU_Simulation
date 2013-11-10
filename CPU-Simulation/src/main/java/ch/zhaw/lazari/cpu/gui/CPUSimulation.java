package ch.zhaw.lazari.cpu.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.zhaw.lazari.cpu.api.CPU;
import ch.zhaw.lazari.cpu.api.Register;

public class CPUSimulation extends JFrame {

	private static final long serialVersionUID = 1L;
	private CPU cpu;
	private List<TickablePanel> panels = new ArrayList<TickablePanel>();

	public CPUSimulation(CPU cpu) {
		this.cpu = cpu;
		this.setTitle("CPU-Simulation");
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(new CommandWindow(this), BorderLayout.SOUTH);
		final MemoryWindow code = new MemoryWindow(cpu.getMemory(), 100, 150);
		panels.add(code);
		final MemoryWindow result = new MemoryWindow(cpu.getMemory(), 500, 520);
		this.getContentPane().add(code, BorderLayout.WEST);
		this.getContentPane().add(result, BorderLayout.EAST);
		panels.add(result);
		final ProgramCounterWindow counter = new ProgramCounterWindow(
				cpu.getProgramCounter());
		panels.add(counter);
		this.getContentPane().add(counter, BorderLayout.NORTH);
		final JPanel registers = new JPanel();
		registers.setLayout(new FlowLayout());
		for (Register register : cpu.getRegisters()) {
			final RegisterWindow registerWin = new RegisterWindow(register);
			registers.add(registerWin);
			panels.add(registerWin);
		}
		this.getContentPane().add(registers, BorderLayout.CENTER);
		pack();
		setVisible(true);
	}

	public void tick() {
		cpu.tick();
		for (TickablePanel window : panels)
			window.tick();

	}

	public boolean isFinished() {
		return cpu.isFinished();
	}

	public void runFast() {
		while(!cpu.isFinished())
			tick();
	}

}
