package Application;

import Model.GameModel;
import Model.Map;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static Application.Entities.WINDOW_HEIGHT;
import static Application.Entities.WINDOW_WIDTH;

public class DrawGame {

    Rectangle background = new Rectangle(WINDOW_WIDTH,WINDOW_HEIGHT, Color.BLACK);

    public DrawGame(){
    }

    public void update(Pane root, GameModel gameModel){
        root.getChildren().addAll(background);
        DrawMap.draw(gameModel.getMap(),root);
    }


}