package jms.tictactoe.domain;

/**
 *
 * @author jaris
 */
public enum WinRow {
    X3 ("XXX"),
    O3 ("OOO"),
    X4 ("XXXX"),
    O4 ("OOOO"),
    X5 ("XXXXX"),
    O5 ("OOOOO")
    ;
    
    private final String winCode;
    
    WinRow(String winCode) {
        this.winCode = winCode;
    }
    
    public String getWinCode() {
        return this.winCode;
    }
}
