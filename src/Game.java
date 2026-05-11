import java.util.Random;
import java.util.Scanner;

public class Game {
	public static void main(String[] args) throws InterruptedException {
		new Game();
	}
	
	public Game() throws InterruptedException {
		System.out.println("Willkommen zu einer Runde Memory!\n");
		selectMode();
	}
	
	public void selectMode() throws InterruptedException {
		System.out.println("Mit wie vielen Memorykarten moechtet ihr spielen? \n [4] 16 \t [6] 36 \t [8] 64");
		Scanner tastatur = new Scanner(System.in);

		int inputField = -1;
		do {
			String eingabe = tastatur.next();
			if ("4".equals(eingabe) || "6".equals(eingabe) || "8".equals(eingabe)) {
				inputField = Integer.parseInt(eingabe);
			} else {
				System.out.println("Ungueltige Eingabe. Bitte waehle 4, 6 oder 8.");
				inputField = -1;
			}
		} while (inputField != 4 && inputField != 6 && inputField != 8);

		createNewField(inputField, inputField);	
	}
	
	public void createNewField(int xLen, int yLen) throws InterruptedException  {
		char sign = 65;
		char coversign = 245;
		int counter = 1;
		System.out.println("Bewege dich mit den Tasten [W = Hoch, A = Links, D = Rechts, S = Runter] im Memoryraster. Druecke [F], um eine Karte auszuwaehlen.");
		
		//Startspielfeld wird erzeugt
		Card[][] card = new Card[xLen][yLen];
		for(int x = 0; x < card.length; x++) {
			for(int y = 0; y < card[0].length; y++) {
				card[x][y] = new Card(sign, coversign, false, false, String.valueOf(coversign));
				if(x == 0 && y == 0) {
					System.out.print(" (" + card[x][y].getBackSign() + ")\t");	
				}else {	
					System.out.print(card[x][y].getBackSign() + "\t");	
				}

				if(y == card[0].length - 1) {
					System.out.print( "\n ");
				}
				
				if(counter % 2 == 0) {
					sign++;
				}			
				
				counter++;
			}
		
		}	
		startMemory(shuffleCards(card));	
	}
	
	public void updateField(Card[][] card) throws InterruptedException {
		for(int x = 0; x < card.length; x++) {
			for(int y = 0; y < card[0].length; y++) {
				System.out.print(card[x][y].getCurrSideSign() + "\t");
				if(y == card[0].length - 1) {
					System.out.print( "\n");
				}
			}
		}	
	}
	
	public Card[][] shuffleCards(Card[][] card) {
		Random random = new Random();
		
		for (int i = card.length - 1; i > 0; i--) {
	        for (int j = card[i].length - 1; j > 0; j--) {
	            int m = random.nextInt(i + 1);
	            int n = random.nextInt(j + 1);
	            Card aux = card[i][j];
	            card[i][j] = card[m][n];
	            card[m][n] = aux;
	        }
	    }		
		return card;
	}
	
	public void startMemory(Card[][] card) throws InterruptedException {
		char prevCardSelection = 32;
		Player playerA = new Player("Spieler 1", true, 0, 0, prevCardSelection);
		Player playerB = new Player("Spieler 2", false, 0, 0, prevCardSelection);
		playerA.setX(0);
		playerA.setY(0);
		playerB.setX(0);
		playerB.setY(0);
			
		//Spielschleife - Die Spieler drehen Karten um. Spieler	1 beginnt initialisierend immer zuerst.
		do {
			//Spieler 1 ist am Zug
			if(playerA.isMyturn() == true) {
				updateField(playerA.moveToCards(card));
				System.out.println(playerA.getName() + ": " + "[" + playerA.getMatches() + "] " + "Paerchen" + "\t" + " <-->  " + playerB.getName() + ": " + "[" + playerB.getMatches() + "] " + "Paerchen");

				if(playerA.isMyturn() == false) {
					playerB.setMyturn(true);
					playerB.setX(playerA.getX());
					playerB.setY(playerA.getY());
					System.out.println("Leider daneben. Der Spieler 2 ist dran");
					card[playerA.getX()][playerA.getY()].flipCard(card, playerA.getX(), playerA.getY(), true);
					card[playerA.getxPrev()][playerA.getyPrev()].flipCard(card, playerA.getxPrev(), playerA.getyPrev(), false);
					Thread.sleep(1000);
					updateField(card);
					System.out.println(playerA.getName() + ": " + "[" + playerA.getMatches() + "] " + "Paerchen" + "\t" + " <-->  " + playerB.getName() + ": " + "[" + playerB.getMatches() + "] " + "Paerchen");
				}
				
			//Spieler 2 ist am Zug
			}else if(playerB.isMyturn() == true) {
				updateField(playerB.moveToCards(card));	
				System.out.println(playerA.getName() + ": " + "[" + playerA.getMatches() + "] " + "Paerchen" + "\t" + " <-->  " + playerB.getName() + ": " + "[" + playerB.getMatches() + "] " + "Paerchen");

				if(playerB.isMyturn() == false) {
					playerA.setMyturn(true);
					playerA.setX(playerB.getX());
					playerA.setY(playerB.getY());
					System.out.println("Leider daneben. Der Spieler 1 ist dran");
					card[playerB.getX()][playerB.getY()].flipCard(card, playerB.getX(), playerB.getY(), true);
					card[playerB.getxPrev()][playerB.getyPrev()].flipCard(card, playerB.getxPrev(), playerB.getyPrev(), false);
					Thread.sleep(1000);
					updateField(card);
					System.out.println(playerA.getName() + ": " + "[" + playerA.getMatches() + "] " + "Paerchen" + "\t" + " <-->  " + playerB.getName() + ": " + "[" + playerB.getMatches() + "] " + "Paerchen");
				}
			}		
		}while((playerA.getMatches() + playerB.getMatches()) < (card.length * card.length / 2));
			
		//Spielauswertung
		if(playerA.getMatches() > playerB.getMatches()) {
			System.out.println("\nHerzlichen Glueckwunsch " + playerA.getName() + ", du hast gewonnen!");
		}else if(playerA.getMatches() < playerB.getMatches()) {
			System.out.println("\nHerzlichen Glueckwunsch " + playerB.getName() + ", du hast gewonnen!");
		}else{
			System.out.println("Das Spiel endet unentschieden!");
		}			
	}		
}