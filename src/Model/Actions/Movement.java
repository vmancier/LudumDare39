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
        this.moveTo = moveTo;
        this.direction = direction;
        this.queue = moveTo.getQueue();
        this.body = queue.getBody();
    }

    @Override
    public void execute() {
        boolean success = true;
        switch (direction) {
            case up: {
                success = body.moveUp();
                break;
            }
            case down: {
                success = body.moveDown();
                break;
            }
            case right: {
                success = body.moveRight();
                break;
            }
            case left: {
                success = body.moveLeft();
                break;
            }
        }
        if (!success) {
            this.interrupt();
        }
//        if (moveTo.getTarget().getPos_x() == body.getPos_x()
//                && moveTo.getTarget().getPos_y() == body.getPos_y()) {
//            moveTo.getTarget().remove_surbrillance();
//        }
    }

    @Override
    public void end() {

    }

    private void interrupt() {
        queue.clearQueue();
//        queue.addFirst(moveTo);
//        @TODO enlever les précedents mouvements
    }
}
