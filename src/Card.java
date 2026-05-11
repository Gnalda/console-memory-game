public class Card {
	private char frontSign;
	private char backSign;
	private boolean flipped;
	private boolean isMatched;
	private String currSideSign;
	
	public char getFrontSign() {
		return frontSign;
	}

	public void setFrontSign(char frontSign) {
		this.frontSign = frontSign;
	}

	public char getBackSign() {
		return backSign;
	}

	public void setBackSign(char backSign) {
		this.backSign = backSign;
	}

	public boolean isFlipped() {
		return flipped;
	}

	public void setFlipped(boolean flipped) {
		this.flipped = flipped;
	}

	public boolean isMatched() {
		return isMatched;
	}

	public void setMatched(boolean isMatched) {
		this.isMatched = isMatched;
	}

	public String getCurrSideSign() {
		return currSideSign;
	}

	public void setCurrSideSign(String currSideSign) {
		this.currSideSign = currSideSign;
	}

	public Card(char frontSign, char backSign, boolean flipped, boolean isMatched, String currSideSign) {
		this.frontSign = frontSign;
		this.backSign = backSign;
		this.flipped = flipped;
		this.isMatched = isMatched;
		this.currSideSign = currSideSign;
	}
	
	public Card[][] flipCard(Card[][] card, int xPrev, int yPrev, boolean isCurrPos) {
		if(isCurrPos == true && isFlipped() == false){	
			setCurrSideSign("(" + String.valueOf(getFrontSign() + ")"));		
			setFlipped(true);
			
		}else if(isCurrPos == true && isFlipped() == true){
			setCurrSideSign("(" + String.valueOf(getBackSign() + ")"));
			setFlipped(false);
			
		}else if(isCurrPos == false && isFlipped() == true){
			setCurrSideSign(String.valueOf(getBackSign()));
			setFlipped(false);
		}
		return card;
	}
}