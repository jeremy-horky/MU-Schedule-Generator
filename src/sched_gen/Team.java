/**
 * Schedule Generator - designed for COEN 4650 - S2019
 *
 * @authors Jeremy Horky, Raaz Khoshnood, and Russell Reding
 * @version Created: //19, Modified //19
 */

package sched_gen;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Team {
	private String name; // team name
	private int seed; // team ranking relative to other teams
	private String home; // team home field
	private Color[] colors; // first team color
	private int[] record; // teams W-L-T record
	private static ArrayList<Game> games = new ArrayList<Game>();

	public Team() { // Initializes team to default values
		name = "Team " + Schedule_Generator.teamCount;
		seed = Schedule_Generator.teamCount;
		home = "Field " + Schedule_Generator.teamCount;
		colors = new Color[] { Color.rgb(255, 255, 255), Color.rgb(0, 0, 0) };
		record = new int[] { 0, 0, 0 };
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public int getSeed() {
		return seed;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getHome() {
		return home;
	}

	public void setColors(Color color1, Color color2) {
		colors = new Color[] { color1, color2 };
	}

	public Color[] getColors() {
		return colors;
	}

	public void setRecord(int wins, int losses, int ties) {
		record = new int[] { wins, losses, ties };
	}

	public void addToRecord(int type) {
		switch (type) {
		case 0:
			record[0] = record[0] + 1;
			break;
		case 1:
			record[1] = record[1] + 1;
			break;
		case 2:
			record[2] = record[2] + 1;
			break;
		default:
			break;
		}
	}

	public int getWins() {
		return record[0];
	}

	public int getLosses() {
		return record[1];
	}

	public int getTies() {
		return record[2];
	}

	public void addGame(Game game) {
		games.add(game);
		sortGames();
	}

	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		Team.games = games;
	}

	public GridPane toPane() {
		GridPane master = new GridPane();
		Pane gamePane = new Pane();
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(12);
		pane.setAlignment(Pos.TOP_CENTER);

		Label nameLabel = new Label("TEAM NAME:");
		nameLabel.setStyle("-fx-font-weight: bold");
		Label nameVal = new Label(name);
		pane.add(nameLabel, 0, 0);
		pane.add(nameVal, 1, 0);

		Label seedLabel = new Label("TEAM SEED:");
		seedLabel.setStyle("-fx-font-weight: bold");
		Label seedVal = new Label("" + seed);
		pane.add(seedLabel, 0, 1);
		pane.add(seedVal, 1, 1);

		Label homeLabel = new Label("HOME FIELD:");
		homeLabel.setStyle("-fx-font-weight: bold");
		Label homeVal = new Label(home);
		pane.add(homeLabel, 0, 2);
		pane.add(homeVal, 1, 2);

		Label colorLabel = new Label("TEAM COLORS:");
		colorLabel.setStyle("-fx-font-weight: bold");
		HBox colorVal = new HBox();
		Rectangle rectColor1 = new Rectangle(20, 20, colors[0]);
		rectColor1.setStroke(Color.BLACK);
		Rectangle rectColor2 = new Rectangle(20, 20, colors[1]);
		rectColor2.setStroke(Color.BLACK);
		colorVal.getChildren().addAll(rectColor1, rectColor2);
		pane.add(colorLabel, 0, 3);
		pane.add(colorVal, 1, 3);

		Label recordLabel = new Label("TEAM RECORD:");
		recordLabel.setStyle("-fx-font-weight: bold");
		Label recordVal = new Label(record[0] + "-" + record[1] + "-" + record[2]);
		pane.add(recordLabel, 0, 4);
		pane.add(recordVal, 1, 4);

		VBox gamesList = new VBox();
		Label gameLabel = new Label("GAMES");
		gameLabel.setStyle("-fx-font-weight: bold");
		gamesList.getChildren().add(gameLabel);
		for (int i = 0; i < games.size(); i++) {
			String game = games.get(i).toString();
			Label nextGame = new Label(game);
			gamesList.getChildren().add(nextGame);
		}

		gamePane.getChildren().add(gamesList);
		master.add(pane, 0, 0);
		master.add(gamePane, 0, 1);
		return master;
	}

	public HBox toLogo() {
		HBox logo = new HBox();
		Rectangle rectColor1 = new Rectangle(10, 10, colors[0]);
		rectColor1.setStroke(Color.BLACK);
		Rectangle rectColor2 = new Rectangle(10, 10, colors[1]);
		rectColor2.setStroke(Color.BLACK);
		logo.getChildren().addAll(rectColor1, new Label(" " + name + " "), rectColor2);
		logo.setAlignment(Pos.CENTER);
		return logo;
	}
	
	private static void sortGames() {
		quicksort(games, 0, games.size() - 1);
	}

	private static void quicksort(ArrayList<Game> list, int start, int end) {
		if (start < end) {
			int index = partition(list, start, end);
			quicksort(list, start, index - 1);
			quicksort(list, index + 1, end);
		}
	}

	private static int partition(ArrayList<Game> list, int start, int end) {
		int pivot = list.get(end).getDate();
		int i = start - 1;
		for (int j = start; j <= end - 1; j++) {
			if (list.get(j).getDate() <= pivot) {
				i++;
				swap(i, j);
			}
		}
		swap(i + 1, end);
		return i + 1;
	}
	
	public static void swap(int a, int b) {
		Game temp = games.get(a);
		games.set(a, games.get(b));
		games.set(b, temp);
	}

}
