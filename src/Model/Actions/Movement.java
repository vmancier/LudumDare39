package Model.Actions;


import Model.ActionQueue;
import Model.Body;

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
//        @TODO Checker si la case visée est libre.
//        @TODO Si elle ne l'est pas, annuler toutes les actions de mouvements suivantes et executeNext (pour pas perdre de tour)
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
