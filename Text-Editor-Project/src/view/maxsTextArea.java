package view;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javafx.scene.control.TextArea;
import model.Tools;

public class maxsTextArea extends TextArea implements KeyListener {
	Tools tools = new Tools();
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		tools.checkWordCount(getText());
		
	}

	
}
