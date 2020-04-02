package jms.tictactoe.ui;

/**
 * Enum for setting and getting winning rows for players X and O.
 * @author jaris
 */
public enum WinRow {
    X (""),
    O ("");
    
    public String winCode;
    
    WinRow(String winCode) {
        this.winCode = winCode;
    }
    
    public String getWinCode() {
        return this.winCode;
    }
    
    public void setWinCode(String winCode) {
        this.winCode = winCode;
    }
}
