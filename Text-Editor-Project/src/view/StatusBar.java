package view;

import java.awt.Dimension;

import javax.swing.JLabel;

import javafx.scene.control.Label;

public class StatusBar extends Label {

	public StatusBar() {
		super();
		setMessage("Ready");
	}
	
	public void setMessage(String message) {
		setText(" " + message);
	}
	
	public void setMessage(Number message) {
		setText(" " + message);
	}
}
