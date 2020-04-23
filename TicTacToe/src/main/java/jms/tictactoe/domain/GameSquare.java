package jms.tictactoe.domain;

import javafx.geometry.Insets;
import javafx.scene.effect.Bloom;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;

//import jms.tictactoe.ui.BackGroundStyle;
//import jms.tictactoe.ui.BorderStyle;
import jms.tictactoe.ui.GameSize;
import jms.tictactoe.ui.WinRow;


/**
 * Class for creating clickable squares for the game area and checking winning rows.
 * @author jaris
 */
public final class GameSquare {

    private final ScoreService scoreService;
    private boolean finished;
    private final Label textLabel;
            
    public GameSquare(Label textLabel, ScoreService scoreService) {
        this.textLabel = textLabel;
        this.scoreService = scoreService;
        this.finished = false;
    }
    
    /**
     * Method for creating the square buttons and their operations.Buttons are for X and O markings.
     * Method uses method "isFinished" to check if someone wins.
     * @param squares array to store X and O marks
     * @param id array item id:s
     * @return gameSquare
     */
    public Button createSquare(String[][] squares, String id) {
        final Button gameSquare = new Button(" ");
        this.getSquareBase(gameSquare, id);
        gameSquare.setOnMouseClicked((MouseEvent event) -> {
            setOnMouseClicked(gameSquare, squares, id);
        });
        return gameSquare;
    }
    
    /**
     * Method for checking if the game is finished.
     * @param squaresTocheck array to store X and O marks
     * @return false if game is not finished, true if someone wins or game is a draw
     */
    public boolean isFinished(String[][] squaresTocheck) {
        if (!this.getFinished()) {
            if (this.loopHorizontalAndVertical(squaresTocheck)) {
                return true;
            }
            String row3 = "";
            String row4 = "";
            if (this.loopDiagonals(squaresTocheck, row3, row4)) {
                return true;
            }
            String row = "";
            if (this.loopAllForDraw(squaresTocheck, row)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method for looping the game grid horizontally and vertically.
     * @param squaresTocheck
     * @return 
     */
    private boolean loopHorizontalAndVertical(String[][] squaresTocheck) {
        for (int i = 0; i < GameSize.SIZE.getGameSize(); i++) {
            String row1 = "";
            String row2 = "";
            for (int j = 0; j < GameSize.SIZE.getGameSize(); j++) {
                row1 += squaresTocheck[i][j];
                row2 += squaresTocheck[j][i];
            }
            if (checkRow(row1)) {
                return true;
            }
            if (checkRow(row2)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Method for looping the game grid diagonally.
     * @param squaresTocheck
     * @param row3
     * @param row4
     * @return 
     */
    private boolean loopDiagonals(String[][] squaresTocheck, String row3, String row4) {
        for (int i = 0; i < GameSize.SIZE.getGameSize(); i++) {
            row3 += squaresTocheck[i][i];
            row4 += squaresTocheck[GameSize.SIZE.getGameSize() - 1 - i][i];
        }
        if (checkRow(row3)) {
            return true;
        }
        return checkRow(row4);
    }
    
    /**
     * Method for looping the whole grid for draw.
     * @param squaresTocheck
     * @param row
     * @return 
     */
    private boolean loopAllForDraw(String[][] squaresTocheck, String row) {
        for (int i = 0; i < GameSize.SIZE.getGameSize(); i++) {
            for (int j = 0; j < GameSize.SIZE.getGameSize(); j++) {
                row += squaresTocheck[i][j];
            }
        }
        return checkDraw(row);
    }

    /**
     * Method for checking rows and to announce winner.
     * @param rowTocheck array to store X and O marks
     * @return false if no ready row, true if ready row
     */
    public boolean checkRow(String rowTocheck) {
        if (rowTocheck.equals(WinRow.X.getWinCode())) {
            this.setFinished(true);
            this.getTextlabel().setText("X wins!");
            this.getTextlabel().setTextFill(Color.LIGHTPINK);
            this.scoreService.createScore("X", this.scoreService.getPoints("X") + 1);
            this.scoreService.createScore("O", this.scoreService.getPoints("O"));
            this.scoreService.setAmount(this.scoreService.getAmount() + 1);
            return true;
        } else if (rowTocheck.equals(WinRow.O.getWinCode())) {
            this.setFinished(true);
            this.getTextlabel().setText("O wins!");
            this.getTextlabel().setTextFill(Color.LEMONCHIFFON);
            this.scoreService.createScore("X", this.scoreService.getPoints("X"));
            this.scoreService.createScore("O", this.scoreService.getPoints("O") + 1);
            this.scoreService.setAmount(this.scoreService.getAmount() + 1);
            return true;
        }
        return false;
    }

    /**
     * Method for checking if the game is a draw.
     * @param rowTocheck array to store X and O marks
     * @return false if not a draw, true if a draw
     */
    public boolean checkDraw(String rowTocheck) {
        if (!rowTocheck.contains(" ")) {
            this.setFinished(true);
            this.getTextlabel().setText("Draw!");
            this.getTextlabel().setTextFill(Color.LIGHTSTEELBLUE);
            this.scoreService.setAmount(this.scoreService.getAmount() + 1);
            this.scoreService.setDraws(this.scoreService.getDraws() + 1);
            return true;
        }

        return this.getFinished();
    }
    
    /**
     * Method for setting square button appearance and textlabel text/color.
     * @param square
     * @param which
     * @param color1
     * @param color2
     * @param turn
     */
    public void setSquare(Button square, String which, Color color1, Color color2, String turn) {
        square.setText(which);
        square.setFont(Font.font("Monospaced", FontWeight.BOLD, 150 / GameSize.SIZE.getGameSize()));
        square.setTextFill(color1);
        this.textLabel.setText(turn);
        this.textLabel.setTextFill(color2);
    }
    
    public void setOnMouseClicked(Button square, String[][] squares, String id) {
        String whoseTurn = this.textLabel.getText();
        if (!this.isFinished(squares) && !this.getFinished()) {
            if (square.getText().trim().isEmpty()) {
                int x = Integer.parseInt(id.split(" ")[0]);
                int y = Integer.parseInt(id.split(" ")[1]);
                if (whoseTurn.contains("X")) {
                    this.setSquare(square, "X", Color.LIGHTPINK, Color.LEMONCHIFFON, "O:s turn");
                    squares[x][y] = "X";
                } else {
                    this.setSquare(square, "O", Color.LEMONCHIFFON, Color.LIGHTPINK, "X:s turn");
                    squares[x][y] = "O";
                }
            }
            this.isFinished(squares);
        } else {
            square.disarm();
        }
    }
    
    /**
     * Method for getting square button with basic appearance.
     * @param base
     * @param id
     * @return bloomEffect
     */
        
    private Button getSquareBase(Button base, String id) {
        base.setId(id);
        base.setFont(Font.font("Monospaced", FontWeight.BOLD, 150 / GameSize.SIZE.getGameSize()));
        base.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        base.setEffect(this.getBloomEffect());
        base.setBorder(new Border(new BorderStroke(Color.LIGHTBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        return base;
    }
    
    /**
     * Method for stylizing text.
     * @return bloomEffect
     */
    private Bloom getBloomEffect() {
        Bloom bloomEffect = new Bloom();
        bloomEffect.setThreshold(0.75);
        return bloomEffect;
    }
    
    private Label getTextlabel() {
        return textLabel;
    }
    
    public void setFinished(boolean which) {
        this.finished = which;
    }
    
    private boolean getFinished() {
        return finished;
    }

}
