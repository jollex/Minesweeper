package com.jollex.Minesweeper;

import javax.swing.JFrame;

public class Screen extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public Screen() {
		super("Minesweeper");
		startGame();
	}
	
	private void startGame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(148, 184);
		Minefield field = new Minefield();
		this.add(field);
		this.setVisible(true);
	}
}
