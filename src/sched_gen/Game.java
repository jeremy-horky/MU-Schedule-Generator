/**
 * Schedule Generator - designed for COEN 4650 - S2019
 *
 * @authors Jeremy Horky, Raaz Khoshnood, and Russell Reding
 * @version Created: //19, Modified //19
 */
package sched_gen;

public class Game {
	private int day;
	private int month;
	private int year;
	private String opponent;
	private String field;
	
	public Game(int day, int month, int year, String opponent, String field){
		this.day = day;
		this.month = month;
		this.year = year;
		this.opponent = opponent;
		this.field = field;
	}
	
	public String toString(){
		return "vs. " + opponent + " at " + field + " | " + month + "/" + day + "/" + year;
	}
	
	public int getDay(){
		return day;
	}
	
	public int getMonth(){
		return month;
	}
	
	public int getYear(){
		return year;
	}
	
	public int getDate(){
		return month * 30 + day;
	}
	
}
