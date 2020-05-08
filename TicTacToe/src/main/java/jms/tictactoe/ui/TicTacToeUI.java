package jms.tictactoe.ui;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import jms.tictactoe.dao.ScoreDataDao;
import jms.tictactoe.domain.ScoreData;
import jms.tictactoe.domain.ScoreService;

/**
 *Main class for GUI generation.
 * @author jaris
 */
public class TicTacToeUI extends Application {
    
    private Connection connection;
    private Statement statement;
    private ScoreData scoreData;
    private ScoreService scoreService;
    private GameComponents gameComponents;
    private GameArea gameArea;
    private Button dbButton;
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
    private HBox databaseChoice;
    private BorderPane pane3x3;
    private BorderPane pane4x4;
    private BorderPane pane5x5;
    private String otherDbName = "";
    private TextField dbChoiceTextField;
    private Stage stage;
    
    /**
     * Initiate main components.
     * @throws java.io.FileNotFoundException throws file not found exception
     */
    @Override
    public void init() throws FileNotFoundException, IOException, Exception {
        this.setDatabaseChoiceButtonActions();
        this.setPanesAndScenes();
        WinRow.X.setWinCode("XXX");
        WinRow.O.setWinCode("OOO");
        GameSize.SIZE.setGameSize(3);
        this.setButtons();
        this.setChoiceButtons3x3();
        this.setChoiceButtons4x4();
        this.setChoiceButtons5x5();
        this.setDaoAndDatabase();
        this.setBoxes3x3();
        this.setBoxes4x4();
        this.setBoxes5x5();
        this.setAreaPaneAndScene3x3();
        this.setAreaPaneAndScene4x4();
        this.setAreaPaneAndScene5x5();
    }
    
    /**
     * Method for starting the user interface.
     * @param primaryStage game stage
     * @throws java.lang.Exception throws exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.setNewGameButtonActions();
        this.setResetGameButtofnActions();
        this.setQuitGameButtonActions();
        this.setGameSizeChoiceButtonActions(this.choiceButton4on4, this.choiceButton3to4, this.choiceButton3on3, this.choiceButton3to5, primaryStage, "XXXX", "OOOO", 4);
        this.setGameSizeChoiceButtonActions(this.choiceButton5on5, this.choiceButton3to5, this.choiceButton3on3, this.choiceButton3to4, primaryStage, "XXXXX", "OOOOO", 5);
        this.setGameSizeChoiceButtonActions(this.choiceButton3on3, this.choiceButton4to3, this.choiceButton4on4, this.choiceButton4to5, primaryStage, "XXX", "OOO", 3);
        this.setGameSizeChoiceButtonActions(this.choiceButton5on5, this.choiceButton4to5, this.choiceButton4to3, this.choiceButton4on4, primaryStage, "XXXXX", "OOOOO", 5);
        this.setGameSizeChoiceButtonActions(this.choiceButton3on3, this.choiceButton5to3, this.choiceButton5to4, this.choiceButton5on5, primaryStage, "XXX", "OOO", 3);
        this.setGameSizeChoiceButtonActions(this.choiceButton4on4, this.choiceButton5to4, this.choiceButton5to3, this.choiceButton5on5, primaryStage, "XXXX", "OOOO", 4);
        this.choiceButton3on3.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setBoxes3x3();
        this.setAreaPaneAndScene3x3();
        this.setStage(this.stage);
        this.stage.show();
    }

    /**
     * Method for closing the user interface.
     * @param primaryStage game stage
     * @throws SQLException throws SQL exception
     */
    public void stop(Stage primaryStage) throws SQLException {
        System.out.println("Application closes");
        this.scoreService.closeStatement(this.statement); 
        this.scoreService.closeConnection(this.connection); 
        primaryStage.close();
    }
    
    /**
     * Default main method.
     * @param args default parameter
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Method for setting dao, database and game area.
     * @throws Exception throws exception
     */
    private void setDaoAndDatabase() throws Exception {
        this.setDataBase();
        this.scoreData = new ScoreData(this.connection, this.statement);
        ScoreDataDao scoreDataDao = new ScoreDataDao(this.scoreData, this.connection, this.statement);
        this.scoreService = new ScoreService(scoreDataDao);
        this.gameArea = new GameArea(this.scoreService);
    }
            
