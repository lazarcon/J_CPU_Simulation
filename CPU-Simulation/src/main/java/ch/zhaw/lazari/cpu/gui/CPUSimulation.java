package ch.zhaw.lazari.cpu.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.zhaw.lazari.cpu.api.CPU;
import ch.zhaw.lazari.cpu.api.Register;

public class CPUSimulation extends JFrame {

	private static final long serialVersionUID = 1L;
	private CPU cpu;
	private List<TickablePanel> panels = new ArrayList<TickablePanel>();
	private long pause = 0l;
	private boolean running;
	private static final ScheduledExecutorService worker = Executors
			.newSingleThreadScheduledExecutor();

	public CPUSimulation(CPU cpu) {
		this.cpu = cpu;
		this.setTitle("CPU-Simulation");
		this.setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(new CommandWindow(this), BorderLayout.SOUTH);
		final MemoryWindow code = new CommandMemoryWindow(cpu.getMemory(),
				cpu.getProgramCounter(), 100, 500);
		panels.add(code);
		final MemoryWindow result = new MemoryWindow(cpu.getMemory(), 500, 530);
		this.getContentPane().add(code, BorderLayout.WEST);
		this.getContentPane().add(result, BorderLayout.EAST);
		panels.add(result);
		final ProgramCounterWindow counter = new ProgramCounterWindow(
				cpu);
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
		refreshPanels();
	}

	public void tick() {
		cpu.tick();
		refreshPanels();

	}

	private void refreshPanels() {
		for (TickablePanel panel : panels)
			panel.tick();
	}

	public boolean isFinished() {
		return cpu.isFinished();
	}

	public void runFast() {
		pause = 0l;
		run();
	}
	
	public void run()
	{
		running = !running;
		if(running)
			doOneRun();
	}

	public void doOneRun() {
		tick();
		Runnable task = new Runnable() {
			public void run() {
				if (!cpu.isFinished() && running) {
					doOneRun();
				}
			}
		};
		worker.schedule(task, pause, TimeUnit.MILLISECONDS);

	}

	public void runSlow() {
		pause = 1000l;
		run();

	}
	

}
