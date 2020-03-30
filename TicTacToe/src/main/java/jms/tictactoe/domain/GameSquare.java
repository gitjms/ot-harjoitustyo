package jms.tictactoe.domain;

import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.effect.Bloom;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Pair;

import jms.tictactoe.dao.FileScoreDao;


/**
 * Class for creating clickable squares for the game area and checking winning rows.
 * @author jaris
 */
public class GameSquare {

    private ScoreService scoreService;
    private boolean finished;
    private Label textLabel;
    private Game game;
    
    public GameSquare(Label textLabel,Game game,ScoreService scoreService,FileScoreDao fileScoreDao) throws Exception{
        this.game = game;
        this.textLabel = textLabel;
        this.scoreService = scoreService;
        this.finished = false;
    }
    
    /**
     * Method for creating the square buttons and their operations.Buttons are for X and O markings.Method uses method "isFinished" to check if someone wins.
     * @param squares array to store X and O marks
     * @param id array item id:s
     * @return gameSquare
     */
    public Button createSquare(String[][] squares,String id){
        final Button gameSquare = new Button(" ");
        this.setTextlabel(textLabel);
        gameSquare.setId(id);
        gameSquare.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));
        gameSquare.setBackground(new Background(new BackgroundFill(Color.STEELBLUE,CornerRadii.EMPTY,Insets.EMPTY)));
        Bloom bloomEffect = new Bloom();
        bloomEffect.setThreshold(0.75);
        gameSquare.setEffect(bloomEffect);
        gameSquare.setBorder(new Border(new BorderStroke(Color.LIGHTBLUE,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        gameSquare.setOnMouseClicked((MouseEvent event) -> {
            String whoseTurn=this.textLabel.getText();

            if(!isFinished(squares) && !this.getFinished()){
                if(gameSquare.getText().trim().isEmpty()){
                    int x=(int) Integer.parseInt(id.split(" ")[0]);
                    int y=(int) Integer.parseInt(id.split(" ")[1]);
                    if(whoseTurn.contains("X")){
                        gameSquare.setText("X");
                        gameSquare.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));
                        gameSquare.setTextFill(Color.LIGHTPINK);
                        this.textLabel.setText("O:s turn");
                        this.textLabel.setTextFill(Color.LEMONCHIFFON);
                        squares[x][y]="X";
                    }else{
                        gameSquare.setText("O");
                        gameSquare.setText("O");
                        gameSquare.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));
                        gameSquare.setTextFill(Color.LEMONCHIFFON);
                        this.textLabel.setText("X:s turn");
                        this.textLabel.setTextFill(Color.LIGHTPINK);
                        squares[x][y]="O";
                    }
                }
            } else gameSquare.disarm();
            this.isFinished(squares);
        });
    
        return gameSquare;
    }
    
    /**
     * Method for checking if the game is finished.
     * @param squaresTocheck array to store X and O marks
     * @return false if game is not finished, true if someone wins or game is a draw
     */
    public boolean isFinished(String[][] squaresTocheck){
        if(!this.getFinished()){
            for(int i=0;i<3;i++){
                String row1="";
                String row2="";
                for(int j=0;j<3;j++){
                    row1+=squaresTocheck[i][j];
                    row2+=squaresTocheck[j][i];
                }
                if(checkRow(row1)) return true;
                if(checkRow(row2)) return true;
            }

            String row3="";
            String row4="";
            for(int i=0;i<3;i++){
                row3+=squaresTocheck[i][i];
                row4+=squaresTocheck[2-i][i];
            }
            if(checkRow(row3)) return true;
            if(checkRow(row4)) return true;

            String row="";
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    row+=squaresTocheck[i][j];
                }
            }
            if(checkDraw(row)) return true;
        }

        return false;
    }

    /**
     * Method for checking rows and to announce winner.
     * @param textLabel label for X or O marks
     * @param rowTocheck array to store X and O marks
     * @return false if no ready row, true if ready row
     */
    private boolean checkRow(String rowTocheck){
        WinRow winRowX3 = WinRow.X3;
        WinRow winRowO3 = WinRow.O3;
        if(rowTocheck.equals(winRowX3.getWinCode())){
            this.setFinished(true);
            this.getTextlabel().setText("X wins!");
            this.getTextlabel().setTextFill(Color.LIGHTPINK);
            this.game.setWinner("X");
            this.scoreService.createScore("X", this.scoreService.getPoints("X")+1, this.scoreService.getGames());
            this.scoreService.createScore("O", this.scoreService.getPoints("O"), this.scoreService.getGames());

            return true;
        }else if(rowTocheck.equals(winRowO3.getWinCode())){
            this.setFinished(true);
            this.getTextlabel().setText("O wins!");
            this.getTextlabel().setTextFill(Color.LEMONCHIFFON);
            this.game.setWinner("O");
            this.scoreService.createScore("X", this.scoreService.getPoints("X"), this.scoreService.getGames());
            this.scoreService.createScore("O", this.scoreService.getPoints("O")+1, this.scoreService.getGames());

            return true;
        }
        return false;
    }

    /**
     * Method for checking if the game is a draw.
     * @param textLabel label for X or O marks
     * @param rowTocheck array to store X and O marks
     * @return false if not a draw, true if a draw
     */
    private boolean checkDraw(String rowTocheck){
        if(!rowTocheck.contains(" ")){
            this.setFinished(true);
            this.getTextlabel().setText("Draw!");
            this.getTextlabel().setTextFill(Color.LIGHTSTEELBLUE);
            this.game.setDraw();
            this.scoreService.createScore("X", this.scoreService.getPoints("X"), this.scoreService.getGames());
            this.scoreService.createScore("O", this.scoreService.getPoints("O"), this.scoreService.getGames());
            Map<String, Pair<Integer,Integer>> fileScores = this.scoreService.getAllMap();
            fileScores.put("X", new Pair(this.scoreService.getPoints("X"), this.scoreService.getGames()));
            fileScores.put("O", new Pair(this.scoreService.getPoints("O"), this.scoreService.getGames()));
            this.scoreService.setAllMap(fileScores);
            return true;
        }

        return this.getFinished();
    }
    
    public void setTextlabel(Label textLabel) {
        this.textLabel = textLabel;
    }
    
    public void setFinished(boolean which) {
        this.finished = which;
    }
    
    public boolean getFinished() {
        return finished;
    }
    
    public Label getTextlabel() {
        return textLabel;
    }

}

