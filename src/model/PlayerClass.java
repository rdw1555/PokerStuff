package model;

import java.util.Arrays;

/**
 * Player Dataclass
 *
 * What information does a Player contain?
 *      -Their player number (how they're identified by the comp, may not be necessary)
 *      -Their nickname
 *      -The cards in their hand
 *      -How many chips they have
 *      -If they've busted out
 */
public class PlayerClass {
    /** The player's number */
    private int playerNum;

    /** Player's nickname */
    private String playerName;

    /** Player's hand */
    private Card[] playerHand;

    /** Player's chips */
    private int playerChips;

    /** If the player's lost */
    private boolean playerLost;

    /**
     * Default Constructor
     * No player info given
     */
    public PlayerClass() {
        this.playerNum = -1;
        this.playerName = "";
        this.playerHand = null;
        this.playerChips = 0;
        this.playerLost = false;
    }

    /**
     * Constructor
     * @param playerNum - the player's number
     * @param playerName - the player's name
     * @param playerHand - the player's current hand
     * @param playerChips - the player's chip count
     * @param playerLost - if the player has lost or not
     */
    public PlayerClass(int playerNum, String playerName, Card[] playerHand, int playerChips, boolean playerLost) {
        this.playerNum = playerNum;
        this.playerName = playerName;
        this.playerHand = playerHand;
        this.playerChips = playerChips;
        this.playerLost = playerLost;
    }

    //----------------------GETTER METHODS----------------------
    /**
     * getPlayerNum
     * Get the player's number
     * @return player's num
     */
    public int getPlayerNum() { return this.playerNum; }

    /**
     * getPlayerName
     * Get the player's nickname
     * @return player's nickname
     */
    public String getPlayerName() { return this.playerName; }

    /**
     * getPlayerHand
     * Get the player's hand
     * @return the player's hand
     */
    public Card[] getPlayerHand() { return this.playerHand; }

    /**
     * getPlayerChips
     * Get the player's chip count
     * @return chip count
     */
    public int getPlayerChips() { return this.playerChips; }

    /**
     * getPlayerLost
     * Tell if the player has lost
     * @return player lost boolean
     */
    public boolean getPlayerLost() { return this.playerLost; }
    //----------------------------------------------------------


    //----------------------SETTER METHODS----------------------
    /**
     * setPlayerName
     * Set's the players name to the given variable (can be used if they change their name)
     * @param name - the name changed by the player
     */
    public void setPlayerName(String name) { this.playerName = name; }

    /**
     * setPlayerHand
     * Set's the player's hand to the given cards
     * @param cards - the cards dealt to the player
     */
    public void setPlayerHand(Card[] cards) { this.playerHand = cards; }

    /**
     * setPlayerChips
     * Increase the player's chip amount
     * (this can also be used to decrease it, just use a negative number)
     */
    public void setPlayerChips(int amount) { this.playerChips += amount; }

    /**
     * setPlayerLost
     * Triggered when the player loses
     */
    public void setPlayerLost() { this.playerLost = true; }

    //Note that there's no method to set the player's number
    //This is due to the fact that the player's Identification Number should never be changed throughout the game
    //----------------------------------------------------------

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        //Add a new line
        result.append("\n");

        //Add the Player Number
        result.append("\nPlayer's Num: ").append(this.getPlayerNum());

        //Add the Player Name
        result.append("\nPlayer's Name: ").append(this.getPlayerName());

        //Add the Player's Hand
        result.append("\nPlayer's Hand: ").append((Arrays.toString(this.getPlayerHand())));

        //Add the Player's chip count
        result.append("\nPlayer's Chips: ").append(this.getPlayerChips());

        //Say if the Player has lost
        result.append("\nHas the Player Lost? ").append(this.getPlayerLost());

        return result.toString();
    }
}
