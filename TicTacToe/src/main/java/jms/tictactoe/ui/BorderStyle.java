package jms.tictactoe.ui;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 *
 * @author jaris
 */
public enum BorderStyle {
    BORDER (new Border(new BorderStroke(Color.LIGHTBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    
    public Border borderStyle;
    
    BorderStyle(Border borderStyle) {
        this.borderStyle = borderStyle;
    }
    
    public Border getBorder() {
        return borderStyle;
    }

}
