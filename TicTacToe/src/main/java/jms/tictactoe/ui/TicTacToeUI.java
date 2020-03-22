package jms.tictactoe.ui;

import jms.tictactoe.domain.Gamebox;
import jms.tictactoe.domain.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
import javafx.stage.Stage;

/**
 * Main class for GUI generation
 * @author jaris
 */
public class TicTacToeUI extends Application {

    private DropShadow shadow;
    private BorderPane mainPane;
    private Gamebox gameBox;
    private VBox gameArea;
    private Label scoreLabelLeft;
    private Label scoreLabelRight;
    private Scene gameView;
    private Game game;

    /**
     * Initiate class instances.
     */
    @Override
    public void init() {
      this.shadow = new DropShadow();
      this.game = new Game(1);
      this.gameBox = new Gamebox(this.game);
      this.gameArea = new VBox();
      this.scoreLabelLeft = new Label();
      this.scoreLabelRight = new Label();
      this.mainPane = getMainpane();
      this.gameView = new Scene(this.mainPane);
      // DAO
    }
    
    /**
     * Create user interface for testing. 
     * @throws java.lang.Exception 
     */
    public void start() throws Exception {
        Button newGameButton = this.createNewgameButton(null);
        Button quitButton = this.createQuitButton(null);
        HBox statBoxbig = getScoreBoxBig(0,0);
        HBox buttonBox = getButtonBox(newGameButton,quitButton);
        this.gameArea = gameBox.createBox(this.game,this.getGameLabel(this.game));
        this.mainPane.setTop(statBoxbig);
        this.mainPane.setCenter(this.gameArea);
        this.mainPane.setBottom(buttonBox);
    }
    
