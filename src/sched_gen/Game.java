/**
 * Schedule Generator - designed for COEN 4650 - S2019
 *
 * @authors Jeremy Horky, Raaz Khoshnood, and Russell Reding
 * @version Created: //19, Modified //19
 */
package sched_gen;

public class Game {
	private String team1;
	private String team2;
	private String field;
	private String week;
	
	public Game(String week, String team1, String team2, String field){
		this.week = week;
		this.team1 = team1;
		this.team2 = team2;
		this.field = field;
	}
	
	public String toString(){
		return week + ":\t" + team1 + "\t vs. \t" + team2 + "\t at \t" + field;
	}

	public String getWeek() {
		return week;
	}
	
	public String getFirstTeam() {
		return team1;
	}
	
	public String getSecondTeam() {
		return team2;
	}
	
	public String getLocation() {
		return field;
	}
}
