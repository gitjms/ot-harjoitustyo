package jms.tictactoe.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import jms.tictactoe.dao.FileScoreDao;
import jms.tictactoe.domain.ScoreService;

/**
 *Main class for GUI generation.
 * @author jaris
 */
public class TicTacToeUI extends Application {
    
    private ScoreService scoreService;
    private FileScoreDao fileScoreDao;
    private GameComponents gameComponents;
    private Button newgameButton3x3;
    private Button newgameButton4x4;
    private Button newgameButton5x5;
    private Button resetButton3x3;
    private Button resetButton4x4;
    private Button resetButton5x5;
    private Button quitButton3x3;
    private Button quitButton4x4;
    private Button quitButton5x5;
    private Button choiceButton3on3;
    private Button choiceButton4to3;
    private Button choiceButton5to3;
    private Button choiceButton3to4;
    private Button choiceButton4on4;
    private Button choiceButton5to4;
    private Button choiceButton3to5;
    private Button choiceButton4to5;
    private Button choiceButton5on5;
    private HBox scoreBox3x3;
    private HBox scoreBox4x4;
    private HBox scoreBox5x5;
    private HBox buttonBox3x3;
    private HBox buttonBox4x4;
    private HBox buttonBox5x5;
    private HBox choiceBox3x3;
    private HBox choiceBox4x4;
    private HBox choiceBox5x5;
    private VBox bottomBox3x3;
    private VBox bottomBox4x4;
    private VBox bottomBox5x5;
    private Scene gameScene3x3;
    private Scene gameScene4x4;
    private Scene gameScene5x5;
    private VBox area3x3;
    private VBox area4x4;
    private VBox area5x5;
    private BorderPane pane3x3;
    private BorderPane pane4x4;
    private BorderPane pane5x5;
    
    /**
     * Initiate instances for class and some component.
     * @throws java.io.FileNotFoundException
     */
    @Override
    public void init() throws FileNotFoundException, IOException, Exception {
        // load data file, set DAO, set Game, set mainpane.
        this.setNecessities();

        WinRow.X.setWinCode("XXX");
        WinRow.O.setWinCode("OOO");
        GameSize.SIZE.setGameSize(3);
        
        // set buttons
        this.setButtons();
        this.setChoiceButtons3x3();
        this.setChoiceButtons4x4();
        this.setChoiceButtons5x5();
        
        // set boxes for buttons etc.
        this.setBoxes3x3();
        this.setBoxes4x4();
        this.setBoxes5x5();
        
        // set Game Areas, BorderPanes for boxes, and Scenes for BorderPanes
        this.setAreaPaneAndScene3x3(1);
        this.setAreaPaneAndScene4x4(1);
        this.setAreaPaneAndScene5x5(1);
    }
    