    /**
     * Create user interface.
     * @param primaryStage 
     * @throws java.lang.Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Image iconImg = new Image("/Tic-Tac-Toe-Game-red.png");
        primaryStage.getIcons().add(iconImg);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setResizable(false);
        
        // create instances for the buttons
        Button newgameButton = this.createNewgameButton(primaryStage);
        Button quitButton = this.createQuitButton(primaryStage);
        
        // box for statistics
        HBox statsBoxbig = getScoreBoxBig(0,0);
        
        // box for buttons
        HBox buttonBox = getButtonBox(newgameButton,quitButton);
        
        // get game area
        this.gameArea = gameBox.createBox(this.game,this.getGameLabel(this.game));
        
        // set components to main pane
        this.mainPane.setTop(statsBoxbig);
        this.mainPane.setCenter(this.gameArea);
        this.mainPane.setBottom(buttonBox);
        
        // set scene to stage and show stage
        primaryStage.setScene(this.gameView);
        primaryStage.show();
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
     * @param winsX
     * @param winsO
     * @return statsBoxbig
     */
    public final HBox getScoreBoxBig(int winsX,int winsO) {
        VBox leftBox = getScoreBoxSmall(this.scoreLabelLeft,"X\nWins: "+winsX);
        VBox rightBox = getScoreBoxSmall(this.scoreLabelRight,"O\nWins: "+winsO);
        HBox hBox = new HBox(leftBox,rightBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(100);
        
        return hBox;
    }
    
    /**
     * Method for stylizing the VBox for scores.
     * @param label
     * @param text
     * @return the statsBoxsmall
     */
    public VBox getScoreBoxSmall(Label label, String text){
        VBox vBox = new VBox(this.getScoreLabel(label, text));
        vBox.setBackground(new Background(new BackgroundFill(Color.rgb(50,50,70),CornerRadii.EMPTY,Insets.EMPTY)));
        vBox.setPrefSize(100, 100);
        vBox.setAlignment(Pos.CENTER);
        
        return vBox;
    }
    
    /**
     * Method for creating a HBox for new game and quit game buttons.
     * @param ngButton
     * @param qButton
     * @return buttonBox
     */
    public final HBox getButtonBox(Button ngButton, Button qButton) {
        HBox bBox = new HBox(ngButton,qButton);
        bBox.setAlignment(Pos.CENTER);
        bBox.setPadding(new Insets(10, 10, 10, 10));
        bBox.setSpacing(100);
        
        return bBox;
    }
    
    /**
     * Method for stylizing the Label for scores VBox.
     * @param label
     * @param text
     * @return textLabel
     */
    public Label getScoreLabel(Label label, String text){
        label.setText(text);
        label.setTextFill(Color.LIGHTBLUE);
        label.setAlignment(Pos.CENTER);
        label.setEffect(this.getBloomEffect());
        label.setFont(Font.font("Cambria", FontWeight.NORMAL, 20));
        label.setTextAlignment(TextAlignment.CENTER);
        
        return label;
    }
    
    /**
     * Method for getting stylized Label for current game indication.
     * @param game
     * @return 
     */
    public Label getGameLabel(Game game) {
        Label label = new Label("Game "+this.game.getId());
        label.setTextFill(Color.LIGHTGREEN);
        label.setAlignment(Pos.CENTER);
        label.setEffect(this.getBloomEffect());
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 30));
        label.setTextAlignment(TextAlignment.CENTER);
        return label;
    }

    /**
     * Method for getting a new game with updated scores.
     * @param game
     * @param primaryStage 
     */
    public void newGame(Game game, Stage primaryStage) {
        game.setId(game.getId()+1);
        this.gameBox = new Gamebox(game);
        this.gameArea = this.gameBox.createBox(game,this.getGameLabel(game));
        this.getMainpane();

        int winsX = game.getScore("X");
        int winsO = game.getScore("O");
        HBox scoreBox = this.getScoreBoxBig(winsX,winsO);
        
        Button newGameButton = this.createNewgameButton(primaryStage);
        Button quitButton = this.createQuitButton(primaryStage);
        HBox bottomBox = getButtonBox(newGameButton,quitButton);
        
        this.mainPane.setTop(scoreBox);
        this.mainPane.setCenter(this.gameArea);
        this.mainPane.setBottom(bottomBox);
        this.gameView = new Scene(this.mainPane);
        primaryStage.setScene(this.gameView);
    }
    
    /**
     * Method for creating a Button for having a new game.
     * @param stage
     * @return newgameButton
     */
    public final Button createNewgameButton(Stage stage){
        Button button = new Button("New Game");
        button.setPrefSize(140, 30);
        button.setTextFill(Color.LIGHTBLUE);
        button.setStyle("-fx-font-size: 20px;");
        button.setBackground(new Background( new BackgroundFill(Color.rgb(50,50,70),CornerRadii.EMPTY,Insets.EMPTY)));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                (MouseEvent e) -> button.setEffect(this.shadow)
        );
        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                (MouseEvent e) -> button.setEffect(null)
        );
        button.setOnAction(event -> {
            this.newGame(this.game, stage);
        });
        button.setAlignment(Pos.CENTER);
        
        return button;
    }
    
    /**
     * Method for creating a Button to quit the game.
     * @param stage
     * @return quitButton
     */
    public final Button createQuitButton(Stage stage){
        Button button = new Button("Quit Game");
        button.setPrefSize(140, 30);
        button.setTextFill(Color.LIGHTBLUE);
        button.setStyle("-fx-font-size: 20px;");
        button.setBackground(new Background( new BackgroundFill(Color.rgb(50,50,70),CornerRadii.EMPTY,Insets.EMPTY)));
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                (MouseEvent e) -> button.setEffect(this.shadow)
        );
        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                (MouseEvent e) -> button.setEffect(null)
        );
        button.setOnAction(event -> {
            this.stop(stage);
        });
        button.setAlignment(Pos.CENTER);
        
        return button;
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

    /**
     * Method for closing the stage.
     * @param primaryStage 
     */
    public void stop(Stage primaryStage) {
      System.out.println("Application closes");
      primaryStage.close();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}  
