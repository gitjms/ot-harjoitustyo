package jms.tictactoe.ui;

import javafx.geometry.Insets;
import jms.tictactoe.domain.GameSquare;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import jms.tictactoe.domain.ScoreService;

/**
 * Class for creating a game area.
 * @author jaris
 */
public final class GameArea {
    
    private GameSquare gameSquare;
    private GridPane gridPane;
    private final ScoreService scoreService;
    
    /**
     * Constructor for the class GameArea.
     * @param scoreService instance of ScoreService class
     */
    public GameArea(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
    
    /**
     * Method for creating the VBox for game area main components.
     * top:     Text Label for game number
     * middle:  Text Label for whose turn/who wins
     * bottom:  GridPane for X and O squares
     * @return gameArea
     */
    public VBox createArea() {
        this.setGridpane();
        String gameLabelText = "Game " + (this.scoreService.getAmount() + 1);
        Label textLabel = this.getLabel("X:s turn", Color.LIGHTPINK, FontWeight.BOLD, 40);
        Label gameLabel = this.getLabel(gameLabelText, Color.LIGHTGREEN, FontWeight.BOLD, 30);
        // actual squares for X and O markings
        String[][] squares = new String[GameSize.SIZE.getGameSize()][GameSize.SIZE.getGameSize()];
        // get square button from the class GameSquare
        this.gameSquare = new GameSquare(textLabel, this.getScoreService());
        for (int x = 0; x < GameSize.SIZE.getGameSize(); x++) {
            for (int y = 0; y < GameSize.SIZE.getGameSize(); y++) {
                String id = Integer.toString(y) + " " + Integer.toString(x);
                squares[x][y] = id;
                Button newSquare = this.gameSquare.createSquare(squares, id);
                this.getGridpane().add(newSquare, x, y);
            }
        }
        // both components into one box to be sent back to caller in class TicTacToeUI
        VBox gameArea = new VBox(gameLabel, textLabel, this.getGridpane());
        gameArea.setAlignment(Pos.CENTER);
        return gameArea;
    }

    /**
     * Method for getting stylized GridPane for X:s and O:s.
     */
    public void setGridpane() {
        this.gridPane = new GridPane();
        this.gridPane.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        this.gridPane.setMinSize(450, 320);
        this.gridPane.setMaxSize(450, 320);
        this.gridPane.setAlignment(Pos.CENTER);
        this.gridPane.setVgap(30 / GameSize.SIZE.getGameSize());
        this.gridPane.setHgap(30 / GameSize.SIZE.getGameSize());
    }
    
    /**
     * Method for getting stylized Label for scores VBox and current game indication.
     * @param text label text
     * @param color font color
     * @param fWeight font weight
     * @param fSize font size
     * @return label
     */
    public Label getLabel(String text, Color color, FontWeight fWeight, int fSize) {
        Label label = new Label(text);
        label.setTextFill(color);
        label.setAlignment(Pos.CENTER);
        Bloom bloomEffect = new Bloom();
        bloomEffect.setThreshold(0.75);
        label.setEffect(bloomEffect);
        label.setFont(Font.font("Cambria", fWeight, fSize));
        label.setTextAlignment(TextAlignment.CENTER);
        return label;
    }
    
    private ScoreService getScoreService() {
        return this.scoreService;
    }
    
    public GridPane getGridpane() {
        return this.gridPane;
    }
    
}
