import java.util.ArrayList;
import java.util.List;


public class Node {

    private int cannibalCount;
    private int missionaryCount;
    private int initialCannibals;
    private boolean west;

    private List<Node> children = new ArrayList<>();
    private List<int []> previousStatesWest = new ArrayList<>();
    private List<int []> previousStatesEast = new ArrayList<>();


    private Node parent = null;

    public Node(int cannibalCount, int missionaryCount, boolean west) {
        this.cannibalCount = cannibalCount;
        this.missionaryCount = missionaryCount;
        this.west = west;
    }

    public Node addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
        for(int index = 0; index < this.previousStatesWest.size(); index++){
            child.previousStatesWest.add(this.previousStatesWest.get(index));
        }
        for(int index = 0; index < this.previousStatesEast.size(); index++){
            child.previousStatesEast.add(this.previousStatesEast.get(index));
        }

        int []state = {child.getCannibalCount() , child.getMissionaryCount()};
        if (child.west){
            child.previousStatesWest.add(state);
        } else {
            child.previousStatesEast.add(state);
        }
        return child;
    }


    public List<Node> getChildren() {
        return children;
    }

    public int getCannibalCount() {
        return cannibalCount;
    }

    public int getMissionaryCount() {
        return missionaryCount;
    }

    public void setInitialState(Node root) {
        int []state = {root.getCannibalCount(), root.getMissionaryCount()};
        this.initialCannibals = root.getCannibalCount();
        root.previousStatesWest.add(state);
    }

    public int getInitialState() {
        return this.initialCannibals;
    }

    private void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() { return parent; }

    public List<int[]> getPreviousStatesWest() {
        return previousStatesWest;
    }

    public List<int[]> getPreviousStatesEast() {
        return previousStatesEast;
    }

    public boolean isWest() {
        return west;
    }

}