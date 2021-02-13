package ptui;

import model.Card;
import model.PlayerClass;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class for the PokerLogic
 * This is where (spoiler alert) all of the logic for poker will be handled
 * As of rn, this has a runner method thrown at the bottom - this will eventually cease to exist, and will strictly be for testing purposes
 * Eventually, this is basically just gonna handle housing a deck of cards, shuffling that deck, removing cards from the deck,
 * adding those cards to player hands, putting cards back into the deck from certain players, and (most importantly) betting
 *
 * (Should operate on a hand by hand basis)
 *
 * Please lmk if I forgot any important poker functions like a dumbass
 */
public class PokerLogic {

    ArrayList<String> playerNames;

    //Default constructor
    public PokerLogic(){
        //Do nothing for now
    }

    //Sets the playerNames ArrayList to the given ArrayList
    public void setPlayerNames(ArrayList<String> playerNames){ this.playerNames = playerNames; }

    /**
     * makeDeck() - Creates the deck
     * @return - the deck that is created
     */
    public ArrayList<Card> makeDeck(){
        //Create a temporary ArrayList
        ArrayList<Card> deck = new ArrayList<>();

        //Loop through all of the possible card values
        for(int i = 2; i < 15; i++) {
            //Create temporary Cards for each possible suit
            Card tempHeart = new Card(i, "Heart");
            Card tempSpade = new Card(i, "Spade");
            Card tempClub = new Card(i, "Club");
            Card tempDiamond = new Card(i, "Diamond");

            //Add the temporary Cards to the deck
            deck.add(tempHeart);
            deck.add(tempSpade);
            deck.add(tempClub);
            deck.add(tempDiamond);
        }

        //Return the deck
        return deck;
    }

    /**
     * playersList() - compiles a complete list of all the players in the game
     * @return - an ArrayList of all the players
     */
    public ArrayList<PlayerClass> playersList(){
        //Create a temporary ArrayList of Players
        ArrayList<PlayerClass> players = new ArrayList<>();

        //Loop through the number of players
        for(int i = 0; i < playerNames.size(); i++){
            //Create a temporary player (10000 default chips, they haven't lost yet)
            PlayerClass tempPlayer = new PlayerClass(i, playerNames.get(i), null, 10000, false);

            //Add that temporary player to the list of players
            players.add(tempPlayer);
        }

        //Return the list of players
        return players;
    }

    public String printPlayerInfo(ArrayList<PlayerClass> players){
        StringBuilder result = new StringBuilder();

        for(PlayerClass p : players){
            result.append(p.toString());
        }

        return result.toString();
    }

    /**
     * toString method
     * @return - A string representation of something idk
     */
    @Override
    public String toString(){
        String result = "";

        result += "nothing coded yet lmao";

        return result;
    }

    /**
     * main method
     * @param args - system arguments
     */
    public static void main(String[] args) {
        //Create a null scanner to read from the input file
        Scanner s = new Scanner(System.in);

        //Create a PokerLogic object
        PokerLogic pl = new PokerLogic();

        //TESTING ----- Player List Outputting
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add("Rick");
        playerNames.add("Colby");
        playerNames.add("Julian");

        pl.setPlayerNames(playerNames);

        System.out.println(pl.printPlayerInfo(pl.playersList()));
    }
}