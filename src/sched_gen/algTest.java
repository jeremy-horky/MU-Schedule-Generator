package sched_gen;

import java.util.ArrayList;

public class algTest {

	static int arr[] = {1, 2, 3, 4, 5, 6, 7, 8};
	static int nPairs = arr.length*(arr.length-1)/2;
	static ArrayList<int[]> pairs = new ArrayList<int[]>();
	
    public static void main (String[] args) { 
        if (arr.length % 2 == 1){
        	
        }
    	for(int i = 1; i <= arr.length; i++){
        	for(int j = i+1; j <= arr.length; j++){
        		int pair[] = {i, j};
        		pairs.add(pair);
        	}
        }
        
        for (int i = 0; i < nPairs; i++){
        	System.out.print(pairs.get(i)[0]);
        	System.out.print(pairs.get(i)[1]);
        	System.out.println();
        }
        
        int num = 12;
        ArrayList<int[]> pairsLeft = new ArrayList<int[]>(pairs);
        ArrayList<ArrayList<int[]>> games = new ArrayList<ArrayList<int[]>>();
        for (int i = 1; i <= num; i++){
        	ArrayList<int[]> gamesToday = new ArrayList<int[]>();
        	boolean playedToday[] = new boolean[arr.length];
        	for (int k = 0; k < playedToday.length; k++){
        		playedToday[k] = false;
        	}
        	if (i % arr.length == 0){
        		pairsLeft = new ArrayList<int[]>(pairs);
        	}
        	for (int j = 0; j < pairsLeft.size();){
        		int team1 = pairsLeft.get(j)[0];
        		int team2 = pairsLeft.get(j)[1];
        		if(!playedToday[team1-1] && !playedToday[team2-1]){
        			gamesToday.add(pairsLeft.get(j));
        			playedToday[team1-1] = true;
        			playedToday[team2-1] = true;
        			pairsLeft.remove(j);
        		} else {
        			j++;
        		}
        	}
        	games.add(gamesToday);
        }
        for (int i = 0; i < games.size(); i++){
        	System.out.print("Week " + (i+1) + "  \t");
        	for (int j = 0; j < games.get(i).size(); j++){
        		System.out.print(games.get(i).get(j)[0] + " vs " + games.get(i).get(j)[1] + "\t");
        	}
        	System.out.println();
        }
    } 
} 
