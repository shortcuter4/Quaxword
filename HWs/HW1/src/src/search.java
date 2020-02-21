import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class search {
    public static void main(String[] args){
        Node root = new Node(4,4);
        boolean west = true;

        List<int[]> previousStatesWest = new ArrayList<>();
        List<int[]> previousStatesEast = new ArrayList<>();

        int stateWest[] = {4,4};
        int stateEast[] = {0,0};

        previousStatesWest.add(stateWest);
        previousStatesEast.add(stateEast);

        root = createTree(root, west, previousStatesWest, previousStatesEast);

        printTree(root);
    }

    public static Node createTree(Node n, boolean west, List<int[]> previousStatesWest, List<int[]> previousStatesEast){
        if(n == null){
            return null;
        }

        List<int[]> previousStates = new ArrayList<>();
        previousStates = previousStatesEast;

        int sign = -1;

        List<int[]> possibilities = new ArrayList<>();
        int [][] cases = {{2,0},{0,2},{1,1},{1,0},{0,1}};

        if(west){
            for(int index = 0; index < cases.length; index++){
                if(n.getCannibalCount() >= cases[index][0] && n.getMissionaryCount() >= cases[index][1]){
                    possibilities.add(cases[index]);
                }
            }
        }

        if(!west){
            previousStates = previousStatesWest;
            sign = 1;
            for(int index = 0; index < cases.length; index++){
                if(4-n.getCannibalCount() >= cases[index][0] && 4-n.getMissionaryCount() >= cases[index][1]){
                    possibilities.add(cases[index]);
                }
            }
        }


        for(int index = 0; index < possibilities.size(); index++){
            boolean loop = false;

            int newCannibalCount = n.getCannibalCount() + sign*(possibilities.get(index)[0]);
            int newMissionaryCount = n.getMissionaryCount() + sign*(possibilities.get(index)[1]);

            for(int j = 0; j< previousStates.size(); j++){
                if((newCannibalCount == previousStates.get(j)[0]) && (newMissionaryCount == previousStates.get(j)[1])){
                    loop = true;
                }
            }
            if(!loop && ((newCannibalCount <= newMissionaryCount) || newMissionaryCount == 0) && (newMissionaryCount == 4 || (4-newCannibalCount <= 4-newMissionaryCount))){
                Node child = new Node(newCannibalCount , newMissionaryCount);
                int currentState[] = {newCannibalCount , newMissionaryCount};
                previousStates.add(currentState);
                n.addChild(child);
                if(!west){
                    previousStatesEast = previousStates;
                } else {
                    previousStatesEast = previousStates;
                }
                createTree(child,!west,previousStatesWest, previousStatesEast);
            }
        }



        return n;
    }

    public static void printTree(Node root){
        List<Node> list = root.getChildren();

        System.out.println(root.getCannibalCount() + " " + root.getMissionaryCount());

        for(int j = 0; j< list.size(); j++){
            printTree(list.get(j));
        }
    }
}
