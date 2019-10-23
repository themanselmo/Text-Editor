package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Tools {
	private static Label actionStatus = new Label("");

	public double getFleschScore(String text) {
		double fScore = (0.39*calcAverageSPW(text)) + (11.8*calcAverageWPS(text))-15.59;
		return fScore;
	}
	
	// methods needed for calculating flesch score
		// calculate average words per sentance
	public int calcAverageWPS(String text) {
		int averageWPS = 0;
		String[] sentanceArray = text.trim().split("\\.+");
		for(int i = 0; i < sentanceArray.length; i++) {
			String[] wordArray = sentanceArray[i].split("\\s+");
			averageWPS += wordArray.length;
		}
		
		return averageWPS / sentanceArray.length;
	}
		// calculate average syllables per word
	public int calcAverageSPW(String text) {
		int averageSPW = 0;
		int numberOfSyllables = 0;
		String[] wordArray = text.trim().split("\\s+");
		for(int i = 0; i < wordArray.length; i++) {
			String[] syllableArray = wordArray[i].split("[aeiouy]+?\\w*?[^e]");
			numberOfSyllables += syllableArray.length;
		}
		averageSPW = numberOfSyllables / wordArray.length;
		return Math.round(averageSPW);
	}
	
	public int checkWordCount(String text) {
		String[] wordArray = text.trim().split("\\s+");
		return wordArray.length;
	}

	public int checkSentanceCount(String text) {
		String[] sentanceArray = text.trim().split("\\.+");
		return sentanceArray.length;
	}

	public String readFile(File file) {
		String readFile = "";
		int lineSkip = 0;
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			confirm("File not found");
		}
		while (scan.hasNext()) {
			lineSkip++;
			readFile += scan.next() + " ";
			if (lineSkip == 15) {
				lineSkip = 0;
				readFile += "\n";
			}
		}
		return readFile;
	}

	public MasterLinkList importMasterList(File file) {
		MasterLinkList masterList = new MasterLinkList();
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			confirm("File not found");
		}

		masterList.insertLast(scan.next());
		while (scan.hasNext()) {
			MasterLink current = masterList.first;
			String next = scan.next();
			current.babyList.insertLast(next); // here
			boolean foundMaster = false;
			for (int i = 0; i < masterList.nElems; i++) {
				if (current.word.equals(next)) {
					foundMaster = true;
					break;
				}
				current = current.next;
			}
			if (!foundMaster) {
				masterList.insertLast(next);
			}
		}

		return masterList;
	}

	// public BabyLinkList importBabyList(String lyricFile) {
	//
	// }

	public static void SuccessAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert!");
		alert.setHeaderText("Information Alert");
		String s = "The ID entered cannot be found!";
		alert.setContentText(s);
		alert.showAndWait();
	}

	public static void failureAlert(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert!");
		alert.setHeaderText("Information Alert");
		String s = message;
		alert.setContentText(s);
		alert.showAndWait();
	}

	public static boolean inputCheck(TextField textField) {
		actionStatus.setText("");
		String txt = textField.getText().trim();
		String msg = "Text saved: ";

		if (txt.isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Alert");
			String s = "The field cannot be empty";
			alert.setContentText(s);
			alert.showAndWait();
			textField.requestFocus();
			actionStatus.setText("Failed to add the item!");
			return false;
		} else {
			actionStatus.setText("Succeed");
			return true;
		}

	}

	public static void confirm(String s) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Success!");
		alert.setContentText(s);
		actionStatus.setText("Succeed");
		Optional<ButtonType> result = alert.showAndWait();
	}

}
