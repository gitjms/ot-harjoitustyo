package jms.tictactoe.ui;

/**
 * Enum for setting and getting game size: 3x3, 4x4, or 5x5.
 * Default is 3x3.
 * @author jaris
 */
public enum GameSize {
    SIZE (3);
    
    public int gameSize;
    
    GameSize(int gameSize) {
        this.gameSize = gameSize;
    }
    
    public int getGameSize() {
        return this.gameSize;
    }
    
    public void setGameSize(int gameSize) {
        this.gameSize = gameSize;
    }
}
