package Model.Actions;

import Model.ActionQueue;
import Model.Body;
import Model.Case;

import static Model.Actions.Directions.*;

public class MoveTo implements Action {
    private ActionQueue queue;
    private Case target;
    private Body subject;

    public MoveTo(ActionQueue queue, Case target) {
        this.queue = queue;
        this.target = target;
        this.subject = queue.getBody();
    }

    @Override
    public void execute() {
        int x_diff = target.getPos_x() - subject.getPos_x();
        int y_diff = target.getPos_y() - subject.getPos_y();
        Directions vert_direct;
        Directions hori_direct;
        if (y_diff > 0) {
            vert_direct = down;
        } else {
            y_diff *= -1;
            vert_direct = up;
        }
        if (x_diff > 0) {
            hori_direct = right;
        } else {
            x_diff *= -1;
            hori_direct = left;
        }
        if (x_diff==0 && y_diff==0){
            target.remove_surbrillance();
        }
        else {
            queue.addFirst(this);
        }

        for (int i = 0; i < x_diff; i++) {
            queue.addFirst(new Movement(this, hori_direct));
        }
        for (int j = 0; j < y_diff; j++) {
            queue.addFirst(new Movement(this, vert_direct));
        }
        queue.executeNext();
    }

    @Override
    public void end() {
        target.remove_surbrillance();
    }

    public ActionQueue getQueue() {
        return queue;
    }

    public Case getTarget() {
        return target;
    }
}

