package view;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.MasterLinkList;
import model.Tools;

// sort our flesch score 
// current work
public class TopPane {
	// menubar object
	private MenuBar menuBar;
	// menu objects
	private Menu fileMenu;
	private Menu viewMenu;
	private Menu editMenu;
	// menu item objects (fileMenu)
	private MenuItem newFileMenuItem;
	private MenuItem openFileMenuItem;
	private MenuItem clearFileMenuItem;
	private MenuItem saveFileMenuItem;
	private MenuItem exitFileMenuItem;
	// menu item objects (viewMenu)
	private MenuItem wordCountViewMenuItem;
	private MenuItem sentanceCountViewMenuItem;
	private MenuItem fleschScoreViewMenuItem;
	// menu item objects (editMenu)
	private MenuItem copyEditMenuItem;
	private MenuItem cutEditMenuItem;
	private MenuItem deleteEditMenuItem;
	private MenuItem pasteEditMenuItem;
	private MenuItem markovEditMenuItem;
	// misc objects
	private MasterLinkList masterList;
	private Tools tools;
	private TextArea newTA;
	private File file;
	private String toBeCopied;
	private static int fileNum = 1;
	private StatusBar wordCountSBar = new StatusBar();
	private StatusBar sentanceCountSBar = new StatusBar();
	private StatusBar contentSBar = new StatusBar();
	
	public TopPane() {
		buildFileMenu();
		buildEditMenu();
		buildViewMenu();
		buildMenuBar();
		tools = new Tools();
		
		newTA = new TextArea();
		newTA.setOnKeyReleased(e -> {
			wordCountSBar.setMessage(" Word#: " + tools.checkWordCount(newTA.getText()));
			sentanceCountSBar.setMessage(" Sentance#: " + tools.checkSentanceCount(newTA.getText()));
			// newTA.spellCheck();
		});
		masterList = new MasterLinkList();
	}

	public void buildMenuBar() {
		menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu);
	}

	// Back-end for File Menu Options
	public void buildFileMenu() {
		final FileChooser fileChooser = new FileChooser();
		Stage stage = new Stage();

		fileMenu = new Menu("File");
		saveFileMenuItem = new MenuItem("Save");
		saveFileMenuItem.setOnAction(e -> {
			save();
		});
		openFileMenuItem = new MenuItem("Open");
		openFileMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				file = fileChooser.showOpenDialog(stage);
				Stage stage = new Stage();

				if (file != null) {
					masterList = tools.importMasterList(file);
					newTA.appendText(tools.readFile(file));
					// newTA.spellCheck();
					tools.confirm("Successfully imported!");

				}
			}
		});
		clearFileMenuItem = new MenuItem("Clear");
		clearFileMenuItem.setOnAction(e -> {
			newTA.clear();
		});
		exitFileMenuItem = new MenuItem("Exit");
		exitFileMenuItem.setOnAction(e -> {
			Platform.exit();
		});
		newFileMenuItem = new MenuItem("New");
		newFileMenuItem.setOnAction(e -> {
			newTA.clear();
			fileNum++;
			// figure out how to create a new blank text file
		});
		fileMenu.getItems().addAll(openFileMenuItem, saveFileMenuItem, newFileMenuItem,
				 clearFileMenuItem, exitFileMenuItem);
	}

	public void buildEditMenu() {
		editMenu = new Menu("Edit");
		copyEditMenuItem = new MenuItem("Copy");
		copyEditMenuItem.setOnAction(e -> {
			setToBeCopied(newTA.getSelectedText());
		});

		cutEditMenuItem = new MenuItem("Cut");
		cutEditMenuItem.setOnAction(e -> {
			setToBeCopied(newTA.getSelectedText());
			newTA.deleteText(newTA.getCaretPosition() - toBeCopied.length(), newTA.getCaretPosition());
		});

		deleteEditMenuItem = new MenuItem("Delete");
		deleteEditMenuItem.setOnAction(e -> {
			String temp = toBeCopied;
			setToBeCopied(newTA.getSelectedText());
			newTA.deleteText(newTA.getCaretPosition() - toBeCopied.length(), newTA.getCaretPosition());
			setToBeCopied(temp);
		});

		pasteEditMenuItem = new MenuItem("Paste");
		pasteEditMenuItem.setOnAction(e -> {
			newTA.insertText(newTA.getAnchor(), toBeCopied);
		});

		markovEditMenuItem = new MenuItem("Markov");
		markovEditMenuItem.setOnAction(e -> {
			newTA.appendText("\n" + "--------------------" + "\n" + "Test" + 
					"\n" + "--------------------");
		});
		editMenu.getItems().addAll(copyEditMenuItem, cutEditMenuItem, deleteEditMenuItem,
				pasteEditMenuItem, markovEditMenuItem);
	}

	public void buildViewMenu() {
		viewMenu = new Menu("View");

		fleschScoreViewMenuItem = new MenuItem("Flesch Score");
		fleschScoreViewMenuItem.setOnAction(e -> {
			contentSBar.setMessage("The Flesch Score is: " + tools.getFleschScore(newTA.getText()));
		});

		sentanceCountViewMenuItem = new MenuItem("Sentance Count");
		sentanceCountViewMenuItem.setOnAction(e -> {
			sentanceCountSBar.setMessage(" Sentance#: " + tools.checkSentanceCount(newTA.getText()));
		});

		wordCountViewMenuItem = new MenuItem("Word Count");
		wordCountViewMenuItem.setOnAction(e -> {
			wordCountSBar.setMessage(" Word#: " + tools.checkWordCount(newTA.getText()));
			// fill in
		});

		viewMenu.getItems().addAll(fleschScoreViewMenuItem, sentanceCountViewMenuItem, wordCountViewMenuItem);
	}

	public void save() {
		try {
			FileWriter fw = new FileWriter("OutputData/SavedFile" + fileNum + ".txt");
			PrintWriter pw = new PrintWriter(fw, true);
			pw.write(newTA.getText());
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			tools.confirm("Error saving file...");
		}
		tools.confirm("File saved!");
	}
	
	public MenuBar getMenuBar() {
		return menuBar;
	}

	public TextArea getTA() {
		return newTA;
	}

	public StatusBar getWordCountStatusBar() {
		return wordCountSBar;
	}

	public StatusBar getSentanceCountStatusBar() {
		return sentanceCountSBar;
	}

	public StatusBar getContentStatusBar() {
		return contentSBar;
	}

	public String getToBeCopied() {
		return toBeCopied;
	}

	public void setToBeCopied(String s) {
		toBeCopied = s;
	};
}
