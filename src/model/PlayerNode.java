package model;

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
public class PlayerNode {
    /** The player's number */
    private int playerNum;

    /** Player's nickname */
    private String playerName;

    /** Player's hand */
    //not sure how to do this
    //Maybe another dataclass for the deck of cards?
    //private something playerHand;

    /** Player's chips */
    private int playerChips;

    /** If the player's lost */
    private boolean playerLost;

    /**
     * Default Constructor
     * No player info given
     */
    public PlayerNode() {
        this.playerNum = 0;
        this.playerName = "";
        //this.playerHand = null;
        this.playerChips = 0;
        this.playerLost = false;
    }

    /**
     * Constructor
     * @param playerNum - the player's number
     * @param playerName - the player's name
     * @param playerChips - the player's chip count
     * @param playerLost - if the player has lost or not
     */
    public PlayerNode(int playerNum, String playerName, int playerChips, boolean playerLost) {
        this.playerNum = playerNum;
        this.playerName = playerName;
        //this.playerHand = playerHand;
        this.playerChips = playerChips;
        this.playerLost = playerLost;
    }

    //----------------------GETTER METHODS----------------------
    /**
     * Get the player's number
     * @return player's num
     */
    public int getPlayerNum() { return this.playerNum; }

    /**
     * Get the player's nickname
     * @return player's nickname
     */
    public String getPlayerName() { return this.playerName; }

    /**
     * Get the player's hand
     * @return the player's hand
     */
    //public something getPlayerHand() { return this.playerHand; }

    /**
     * Get the player's chip count
     * @return chip count
     */
    public int getPlayerChips() { return this.playerChips; }

    /**
     * Tell if the player has lost
     * @return player lost boolean
     */
    public boolean getPlayerLost() { return this.playerLost; }
    //----------------------------------------------------------


    //----------------------SETTER METHODS----------------------
    /**
     * Set's the players name to the given variable (can be used if they change their name)
     * @param name - the name changed by the player
     */
    public void setPlayerName(String name) { this.playerName = name; }

    /**
     * Increase the player's chip amount
     * (this can also be used to decrease it, just use a negative number)
     */
    public void setPlayerChips(int amount) { this.playerChips += amount; }

    /**
     * Triggered when the player loses
     */
    public void setPlayerLost() { this.playerLost = true; }
    //----------------------------------------------------------

    @Override
    public String toString() {
        return String.valueOf(this.playerNum);
    }
}
