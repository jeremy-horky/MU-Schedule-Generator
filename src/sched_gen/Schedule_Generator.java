package sched_gen;

import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Schedule_Generator extends Application {
	public static int teamCount = 0;
	public static ArrayList<Team> teams = new ArrayList<Team>();
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Would you like to load a saved team set or create a new one? [L/N] ");
		String choice = in.next().toUpperCase();
		if(choice.equals("L")){
			System.out.println("Unfortunately, the developers have not implemented this option yet :(");
		}
		else if(choice.equals("N")){
			createTeams();
		}
		in.close();
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Tabs");
		Group root = new Group();
		Scene scene = new Scene(root, 400, 250, Color.WHITE);
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		BorderPane borderPane = new BorderPane();
		for (int i = 0; i < teamCount; i++) {
			Tab tab = teams.get(i).toTab();
			tabPane.getTabs().add(tab);
		}
		// bind to take available space
		borderPane.prefHeightProperty().bind(scene.heightProperty());
		borderPane.prefWidthProperty().bind(scene.widthProperty());

		borderPane.setCenter(tabPane);
		root.getChildren().add(borderPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void createTeams(){
		Scanner in = new Scanner(System.in);
		String choice;
		System.out.println("Enter team information:");
		boolean more = true;
		while(more){
			teamCount++;
			Team team = new Team();
			System.out.print("Edit name? [Y/N] ");
			choice = in.next().toUpperCase();
			if(choice.equals("Y")){
				System.out.print("Enter team name: ");
				String name = in.next();
				team.setName(name);
			}
			System.out.print("Edit seed? [Y/N] ");
			choice = in.next().toUpperCase();
			if(choice.equals("Y")){
				System.out.print("Enter team seed: ");
				int seed = in.nextInt();
				team.setSeed(seed);
			}
			System.out.print("Edit home field? [Y/N] ");
			choice = in.next().toUpperCase();
			if(choice.equals("Y")){
				System.out.print("Enter home field: ");
				String home = in.next();
				team.setHome(home);
			}
			System.out.print("Edit colors? [Y/N] ");
			choice = in.next().toUpperCase();
			if(choice.equals("Y")){
				System.out.println("Enter color RGB values: ");
				System.out.print("R1: ");
				int r1 = in.nextInt();
				System.out.print("G1: ");
				int g1 = in.nextInt();
				System.out.print("B1: ");
				int b1 = in.nextInt();
				System.out.print("R2: ");
				int r2 = in.nextInt();
				System.out.print("G2: ");
				int g2 = in.nextInt();
				System.out.print("B2: ");
				int b2 = in.nextInt();
				team.setColors(Color.rgb(r1,g1,b1),Color.rgb(r2, g2, b2));
			}
			System.out.print("Edit record? [Y/N] ");
			choice = in.next().toUpperCase();
			if(choice.equals("Y")){
				System.out.print("Enter wins: ");
				int wins = in.nextInt();
				System.out.print("Enter losses: ");
				int losses = in.nextInt();
				System.out.print("Enter ties: ");
				int ties = in.nextInt();
				team.setRecord(wins, losses, ties);
			}
			System.out.print("Would you like to add another team? [Y/N] ");
			choice = in.next().toUpperCase();
			teams.add(team);
			if(choice.equals("Y")) more = true;
			else more = false;
		}
		in.close();
	}
}
