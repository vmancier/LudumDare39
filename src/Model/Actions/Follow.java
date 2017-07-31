package Model.Actions;

import Model.ActionQueue;
import Model.Body;

public class Follow implements Action {
    private int range;
    private Action action;
    private Body subject;
    private Body target;
    private ActionQueue queue;
    private int attemps = 0;


    public Follow(int range, Action action, Body subject, Body target) {
        this.range = range;
        this.action = action;
        this.subject = subject;
        this.target = target;
        this.queue = subject.getActionQueue();
    }

    public Follow(int range, Action action, Body subject, Body target, int attemps) {
        this.range = range;
        this.action = action;
        this.subject = subject;
        this.target = target;
        this.queue = subject.getActionQueue();
        this.attemps = attemps;
    }

    @Override
    public void execute() {
        int distance = Math.abs(target.getPos_x() - subject.getPos_x())
                + Math.abs(target.getPos_y() - subject.getPos_y());
        if (distance <= range) {
            attemps -= 1;
            if (attemps != 0) {
                queue.addFirst(this);
            }
            queue.addFirst(action);
        } else {
            queue.addFirst(this);
            queue.addFirst(new MoveTo(queue, target.getCase(), range, 1));
        }
        queue.executeNext();

    }

    @Override
    public void end() {

    }
}
