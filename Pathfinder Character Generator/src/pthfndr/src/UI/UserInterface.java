package pthfndr.src.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class UserInterface extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static int width = 320;
	private static int height = width / 12 * 9;
	private static int scale = 2;
	private final static String TITLE = "Pathfinder Campaign Manager";
	private static UserInterface ui = new UserInterface(TITLE);
	
	private DiceRoller diceRoller = new DiceRoller();

	
	public UserInterface(String title) {
		super(title);
	}

	private void menu()
	{
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		JMenu tools = new JMenu("Tools");
		menuBar.add(tools);
		
		JMenuItem diceRoller = new JMenuItem("Dice Roller");
		tools.add(diceRoller);
		setJMenuBar(menuBar);
		diceRoller.addActionListener(this);
		
		
		
		menuBar.setVisible(true);
		
	}
	
	public static void main(String args[]) {
		
		
		ui.setBackground(Color.WHITE);
		ui.setPreferredSize(new Dimension(width * scale, height * scale));
		ui.setMinimumSize(new Dimension(width, height));
		ui.menu();
		ui.setVisible(true);
		ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ui.setResizable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonString = e.getActionCommand();
		
		if (buttonString.equals("Dice Roller"));
		{ diceRoller.tell(); }
		
	}

}
