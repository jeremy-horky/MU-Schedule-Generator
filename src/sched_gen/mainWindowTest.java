package sched_gen;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import javafx.stage.Stage;

public class mainWindowTest extends Application{

	public static int height = 250;
	public static int width = 400;
	
	public static void main(String[] args) {
		Application.launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Schedule Generator");
		Group root = new Group();
		Scene mainWindow = new Scene(root, width, height, Color.WHITE);
		BorderPane borderPane = new BorderPane();
		
		Pane buttonPane = new Pane();
		Button backButton = new Button("Back");		
		backButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	borderPane.setCenter(buttonPane);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
		    }
		});
		
		Button teamsButton = new Button("Team Tabs");
		teamsButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	/*Pane teamsPane = new Pane();
				VBox teamsBox = new VBox();
				teamsBox.getChildren().addAll(new Label("This is where the teams go"), backButton);
				teamsPane.getChildren().add(teamsBox);*/
		    	Scene tabsWindow = new Scene(root, width, height, Color.WHITE);
				TabPane teamsPane = new TabPane();
				teamsPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
				BorderPane borderPane = new BorderPane();
				/*for (int i = 0; i < teamCount; i++) {
					Tab tab = teams.get(i).toTab();
					teamsPane.getTabs().add(tab);
				}*/
		    	
		    	borderPane.setCenter(teamsPane);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
		    }
		});
		
		Button createButton = new Button("Create new teams");
		createButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Pane createPane = new Pane();
				VBox createBox = new VBox();
				createBox.getChildren().addAll(new Label("This is where you enter teams"), backButton);
				createPane.getChildren().add(createBox);
		    	borderPane.setCenter(createPane);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
		    }
		});
		
		Button loadButton = new Button("Load teams from file");
		loadButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Pane loadPane = new Pane();
				VBox loadBox = new VBox();
				loadBox.getChildren().addAll(new Label("This is where you load in teams"), backButton);
				loadPane.getChildren().add(loadBox);
		    	borderPane.setCenter(loadPane);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
		    }
		});
		
		Button saveButton = new Button("Save teams to file");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Pane savePane = new Pane();
				VBox saveBox = new VBox();
				saveBox.getChildren().addAll(new Label("This is where you save teams"), backButton);
				savePane.getChildren().add(saveBox);
		    	borderPane.setCenter(savePane);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
		    }
		});
		
		Button schedButton = new Button("Create schedule");		
		schedButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Pane schedPane = new Pane();
				VBox schedBox = new VBox();
				schedBox.getChildren().addAll(new Label("This is where a schedule is generated"), backButton);
				schedPane.getChildren().add(schedBox);
		    	borderPane.setCenter(schedPane);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
		    }
		});
		
		Button exportButton = new Button("Export schedule");
		exportButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Pane exportPane = new Pane();
				VBox exportBox = new VBox();
				exportBox.getChildren().addAll(new Label("This is where you can export a schedule"), backButton);
				exportPane.getChildren().add(exportBox);
		    	borderPane.setCenter(exportPane);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
		    }
		});
		
		VBox buttons = new VBox();
		buttons.getChildren().addAll(teamsButton, createButton, loadButton, saveButton, schedButton, exportButton);
		buttonPane.getChildren().add(buttons);
		
		
		// bind to take available space
		borderPane.prefHeightProperty().bind(mainWindow.heightProperty());
		borderPane.prefWidthProperty().bind(mainWindow.widthProperty());

		borderPane.setCenter(buttonPane);
		root.getChildren().add(borderPane);
		primaryStage.setScene(mainWindow);
		primaryStage.show();
	}
	
}
