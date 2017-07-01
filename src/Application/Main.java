package Application;


import Model.GameModel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.time.LocalTime;

enum GameState { Menu, Play, Settings, Lose };

public class Main extends Application {
    private static GameModel _model;
    private static GameState _gs;
    private static long _time;

    public static void main(String args[]) {
        _model = new GameModel();
        _gs = GameState.Menu;
        _time = System.currentTimeMillis();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(Entities.GAME_NAME);
        StackPane root = new StackPane();

        //Event selon l'etat du jeu
        switch (_gs)
        {
            case Menu :
                drawMenu(primaryStage, root);
                break;
            case Play:
                //Temps écoulé depuis la dernière exécution
                double elapsedTime = (System.currentTimeMillis()-_time)/1000.0;
                _time = System.currentTimeMillis();

                _model.nextStep(elapsedTime);
                drawGame(primaryStage, root);
                break;
            case Settings:
                drawSettings(primaryStage, root);
                break;
            case Lose:
                drawLose(primaryStage, root);
                break;
            default:
                //Problems
                break;
        }

        primaryStage.setScene(new Scene(root,Entities.WINDOW_WIDTH ,Entities.WINDOW_HEIGHT ));
        primaryStage.show();
    }

    public void drawMenu(Stage primaryStage, StackPane root){
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnMousePressed(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {

                System.out.println(_model.getNumber());
                _model.setNumber();
            }

        });

        root.getChildren().add(btn);

    }

    public void drawGame(Stage primaryStage, StackPane root){

    }

    public void drawLose(Stage primaryStage, StackPane root){

    }

    public void drawSettings(Stage primaryStage, StackPane root){

    }

    public void drawSubMenu(Stage primaryStage, StackPane root){

    }
}
