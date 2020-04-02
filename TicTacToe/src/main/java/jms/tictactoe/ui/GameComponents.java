package jms.tictactoe.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
public class GameComponents {
       
    /**
     * Method for creating a main BorderPane for the Scene.
     * @return mainPane
     */
    public final BorderPane getMainpane() {
        BorderPane mainPane = new BorderPane();
        mainPane.setBackground(BackGroundStyle.BASIC.getBackGround());
        return mainPane;
    }
    
    /**
     * Method for creating a HBox for statistics which takes two VBoxes.
     * @param scoreService
     * @return hBox
     */
    public final HBox getScoreBoxBig(ScoreService scoreService) {
        int winsX = scoreService.getPoints("X");
        int winsO = scoreService.getPoints("O");
        int draws = scoreService.getGames() - winsX - winsO;
        String textLeftGames = "Games\n" + scoreService.getGames();
        String textMiddleX = "X Scores\n" + winsX;
        String textMiddleO = "O Scores\n" + winsO;
        String textRightDraws = "Draws\n" + draws;
        VBox leftBoxGames = getScoreBoxSmall(textLeftGames);
        VBox middleBoxX = getScoreBoxSmall(textMiddleX);
        VBox middleBoxO = getScoreBoxSmall(textMiddleO);
        VBox rightBoxDraws = getScoreBoxSmall(textRightDraws);
        HBox hBox = new HBox(leftBoxGames, middleBoxX, middleBoxO, rightBoxDraws);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }
    
    /**
     * Method for stylizing the VBox for scores.
     * @param text
     * @return vBox
     */
    public VBox getScoreBoxSmall(String text) {
        VBox vBox = new VBox(this.getLabel(text, Color.LIGHTBLUE, FontWeight.NORMAL, 20));
        vBox.setBackground(BackGroundStyle.BASIC.getBackGround());
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
    public final HBox getButtonBox(Button ngButton, Button rButton, Button qButton) {
        HBox bBox = new HBox(ngButton, rButton, qButton);
        bBox.setAlignment(Pos.CENTER);
        bBox.setSpacing(10);
        bBox.setPadding(new Insets(10, 5, 0, 5));
        return bBox;
    }
    
    
    public HBox getChoiceBox(Button choiceButton3, Button choiceButton4, Button choiceButton5) {
        HBox hBox = new HBox(choiceButton3, choiceButton4, choiceButton5);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 5, 20, 5));
        hBox.setId("choice-box");
        return hBox;
    }

    /**
     * Method for stylizing the VBox for game size decision and buttons.
     * @param topBox
     * @param bottomBox
     * @return vBox
     */
    public VBox getBottomBox(HBox topBox, HBox bottomBox) {
        VBox vBox = new VBox(topBox, bottomBox);
        vBox.setBackground(BackGroundStyle.BASIC.getBackGround());
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }
    
    /**
     * Method for creating main button bases.
     * @param text
     * @return button
     */
    public Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(150, 30);
        button.setTextFill(Color.LIGHTBLUE);
        button.setStyle("-fx-font-size: 20px;");
        button.setBackground(BackGroundStyle.BASIC.getBackGround());
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
            (MouseEvent e) -> button.setEffect(new DropShadow())
        );
        button.addEventHandler(MouseEvent.MOUSE_EXITED,
            (MouseEvent e) -> button.setEffect(null)
        );
        button.setAlignment(Pos.CENTER);
        return button;
    }
    
    /**
     * Method for creating game size choice buttons.
     * @param text
     * @return button
     */
    public final Button createChoiceButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(80, 20);
        button.setTextFill(Color.LIGHTBLUE);
        button.setStyle("-fx-font-size: 16;");
        button.setBackground(BackGroundStyle.BASIC.getBackGround());
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
            (MouseEvent e) -> button.setEffect(new DropShadow())
        );
        button.addEventHandler(MouseEvent.MOUSE_EXITED,
            (MouseEvent e) -> button.setEffect(null)
        );
        button.setAlignment(Pos.CENTER);
        return button;
    }
    
    /**
     * Method for getting game area (VBox).
     * @param addGames
     * @param scoreService
     * @param fileScoreDao
     * @return 
     * @throws java.lang.Exception
     */
    public VBox getArea(int addGames, ScoreService scoreService, FileScoreDao fileScoreDao) throws Exception {
        scoreService.setGames(fileScoreDao.getGames());
        String gameLabelText = "Game " + (scoreService.getGames() + addGames);
        Gamebox gBox = new Gamebox(scoreService, fileScoreDao);
        VBox vBox = gBox.createBox(
            this.getLabel("X:s turn", Color.LIGHTPINK, FontWeight.BOLD, 40),
            this.getLabel(gameLabelText, Color.LIGHTGREEN, FontWeight.BOLD, 30));
        return vBox;
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
