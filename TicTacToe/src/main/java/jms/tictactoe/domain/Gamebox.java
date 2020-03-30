package jms.tictactoe.domain;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import jms.tictactoe.dao.FileScoreDao;

/**
 * Class for creating a game area.
 * @author jaris
 */
public final class Gamebox {
    
    private GameSquare gameSquare;
    private final GridPane gridPane;
    private Game game;
    
    public Gamebox(Game game, ScoreService scoreService,FileScoreDao fileScoreDao) {
        this.game = game;
        this.gridPane = new GridPane();
        this.setGridpane();
    }
    
    /**
     * Method for creating the VBox for game area main components:
     * top:     Text Label for game number
     * middle:  Text Label for whose turn/who wins
     * bottom:  GridPane for X and O squares
     * @param textLabel
     * @param gameLabel
     * @param scoreService
     * @param fileScoreDao
     * @return gameBox
     * @throws java.lang.Exception
     */
    public VBox createBox(Label textLabel,Label gameLabel,ScoreService scoreService,FileScoreDao fileScoreDao) throws Exception {
        // actual squares for X and O markings
        String[][] squares = new String[3][3];
        for(int x=0;x<3;x++) {
            for(int y=0;y<3;y++){
                // get square button from the class GameSquare
                this.gameSquare = new GameSquare(textLabel,this.game,scoreService,fileScoreDao);
                String id = Integer.toString(y)+" "+Integer.toString(x);
                squares[x][y]=id;
                Button newSquare = this.gameSquare.createSquare(squares,id);
                this.getGridpane().add(newSquare, x, y);
            }
        }
        
        // both components into one box to be sent back to caller in class TicTacToeUI
        VBox gameBox = new VBox(gameLabel,textLabel,this.getGridpane());
        gameBox.setAlignment(Pos.CENTER);
        
        return gameBox;
    }

    /**
     * Method for getting stylized GridPane for X:s and O:s.
     */
    public void setGridpane(){
        this.gridPane.setBackground(new Background(new BackgroundFill(Color.rgb(50,50,70),CornerRadii.EMPTY,Insets.EMPTY)));
        this.gridPane.setPrefSize(400, 320);
        this.gridPane.setAlignment(Pos.CENTER);
        this.gridPane.setVgap(10);
        this.gridPane.setHgap(10);
    }
    
    
    public GridPane getGridpane(){
        return this.gridPane;
    }
    
}
