package Model.Actions;

import Application.Main;

import java.util.*;

import static Application.Entities.WINDOW_HEIGHT;
import static Application.Entities.WINDOW_WIDTH;

public class AStarNode implements Comparable<AStarNode> {
    private Integer infinity = WINDOW_WIDTH * WINDOW_HEIGHT;
    private int x;
    private int y;
    private AStarNode cameFrom;
    private int gScore;
    private int fScore;

    public AStarNode(int x, int y) {
        this.x = x;
        this.y = y;
        this.cameFrom = null;
        this.gScore = infinity;
        this.fScore = infinity;
    }

    public AStarNode(Coordinate coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
        this.cameFrom = null;
        this.gScore = infinity;
        this.fScore = infinity;
    }

    public static ArrayList<AStarNode> getShortestPath(AStarNode start, AStarNode goal) {
        HashSet<AStarNode> closedSet = new HashSet<>();
        PriorityQueue<AStarNode> openSet = new PriorityQueue<>();
        HashMap<Coordinate, AStarNode> openSet2 = new HashMap<>();
        openSet.add(start);
        openSet2.put(start.getCoordinate(), start);
        start.setgScore(0);
        start.setfScore(start.heuristicDistanceTo(goal));

        while (!openSet.isEmpty()) {
            //On récupere la node prioritaire (avec f le plus faible)
//            System.out.println(openSet.toString());
            AStarNode current = openSet.poll();
//            System.out.println("Node examinée : "+current.toString());
//            System.out.println("FScore  : "+current.getfScore());
//            System.out.println("Node examinée :"+current.toString());
            //a priori pas besoin de l'enelver de openSet2
            if (current.equals(goal)) {
//                System.out.println("L'arrivé a été atteinte");
                return AStarNode.reconstructPath(current);
            }
            closedSet.add(current);
            LinkedList<Integer[]> neighbours_pos = Main.get_model().neighbours(current.getX(), current.getY());
            for (Integer[] coordinate_array : neighbours_pos) {
                Coordinate coordinate = new Coordinate(coordinate_array);
                AStarNode neighbour = new AStarNode(coordinate);
//                System.out.println("Node voisine examinée :"+neighbour.toString());
                if (!(closedSet.contains(neighbour)
                        || !Main.get_model().isFree(coordinate_array[0], coordinate_array[1]))) {
//                    System.out.println("Le voisin n'est pas closed, la case est disponible");
                    if (!openSet.contains(neighbour)) {
                        openSet2.put(coordinate, neighbour);
//                          System.out.println("Node inconnue, ajout");
                    } else {
                        neighbour = openSet2.get(coordinate);
                        openSet.remove(neighbour);
//                        System.out.println("Node connue.");
                    }

                    int tentative_gScore = current.getgScore() + current.heuristicDistanceTo(neighbour);
//                    System.out.println("Score tenté : " + tentative_gScore);
//                    System.out.println("Score ancien : " + neighbour.getgScore());
                    if (tentative_gScore < neighbour.getgScore()) {
//                        System.out.println("La tentative vaut le cout ! On actualise");
                        neighbour.setCameFrom(current);
                        neighbour.setgScore(tentative_gScore);
                        neighbour.setfScore(tentative_gScore + neighbour.heuristicDistanceTo(goal));
                    }
                    openSet.add(neighbour);
                }
            }

        }

//        System.out.println("No path found");
        return null;
    }

    private static ArrayList<AStarNode> reconstructPath(AStarNode current) {
        ArrayList<AStarNode> way = new ArrayList();
        way.add(current);
        AStarNode node = current.getCameFrom();
        while (node != null) {
            way.add(node);
            node = node.getCameFrom();
        }
        return way;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(x, y);
    }

    public AStarNode getCameFrom() {
        return cameFrom;
    }

    public void setCameFrom(AStarNode cameFrom) {
        this.cameFrom = cameFrom;
    }

    public int getgScore() {
        return gScore;
    }

    public void setgScore(int gScore) {
        this.gScore = gScore;
    }

    public int getfScore() {
        return fScore;
    }

    public void setfScore(int fScore) {
        this.fScore = fScore;
    }

    public int heuristicDistanceTo(AStarNode o) {
        return Math.abs(o.getX() - x) + Math.abs(o.getY() - y);
    }

    @Override
    public int compareTo(AStarNode o) {
        //Si le classement est dans le mauvais sens, c'est parcequ'il faut enlever le -
        return this.fScore - o.getfScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AStarNode aStarNode = (AStarNode) o;

        if (x != aStarNode.x) return false;
        return y == aStarNode.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public Directions goTo(AStarNode lastOne) {
        if (y < lastOne.getY()) {
//            System.out.println("down");
            return Directions.down;
        }
        if (y > lastOne.getY()) {
//            System.out.println("up");
            return Directions.up;
        }
        if (x < lastOne.getX()) {
//            System.out.println("right");
            return Directions.right;
        }
        if (x > lastOne.getX()) {
//            System.out.println("left");
            return Directions.left;
        }
        System.out.println("Meme case...");
        return null;
    }

    @Override
    public String toString() {
        return "AStarNode{" +
                "x=" + x +
                ", y=" + y +
                "}\n";
    }
}
