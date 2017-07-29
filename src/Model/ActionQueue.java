package Model;

import java.util.ArrayList;

public class ActionQueue {
    private ArrayList<Action> Queue;

    public void addFirst(Action action){
        Queue.add(0,action);
    }
    public void addLast(Action action){
        Queue.add(action);
    }
    public void removeFirst(){
        Queue.remove(0);
    }
    public Action getFirst(){
        return Queue.get(0);
    }
    public void removeAllMovable(){
        for (Action a:Queue){
            if(a.isMovable()){
                Queue.remove(a);
            }
        }
    }
    public void clearQueue(){
        Queue.clear();
    }
}
