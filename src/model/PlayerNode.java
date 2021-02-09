package model;

/**
 * Player Dataclass
 */
public class PlayerNode {
    /** The node's value */
    private int playerNum;

    /** quadrant II */
    private PlayerNode ul;

    /** quadrant I */
    private PlayerNode ur;

    /** quadrant III */
    private PlayerNode ll;

    /** quadrant IV */
    private PlayerNode lr;

    /**
     * Construct a leaf node with no children.
     * @param playerNum node value
     */
    public PlayerNode(int playerNum) {
        this(playerNum, null, null, null, null);
    }

    /**
     * Construct a quad tree node.
     *
     * @param playerNum the node's value
     * @param ul the upper left sub-node
     * @param ur the upper right sub-node
     * @param ll the lower left sub-node
     * @param lr the lower right sub-node
     */
    public PlayerNode(int playerNum, PlayerNode ul, PlayerNode ur, PlayerNode ll, PlayerNode lr) {
        this.playerNum = playerNum;
        this.ul = ul;
        this.ur = ur;
        this.ll = ll;
        this.lr = lr;
    }

    /**
     * Get the node's value.
     *
     * @return node's value
     */
    public int getPlayerNum() { return this.playerNum; }

    /**
     * Get the upper left sub-node.
     *
     * @return upper left sub-node
     */
    public PlayerNode getUpperLeft() { return this.ul; }

    /**
     * Get the upper right sub-node.
     *
     * @return upper right sub-node
     */
    public PlayerNode getUpperRight() { return this.ur; }

    /**
     * Get the lower left sub-node.
     *
     * @return lower left sub-node
     */
    public PlayerNode getLowerLeft() { return this.ll; }

    /**
     * Get the lower right sub-node
     *
     * @return lower right sub-node
     */
    public PlayerNode getLowerRight() { return this.lr; }

    @Override
    public String toString() {
        return String.valueOf(this.playerNum);
    }
}
