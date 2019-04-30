package sched_gen;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javafx.scene.paint.Color;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XML {
	public static int teamCount = 0;
	
	public static void write(ArrayList<Team> teams, int teamCount) {
		
		//System.out.println(teamCount);
		
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			//root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("data");
			doc.appendChild(rootElement);
			
			for(int i=0; i<teamCount; i++ ) {
				//<team id="i">
				Element team = doc.createElement("team");
				Attr attr = doc.createAttribute("id");
				attr.setValue(Integer.toString(i+1));
				team.setAttributeNode(attr);
				rootElement.appendChild(team);
				
				//<team id="i">
				//	<name>[Team Name]<name>
				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(teams.get(i).getName()));
				team.appendChild(name);
				
				//<team id="i">
				//	<seed>[Seed Number]<seed>
				Element seed = doc.createElement("seed");
				seed.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getSeed())));
				team.appendChild(seed);
				
				//<team id="i">
				//	<home>[Seed Number]<home>
				Element home = doc.createElement("home");
				home.appendChild(doc.createTextNode(teams.get(i).getHome()));
				team.appendChild(home);
				
				//<team id="i">
				//	<colorPrim>[Primary Color]<colorPrim>
				//	<colorSec>[Secondary Color]<colorSec>
				Element colorPrim = doc.createElement("colorPrim");
				Element colorSec = doc.createElement("colorSec");
				Color color [] = teams.get(i).getColors();
				String primColor = color[0].toString();
				String secColor = color[1].toString();
				colorPrim.appendChild(doc.createTextNode(primColor));
				colorSec.appendChild(doc.createTextNode(secColor));
				team.appendChild(colorPrim);
				team.appendChild(colorSec);
				
				Element wins = doc.createElement("wins");
				wins.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getWins())));
				team.appendChild(wins);
				
				Element losses = doc.createElement("losses");
				losses.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getLosses())));
				team.appendChild(losses);
				
				Element ties = doc.createElement("ties");
				ties.appendChild(doc.createTextNode(Integer.toString(teams.get(i).getTies())));
				team.appendChild(ties);
			}
			
			//Write out
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			System.out.println(System.getProperty("user.dir"));
			StreamResult result = new StreamResult(new File(System.getProperty("user.dir") + "\\data.xml"));
			
			transformer.transform(source, result);
			
			System.out.println("File was saved!");
			
		}
		
		catch (ParserConfigurationException pce){
			pce.printStackTrace();
		}
		catch (TransformerException tfe){
			tfe.printStackTrace();
		}
	}
	
	public static ArrayList<Team> onload(ArrayList<Team> teams, String file) {
		teamCount = 0;
		try{
			File fXmlFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("team");

			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					teamCount++;
					Element eElement = (Element) nNode;

					//System.out.println("name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
					//System.out.println("seed: " + eElement.getElementsByTagName("seed").item(0).getTextContent());
					//System.out.println("home: " + eElement.getElementsByTagName("home").item(0).getTextContent());
					//System.out.println("colorPrim: " + eElement.getElementsByTagName("colorPrim").item(0).getTextContent());
					//System.out.println("colorSec: " + eElement.getElementsByTagName("colorSec").item(0).getTextContent());
					//System.out.println("wins: " + eElement.getElementsByTagName("wins").item(0).getTextContent());
					//System.out.println("losses: " + eElement.getElementsByTagName("losses").item(0).getTextContent());
					//System.out.println("ties: " + eElement.getElementsByTagName("ties").item(0).getTextContent());

					
					Team team = new Team();

					String name = eElement.getElementsByTagName("name").item(0).getTextContent();
					if (name.equals("")) {
						team.setName("Team " + teamCount);
					} else {
						team.setName(name);
					}

					String seedS = eElement.getElementsByTagName("seed").item(0).getTextContent();
					if (seedS.equals("")) {
						team.setSeed(teamCount);
					} else {
						team.setSeed(Integer.parseInt(seedS));
					}

					String home = eElement.getElementsByTagName("home").item(0).getTextContent();
					if (home.equals("")) {
						team.setHome("Field " + teamCount);
					} else {
						team.setHome(home);
					}

					team.setColors(Color.web(eElement.getElementsByTagName("colorPrim").item(0).getTextContent()), Color.web(eElement.getElementsByTagName("colorSec").item(0).getTextContent()));

					String winsS = eElement.getElementsByTagName("wins").item(0).getTextContent();
					int wins;
					if (winsS.equals("")) {
						wins = 0;
					} else {
						wins = Integer.parseInt(winsS);
					}
					String lossS = eElement.getElementsByTagName("losses").item(0).getTextContent();
					int losses;
					if (lossS.equals("")) {
						losses = 0;
					} else {
						losses = Integer.parseInt(lossS);
					}
					String tiesS = eElement.getElementsByTagName("ties").item(0).getTextContent();
					int ties;
					if (tiesS.equals("")) {
						ties = 0;
					} else {
						ties = Integer.parseInt(tiesS);
					}
					team.setRecord(wins, losses, ties);

					teams.add(team);
				}
			}
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return teams;
	}
	
	public static int getTeamCount(){
		return teamCount;
	}
	
}
