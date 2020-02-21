import java.util.ArrayList;
import java.util.List;

public class Tree {
    public  Node createTree(Node n, boolean west, List<int[]> previousStatesWest, List<int[]> previousStatesEast){
        if(n == null){
            return null;
        }
        int sign = -1;

        int [][] cases = {{2,0},{0,2},{1,1},{1,0},{0,1}};

        List<int[]> previousStates = previousStatesEast;
        List<int[]> possibilities = new ArrayList<>();

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

    public void printTree(Node root){
        List<Node> list = root.getChildren();

        System.out.println(root.getCannibalCount() + " " + root.getMissionaryCount());

        for(int j = 0; j< list.size(); j++){
            printTree(list.get(j));
        }
    }
}
