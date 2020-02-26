import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Search {
    public boolean search(Queue queue){
        if(queue.size() == 0){
            return false;
        } else {
            Node currentNode = (Node)queue.remove();

            List<Node> children;
            List<Node> paths = new ArrayList<>();
            List<int[]> previousStates;

            int sign = -1;

            int [][] cases = {{2,0},{0,2},{1,1},{1,0},{0,1}};
            List<int[]> possibilities = new ArrayList<>();


            if(currentNode.isWest()){
                previousStates = currentNode.getPreviousStatesEast();
                for(int index = 0; index < cases.length; index++){
                    if(currentNode.getCannibalCount() >= cases[index][0] && currentNode.getMissionaryCount() >= cases[index][1]){
                        possibilities.add(cases[index]);
                    }
                }
            } else {
                previousStates = currentNode.getPreviousStatesWest();
                sign = 1;
                for(int index = 0; index < cases.length; index++){
                    if(4-currentNode.getCannibalCount() >= cases[index][0] && 4-currentNode.getMissionaryCount() >= cases[index][1]){
                        possibilities.add(cases[index]);
                    }
                }
            }
            for(int index = 0; index < possibilities.size(); index++) {
                boolean loop = false;

                int newCannibalCount = currentNode.getCannibalCount() + sign * (possibilities.get(index)[0]);
                int newMissionaryCount = currentNode.getMissionaryCount() + sign * (possibilities.get(index)[1]);

                if(newCannibalCount == 0 && newMissionaryCount == 0){
                    return true;
                }

                for (int j = 0; j < previousStates.size(); j++) {
                    if ((newCannibalCount == previousStates.get(j)[0]) && (newMissionaryCount == previousStates.get(j)[1])) {
                        loop = true;
                    }
                }
                if(!loop && ((newCannibalCount <= newMissionaryCount) || newMissionaryCount == 0) && (newMissionaryCount == 4 || (4-newCannibalCount <= 4-newMissionaryCount))){
                    Node child = new Node(newCannibalCount , newMissionaryCount,!currentNode.isWest());
                    currentNode.addChild(child);
                }
            }

            int size = queue.size();
            for(int index = 0; index < size; index++) {
                paths.add((Node)queue.remove());
            }
            children = currentNode.getChildren();
            paths.addAll(children);
            Collections.shuffle(paths);
            System.out.println("\nCURRENT QUEUE POSITIONS:");
            for(int index = 0; index < paths.size(); index++) {
                paths.get(index).printPath(paths.get(index));
                System.out.println();
                queue.add(paths.get(index));
            }
            search(queue);
        }

        return true;
    }
}
