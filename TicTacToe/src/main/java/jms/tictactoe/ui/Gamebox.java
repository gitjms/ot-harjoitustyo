package jms.tictactoe.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import jms.tictactoe.dao.FileScoreDao;
import jms.tictactoe.domain.Game;
import jms.tictactoe.domain.ScoreService;

/**
 * Class for creating a game area.
 * @author jaris
 */
public final class Gamebox {
    
    private GameSquare gameSquare;
    private GridPane gridPane;
    private final Game game;
    private final ScoreService scoreService;
    private final FileScoreDao fileScoreDao;
    
    public Gamebox(ScoreService scoreService, FileScoreDao fileScoreDao) throws Exception {
        this.scoreService = scoreService;
        this.fileScoreDao = fileScoreDao;
        this.game = new Game(1, this.scoreService);
    }
    
    /**
     * Method for creating the VBox for game area main components:
     * top:     Text Label for game number
     * middle:  Text Label for whose turn/who wins
     * bottom:  GridPane for X and O squares
     * @param addGames
     * @return gameBox
     * @throws java.lang.Exception
     */
    public VBox createBox(int addGames) throws Exception {
        this.setGridpane();
        this.scoreService.setGames(this.fileScoreDao.getGames());
        String gameLabelText = "Game " + (this.scoreService.getGames() + addGames);
        Label textLabel = this.getLabel("X:s turn", Color.LIGHTPINK, FontWeight.BOLD, 40);
        Label gameLabel = this.getLabel(gameLabelText, Color.LIGHTGREEN, FontWeight.BOLD, 30);
        // actual squares for X and O markings
        String[][] squares = new String[GameSize.SIZE.getGameSize()][GameSize.SIZE.getGameSize()];
        // get square button from the class GameSquare
        this.gameSquare = new GameSquare(textLabel, this.game, this.getScoreService(), this.getFileScoreDao());
        for (int x = 0; x < GameSize.SIZE.getGameSize(); x++) {
            for (int y = 0; y < GameSize.SIZE.getGameSize(); y++) {
                String id = Integer.toString(y) + " " + Integer.toString(x);
                squares[x][y] = id;
                Button newSquare = this.gameSquare.createSquare(squares, id);
                this.getGridpane().add(newSquare, x, y);
            }
        }
        
        // both components into one box to be sent back to caller in class TicTacToeUI
        VBox gameBox = new VBox(gameLabel, textLabel, this.getGridpane());
        gameBox.setAlignment(Pos.CENTER);
        
        return gameBox;
    }

    /**
     * Method for getting stylized GridPane for X:s and O:s.
     */
    public void setGridpane() {
        this.gridPane = new GridPane();
        this.gridPane.setBackground(BackGroundStyle.BASIC.getBackGround());
        this.gridPane.setMinSize(450, 320);
        this.gridPane.setMaxSize(450, 320);
        this.gridPane.setAlignment(Pos.CENTER);
        this.gridPane.setVgap(30 / GameSize.SIZE.getGameSize());
        this.gridPane.setHgap(30 / GameSize.SIZE.getGameSize());
    }
    
    /**
     * Method for getting stylized Label for scores VBox and current game indication.
     * @param text
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
    
    private FileScoreDao getFileScoreDao() {
        return this.fileScoreDao;
    }
    
    public GridPane getGridpane() {
        return this.gridPane;
    }
    
}
