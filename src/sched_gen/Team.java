/**
 * Schedule Generator - designed for COEN 4650 - S2019
 *
 * @authors Jeremy Horky, Raaz Khoshnood, and Russell Reding
 * @version Created: //19, Modified //19
 */

package sched_gen;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Team {
	private String name;	//team name
	private int seed;		//team ranking relative to other teams
	private String home;	//team home field
	private Color[] colors;	//first team color
	private int[] record;	//teams W-L-T record
	
	public Team(){			//Initializes team to default values
		name = "Team " + Schedule_Generator.teamCount;
		seed = Schedule_Generator.teamCount;
		home = "Field " + Schedule_Generator.teamCount;
		colors = new Color[] {Color.rgb(255,255,255), Color.rgb(0, 0, 0)};
		record = new int[] {0,0,0};
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setSeed(int seed){
		this.seed = seed;
	}
	
	public int getSeed(){
		return seed;
	}
	
	public void setHome(String home){
		this.home = home;
	}
	
	public String getHome(){
		return home;
	}
	
	public void setColors(Color color1, Color color2){
		colors = new Color[] {color1, color2};
	}
	
	public Color[] getColors(){
		return colors;
	}
	
	public void setRecord(int wins, int losses, int ties){
		record = new int[] {wins, losses, ties};
	}
	
	public void addToRecord(int type){
		switch (type){
		case 0: record[0] = record[0] + 1; break;
		case 1: record[1] = record[1] + 1; break;
		case 2: record[2] = record[2] + 1; break;
		default: break;
		}
	}
	
	public Tab toTab(){
		Tab tab = new Tab();
	    tab.setText(name);
	    HBox namefield = new HBox();
	    namefield.getChildren().add(new Label("Team name: \t" + name));
	    namefield.setAlignment(Pos.CENTER);
	    HBox seedfield = new HBox();
	    seedfield.getChildren().add(new Label("Team seed: \t" + seed));
	    seedfield.setAlignment(Pos.CENTER);
	    HBox homefield = new HBox();
	    homefield.getChildren().add(new Label("Homefield: \t" + home));
	    homefield.setAlignment(Pos.CENTER);
	    HBox colorfield = new HBox();
	    colorfield.getChildren().add(new Label("Team colors: \t"));
	    colorfield.getChildren().add(new Rectangle(100, 100, colors[0]));
	    colorfield.getChildren().add(new Rectangle(100, 100, colors[1]));
	    colorfield.setAlignment(Pos.CENTER);
	    HBox recordfield = new HBox();
	    recordfield.getChildren().add(new Label("Team record: \t" + record[0] + "-" + record[1] + "-" + record[2]));
	    recordfield.setAlignment(Pos.CENTER);
	    VBox fields = new VBox();
	    fields.getChildren().add(namefield);
	    fields.getChildren().add(seedfield);
	    fields.getChildren().add(homefield);
	    fields.getChildren().add(colorfield);
	    fields.getChildren().add(recordfield);
	    tab.setContent(fields);
	    return tab;
	}
	
}
