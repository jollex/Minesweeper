package com.jollex.Minesweeper;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;

public class Game extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private Container mines = null;
	private int rows = 8, cols = 8;
	private JToggleButton buttons[][] = new JToggleButton[rows][cols];
	private int cells[][] = new int[rows][cols];
	private boolean shown[][] = new boolean[rows][cols];
	private ImageIcon blank = new ImageIcon("images/blank.gif");

	public Game(){
		super("Minesweeper");
		startGame();
	}
	
	private void startGame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(148, 184);
		
		mines = this.getContentPane();
		mines.setLayout(new GridLayout(rows, cols));
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				//Populate minefield
				buttons[row][col] = new JToggleButton(blank);
				buttons[row][col].setSize(16, 16);
				
				buttons[row][col].addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseReleased(java.awt.event.MouseEvent e) {
						if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
							//markCell(e);
						} else if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
							//showCell(e);
						}
					}
				});
				
				mines.add(buttons[row][col]);
				
				cells[row][col] = bombCount(row, col);
				shown[row][col] = false;
			}
		}
		
		this.setVisible(true);
		this.validate();
		this.repaint();
	}
	
	private int bombCount(int x, int y){
		int bombCount = 0;
		for(int row = -1; row <= 1; row++){
			for(int col = -1; col <= -1; col++) {
				int i = x + row;
				int j = y + col;
 
				if(i >= 0 && i < cells.length && j >= 0 && j < cells[i].length){
					bombCount++;
				}
			}
		}
		return bombCount;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Game start = new Game();
	}

}
