import java.util.Scanner;

public class Player {
	private String name;
	private boolean myturn;
	private int maxFlip;
	private char prevCardSelection;
	private int matches;
	private int x, y;
	private int xPrev, yPrev;
	
	public Card[][] selectCard(Card[][] card) {			
		if(getMaxFlip() < 2) { 																				//2x Umdrehen
			if(card[getX()][getY()].isFlipped() == false) {													//Wenn die Karte nicht umgedreht ist					
				if(getMaxFlip() == 0) {																		//Eine Karte bisher umgedreht
					setPrevCardSelection(card[getX()][getY()].getFrontSign());								//Zeichen erster Umgedrehten Karte wird gespeichert
					setxPrev(getX());
					setyPrev(getY());
					card[getX()][getY()].flipCard(card,getxPrev(),getyPrev(), true);				//Drehe erste Karte um
					setMaxFlip(getMaxFlip() + 1);
				}else if(getMaxFlip() == 1) {																//Zweite Karte wird umgedreht
					if(card[getX()][getY()].getFrontSign() == getPrevCardSelection()) {						//Karte mit der vorherigen gespeicherten Karte identisch?
						card[getX()][getY()].flipCard(card,getX(),getY(), true);
						setMatches(getMatches() + 1);														//Match fuer Spieler wird gespeichert
						setMaxFlip(0);
					}else {
						setMyturn(false);															//Anderer Spieler ist dran
						setMaxFlip(0);
						card[getX()][getY()].flipCard(card,getX(),getY(), true);
					}					
				}
			}
		}
		return card;
	}

	public Card[][] moveToCards(Card[][] card) {
		int xMin = 0;
		int xMax = card.length;
		int yMin = 0;
		int yMax = card.length;

		Scanner tastatur = new Scanner(System.in);
		
		switch(tastatur.next()) {
			case "d" : //Nach rechts bewegen
				if(getY() < yMax - 1) {
					if(card[getX()][getY()].isFlipped() == true) {		
						card[getX()][getY()].setCurrSideSign(String.valueOf(card[getX()][getY()].getFrontSign()));
					}else {
						card[getX()][getY()].setCurrSideSign(String.valueOf(card[getX()][getY()].getBackSign()));
					}
					
					setY(getY() + 1);
					if(card[getX()][getY()].isFlipped() == true) {
						card[getX()][getY()].setCurrSideSign( "(" + card[getX()][getY()].getFrontSign() + ") ");
					}else {
						card[getX()][getY()].setCurrSideSign( "(" + card[getX()][getY()].getBackSign() + ") ");
					}

					setX(getX());
					System.out.println( "[" + getX() + "]" + "[" + getY() + "]" + " Bisher umgedreht: " + getMaxFlip() +  " - Am Zug: " + getName());		
				}
			break;
			
			case "a" : //Nach links bewegen
				if(getY() > yMin) {
					if(card[getX()][getY()].isFlipped() == true) {
						card[getX()][getY()].setCurrSideSign(String.valueOf(card[getX()][getY()].getFrontSign()));
					}else {
					card[getX()][getY()].setCurrSideSign(String.valueOf(card[getX()][getY()].getBackSign()));
					}
					
					setY(getY() - 1);
					if(card[getX()][getY()].isFlipped() == true) {
						card[getX()][getY()].setCurrSideSign( "(" + card[getX()][getY()].getFrontSign() + ") ");
					}else {
						card[getX()][getY()].setCurrSideSign( "(" + card[getX()][getY()].getBackSign() + ") ");
					}
					setX(getX());
					System.out.println( "[" + getX() + "]" + "[" + getY() + "]" + " Bisher umgedreht: " + getMaxFlip() +  " - Am Zug: " + getName());
				}		
			break;

			case "s" : //Nach unten bewegen
				if(getX() < xMax - 1) {
					if(card[getX()][getY()].isFlipped() == true) {
						card[getX()][getY()].setCurrSideSign(String.valueOf(card[getX()][getY()].getFrontSign()));
					}else {
						card[getX()][getY()].setCurrSideSign(String.valueOf(card[getX()][getY()].getBackSign()));
					}
					setX(getX() + 1);
					if(card[getX()][getY()].isFlipped() == true) {
						card[getX()][getY()].setCurrSideSign( "(" + card[getX()][getY()].getFrontSign() + ") ");
					}else {
						card[getX()][getY()].setCurrSideSign( "(" + card[getX()][getY()].getBackSign() + ") ");
					}
					setY(getY());
					System.out.println( "[" + getX() + "]" + "[" + getY() + "]" + " Bisher umgedreht: " + getMaxFlip() +  " - Am Zug: " + getName());
				}
			break;

			case "w" : //Nach oben bewegen
				if(getX() > xMin) {
					if(card[getX()][getY()].isFlipped() == true) {
						card[getX()][getY()].setCurrSideSign(String.valueOf(card[getX()][getY()].getFrontSign()));
					}else {
					card[getX()][getY()].setCurrSideSign(String.valueOf(card[getX()][getY()].getBackSign()));
					}
					setX(getX() - 1);
					if(card[getX()][getY()].isFlipped() == true) {
						card[getX()][getY()].setCurrSideSign( "(" + card[getX()][getY()].getFrontSign() + ") ");
					}else{
						card[getX()][getY()].setCurrSideSign( "(" + card[getX()][getY()].getBackSign() + ") ");
					}
					setY(getY());
					System.out.println( "[" + getX() + "]" + "[" + getY() + "]" + " Bisher umgedreht: " + getMaxFlip() +  " - Am Zug: " + getName());
				}
			break;
				
			case "f" : //Karte umdrehen
				setX(getX());
				setY(getY());
				selectCard(card);
				System.out.println( "[" + getX() + "]" + "[" + getY() + "]" + " Bisher umgedreht: " + getMaxFlip() +  " - Am Zug: " + getName());
			break;
		}
		return card;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMyturn() {
		return myturn;
	}

	public void setMyturn(boolean myturn) {
		this.myturn = myturn;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}	

	public int getxPrev() {
		return xPrev;
	}

	public void setxPrev(int xPrev) {
		this.xPrev = xPrev;
	}

	public int getyPrev() {
		return yPrev;
	}

	public void setyPrev(int yPrev) {
		this.yPrev = yPrev;
	}

	public int getMaxFlip() {
		return maxFlip;
	}

	public void setMaxFlip(int maxFlip) {
		this.maxFlip = maxFlip;
	}
	
	public char getPrevCardSelection() {
		return prevCardSelection;
	}

	public void setPrevCardSelection(char prevCardSelection) {
		this.prevCardSelection = prevCardSelection;
	}

	public Player(String name, boolean myturn, int matches, int maxFlip, char preCardSelection) {
		this.name = name;
		this.myturn = myturn;
		this.matches = matches;
		this.maxFlip = maxFlip;
		this.prevCardSelection = preCardSelection;
	}
}