package jms.tictactoe.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import jms.tictactoe.dao.FileScoreDao;
import jms.tictactoe.domain.ScoreService;

/**
 * Class for creating smaller components to the main pane and game area.
 * @author jaris
 */
public class GameComponents extends TicTacToeUI {
    
    private ScoreService scoreService;
    private BorderPane mainPane;
    
    public GameComponents(ScoreService scoreService,FileScoreDao fileScoreDao) throws Exception{
        this.scoreService = scoreService;
    }
    
    /**
     * Method for creating a main BorderPane for the Scene.
     * @return mainPane
     */
    public final BorderPane getMainpane() {
        this.mainPane = new BorderPane();
        this.mainPane.setBackground(new Background(new BackgroundFill(
                Color.rgb(50,50,70),CornerRadii.EMPTY,Insets.EMPTY)));
        
        return this.mainPane;
    }
    
    /**
     * Method for creating a HBox for statistics which takes two VBoxes.
     * @return hBox
     */
    public final HBox getScoreBoxBig() {
        int winsX = this.scoreService.getPoints("X");
        int winsO = this.scoreService.getPoints("O");
        int draws = this.scoreService.getGames() - winsX - winsO;
        String textLeftGames = "Games\n"+this.scoreService.getGames();
        String textMiddleX = "X Scores\n"+winsX;
        String textMiddleO = "O Scores\n"+winsO;
        String textRightDraws = "Draws\n"+draws;
        VBox leftBoxGames = getScoreBoxSmall(textLeftGames);
        VBox middleBoxX = getScoreBoxSmall(textMiddleX);
        VBox middleBoxO = getScoreBoxSmall(textMiddleO);
        VBox rightBoxDraws = getScoreBoxSmall(textRightDraws);
        HBox hBox = new HBox(leftBoxGames,middleBoxX,middleBoxO,rightBoxDraws);
        hBox.setAlignment(Pos.CENTER);
        
        return hBox;
    }
    
    /**
     * Method for stylizing the VBox for scores.
     * @param text
     * @return vBox
     */
    public VBox getScoreBoxSmall(String text){
        VBox vBox = new VBox(this.getLabel(text,Color.LIGHTBLUE,FontWeight.NORMAL,20));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(50,50,70),CornerRadii.EMPTY,Insets.EMPTY)));
        vBox.setPrefSize(100, 100);
        vBox.setAlignment(Pos.CENTER);
        
        return vBox;
    }
    
    /**
     * Method for creating a HBox for new game and quit game buttons.
     * @param ngButton new game button
     * @param rButton
     * @param qButton quit button
     * @return bBox
     */
    public final HBox getButtonBox(Button ngButton,Button rButton,Button qButton) {
        HBox bBox = new HBox(ngButton,rButton,qButton);
        bBox.setAlignment(Pos.CENTER);
        bBox.setPadding(new Insets(10, 5, 10, 5));
        
        return bBox;
    }
    
    /**
     * Method for getting stylized Label for scores VBox and current game indication.
     * @param text
     * @param color font color
     * @param fWeight font weight
     * @param fSize font size
     * @return label
     */
    public Label getLabel(String text,Color color,FontWeight fWeight,int fSize){
        Label label = new Label(text);
        label.setTextFill(color);
        label.setAlignment(Pos.CENTER);
        label.setEffect(this.getBloomEffect());
        label.setFont(Font.font("Cambria", fWeight, fSize));
        label.setTextAlignment(TextAlignment.CENTER);
        
        return label;
    }
    
    /**
     * Method for stylizing text
     * @return bloomEffect
     */
    public Bloom getBloomEffect() {
        Bloom bloomEffect = new Bloom();
        bloomEffect.setThreshold(0.75);
        
        return bloomEffect;
    }
    
}
