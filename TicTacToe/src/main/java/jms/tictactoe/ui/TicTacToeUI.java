package jms.tictactoe.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.effect.DropShadow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import jms.tictactoe.dao.FileScoreDao;
import jms.tictactoe.domain.ScoreService;
import jms.tictactoe.domain.Game;
import jms.tictactoe.domain.Gamebox;

/**
 *Main class for GUI generation.
 * @author jaris
 */
public class TicTacToeUI extends Application {
    
    private FileScoreDao fileScoreDao;
    private ScoreService scoreService;
    private GameComponents gameComponents;
    private Gamebox gameBox;
    private VBox gameArea;
    private DropShadow shadow;
    private BorderPane mainPane;
    private Scene gameView;
    private Game game;
    
    /**
     * Initiate instances for class and some component.
     * @throws java.io.FileNotFoundException
     */
    @Override
    public void init() throws FileNotFoundException, IOException, Exception {
        // Score data from file, score DAO 
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String scoreFile = properties.getProperty("scoreFile");
        this.fileScoreDao = new FileScoreDao(scoreFile);
        
        // Score service instance, set score data HashMap
        this.scoreService = new ScoreService(this.fileScoreDao);
        this.scoreService.setAllMap(this.fileScoreDao.getAllMap());
        
        // Game and gameBox instances 
        this.game = new Game(1,this.scoreService);
        this.scoreService.setGames(this.fileScoreDao.getGames());
        this.gameBox = new Gamebox(this.game,this.scoreService,this.fileScoreDao);
        
        // Other components, most from gameComponents instance
        this.gameComponents = new GameComponents(this.scoreService,this.fileScoreDao);
        this.shadow = new DropShadow();
        this.mainPane = this.gameComponents.getMainpane();
        this.gameView = new Scene(this.mainPane);
    }
    
    /**
     * Create user interface for testing. 
     * @throws java.lang.Exception 
     */
    public void start() throws Exception {
        Button newgameButton = this.createButton("New Game",null);
        newgameButton.setOnAction(e -> {
            try { this.newGame(null);
            } catch (Exception ex) { }
        });
        Button resetButton = this.createButton("Reset Game",null);
        resetButton.setOnAction(e -> {
            try {
                this.resetGame();
                this.newGame(null);
            } catch (Exception ex) { }
        });
        Button quitButton = this.createButton("Quit Game",null);
        quitButton.setOnAction(e -> {
            try { this.stop(null);
            } catch (Exception ex) { }
        });
        HBox statBoxbig = this.gameComponents.getScoreBoxBig();
        HBox buttonBox = this.gameComponents.getButtonBox(newgameButton,resetButton,quitButton);
        String gameLabelText = "Game "+ (this.scoreService.getGames()+1);
        
        this.gameArea = this.gameBox.createBox(
                this.gameComponents.getLabel("X:s turn",Color.LIGHTPINK,FontWeight.BOLD,40),
                this.gameComponents.getLabel(gameLabelText,Color.LIGHTGREEN,FontWeight.BOLD,30),
                this.scoreService,this.fileScoreDao);
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
        Button newgameButton = this.createButton("New Game",primaryStage);
        newgameButton.setOnAction(e -> {
            try {
                this.newGame(primaryStage);
            } catch (Exception ex) { }
        });
        Button resetButton = this.createButton("Reset Game",primaryStage);
        resetButton.setOnAction(e -> {
            try {
                this.resetGame();
                this.newGame(primaryStage);
            } catch (Exception ex) { }
        });
        Button quitButton = this.createButton("Quit Game",primaryStage);
        quitButton.setOnAction(e -> {
            try {
                this.stop(primaryStage);
            } catch (Exception ex) { }
        });

        // box for statistics
        HBox statsBoxbig = this.gameComponents.getScoreBoxBig();
        
        // box for buttons
        HBox buttonBox = this.gameComponents.getButtonBox(newgameButton,resetButton,quitButton);
        
        // get game area
        String gameLabelText = "Game "+ (this.scoreService.getGames()+1);
        this.gameArea = this.gameBox.createBox(
                this.gameComponents.getLabel("X:s turn",Color.LIGHTPINK,FontWeight.BOLD,40),
                this.gameComponents.getLabel(gameLabelText,Color.LIGHTGREEN,FontWeight.BOLD,30),
                this.scoreService,this.fileScoreDao);
        
        // set components to main pane
        this.mainPane.setTop(statsBoxbig);
        this.mainPane.setCenter(this.gameArea);
        this.mainPane.setBottom(buttonBox);
        
        // set scene to stage and show stage
        primaryStage.setScene(this.gameView);
        primaryStage.show();
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
    
    /**
     * Method for getting a new game with updated scores.
     * @param primaryStage 
     * @throws java.lang.Exception 
     */
    public void newGame(Stage primaryStage) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String scoreFile = properties.getProperty("scoreFile");
        this.fileScoreDao = new FileScoreDao(scoreFile);
        
        this.scoreService.setAllMap(this.fileScoreDao.getAllMap());
        this.scoreService.setGames(this.fileScoreDao.getGames());
        
        String gameLabelText = "Game "+ (this.scoreService.getGames()+1);
        this.gameArea = this.gameBox.createBox(
                this.gameComponents.getLabel("X:s turn",Color.LIGHTPINK,FontWeight.BOLD,40),
                this.gameComponents.getLabel(gameLabelText,Color.LIGHTGREEN,FontWeight.BOLD,30),
                this.scoreService,this.fileScoreDao);
        this.gameComponents.getMainpane();

        HBox scoreBox = this.gameComponents.getScoreBoxBig();
        
        Button newgameButton = this.createButton("New Game",primaryStage);
        newgameButton.setOnAction(e -> {
            try {
                this.newGame(primaryStage);
            } catch (Exception ex) { }
        });
        Button resetButton = this.createButton("Reset Game",primaryStage);
        resetButton.setOnAction(e -> {
            try {
                this.resetGame();
                this.newGame(primaryStage);
            } catch (Exception ex) { }
        });
        Button quitButton = this.createButton("Quit Game",primaryStage);
        quitButton.setOnAction(e -> {
            try { this.stop(primaryStage);
            } catch (Exception ex) { }
        });
        HBox bottomBox = this.gameComponents.getButtonBox(newgameButton,resetButton,quitButton);
        
        this.mainPane.setTop(scoreBox);
        this.mainPane.setCenter(this.gameArea);
        this.mainPane.setBottom(bottomBox);
        this.gameView = new Scene(this.mainPane);
        primaryStage.setScene(this.gameView);
    }
    
    /**
     * Method for resetting game. 
     * @throws java.lang.Exception 
     */
    public void resetGame() throws Exception {
        Map<String,Pair<Integer,Integer>> fileScores = new HashMap<>();
        Pair<Integer,Integer> newPair = new Pair(0,0);
        fileScores.put("X", newPair);
        fileScores.put("O", newPair);
        
        this.scoreService.resetPoints();
        this.scoreService.setAllMap(fileScores);
        this.scoreService.createScore("X", 0, 0);
        this.scoreService.createScore("O", 0, 0);
        
        this.game = new Game(1,this.scoreService);
        this.gameComponents = new GameComponents(this.scoreService,this.fileScoreDao);
    }
    
    /**
     * Method for creating Buttons.
     * @param text
     * @param stage
     * @return button
     */
    public final Button createButton(String text,Stage stage){
        Button button = new Button(text);
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
        button.setAlignment(Pos.CENTER);
        
        return button;
    }

}  
