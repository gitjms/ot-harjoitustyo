package jms.tictactoe.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
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
     * Method for creating a HBox for statistics.
     * @param scoreService
     * @return hBox
     */
    public final HBox getScoreBoxBig(ScoreService scoreService) {
        String textLeftGames = "Games\n" + scoreService.getAmount();
        String textMiddleX = "X Scores\n" + scoreService.getPoints("X");
        String textMiddleO = "O Scores\n" + scoreService.getPoints("O");
        String textRightDraws = "Draws\n" + scoreService.getDraws();
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
        VBox vBox = new VBox(this.getLabel(text, Color.LIGHTBLUE, FontWeight.NORMAL, 20, true));
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
    
    /**
     * Method for creating the HBox for database choice and game size choice.
     * @param databaseChoice
     * @param choiceButton3
     * @param choiceButton4
     * @param choiceButton5
     * @return hBox
     */
    public HBox getChoiceBox(HBox databaseChoice, Button choiceButton3, Button choiceButton4, Button choiceButton5) {
        HBox rightBox = new HBox(choiceButton3, choiceButton4, choiceButton5);
        rightBox.setSpacing(5);
        rightBox.setPadding(new Insets(0, 2, 0, 2));
        rightBox.setBackground(BackGroundStyle.BASIC.getBackGround());
        rightBox.setBorder(BorderStyle.MEDIUM.getBorder());
        rightBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(databaseChoice, rightBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(15);
        hBox.setPadding(new Insets(10, 5, 20, 5));
        hBox.setId("choice-box");
        return hBox;
    }

    /**
     * Method for stylizing the VBox for game size decision and buttons.
     * @param buttonBox
     * @param choiceBox
     * @return vBox
     */
    public VBox getBottomBox(HBox buttonBox, HBox choiceBox) {
        VBox vBox = new VBox(buttonBox, choiceBox);
        vBox.setBackground(BackGroundStyle.BASIC.getBackGround());
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    /**
     * Method for creating the HBox for database choice.
     * @param dbButton
     * @param textField
     * @return hBox
     */
    public HBox getDatabaseChoice(Button dbButton, TextField textField) {
        Label label = this.getLabel("Database:", Color.LIGHTBLUE, FontWeight.NORMAL, 17, false);
        textField.setMinSize(120, 25);
        textField.setMaxSize(120, 25);
        VBox vBox = new VBox(label, textField);
        HBox hBox = new HBox(vBox, dbButton);
        hBox.setSpacing(2);
        hBox.setPadding(new Insets(2, 2, 2, 5));
        hBox.setBorder(BorderStyle.MEDIUM.getBorder());
        return hBox;
    }
    
    /**
     * Method for creating main button bases.
     * @param text
     * @param width
     * @param height
     * @param fontSize
     * @param backGround
     * @return button
     */
    public Button createButton(String text, int width, int height, int fontSize, Background backGround) {
        Button button = new Button(text);
        button.setPrefSize(width, height);
        button.setTextFill(Color.LIGHTBLUE);
        button.setStyle("-fx-font-size: " + fontSize + "px;");
        button.setBackground(backGround);
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
            (MouseEvent e) -> button.setEffect(new DropShadow())
        );
        button.addEventHandler(MouseEvent.MOUSE_EXITED,
            (MouseEvent e) -> button.setEffect(null)
        );
        return button;
    }
    
    /**
     * Method for getting game area (VBox).
     * @param scoreService
     * @return
     */
    public VBox getArea(ScoreService scoreService) {
        GameArea gameArea = new GameArea(scoreService);
        VBox vBox = gameArea.createArea();
        return vBox;
    }
    
    /**
     * Method for getting stylized Label for scores VBox and current game indication.
     * @param text
     * @param color font color
     * @param fWeight font weight
     * @param fSize font size
     * @param bloom
     * @return label
     */
    public Label getLabel(String text, Color color, FontWeight fWeight, int fSize, boolean bloom) {
        Label label = new Label(text);
        label.setTextFill(color);
        label.setAlignment(Pos.CENTER);
        if (bloom) {
            Bloom bloomEffect = new Bloom();
            bloomEffect.setThreshold(0.75);
            label.setEffect(bloomEffect);
        }
        label.setFont(Font.font("Cambria", fWeight, fSize));
        label.setTextAlignment(TextAlignment.CENTER);
        return label;
    }
}
