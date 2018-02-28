package pthfndr.src.UI;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;

public class AutoClicker {

	private static Robot macro = null;
	
	public static void MainFunction() {
		while(true)
		{
			leftClick();
			delay(.007);
		}
	}
	
	protected static void delay(double seconds) {
		createMacro();
		macro.delay((int)(seconds * 1000.0));
	}
	
	protected static void leftClick() {
		createMacro();
		macro.mousePress(MouseEvent.BUTTON1_MASK);
		macro.mouseRelease(MouseEvent.BUTTON1_MASK);
	}
	
	private static void createMacro() {
		try {
			macro = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MainFunction();

	}

}
