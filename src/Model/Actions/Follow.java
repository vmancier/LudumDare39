package Model.Actions;

import Model.ActionQueue;
import Model.Body;

;

public class Follow implements Action {
    private int range;
    private Attacks attack;
    private Body subject;
    private Body target;
    private ActionQueue queue;
    private int attemps = 0;


    public Follow(int range, Attacks attack, Body subject, Body target) {
        this.range = range;
        this.attack = attack;
        this.subject = subject;
        this.target = target;
        this.queue = subject.getActionQueue();
    }

    public Follow(int range, Attacks attack, Body subject, Body target, int attemps) {
        this.range = range;
        this.attack = attack;
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
            switch (attack){
                case Lazer:{
                    queue.addFirst(new Lazer(subject,target));
                    break;
                }
//                @TODO implementer les autres attaques
            }
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
