package view;

import javax.swing.GroupLayout.Alignment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.MasterLinkList;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		MasterLinkList Masterlist = new MasterLinkList();
		BorderPane root = new BorderPane();
		TopPane topPane = new TopPane();
		
		HBox box = new HBox();

		root.setTop(topPane.getMenuBar());
		root.setCenter(topPane.getTA());
		box.getChildren().addAll(topPane.getWordCountStatusBar(), topPane.getSentanceCountStatusBar(),
				topPane.getContentStatusBar());
		root.setBottom(box);
		
		Scene scene = new Scene(root, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
