package model;

/**
 * Card Dataclass
 *
 * Information the cards hold:
 *      Number value of the card (11 for jack, 12 for queen, 13 for king, 14 for Ace)
 *      Suit of the card (Spades, Clubs, Diamonds, Hearts)
 *      If the card is a face card (easier for calculations)
 */
public class Card {
    //Value of the card
    private int cardNum;
    //suit of the card
    private String suit;

    /**
     * Default Constructor
     * No card info given
     */
    public Card() {
        this.cardNum = 0;
        this.suit = "";
    }

    /**
     * Constructor with given parameters
     * @param cardNum - value of the card
     * @param suit - suit of the card
     */
    public Card(int cardNum, String suit){
        this.cardNum = cardNum;
        this.suit = suit;
    }

    /**
     * getCardNum() - getter method
     * @return - cardNum
     */
    public int getCardNum() { return this.cardNum; }

    /**
     * getSuit() - getter method
     * @return - suit
     */
    public String getSuit() { return this.suit; }

    /**
     * toString() - overloaded method
     * @return - String value of a Card
     */
    @Override
    public String toString(){
        //Create a resultant string
        String result = "";

        //Check if it's a face card or not
        //Check the value, if it's above 11 it's special, else it's just the number
        switch (cardNum) {
            case 11 -> result += "Jack";
            case 12 -> result += "Queen";
            case 13 -> result += "King";
            case 14 -> result += "Ace";
            case 0 -> result += "ZERO";
            default -> result += "" + cardNum;
        }

        //Now check suit
        switch (this.suit) {
            case "Heart" -> result += " of Hearts";
            case "Clubs" -> result += " of Clubs";
            case "Spades" -> result += " of Spades";
            case "Diamonds" -> result += " of Diamonds";
            default -> result += " of NO SUIT";
        }

        //Finally, return this result
        return result;
    }
}
