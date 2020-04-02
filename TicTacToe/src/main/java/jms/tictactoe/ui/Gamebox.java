package jms.tictactoe.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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
     * @param textLabel
     * @param gameLabel
     * @return gameBox
     * @throws java.lang.Exception
     */
    public VBox createBox(Label textLabel, Label gameLabel) throws Exception {
        this.setGridpane();
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
