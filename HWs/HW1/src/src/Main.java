import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Node root = new Node(4,4);
        Tree tree = new Tree();

        boolean west = true;

        List<int[]> previousStatesWest = new ArrayList<>();
        List<int[]> previousStatesEast = new ArrayList<>();

        int stateWest[] = {4,4};
        int stateEast[] = {0,0};

        previousStatesWest.add(stateWest);
        previousStatesEast.add(stateEast);

        root = tree.createTree(root, west, previousStatesWest, previousStatesEast);

        tree.printTree(root);
    }
}
