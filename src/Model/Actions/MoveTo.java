package Model.Actions;

import Model.ActionQueue;
import Model.Body;
import Model.Case;

import java.util.*;

import static Application.Entities.*;
import static Model.Actions.Directions.*;
import static Model.CharacterTypes.Player;

public class MoveTo implements Action {
    private ActionQueue queue;
    private Case target;
    private Body subject;

    public MoveTo(ActionQueue queue, Case target) {
        this.queue = queue;
        this.target = target;
        this.subject = queue.getBody();
    }

    //    @Override
    public void execute2() {
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
        if (x_diff == 0 && y_diff == 0) {
            if (subject.getCharacter() == Player) {
                target.remove_surbrillance();
            }
        } else {
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
    public void execute() {
        AStarNode start = new AStarNode(subject.getPos_x(), subject.getPos_y());
        AStarNode goal = new AStarNode(target.getPos_x(), target.getPos_y());
//        System.out.println("start"+start.toString());
//        System.out.println("goal"+goal.toString());
        if (start.equals(goal)) {
            if (subject.getCharacter() == Player) {
                target.remove_surbrillance();
            }
        } else {
            ArrayList<AStarNode> way = AStarNode.getShortestPath(start, goal);
            if (way != null && way.size() != 0) {
                queue.addFirst(this);
                AStarNode lastOne = way.get(0);
                AStarNode node;
//                System.out.println(way.toString());
                for (int i = 1; i < way.size(); i++) {
                    node = way.get(i);
                    //pour savoir si il faut aller en haut, en bas ...
                    Directions direction = node.goTo(lastOne);
//                    System.out.println("Direction : " +direction+"\n"+node.toString());
                    if (direction != null) {
                        queue.addFirst(new Movement(this, direction));
                    }
                    lastOne = node;
                }
            } else {
                target.remove_surbrillance();
                System.out.println("Not accessible");
            }
        }
//        System.out.println("Avant execute next");
        queue.executeNext();
//        System.out.println("premiere action effectuée");
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

