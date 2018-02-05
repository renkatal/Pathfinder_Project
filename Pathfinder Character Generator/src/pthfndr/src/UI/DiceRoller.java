package pthfndr.src.UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import pthfndr.src.main.Roll;

public class DiceRoller extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean flag = false;
	JFrame diceCup = new JFrame("Dice Roller");
	JButton d2 = new JButton("D2");
	JButton d4 = new JButton("D4");
	JButton d6 = new JButton("D6");
	JButton d8 = new JButton("D8");
	JButton d10 = new JButton("D10");
	JButton d12 = new JButton("D12");
	JButton d20 = new JButton("D20");
	JButton d100 = new JButton("D100");
	JButton clear = new JButton("Clear");
	JTextArea resultBox = new JTextArea();
	GridLayout layout = new GridLayout(6, 1, 0, 5);
	GridLayout buttonHolder = new GridLayout(1, 2, 5, 0);
	JPanel hold1 = new JPanel(buttonHolder);
	JPanel hold2 = new JPanel(buttonHolder);
	JPanel hold3 = new JPanel(buttonHolder);
	JPanel hold4 = new JPanel(buttonHolder);
	
	ActionListener clearEar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			resultBox.setText("");
			flag = false;
		}
	};
	ActionListener dieEar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String dieString = e.getActionCommand();
			String result = resultBox.getText();
			if(flag)
				{	result += ", ";}
			else
			{
				flag = true;
			}
			result += Roll.d(dNametoInt(dieString));
			resultBox.setText(result);
			
		}
	};
	private int dNametoInt(String dName) {
		String hold = "";
		for(int i = 0; i < dName.length() - 1; i++)
		{
			hold += dName.charAt(i+1);
		}
		int value = Integer.parseInt(hold);
		return value;
		
	}
	public DiceRoller() {
		d2.addActionListener(dieEar);
		d4.addActionListener(dieEar);
		d6.addActionListener(dieEar);
		d8.addActionListener(dieEar);
		d10.addActionListener(dieEar);
		d12.addActionListener(dieEar);
		d20.addActionListener(dieEar);
		d100.addActionListener(dieEar);
		clear.addActionListener(clearEar);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public void tell() 
	{
		diceCup.setSize(200,240);
		diceCup.setLayout(layout);
		hold1.add(d2);
		hold1.add(d4);
		diceCup.add(hold1);
		hold2.add(d6);
		hold2.add(d8);
		diceCup.add(hold2);
		hold3.add(d10);
		hold3.add(d12);
		diceCup.add(hold3);
		hold4.add(d20);
		hold4.add(d100);
		diceCup.add(hold4);
		resultBox.setEditable(false);
		resultBox.setLineWrap(true);
		diceCup.add(resultBox); 
		diceCup.add(clear);
		diceCup.setVisible(true);
		diceCup.setResizable(false);
		
	}
	public static void main(String[] args) {
	
		DiceRoller die = new DiceRoller();
		die.tell();
	}
}