    /**
     * Method for setting necesary tools: database, mainpanes and scenes.
     */
    private void setPanesAndScenes() {
        this.pane3x3 = this.gameComponents.getMainpane();
        this.pane4x4 = this.gameComponents.getMainpane();
        this.pane5x5 = this.gameComponents.getMainpane();
        this.gameScene3x3 = new Scene(this.pane3x3);
        this.gameScene4x4 = new Scene(this.pane4x4);
        this.gameScene5x5 = new Scene(this.pane5x5);
    }
    
    /**
     * Method for setting database. Default is scoreData. If there is not scoreData file,
     * it will be created. Checks if user wants other database and tries to use it.
     * @throws FileNotFoundException throws file not found exception
     * @throws IOException throws IO exception
     * @throws SQLException throws SQL exception
     */
    public void setDataBase() throws FileNotFoundException, IOException, SQLException {
        Properties properties = new Properties();
        if (!new File("config.properties").exists()) {
            try (FileWriter writer = new FileWriter("config.properties")) {
                writer.write("scoreData=scoreData\nurl=jdbc:h2:./\nuser=sa\npassword=");
                writer.close();
            } catch (IOException e) {
            }
        }
        properties.load(new FileInputStream("config.properties"));
        final String fileName = this.otherDbName.trim().isEmpty() ? "./" + properties.getProperty("scoreData") : this.otherDbName.trim();
        final String dbUrl = properties.getProperty("url");
        final String dbUser = properties.getProperty("user");
        final String dbPass = properties.getProperty("password");
        this.tryConnection(properties, dbUrl, fileName, dbUser, dbPass);
        this.statement = this.connection.createStatement();
    }
    
