package Model.Actions;

import java.util.Queue;
enum Directions {up, down, right, left}

public class Movement implements Action {
    MoveTo moveTo;
    Queue queue;
    Directions direction;
    Character character;

//    @TODO si interruption, remplacer par un MoveTo

    @Override
    public void execute() {
        
    }
}
