package com.jollex.Minesweeper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Secret extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//Creates components for prom
	private JLabel question = null;
	private JPanel promButtons = null;
	private JButton yes = null;
	private JButton no = null;
	private JLabel answer = null;
	
	private ImageIcon button = new javax.swing.ImageIcon(getClass().getResource("images/button.gif"));
	
	public Secret() {
		this.setPreferredSize(new Dimension(128, 164));
		this.setBackground(Color.RED);
		setUpProm();
	}
	
	private void setUpProm() {
		answer = new JLabel();
		answer.setFont(new Font("Arial", Font.BOLD, 12));
		this.add(answer);
		answer.setVisible(false);
		
		question = new JLabel();
		question.setText("<html><center>Janine, will you go" +
				"<br>to prom with me?" +
				"</center></html>");
		question.setFont(new Font("Arial", Font.BOLD, 12));
		question.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		this.add(question);
		
		promButtons = new JPanel();
		
		yes = new JButton("Yes");
		yes.setIcon(button);
		yes.setBorder(BorderFactory.createEmptyBorder());
		yes.setIconTextGap(-35);
		yes.setFont(new Font("Arial", Font.BOLD, 12));
		ActionListener yesClick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				yes();
			}
		};
		yes.addActionListener(yesClick);
		
		no = new JButton("No");
		no.setIcon(button);
		no.setBorder(BorderFactory.createEmptyBorder());
		no.setIconTextGap(-32);
		no.setFont(new Font("Arial", Font.BOLD, 12));
		ActionListener noClick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				no();
			}
		};
		no.addActionListener(noClick);
		
		promButtons.add(yes);
		promButtons.add(no);
		this.add(promButtons);
	}
	
	private void yes() {
		question.setVisible(false);
		yes.setVisible(false);
		no.setVisible(false);
		
		answer.setText("Yay!");
		answer.setVisible(true);
	}
	
	private void no() {
		question.setVisible(false);
		yes.setVisible(false);
		no.setVisible(false);
		
		answer.setText("<html><center>Well..." +
				"<br>this is awkward" +
				"</center></html>");
		answer.setVisible(true);
	}
}
