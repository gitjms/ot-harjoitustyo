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
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
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
        mainPane.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        return mainPane;
    }
    
    /**
     * Method for creating a HBox for statistics.
     * @param scoreService instance of ScoreService class
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
     * @param text game and score info text strings from method getScoreBoxBig
     * @return vBox
     */
    public VBox getScoreBoxSmall(String text) {
        VBox vBox = new VBox(this.getLabel(text, Color.LIGHTBLUE, FontWeight.NORMAL, 20, true));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setPrefSize(100, 100);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }
    
    /**
     * Method for creating a HBox for new game and quit game buttons.
     * @param newGameButton new game button
     * @param resetButton reset button
     * @param quitButton quit button
     * @return bBox
     */
    public final HBox getButtonBox(Button newGameButton, Button resetButton, Button quitButton) {
        HBox bBox = new HBox(newGameButton, resetButton, quitButton);
        bBox.setAlignment(Pos.CENTER);
        bBox.setSpacing(10);
        bBox.setPadding(new Insets(10, 5, 5, 5));
        return bBox;
    }
    
    /**
     * Method for creating the HBox for database choice and game size choice.
     * @param databaseChoice HBox for database choise from method getDatabaseChoice
     * @param choiceButton3 choice button for game size 3x3
     * @param choiceButton4 choice button for game size 4x4
     * @param choiceButton5 choice button for game size 5x5
     * @return hBox
     */
    public HBox getChoiceBox(HBox databaseChoice, Button choiceButton3, Button choiceButton4, Button choiceButton5) {
        HBox rightBox = new HBox(choiceButton3, choiceButton4, choiceButton5);
        rightBox.setSpacing(5);
        rightBox.setPadding(new Insets(3, 7, 3, 7));
        rightBox.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        rightBox.setBorder(new Border(new BorderStroke(Color.rgb(70, 80, 110), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        rightBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(databaseChoice, rightBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(8);
        hBox.setPadding(new Insets(10, 5, 20, 5));
        hBox.setId("choice-box");
        return hBox;
    }

    /**
     * Method for stylizing the VBox for game size decision and buttons.
     * @param buttonBox HBox for new game, reset, and quit buttons from method getButtonBox
     * @param choiceBox HBox for database choice from method getDatabaseChoice
     * @return vBox
     */
    public VBox getBottomBox(HBox buttonBox, HBox choiceBox) {
        VBox vBox = new VBox(buttonBox, choiceBox);
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    /**
     * Method for creating the HBox for database choice.
     * @param databaseButton button for accepting database change
     * @param textField TextField to indicate database choice
     * @return hBox
     */
    public HBox getDatabaseChoice(Button databaseButton, TextField textField) {
        Label label = this.getLabel("Database:", Color.LIGHTBLUE, FontWeight.NORMAL, 17, false);
        textField.setMinSize(130, 25);
        textField.setMaxSize(130, 25);
        VBox vBox = new VBox(label, textField);
        HBox hBox = new HBox(vBox, databaseButton);
        hBox.setSpacing(4);
        hBox.setPadding(new Insets(7, 7, 7, 5));
        hBox.setBorder(new Border(new BorderStroke(Color.rgb(70, 80, 110), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        return hBox;
    }
    
    /**
     * Method for creating main button bases.
     * @param text text on the button
     * @param width button width
     * @param height button height
     * @param fontSize button text font size
     * @param backGround button appearance.
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
     * @param scoreService instance of ScoreService class
     * @return vBox
     */
    public VBox getArea(ScoreService scoreService) {
        GameArea gameArea = new GameArea(scoreService);
        VBox vBox = gameArea.createArea();
        return vBox;
    }
    
    /**
     * Method for getting stylized Label for score VBox, database choice, and current game indication.
     * @param text text to set
     * @param color text font color
     * @param fWeight text font weight
     * @param fSize text font size
     * @param bloom bloom effect for the text
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