    /**
     * Method for testing the database connection.If it fails, default scoreData will be used. 
     * @param properties configuration properties
     * @param dbUrl database URL
     * @param file database name
     * @param dbUser database user
     * @param dbPass database password
     */
    public void tryConnection(Properties properties, String dbUrl, String file, String dbUser, String dbPass) {
        try { 
            this.connection = DriverManager.getConnection(dbUrl + file, dbUser, dbPass);
        } catch (SQLException ex) {
            final String fileName = "./" + properties.getProperty("scoreData");
            try {
                this.connection = DriverManager.getConnection(dbUrl + fileName, dbUser, dbPass);
            } catch (SQLException ex1) {
                Logger.getLogger(TicTacToeUI.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    /**
     * Method for setting the basic stage appearance.
     * @param primaryStage game stage
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
     * Method for setting main buttons.
     */
    private void setButtons() {
        this.newgameButton3x3 = this.gameComponents.createButton("New Game", 150, 30, 20, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.newgameButton4x4 = this.gameComponents.createButton("New Game", 150, 30, 20, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.newgameButton5x5 = this.gameComponents.createButton("New Game", 150, 30, 20, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.resetButton3x3 = this.gameComponents.createButton("Reset", 100, 30, 20, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.resetButton4x4 = this.gameComponents.createButton("Reset", 100, 30, 20, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.resetButton5x5 = this.gameComponents.createButton("Reset", 100, 30, 20, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.quitButton3x3 = this.gameComponents.createButton("Quit Game", 150, 30, 20, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.quitButton4x4 = this.gameComponents.createButton("Quit Game", 150, 30, 20, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.quitButton5x5 = this.gameComponents.createButton("Quit Game", 150, 30, 20, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
    }
    
    /**
     * Method for setting "new game" button actions for 3x3 area.
     * @param stage game stage
     */
    private void setDatabaseChoiceButtonActions() {
        this.gameComponents = new GameComponents();
        this.dbChoiceTextField = new TextField("");
        this.dbChoiceTextField.setStyle("-fx-font-size: 16px;");
        this.dbButton = this.gameComponents.createButton("GO", 55, 45, 15, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.databaseChoice = this.gameComponents.getDatabaseChoice(this.dbButton, this.dbChoiceTextField);
        this.dbButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
            (MouseEvent e) -> {
                this.otherDbName = this.dbChoiceTextField.getText();
                try {
                    this.scoreService.closeStatement(this.statement); 
                    this.scoreService.closeConnection(this.connection); 
                    this.init();
                    this.start(this.stage);
                } catch (Exception ex) {
                    Logger.getLogger(TicTacToeUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        );
    }
    
    /**
     * Method for setting "new game" button actions.
     * @param stage game stage
     */
    private void setNewGameButtonActions() {
        this.newgameButton3x3.setOnAction(e -> {
            this.setBoxes3x3();
            this.setAreaPaneAndScene3x3();
            this.stage.setScene(this.gameScene3x3);
        });
        this.newgameButton4x4.setOnAction(e -> {
            this.setBoxes4x4();
            this.setAreaPaneAndScene4x4();
            this.stage.setScene(this.gameScene4x4);
        });
        this.newgameButton5x5.setOnAction(e -> {
            this.setBoxes5x5();
            this.setAreaPaneAndScene5x5();
            this.stage.setScene(this.gameScene5x5);

        });
    }

    /**
     * Method for setting "reset game" button actions for 3x3 area.
     * @param stage game stage
     */
    private void setResetGameButtofnActions() {
        this.resetButton3x3.setOnAction(e -> {
            this.scoreService.resetPoints();
            this.setBoxes3x3();
            this.setAreaPaneAndScene3x3();
            this.stage.setScene(this.gameScene3x3);
        });
        this.resetButton4x4.setOnAction(e -> {
            this.scoreService.resetPoints();
            this.setBoxes4x4();
            this.setAreaPaneAndScene4x4();
            this.stage.setScene(this.gameScene4x4);
        });
        this.resetButton5x5.setOnAction(e -> {
            this.scoreService.resetPoints();
            this.setBoxes5x5();
            this.setAreaPaneAndScene5x5();
            this.stage.setScene(this.gameScene5x5);
        });
    }
     
    /**
     * Method for setting "quit game" button actions.
     * @param stage game stage
     */
    private void setQuitGameButtonActions() {
        this.quitButton3x3.setOnAction(e -> {
            try { 
                this.stop(this.stage);
            } catch (SQLException ex) {
            }
        });
        this.quitButton4x4.setOnAction(e -> {
            try { 
                this.stop(this.stage);
            } catch (SQLException ex) {
            }
        });
        this.quitButton5x5.setOnAction(e -> {
            try { 
                this.stop(this.stage);
            } catch (SQLException ex) {
            }
        });
    }
    
    /** 
     * Method for setting game size choice button actions.
     * @param stage game stage
     */
    private void setGameSizeChoiceButtonActions(Button on, Button off1, Button off2, Button off3, Stage stage, String xs, String os, int which) {
        off1.setOnMouseClicked(f -> {
            handleButtons(on, off1, off2, off3, which == 3 ? this.gameScene3x3 : which == 4 ? this.gameScene4x4 : this.gameScene5x5, stage, xs, os, which);
            switch (which) {
                case 3:
                    this.setBoxes3x3();
                    this.setAreaPaneAndScene3x3();
                    break;
                case 4:
                    this.setBoxes4x4();
                    this.setAreaPaneAndScene4x4();
                    break;
                default:
                    this.setBoxes5x5();
                    this.setAreaPaneAndScene5x5();
                    break;
            }
        });
    }
 
    /**
     * Method for setting game size choice buttons for 3x3 game.
     */
    private void setChoiceButtons3x3() {
        this.choiceButton3on3 = this.gameComponents.createButton("3x3", 60, 45, 17, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.choiceButton3to4 = this.gameComponents.createButton("4x4", 60, 45, 17, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.choiceButton3to5 = this.gameComponents.createButton("5x5", 60, 45, 17, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
    }
    
    /**
     * Method for setting game size choice buttons for 4x4 game.
     */
    private void setChoiceButtons4x4() {
        this.choiceButton4to3 = this.gameComponents.createButton("3x3", 60, 45, 17, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.choiceButton4on4 = this.gameComponents.createButton("4x4", 60, 45, 17, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.choiceButton4to5 = this.gameComponents.createButton("5x5", 60, 45, 17, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
    }
    
    /**
     * Method for setting game size choice buttons for 5x5 game.
     */
    private void setChoiceButtons5x5() {
        this.choiceButton5to3 = this.gameComponents.createButton("3x3", 60, 45, 17, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.choiceButton5to4 = this.gameComponents.createButton("4x4", 60, 45, 17, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
        this.choiceButton5on5 = this.gameComponents.createButton("5x5", 60, 45, 17, (new Background(new BackgroundFill(Color.rgb(70, 80, 110), CornerRadii.EMPTY, Insets.EMPTY))));
    }
    
    /**
     * Method for changing game size choice button appearances.
     * @param buttonON button to set on
     * @param buttonOFF1 button to set off
     * @param buttonOFF2 button to set off
     * @param buttonOFF3 button to set off
     * @param view scene to handle
     * @param stage game stage
     * @param xs text string for the win code for X
     * @param os text string for the win code for O
     * @param which indication for the game size
     */
    private void handleButtons(Button buttonON, Button buttonOFF1, Button buttonOFF2, Button buttonOFF3, Scene view, Stage stage, String xs, String os, int which) {
        WinRow.X.setWinCode(xs);
        WinRow.O.setWinCode(os);
        GameSize.SIZE.setGameSize(which == 3 ? 3 : which == 4 ? 4 : 5);
        buttonON.setBackground(new Background(new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        buttonOFF1.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        buttonOFF2.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        buttonOFF3.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 70), CornerRadii.EMPTY, Insets.EMPTY)));
        stage.setScene(view);
    }
    
    /**
     * Method for setting button boxes for 3x3 game.
     */
    private void setBoxes3x3() {
        this.scoreBox3x3 = this.gameComponents.getScoreBoxBig(this.scoreService);
        this.buttonBox3x3 = this.gameComponents.getButtonBox(this.newgameButton3x3, this.resetButton3x3, this.quitButton3x3);
        this.choiceBox3x3 = this.gameComponents.getChoiceBox(this.databaseChoice, this.choiceButton3on3, this.choiceButton3to4, this.choiceButton3to5);
        this.bottomBox3x3 = this.gameComponents.getBottomBox(this.buttonBox3x3, this.choiceBox3x3);
    }
    
    /**
     * Method for setting button boxes for 4x4 game.
     */
    private void setBoxes4x4() {
        this.scoreBox4x4 = this.gameComponents.getScoreBoxBig(this.scoreService);
        this.buttonBox4x4 = this.gameComponents.getButtonBox(this.newgameButton4x4, this.resetButton4x4, this.quitButton4x4);
        this.choiceBox4x4 = this.gameComponents.getChoiceBox(this.databaseChoice, this.choiceButton4to3, this.choiceButton4on4, this.choiceButton4to5);
        this.bottomBox4x4 = this.gameComponents.getBottomBox(this.buttonBox4x4, this.choiceBox4x4);
    }
    
    /**
     * Method for setting button boxes for 5x5 game.
     */
    private void setBoxes5x5() {
        this.scoreBox5x5 = this.gameComponents.getScoreBoxBig(this.scoreService);
        this.buttonBox5x5 = this.gameComponents.getButtonBox(this.newgameButton5x5, this.resetButton5x5, this.quitButton5x5);
        this.choiceBox5x5 = this.gameComponents.getChoiceBox(this.databaseChoice, this.choiceButton5to3, this.choiceButton5to4, this.choiceButton5on5);
        this.bottomBox5x5 = this.gameComponents.getBottomBox(this.buttonBox5x5, this.choiceBox5x5);
    }
    
    /**
     * Method for setting game area (VBox), panes (BorderPane), and scene for 3x3 game.
     */
    private void setAreaPaneAndScene3x3() {
        this.area3x3 = this.gameArea.createArea();
        this.pane3x3.setTop(this.scoreBox3x3);
        this.pane3x3.setCenter(this.area3x3);
        this.pane3x3.setBottom(this.bottomBox3x3);
    }
    
    /**
     * Method for setting game area (VBox), panes (BorderPane), and scene for 4x4 game.
     */
    private void setAreaPaneAndScene4x4() {
        this.area4x4 = this.gameArea.createArea();
        this.pane4x4.setTop(this.scoreBox4x4);
        this.pane4x4.setCenter(this.area4x4);
        this.pane4x4.setBottom(this.bottomBox4x4);
    }
    
    /**
     * Method for setting game area (VBox), panes (BorderPane), and scene for 5x5 game.
     */
    private void setAreaPaneAndScene5x5() {
        this.area5x5 = this.gameArea.createArea();
        this.pane5x5.setTop(this.scoreBox5x5);
        this.pane5x5.setCenter(this.area5x5);
        this.pane5x5.setBottom(this.bottomBox5x5);
    }
}  
