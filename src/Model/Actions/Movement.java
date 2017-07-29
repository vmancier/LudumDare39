package Model.Actions;


import Model.ActionQueue;
import Model.Body;

enum Directions {up, down, right, left}

public class Movement implements Action {
    private MoveTo moveTo;
    private ActionQueue queue;
    private Directions direction;
    private Body body;

    public Movement(MoveTo moveTo, Directions direction) {
        this.moveTo=moveTo;
        this.direction=direction;
        this.queue=moveTo.getQueue();
        this.body=queue.getCharacter();
    }

//    @TODO si interruption, remplacer par un MoveTo

    @Override
    public void execute() {
        switch (direction) {
            case up: {
                body.moveUp();
                break;
            }
            case down: {
                body.moveDown();
                break;
            }
            case right: {
                body.moveRight();
                break;
            }
            case left: {
                body.moveLeft();
                break;
            }
        }
    }
}
