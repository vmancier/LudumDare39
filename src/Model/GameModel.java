package Model;

public class GameModel {

    private int _number;

    public GameModel(){
        _number = 0;
    }

    public int getNumber(){
        return _number;
    }

    public void setNumber(){
        _number++;
    }

    public void nextStep(double elapsedTime){

    }
}
