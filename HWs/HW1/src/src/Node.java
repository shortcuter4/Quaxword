import java.util.ArrayList;
import java.util.List;


public class Node {

    private int cannibalCount;
    private int missionaryCount;

    private List<Node> children = new ArrayList<>();

    private Node parent = null;

    public Node(int cannibalCount, int missionaryCount) {
        this.cannibalCount = cannibalCount;
        this.missionaryCount = missionaryCount;
    }

    public Node addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
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

    private void setParent(Node parent) {
        this.parent = parent;
    }

}