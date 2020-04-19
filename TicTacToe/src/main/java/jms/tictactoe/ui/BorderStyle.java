package jms.tictactoe.ui;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Enum for setting button border style.
 * @author jaris
 */
public enum BorderStyle {
    LIGHT (new Border(new BorderStroke(Color.LIGHTBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT))),
    MEDIUM (new Border(new BorderStroke(Color.rgb(70, 80, 110), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
    
    public Border borderStyle;
    
    BorderStyle(Border borderStyle) {
        this.borderStyle = borderStyle;
    }
    
    public Border getBorder() {
        return borderStyle;
    }

}
