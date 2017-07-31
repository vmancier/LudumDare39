package Model.Actions;


import Application.Main;
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
                if (Main.get_model().isFree(body.getPos_x(),body.getPos_y()-1)){
                    success = body.moveUp();
                }
                break;
            }
            case down: {
                if (Main.get_model().isFree(body.getPos_x(),body.getPos_y()+1)){
                    success = body.moveDown();
                }
                break;
            }
            case right: {
                if (Main.get_model().isFree(body.getPos_x()+1,body.getPos_y())){
                success = body.moveRight();}
                break;
            }
            case left: {
                if (Main.get_model().isFree(body.getPos_x()-1,body.getPos_y())){
                success = body.moveLeft();}
                break;
            }
        }
        if (!success) {
            queue.removeNextMouvements();
            queue.executeNext();
        }
    }

    @Override
    public void end() {

    }
}
