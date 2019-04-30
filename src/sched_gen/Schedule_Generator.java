/**
 * Schedule Generator - designed for COEN 4650 - S2019
 *
 * @authors Jeremy Horky, Raaz Khoshnood, and Russell Reding
 * @version Created: //19, Modified //19
 */

package sched_gen;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Schedule_Generator extends Application {
	
	public static int teamCount = 0;
	public static ArrayList<Team> teams = new ArrayList<Team>();
	public static int height = 500;
	public static int width = 800;
	public static Scanner in = new Scanner(System.in);
	public static String file = "No File Selected";
	public static ArrayList<ArrayList<Game>> gameMaster = new ArrayList<ArrayList<Game>>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Schedule Generator");
		Group root = new Group();
		Scene mainWindow = new Scene(root, width, height, Color.WHITE);
		BorderPane borderPane = new BorderPane();
		
		//This sets the parameters for the title
		GridPane title = new GridPane();
		title.setAlignment(Pos.TOP_CENTER);
		title.setPadding(new Insets(10, 10, 10, 10));
		Text t = new Text("Game Scheduler");
		t.setStyle("-fx-font: 30 Arial");
		t.setBoundsType(TextBoundsType.VISUAL);
		title.getChildren().addAll(t);
		
		//This sets the parameters for the left pane called 'nav'
		GridPane nav = new GridPane();
		nav.setHgap(10);
		nav.setVgap(12);
		nav.setAlignment(Pos.TOP_CENTER);
		nav.setBackground(new Background(new BackgroundFill(Color.SILVER, null, null)));
		nav.setPadding(new Insets(10, 10, 10, 10));

		GridPane buttonPane = new GridPane();
		buttonPane.setHgap(10);
		buttonPane.setVgap(12);
		buttonPane.setAlignment(Pos.TOP_CENTER);

		Button backButton = new Button("Back");
		backButton.setPrefWidth(100);
		backButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				borderPane.setCenter(buttonPane);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
			}
		});

		Button teamsButton = new Button("View teams");
		teamsButton.setPrefWidth(200);
		teamsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				ScrollPane teamsPaneScroll = new ScrollPane();
				GridPane teamsPane = new GridPane();
				teamsPane.setHgap(10);
				teamsPane.setHgap(12);
				String cssLayout = "-fx-border-color: black;\n" + "-fx-border-insets: 5;\n" + "-fx-border-width: 3;\n";

				teamsPane.add(new Label("Rank"), 0, 0);
				teamsPane.add(new Label("Team"), 1, 0);

				int i;
				for (i = 0; i < teamCount; i++) {
					final int j = i;
					HBox logo = teams.get(i).toLogo();
					logo.setStyle(cssLayout);
					logo.setAlignment(Pos.CENTER);
					teamsPane.add(new Label((i + 1) + ". "), 0, (i + 1));
					teamsPane.add(logo, 1, (i + 1));

					Button backToTeams = new Button("Back");
					backToTeams.setPrefWidth(100);
					backToTeams.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							borderPane.setCenter(teamsPaneScroll);
							primaryStage.setScene(mainWindow);
							primaryStage.show();
						}
					});

					Button viewButton = new Button("View");
					viewButton.setPrefWidth(100);
					viewButton.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							ScrollPane viewPaneScroll = new ScrollPane();
							GridPane viewPane = teams.get(j).toPane();

							viewPane.add(backToTeams, 0, 5);
							viewPaneScroll.setContent(viewPane);

							borderPane.setCenter(viewPaneScroll);
							primaryStage.setScene(mainWindow);
							primaryStage.show();

						}
					});

					Button editButton = new Button("Edit");
					editButton.setPrefWidth(100);
					editButton.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {

							GridPane createPane = new GridPane();
							createPane.setHgap(10);
							createPane.setVgap(12);
							createPane.setAlignment(Pos.TOP_CENTER);

							GridPane textBoxPane = new GridPane();
							textBoxPane.setHgap(10);
							textBoxPane.setVgap(12);
							textBoxPane.setAlignment(Pos.TOP_CENTER);

							GridPane recordBoxPane = new GridPane();
							recordBoxPane.setHgap(50);
							recordBoxPane.setAlignment(Pos.TOP_CENTER);

							Label nameLabel = new Label("Enter team name:");
							TextField nameField = new TextField(teams.get(j).getName());
							nameField.setPromptText("Team " + (teamCount + 1));

							Label seedLabel = new Label("Enter seed (as an integer):");
							TextField seedField = new TextField("" + teams.get(j).getSeed());
							seedField.setPromptText("" + (teamCount + 1));

							Label homeLabel = new Label("Enter home field:");
							TextField homeField = new TextField(teams.get(j).getHome());
							homeField.setPromptText("Field " + (teamCount + 1));

							textBoxPane.add(nameLabel, 0, 0);
							textBoxPane.add(nameField, 1, 0);
							textBoxPane.add(seedLabel, 0, 1);
							textBoxPane.add(seedField, 1, 1);
							textBoxPane.add(homeLabel, 0, 2);
							textBoxPane.add(homeField, 1, 2);

							VBox colorBox = new VBox();
							HBox colorBoxSub = new HBox();
							colorBoxSub.setSpacing(10);
							Label colorLabel = new Label("Team colors:");
							Color[] colors = teams.get(j).getColors();
							final ColorPicker color1 = new ColorPicker(colors[0]);
							final ColorPicker color2 = new ColorPicker(colors[1]);
							colorBoxSub.getChildren().addAll(color1, color2);
							colorBoxSub.setAlignment(Pos.CENTER);
							colorBox.getChildren().addAll(colorLabel, colorBoxSub);
							colorBox.setAlignment(Pos.CENTER);

							Label winLabel = new Label("Wins");
							TextField winField = new TextField("" + teams.get(j).getWins());
							winField.setPromptText("0");
							winField.setPrefWidth(30);
							Label lossLabel = new Label("Losses");
							TextField lossField = new TextField("" + teams.get(j).getLosses());
							lossField.setPromptText("0");
							lossField.setPrefWidth(30);
							Label tieLabel = new Label("Ties");
							TextField tieField = new TextField("" + teams.get(j).getTies());
							tieField.setPromptText("0");
							tieField.setPrefWidth(30);

							recordBoxPane.add(winLabel, 0, 0);
							recordBoxPane.add(winField, 0, 1);
							recordBoxPane.add(lossLabel, 1, 0);
							recordBoxPane.add(lossField, 1, 1);
							recordBoxPane.add(tieLabel, 2, 0);
							recordBoxPane.add(tieField, 2, 1);

							HBox buttonBox = new HBox();
							buttonBox.setSpacing(10);
							Button submit = new Button("Submit");
							submit.setPrefWidth(100);
							submit.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent e) {
									ArrayList<Game> games = new ArrayList<Game>();
									teams.remove(j);
									teamCount--;
									createTeam(nameField, seedField, homeField, winField, lossField, tieField, color1,
											color2);
									nameField.clear();
									nameField.setPromptText("Team " + (teamCount + 1));
									seedField.clear();
									seedField.setPromptText("" + (teamCount + 1));
									homeField.clear();
									homeField.setPromptText("Field " + (teamCount + 1));
									color1.setValue(Color.WHITE);
									color2.setValue(Color.BLACK);
									winField.clear();
									lossField.clear();
									tieField.clear();
									teams.get(teamCount - 1).setGames(games);
									sortTeams();
									backToTeams.fire();
								}
							});

							Button clear = new Button("Clear");
							clear.setPrefWidth(100);
							clear.setOnAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent e) {
									nameField.clear();
									nameField.setPromptText("Team " + (teamCount + 1));
									seedField.clear();
									seedField.setPromptText("" + (teamCount + 1));
									homeField.clear();
									homeField.setPromptText("Field " + (teamCount + 1));
									color1.setValue(Color.WHITE);
									color2.setValue(Color.BLACK);
									winField.clear();
									lossField.clear();
									tieField.clear();
								}
							});

							buttonBox.setAlignment(Pos.CENTER);
							buttonBox.getChildren().addAll(submit, clear, backToTeams);

							createPane.add(textBoxPane, 0, 0);
							createPane.add(colorBox, 0, 1);
							createPane.add(recordBoxPane, 0, 2);
							createPane.add(buttonBox, 0, 3);
							borderPane.setCenter(createPane);
							primaryStage.setScene(mainWindow);
							primaryStage.show();
						}
					});

					Button deleteButton = new Button("Delete");
					deleteButton.setPrefWidth(100);
					deleteButton.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {

							teams.remove(j);
							teamCount--;
							borderPane.setCenter(buttonPane);
							primaryStage.setScene(mainWindow);
							primaryStage.show();
							teamsButton.fire();

						}
					});

					teamsPane.add(viewButton, 2, (i + 1));
					teamsPane.add(editButton, 3, (i + 1));
					teamsPane.add(deleteButton, 4, (i + 1));
				}

				teamsPane.add(backButton, 4, (i + 1));
				teamsPaneScroll.setContent(teamsPane);
				borderPane.setCenter(teamsPaneScroll);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
			}
		});

		Button createButton = new Button("Create new teams"); // COMPLETE!
		createButton.setPrefWidth(200);
		createButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				GridPane createPane = new GridPane();
				createPane.setHgap(10);
				createPane.setVgap(12);
				createPane.setAlignment(Pos.TOP_CENTER);

				GridPane textBoxPane = new GridPane();
				textBoxPane.setHgap(10);
				textBoxPane.setVgap(12);
				textBoxPane.setAlignment(Pos.TOP_CENTER);

				GridPane recordBoxPane = new GridPane();
				recordBoxPane.setHgap(50);
				recordBoxPane.setAlignment(Pos.TOP_CENTER);

				Label nameLabel = new Label("Enter team name:");
				TextField nameField = new TextField();
				nameField.setPromptText("Team " + (teamCount + 1));

				Label seedLabel = new Label("Enter seed (as an integer):");
				TextField seedField = new TextField();
				seedField.setPromptText("" + (teamCount + 1));

				Label homeLabel = new Label("Enter home field:");
				TextField homeField = new TextField();
				homeField.setPromptText("Field " + (teamCount + 1));

				textBoxPane.add(nameLabel, 0, 0);
				textBoxPane.add(nameField, 1, 0);
				textBoxPane.add(seedLabel, 0, 1);
				textBoxPane.add(seedField, 1, 1);
				textBoxPane.add(homeLabel, 0, 2);
				textBoxPane.add(homeField, 1, 2);

				VBox colorBox = new VBox();
				HBox colorBoxSub = new HBox();
				colorBoxSub.setSpacing(10);
				Label colorLabel = new Label("Team colors:");
				final ColorPicker color1 = new ColorPicker(Color.WHITE);
				final ColorPicker color2 = new ColorPicker(Color.BLACK);
				colorBoxSub.getChildren().addAll(color1, color2);
				colorBoxSub.setAlignment(Pos.CENTER);
				colorBox.getChildren().addAll(colorLabel, colorBoxSub);
				colorBox.setAlignment(Pos.CENTER);

				Label winLabel = new Label("Wins");
				TextField winField = new TextField();
				winField.setPromptText("0");
				winField.setPrefWidth(30);
				Label lossLabel = new Label("Losses");
				TextField lossField = new TextField();
				lossField.setPromptText("0");
				lossField.setPrefWidth(30);
				Label tieLabel = new Label("Ties");
				TextField tieField = new TextField();
				tieField.setPromptText("0");
				tieField.setPrefWidth(30);

				recordBoxPane.add(winLabel, 0, 0);
				recordBoxPane.add(winField, 0, 1);
				recordBoxPane.add(lossLabel, 1, 0);
				recordBoxPane.add(lossField, 1, 1);
				recordBoxPane.add(tieLabel, 2, 0);
				recordBoxPane.add(tieField, 2, 1);

				HBox buttonBox = new HBox();
				buttonBox.setSpacing(10);
				Button submit = new Button("Submit");
				submit.setPrefWidth(100);
				submit.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						createTeam(nameField, seedField, homeField, winField, lossField, tieField, color1, color2);
						nameField.clear();
						nameField.setPromptText("Team " + (teamCount + 1));
						seedField.clear();
						seedField.setPromptText("" + (teamCount + 1));
						homeField.clear();
						homeField.setPromptText("Field " + (teamCount + 1));
						color1.setValue(Color.WHITE);
						color2.setValue(Color.BLACK);
						winField.clear();
						lossField.clear();
						tieField.clear();
						sortTeams();
					}
				});

				Button clear = new Button("Clear");
				clear.setPrefWidth(100);
				clear.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						nameField.clear();
						nameField.setPromptText("Team " + (teamCount + 1));
						seedField.clear();
						seedField.setPromptText("" + (teamCount + 1));
						homeField.clear();
						homeField.setPromptText("Field " + (teamCount + 1));
						color1.setValue(Color.WHITE);
						color2.setValue(Color.BLACK);
						winField.clear();
						lossField.clear();
						tieField.clear();
					}
				});

				buttonBox.setAlignment(Pos.CENTER);
				buttonBox.getChildren().addAll(submit, clear, backButton);

				createPane.add(textBoxPane, 0, 0);
				createPane.add(colorBox, 0, 1);
				createPane.add(recordBoxPane, 0, 2);
				createPane.add(buttonBox, 0, 3);
				borderPane.setCenter(createPane);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
			}
		});

		Button loadButton = new Button("Load teams from file");
		loadButton.setPrefWidth(200);
		loadButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				VBox page = new VBox();
				GridPane loadPane = new GridPane();
				loadPane.setPadding(new Insets(100, 10, 10, 10));
				
				TextField currentFile = new TextField(file);
				currentFile.setPrefWidth(400);
				
				GridPane label = new GridPane();
				Label prompt = new Label();
				prompt.setPrefWidth(100);
				
				Button selectFile = new Button("Browse");
				selectFile.setPrefWidth(100);
				selectFile.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						FileChooser inputFile = new FileChooser();
						inputFile.setTitle("Select a file to import . . .");
						File in = inputFile.showOpenDialog(null);
						
						
						if(in != null) {
							currentFile.setText(in.getAbsolutePath());
							file = in.getAbsolutePath();
							prompt.setTextFill(Color.DARKGREEN);
							prompt.setText("File Added!");
							teams.clear();
							teams = XML.onload(teams, file);
							teamCount = XML.getTeamCount();
							label.add(prompt, 0, 0);
						}
						else {
							prompt.setText("Invalid Entry!");
							prompt.setTextFill(Color.RED);
							label.add(prompt, 0, 0);
						}
					}
					
				});
				loadPane.add(currentFile, 4, 1);
				loadPane.add(selectFile, 1000, 1);
				
				page.getChildren().addAll(loadPane, label);
				
				borderPane.setCenter(page);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
			}
		});

		Button saveButton = new Button("Save teams to file");
		saveButton.setPrefWidth(200);
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				XML.write(teams, teamCount);
//				Pane savePane = new Pane();
//				VBox saveBox = new VBox();
//				saveBox.getChildren().addAll(new Label("This is where you save teams"), backButton);
//				savePane.getChildren().add(saveBox);
//				borderPane.setCenter(savePane);
//				primaryStage.setScene(mainWindow);
//				primaryStage.show();
			}
		});

		Button schedButton = new Button("Create schedule");
		schedButton.setPrefWidth(200);
		schedButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Pane schedPane = new Pane();
				VBox schedBox = new VBox();
				
				HBox numBox = new HBox();
				Label numGames = new Label("Enter number of games:");
				TextField numField = new TextField();
				numField.setPromptText("1");
				numBox.getChildren().addAll(numGames, numField);
				
				Button genButton = new Button("Generate Schedule");
				genButton.setPrefWidth(200);
				genButton.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e){
						int num = 0;
						if (numField.getText() != ""){
							try{
								num = Integer.parseInt(numField.getText());
							} catch (Exception ex){}
						}
						for (int i = 0; i < teamCount; i++){
							teams.get(i).games = new ArrayList<Game>();
						}
						generateSchedule(num);
					}	
				});
				
				schedBox.getChildren().addAll(numBox, genButton, backButton);
				schedPane.getChildren().add(schedBox);
				borderPane.setCenter(schedPane);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
			}
		});

		Button exportButton = new Button("Export schedule (WIP)");
		exportButton.setPrefWidth(200);
		exportButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Pane exportPane = new Pane();
				VBox exportBox = new VBox();
				exportBox.getChildren().addAll(new Label("This is where you can export a schedule"), backButton);
				exportPane.getChildren().add(exportBox);
				borderPane.setCenter(exportPane);
				primaryStage.setScene(mainWindow);
				primaryStage.show();
			}
		});


		nav.add(teamsButton, 0, 2);
		nav.add(createButton, 0, 3);
		nav.add(loadButton, 0, 4);
		nav.add(saveButton, 0, 5);
		nav.add(schedButton, 0, 6);
		nav.add(exportButton, 0, 7);
		
		borderPane.prefHeightProperty().bind(mainWindow.heightProperty());
		borderPane.prefWidthProperty().bind(mainWindow.widthProperty());
		borderPane.setLeft(nav);
		borderPane.setCenter(buttonPane);
		borderPane.setTop(title);
		root.getChildren().addAll(borderPane);
		primaryStage.setScene(mainWindow);
		primaryStage.show();

	}

	public static void createTeam(TextField nameField, TextField seedField, TextField homeField, TextField winField,
			TextField lossField, TextField tieField, ColorPicker colorPicker1, ColorPicker colorPicker2) {
		teamCount++;
		Team team = new Team();

		String name = nameField.getText();
		if (name.equals("")) {
			team.setName("Team " + teamCount);
		} else {
			team.setName(name);
		}

		String seedS = seedField.getText();
		if (seedS.equals("")) {
			team.setSeed(teamCount);
		} else {
			team.setSeed(Integer.parseInt(seedS));
		}

		String home = homeField.getText();
		if (home.equals("")) {
			team.setHome("Field " + teamCount);
		} else {
			team.setHome(home);
		}

		team.setColors(colorPicker1.getValue(), colorPicker2.getValue());

		String winsS = winField.getText();
		int wins;
		if (winsS.equals("")) {
			wins = 0;
		} else {
			wins = Integer.parseInt(winsS);
		}
		String lossS = lossField.getText();
		int losses;
		if (lossS.equals("")) {
			losses = 0;
		} else {
			losses = Integer.parseInt(lossS);
		}
		String tiesS = tieField.getText();
		int ties;
		if (tiesS.equals("")) {
			ties = 0;
		} else {
			ties = Integer.parseInt(tiesS);
		}
		team.setRecord(wins, losses, ties);

		teams.add(team);

	}

	public static void sortTeams() {
		quicksort(teams, 0, teamCount - 1);
	}

	public static void quicksort(ArrayList<Team> list, int start, int end) {
		if (start < end) {
			int index = partition(list, start, end);
			quicksort(list, start, index - 1);
			quicksort(list, index + 1, end);
		}
	}

	public static int partition(ArrayList<Team> list, int start, int end) {
		int pivot = list.get(end).getSeed();
		int i = start - 1;
		for (int j = start; j <= end - 1; j++) {
			if (list.get(j).getSeed() <= pivot) {
				i++;
				swap(i, j);
			}
		}
		swap(i + 1, end);
		return i + 1;
	}

	public static void swap(int a, int b) {
		Team temp = teams.get(a);
		teams.set(a, teams.get(b));
		teams.set(b, temp);
	}

	public static void generateRandomGames() {
		for (int i = 0; i < teamCount; i++) {
			for (int j = 0; j < 5; j++) {
				Random rand = new Random();
				int team = rand.nextInt(teamCount);

				String opponent = teams.get(team).getName();
				String field;
				int fieldBool = rand.nextInt(2) - 1;
				if (fieldBool == 0)
					field = teams.get(i).getHome();
				else
					field = teams.get(team).getHome();
				Game game = new Game(("Week " + i), teams.get(i).getName(), opponent, field);
				teams.get(i).addGame(game);
				//teams.get(team).addGame(game);

			}
		}
	}
	
	public static void generateSchedule (int num) { 
        ArrayList<int[]> pairs = new ArrayList<int[]>();
        int teamCountBye = teamCount;
		int byeIndex = -1;
        if (teamCount % 2 == 1){
        	teamCountBye++;
        	byeIndex = teamCountBye;
        }
		//int nPairs = teamCountBye*(teamCountBye-1)/2;
    	for(int i = 1; i <= teamCountBye; i++){
        	for(int j = i+1; j <= teamCountBye; j++){
        		int pair[] = {i, j};
        		pairs.add(pair);
        	}
        }
        
        ArrayList<int[]> pairsLeft = new ArrayList<int[]>(pairs);
        for (int i = 1; i <= num; i++){
        	ArrayList<Game> gamesToday = new ArrayList<Game>();
        	boolean playedToday[] = new boolean[teamCountBye];
        	for (int k = 0; k < playedToday.length; k++){
        		playedToday[k] = false;
        	}
        	if (i % teamCountBye == 0){
        		pairsLeft = new ArrayList<int[]>(pairs);
        	}
        	for (int j = 0; j < pairsLeft.size();){
        		int team1 = pairsLeft.get(j)[0];
        		int team2 = pairsLeft.get(j)[1];
        		if(!playedToday[team1-1] && !playedToday[team2-1]){
        			String field = "field";
        			String week = "Week " + i;
        			if (team1 == byeIndex){
        				field = teams.get(team2-1).getHome();
        				Game game = new Game(week, teams.get(team2-1).getName(), "BYE", field);
        				gamesToday.add(game);
        				teams.get(team2-1).games.add(game);
        			} else if (team2 == byeIndex){
        				field = teams.get(team1-1).getHome();
        				Game game = new Game(week, teams.get(team1-1).getName(), "BYE", field);
        				gamesToday.add(game);
        				teams.get(team1-1).games.add(game);
        			} else {
        				if (teams.get(team1-1).homeCount > teams.get(team2-1).homeCount){
        					field = teams.get(team2-1).getHome();
        					teams.get(team2-1).homeCount++;
        				} else if (teams.get(team1-1).homeCount < teams.get(team2-1).homeCount){
        					field = teams.get(team1-1).getHome();
        					teams.get(team1-1).homeCount++;
        				} else {
        					Random rand = new Random();
        					int randTeam = rand.nextInt(1);
        					field = teams.get(pairsLeft.get(j)[randTeam]-1).getHome();
        					teams.get(pairsLeft.get(j)[randTeam]-1).homeCount++;
        				}
        				Game game = new Game(week, teams.get(team1-1).getName(), teams.get(team2-1).getName(), field);
        				gamesToday.add(game);
        				teams.get(team1-1).games.add(game);
        				teams.get(team2-1).games.add(game);
        			}
        			playedToday[team1-1] = true;
        			playedToday[team2-1] = true;
        			pairsLeft.remove(j);
        		} else {
        			j++;
        		}
        	}
        	gameMaster.add(gamesToday);
        }
//        for (int i = 0; i < gameMaster.size(); i++){
//        	System.out.println("Week " + (i+1));
//        	for (int j = 0; j < gameMaster.get(i).size(); j++){
//        		System.out.println(gameMaster.get(i).get(j).toString());
//        	}
//        	System.out.println();
//        }
    } 
} 