package jms.tictactoe.domain;

import jms.tictactoe.domain.Game;
import jms.tictactoe.domain.GameSquare;
import javafx.geometry.Insets;
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

/**
 * Class for creating a game area.
 * @author jaris
 */
public final class Gamebox {
    
    private GameSquare gameSquare;
    private final GridPane gridPane;
    private final Label label;
    private final Label gameLabel;
    
    public Gamebox(Game game) {
        this.gridPane = new GridPane();
        this.setGridpane();
        this.label = new Label("X:s turn");
        this.gameLabel = new Label("Game "+game.getId());
    }
    
    /**
     * Method for creating the VBox for game area main components:
     * top:     Text Label for scores
     * middle:  Text Label for whose turn/who wins
     * bottom:  GridPane for X and O squares
     * @param game
     * @param gameLabel
     * @return gameBox
     */
    public VBox createBox(Game game, Label gameLabel){
        // actual squares for X and O markings
        String[][] squares = new String[3][3];
        for(int x=0;x<3;x++) {
            for(int y=0;y<3;y++){
                // get square button from the class GameSquare
                this.gameSquare = new GameSquare(this.getTextLabel(game),this.getGameLabel(game));
                String id = Integer.toString(y)+" "+Integer.toString(x);
                squares[x][y]=id;
                Button newSquare = this.gameSquare.createSquare(game,this.getTextLabel(game),this.getGameLabel(game),squares,id);
                this.gridPane.add(newSquare, x, y);
            }
        }
        
        // both components into one box to be sent back to caller in class TicTacToeUI
        VBox gameBox = new VBox(this.getGameLabel(game),this.getTextLabel(game),this.gridPane);
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

    /**
     * Method for getting stylized Label for turn/win indication.
     * @param game
     * @return 
     */
    public Label getTextLabel(Game game) {
        this.label.setTextFill(Color.LIGHTPINK);
        this.label.setAlignment(Pos.CENTER);
        this.label.setEffect(this.getBloomEffect());
        this.label.setFont(Font.font("Cambria", FontWeight.BOLD, 40));
        this.label.setTextAlignment(TextAlignment.CENTER);
        return this.label;
    }
    
    /**
     * Method for getting stylized Label for current game indication.
     * @param game
     * @return 
     */
    public Label getGameLabel(Game game) {
        this.gameLabel.setTextFill(Color.LIGHTGREEN);
        this.gameLabel.setAlignment(Pos.CENTER);
        this.gameLabel.setEffect(this.getBloomEffect());
        this.gameLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 30));
        this.gameLabel.setTextAlignment(TextAlignment.CENTER);
        return this.gameLabel;
    }
    
    /**
     * Method for stylizing text
     * @return 
     */
    public Bloom getBloomEffect() {
        Bloom bloomEffect = new Bloom();
        bloomEffect.setThreshold(0.75);
        
        return bloomEffect;
    }
}
