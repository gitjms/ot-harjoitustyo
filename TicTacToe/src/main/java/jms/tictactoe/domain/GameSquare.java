package jms.tictactoe.domain;

import jms.tictactoe.domain.Game;
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

/**
 * Class for creating clickable squares for the game area.
 * @author jaris
 */
public class GameSquare {

    private Button gameSquare;
    private boolean finished;
    private Label textLabel;
    private Label gameLabel;
    
    
    public GameSquare(Label textLabel,Label gameLabel){
        this.textLabel = textLabel;
        this.gameLabel = gameLabel;
        this.finished = false;
    }

    /**
     * Method for creating the square buttons and their operations.
     * Buttons are for X and O markings.
     * Method uses method "isFinished" to check if someone wins.
     * @param game
     * @param textLabel label for X or O marks
     * @param gameLabel
     * @param squares array to store X and O marks
     * @param id array item id:s
     * @return gameSquare
     */
    public Button createSquare(Game game, Label textLabel, Label gameLabel, String[][] squares, String id){
        this.setTextlabel(textLabel);
        this.setGamelabel(gameLabel);
        this.gameSquare = new Button(" ");
        this.gameSquare.setId(id);
        this.gameSquare.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));
        this.gameSquare.setBackground(new Background(new BackgroundFill(Color.STEELBLUE,CornerRadii.EMPTY,Insets.EMPTY)));
        Bloom bloomEffect = new Bloom();
        bloomEffect.setThreshold(0.75);
        this.gameSquare.setEffect(bloomEffect);
        this.gameSquare.setBorder(new Border(new BorderStroke(Color.LIGHTBLUE,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        this.gameSquare.setOnMouseClicked((MouseEvent event) -> {
            String whoseTurn=this.textLabel.getText();

            if(!isFinished(game,squares) && !this.finished){
                if(this.gameSquare.getText().trim().isEmpty()){
                    int x=(int) Integer.parseInt(id.split(" ")[0]);
                    int y=(int) Integer.parseInt(id.split(" ")[1]);
                    if(whoseTurn.contains("X")){
                        this.gameSquare.setText("X");
                        this.gameSquare.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));
                        this.gameSquare.setTextFill(Color.LIGHTPINK);
                        this.textLabel.setText("O:s turn");
                        this.textLabel.setTextFill(Color.LEMONCHIFFON);
                        squares[x][y]="X";
                    }else{
                        this.gameSquare.setText("O");
                        this.gameSquare.setText("O");
                        this.gameSquare.setFont(Font.font("Monospaced", FontWeight.BOLD, 50));
                        this.gameSquare.setTextFill(Color.LEMONCHIFFON);
                        this.textLabel.setText("X:s turn");
                        this.textLabel.setTextFill(Color.LIGHTPINK);
                        squares[x][y]="O";
                    }
                }
            } else this.gameSquare.disarm();
            this.isFinished(game,squares);
        });
    
        return this.gameSquare;
    }

    /**
     * Method for checking if the game is finished.
     * @param game
     * @param squaresTocheck array to store X and O marks
     * @return false if game is not finished, true if someone wins or game is a draw
     */
    public boolean isFinished(Game game, String[][] squaresTocheck){
        if(this.finished==false){
            for(int i=0;i<3;i++){
                String row1="";
                String row2="";
                for(int j=0;j<3;j++){
                    row1+=squaresTocheck[i][j];
                    row2+=squaresTocheck[j][i];
                }
                if(checkRow(game,row1)) return true;
                if(checkRow(game,row2)) return true;
            }

            String row3="";
            String row4="";
            for(int i=0;i<3;i++){
                row3+=squaresTocheck[i][i];
                row4+=squaresTocheck[2-i][i];
            }
            if(checkRow(game,row3)) return true;
            if(checkRow(game,row4)) return true;

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
    private boolean checkRow(Game game, String rowTocheck){
        if(rowTocheck.equals("XXX")){
            this.finished=true;
            this.getTextlabel().setText("X wins!");
            this.getTextlabel().setTextFill(Color.LIGHTPINK);
            game.setWinner("X");
            return true;
        }else if(rowTocheck.equals("OOO")){
            this.finished=true;
            this.getTextlabel().setText("O wins!");
            this.getTextlabel().setTextFill(Color.LEMONCHIFFON);
            game.setWinner("O");
            return true;
        }

        return this.finished;
    }

    /**
     * Method for checking if the game is in draw.
     * @param textLabel label for X or O marks
     * @param rowTocheck array to store X and O marks
     * @return false if not a draw, true if a draw
     */
    private boolean checkDraw(String rowTocheck){
        if(!rowTocheck.contains(" ")){
            this.finished=true;
            this.getTextlabel().setText("Draw!");
            this.getTextlabel().setTextFill(Color.LIGHTSTEELBLUE);
            return true;
        }

        return this.finished;
    }
    
    public void setTextlabel(Label textLabel) {
        this.textLabel = textLabel;
    }
    
    public Label getTextlabel() {
        return textLabel;
    }
    
    public void setGamelabel(Label gameLabel) {
        this.gameLabel = gameLabel;
    }
    
    public Label getGamelabel() {
        return gameLabel;
    }
}