    /**
     * Methid for starting the user interface.
     * @param primaryStage 
     * @throws java.lang.Exception 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // set main buttons
        this.setNewGameButtonActions3x3(primaryStage);
        this.setNewGameButtonActions4x4(primaryStage);
        this.setNewGameButtonActions5x5(primaryStage);
        this.setResetGameButtofnActions3x3(primaryStage);
        this.setResetGameButtofnActions4x4(primaryStage);
        this.setResetGameButtofnActions5x5(primaryStage);
        this.setQuitGameButtonActions(primaryStage);
        
        // set game size choice buttons
        this.setGameSizeChoiceButtonActions3to4(primaryStage);
        this.setGameSizeChoiceButtonActions3to5(primaryStage);
        this.setGameSizeChoiceButtonActions4to3(primaryStage);
        this.setGameSizeChoiceButtonActions4to5(primaryStage);
        this.setGameSizeChoiceButtonActions5to3(primaryStage);
        this.setGameSizeChoiceButtonActions5to4(primaryStage);
        
        // set icon, title and scene to stage
        this.setStage(primaryStage);
        primaryStage.show();
    }

    /**
     * Method for closing the user interface.
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
     * Method for setting necesary tools: data, dao, mainpane. 
     * @throws java.io.FileNotFoundException
     * @throws java.lang.Exception 
     */
    private void setNecessities() throws FileNotFoundException, IOException, Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String scoreFile = properties.getProperty("scoreFile");
        this.fileScoreDao = new FileScoreDao(scoreFile);
        this.scoreService = new ScoreService(this.fileScoreDao);
        this.scoreService.setAllMap(this.fileScoreDao.getAllMap());
        this.gameComponents = new GameComponents();
        this.pane3x3 = this.gameComponents.getMainpane();
        this.pane4x4 = this.gameComponents.getMainpane();
        this.pane5x5 = this.gameComponents.getMainpane();
    }
    
    /**
     * Method for setting the basic stage appearance.
     * @param primaryStage 
     */
    private void setStage(Stage primaryStage) {
        Image iconImg = new Image("/Tic-Tac-Toe-Game-red.png");
        primaryStage.getIcons().add(iconImg);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(this.gameScene3x3);
        primaryStage.setResizable(false);
        primaryStage.toFront();
    }
    
    /**
     * Method for resetting game. 
     * @throws java.lang.Exception 
     */
    private void resetGame() throws Exception {
        Map<String, Pair<Integer, Integer>> fileScores = new HashMap<>();
        Pair<Integer, Integer> newPair = new Pair(0, 0);
        fileScores.put("X", newPair);
        fileScores.put("O", newPair);
        this.scoreService.resetPoints();
        this.scoreService.setAllMap(fileScores);
        this.scoreService.createScore("X", 0, 0);
        this.scoreService.createScore("O", 0, 0);
    }
    
    /**
     * Method for setting main buttons.
     */
    private void setButtons() {
        this.newgameButton3x3 = this.gameComponents.createButton("New Game", 150);
        this.newgameButton4x4 = this.gameComponents.createButton("New Game", 150);
        this.newgameButton5x5 = this.gameComponents.createButton("New Game", 150);
        this.resetButton3x3 = this.gameComponents.createButton("Reset", 100);
        this.resetButton4x4 = this.gameComponents.createButton("Reset", 100);
        this.resetButton5x5 = this.gameComponents.createButton("Reset", 100);
        this.quitButton3x3 = this.gameComponents.createButton("Quit Game", 150);
        this.quitButton4x4 = this.gameComponents.createButton("Quit Game", 150);
        this.quitButton5x5 = this.gameComponents.createButton("Quit Game", 150);
    }
    
    /**
     * Method for setting "new game" button actions for 3x3 area.
     * @param stage
     */
    private void setNewGameButtonActions3x3(Stage stage) {
        this.newgameButton3x3.setOnAction(e -> {
            try {
                this.setBoxes3x3();
                this.setAreaPaneAndScene3x3(1);
                stage.setScene(this.gameScene3x3);
            } catch (Exception ex) {
            }
        });
    }
    
    /**
     * Method for setting "new game" button actions for 4x4 area.
     * @param stage
     */
    private void setNewGameButtonActions4x4(Stage stage) {
        this.newgameButton4x4.setOnAction(e -> {
            try {
                this.setBoxes4x4();
                this.setAreaPaneAndScene4x4(1);
                stage.setScene(this.gameScene4x4);
            } catch (Exception ex) { 
            }
        });
    }
    
    /**
     * Method for setting "new game" button actions for 5x5 area.
     * @param stage
     */
    private void setNewGameButtonActions5x5(Stage stage) {
        this.newgameButton5x5.setOnAction(e -> {
            try {
                this.setBoxes5x5();
                this.setAreaPaneAndScene5x5(1);
                stage.setScene(this.gameScene5x5);
            } catch (Exception ex) {
            }
        });
    }
    
    /**
     * Method for setting "reset game" button actions for 3x3 area.
     * @param stage
     */
    private void setResetGameButtofnActions3x3(Stage stage) {
        this.resetButton3x3.setOnAction(e -> {
            try { 
                this.resetGame();
                this.setBoxes3x3();
                this.setAreaPaneAndScene3x3(1);
                stage.setScene(this.gameScene3x3);
            } catch (Exception ex) {
            }
        });
    }
    
    /**
     * Method for setting "reset game" button actions for 4x4 area.
     * @param stage
     */
    private void setResetGameButtofnActions4x4(Stage stage) {
        this.resetButton4x4.setOnAction(e -> {
            try { 
                this.resetGame();
                this.setBoxes4x4();
                this.setAreaPaneAndScene4x4(1);
                stage.setScene(this.gameScene4x4);
            } catch (Exception ex) { 
            }
        });
    }
    
    /**
     * Method for setting "reset game" button actions for 5x5 area.
     * @param stage
     */
    private void setResetGameButtofnActions5x5(Stage stage) {
        this.resetButton5x5.setOnAction(e -> {
            try { 
                this.resetGame();
                this.setBoxes5x5();
                this.setAreaPaneAndScene5x5(1);
                stage.setScene(this.gameScene5x5);
            } catch (Exception ex) {
            }
        });
    }
    
    /**
     * Method for setting "quit game" button actions.
     * @param stage
     */
    private void setQuitGameButtonActions(Stage stage) {
        this.quitButton3x3.setOnAction(e -> {
            try { 
                this.stop(stage);
            } catch (Exception ex) {
            }
        });
        this.quitButton4x4.setOnAction(e -> {
            try { 
                this.stop(stage);
            } catch (Exception ex) {
            }
        });
        this.quitButton5x5.setOnAction(e -> {
            try { 
                this.stop(stage);
            } catch (Exception ex) {
            }
        });
    }
    
    /**
     * Method for setting game size choice button actions for 3x3 to 4x4.
     * @param stage
     */
    private void setGameSizeChoiceButtonActions3to4(Stage stage) {
        this.choiceButton3to4.setOnMouseClicked(f -> {
            handleButtons(this.choiceButton4on4, this.choiceButton3on3, this.choiceButton3to4, this.choiceButton3to5, this.gameScene4x4, stage);
            WinRow.X.setWinCode("XXXX");
            WinRow.O.setWinCode("OOOO");
            GameSize.SIZE.setGameSize(4);
            try {
                this.setBoxes4x4();
                this.setAreaPaneAndScene4x4(1);
            } catch (Exception ex) {
            }
        });
    }
    
    /**
     * Method for setting game size choice button actions for 3x3 to 5x5.
     * @param stage
     */
    private void setGameSizeChoiceButtonActions3to5(Stage stage) {
        this.choiceButton3to5.setOnMouseClicked(f -> {
            handleButtons(this.choiceButton5on5, this.choiceButton3on3, this.choiceButton3to4, this.choiceButton3to5, this.gameScene5x5, stage);
            WinRow.X.setWinCode("XXXXX");
            WinRow.O.setWinCode("OOOOO");
            GameSize.SIZE.setGameSize(5);
            try {
                this.setBoxes5x5();
                this.setAreaPaneAndScene5x5(1);
            } catch (Exception ex) {
            }
        });
    }
    
    /**
     * Method for setting game size choice button action actions for 4x4 to 3x3.
     * @param stage
     */
    private void setGameSizeChoiceButtonActions4to3(Stage stage) {
        this.choiceButton4to3.setOnMouseClicked(f -> {
            handleButtons(this.choiceButton3on3, this.choiceButton4to3, this.choiceButton4on4, this.choiceButton4to5, this.gameScene3x3, stage);
            WinRow.X.setWinCode("XXX");
            WinRow.O.setWinCode("OOO");
            GameSize.SIZE.setGameSize(3);
            try {
                this.setBoxes3x3();
                this.setAreaPaneAndScene3x3(1);
            } catch (Exception ex) {
            }
        });
    }
    
    /**
     * Method for setting game size choice button action actions for 4x4 to 5x5.
     * @param stage
     */
    private void setGameSizeChoiceButtonActions4to5(Stage stage) {
        this.choiceButton4to5.setOnMouseClicked(f -> {
            handleButtons(this.choiceButton5on5, this.choiceButton4to3, this.choiceButton4on4, this.choiceButton4to5, this.gameScene5x5, stage);
            WinRow.X.setWinCode("XXXXX");
            WinRow.O.setWinCode("OOOOO");
            GameSize.SIZE.setGameSize(5);
            try {
                this.setBoxes5x5();
                this.setAreaPaneAndScene5x5(1);
            } catch (Exception ex) {
            }
        });
    }
    
    /**
     * Method for setting game size choice button actions for 5x5 to 3x3.
     * @param stage
     */
    private void setGameSizeChoiceButtonActions5to3(Stage stage) {
        this.choiceButton5to3.setOnMouseClicked(f -> {
            handleButtons(this.choiceButton3on3, this.choiceButton5to3, this.choiceButton5to4, this.choiceButton5on5, this.gameScene3x3, stage);
            WinRow.X.setWinCode("XXX");
            WinRow.O.setWinCode("OOO");
            GameSize.SIZE.setGameSize(3);
            try {
                this.setBoxes3x3();
                this.setAreaPaneAndScene3x3(1);
            } catch (Exception ex) {
            }
        });
    }
    
    /**
     * Method for setting game size choice button actions for 5x5 to 4x4.
     * @param stage
     */
    private void setGameSizeChoiceButtonActions5to4(Stage stage) {
        this.choiceButton5to4.setOnMouseClicked(f -> {
            handleButtons(this.choiceButton4on4, this.choiceButton5to3, this.choiceButton5to4, this.choiceButton5on5, this.gameScene4x4, stage);
            WinRow.X.setWinCode("XXXX");
            WinRow.O.setWinCode("OOOO");
            GameSize.SIZE.setGameSize(4);
            try {
                this.setBoxes4x4();
                this.setAreaPaneAndScene4x4(1);
            } catch (Exception ex) {
            }
        });
    }
    
    /**
     * Method for setting game size choice buttons for 3x3 game.
     */
    private void setChoiceButtons3x3() {
        this.choiceButton3on3 = this.gameComponents.createChoiceButton("3x3");
        this.choiceButton3to4 = this.gameComponents.createChoiceButton("4x4");
        this.choiceButton3to5 = this.gameComponents.createChoiceButton("5x5");
        this.choiceButton3on3.setBackground(BackGroundStyle.EFFECT.getBackGround());
        this.choiceButton3to4.setBackground(BackGroundStyle.BASIC.getBackGround());
        this.choiceButton3to5.setBackground(BackGroundStyle.BASIC.getBackGround());
    }
    
    /**
     * Method for setting game size choice buttons for 4x4 game.
     */
    private void setChoiceButtons4x4() {
        this.choiceButton4to3 = this.gameComponents.createChoiceButton("3x3");
        this.choiceButton4on4 = this.gameComponents.createChoiceButton("4x4");
        this.choiceButton4to5 = this.gameComponents.createChoiceButton("5x5");
        this.choiceButton4to3.setBackground(BackGroundStyle.BASIC.getBackGround());
        this.choiceButton4on4.setBackground(BackGroundStyle.BASIC.getBackGround());
        this.choiceButton4to5.setBackground(BackGroundStyle.BASIC.getBackGround());
    }
    
    /**
     * Method for setting game size choice buttons for 5x5 game.
     */
    private void setChoiceButtons5x5() {
        this.choiceButton5to3 = this.gameComponents.createChoiceButton("3x3");
        this.choiceButton5to4 = this.gameComponents.createChoiceButton("4x4");
        this.choiceButton5on5 = this.gameComponents.createChoiceButton("5x5");
        this.choiceButton5to3.setBackground(BackGroundStyle.BASIC.getBackGround());
        this.choiceButton5to4.setBackground(BackGroundStyle.BASIC.getBackGround());
        this.choiceButton5on5.setBackground(BackGroundStyle.BASIC.getBackGround());
    }
    
    /**
     * Method for changing game size choice button appearances.
     * @param buttonON
     * @param buttonOFF1
     * @param buttonOFF2
     * @param buttonOFF3
     * @param view
     * @param stage
     */
    private void handleButtons(Button buttonON, Button buttonOFF1, Button buttonOFF2, Button buttonOFF3, Scene view, Stage stage) {
        buttonON.setBackground(BackGroundStyle.EFFECT.getBackGround());
        buttonOFF1.setBackground(BackGroundStyle.BASIC.getBackGround());
        buttonOFF2.setBackground(BackGroundStyle.BASIC.getBackGround());
        buttonOFF3.setBackground(BackGroundStyle.BASIC.getBackGround());
        stage.setScene(view);
    }
    
    /**
     * Method for setting button boxes for 3x3 game.
     */
    private void setBoxes3x3() {
        this.scoreBox3x3 = this.gameComponents.getScoreBoxBig(this.scoreService);
        this.buttonBox3x3 = this.gameComponents.getButtonBox(this.newgameButton3x3, this.resetButton3x3, this.quitButton3x3);
        this.choiceBox3x3 = this.gameComponents.getChoiceBox(this.choiceButton3on3, this.choiceButton3to4, this.choiceButton3to5);
        this.bottomBox3x3 = this.gameComponents.getBottomBox(this.buttonBox3x3, this.choiceBox3x3);
    }
    
    /**
     * Method for setting button boxes for 4x4 game.
     */
    private void setBoxes4x4() {
        this.scoreBox4x4 = this.gameComponents.getScoreBoxBig(this.scoreService);
        this.buttonBox4x4 = this.gameComponents.getButtonBox(this.newgameButton4x4, this.resetButton4x4, this.quitButton4x4);
        this.choiceBox4x4 = this.gameComponents.getChoiceBox(this.choiceButton4to3, this.choiceButton4on4, this.choiceButton4to5);
        this.bottomBox4x4 = this.gameComponents.getBottomBox(this.buttonBox4x4, this.choiceBox4x4);
    }
    
    /**
     * Method for setting button boxes for 5x5 game.
     */
    private void setBoxes5x5() {
        this.scoreBox5x5 = this.gameComponents.getScoreBoxBig(this.scoreService);
        this.buttonBox5x5 = this.gameComponents.getButtonBox(this.newgameButton5x5, this.resetButton5x5, this.quitButton5x5);
        this.choiceBox5x5 = this.gameComponents.getChoiceBox(this.choiceButton5to3, this.choiceButton5to4, this.choiceButton5on5);
        this.bottomBox5x5 = this.gameComponents.getBottomBox(this.buttonBox5x5, this.choiceBox5x5);
    }
    
    /**
     * Method for setting game area (VBox), panes (BorderPane), and scene for 3x3 game.
     * @param addGames
     * @throws java.lang.Exception
     */
    private void setAreaPaneAndScene3x3(int addGames) throws Exception {
        this.area3x3 = this.gameComponents.getArea(addGames, this.scoreService, this.fileScoreDao);
        this.pane3x3.setTop(this.scoreBox3x3);
        this.pane3x3.setCenter(this.area3x3);
        this.pane3x3.setBottom(this.bottomBox3x3);
        this.gameScene3x3 = new Scene(this.pane3x3);
    }
    
    /**
     * Method for setting game area (VBox), panes (BorderPane), and scene for 4x4 game.
     * @param addGames
     * @throws java.lang.Exception
     */
    private void setAreaPaneAndScene4x4(int addGames) throws Exception {
        this.area4x4 = this.gameComponents.getArea(addGames, this.scoreService, this.fileScoreDao);
        this.pane4x4.setTop(this.scoreBox4x4);
        this.pane4x4.setCenter(this.area4x4);
        this.pane4x4.setBottom(this.bottomBox4x4);
        this.gameScene4x4 = new Scene(this.pane4x4);
    }
    
    /**
     * Method for setting game area (VBox), panes (BorderPane), and scene for 5x5 game.
     * @param addGames
     * @throws java.lang.Exception
     */
    private void setAreaPaneAndScene5x5(int addGames) throws Exception {
        this.area5x5 = this.gameComponents.getArea(addGames, this.scoreService, this.fileScoreDao);
        this.pane5x5.setTop(this.scoreBox5x5);
        this.pane5x5.setCenter(this.area5x5);
        this.pane5x5.setBottom(this.bottomBox5x5);
        this.gameScene5x5 = new Scene(this.pane5x5);
    }
}  
