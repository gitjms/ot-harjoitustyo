package jms.tictactoe.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 *
 * @author jaris
 */
public enum BackGroundStyle {
    BASIC (new Background(new BackgroundFill(Color.rgb(50, 50, 70), CornerRadii.EMPTY, Insets.EMPTY))),
    EFFECT (new Background(new BackgroundFill(Color.STEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    
    public Background background;
    
    BackGroundStyle(Background background) {
        this.background = background;
    }
    
    public Background getBackGround() {
        return background;
    }

}
