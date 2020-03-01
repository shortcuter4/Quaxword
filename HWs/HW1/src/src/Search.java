import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Search {
    public boolean search(Queue queue, boolean part){
        //IF THE QUEUE IS EMPTY TERMINATE THE ALGORITHM
        if(queue.size() == 0){
            return false;
        } else {
            Node currentNode = (Node)queue.remove(); //POP THE FIRST ELEMENT AT THE QUEUE

            List<Node> children;
            List<Node> paths = new ArrayList<>();
            List<int[]> previousStates;

            //IF BOAT IS AT WEST SIGN IS 1 MEANING DECREASE THE NUMBER OF PEOPLE AT WEST AFTER THE MOVEMENT OF BOAT
            int sign = -1;

            //ALL POSSIBLE CASES
            int [][] cases = {{2,0},{0,2},{1,1},{1,0},{0,1}};
            List<int[]> possibilities = new ArrayList<>();


            //ADD THE CASES INTO POSSIBILITIES BY CHECKING THE LOCATION OF THE BOAT AND NUMBER OF CANNIBALS AND MISSIONARIES
            if(currentNode.isWest()){
                previousStates = currentNode.getPreviousStatesEast();
                for(int index = 0; index < cases.length; index++){
                    if(currentNode.getCannibalCount() >= cases[index][0]
                            && currentNode.getMissionaryCount() >= cases[index][1]){
                        possibilities.add(cases[index]);
                    }
                }
            } else {
                previousStates = currentNode.getPreviousStatesWest();
                sign = 1; //MAKE SIGN +1 IF BOAT IS AT WEST(INCREASE THE NUMBER OF PEOPLE AT WEST)
                for(int index = 0; index < cases.length; index++){
                    if(currentNode.getInitialState()-currentNode.getCannibalCount() >= cases[index][0]
                            && 4-currentNode.getMissionaryCount() >= cases[index][1]){
                        possibilities.add(cases[index]);
                    }
                }
            }

            //FOR ALL POSSIBILITIES CREATE THE NEWMISSIONARY AND NEWCANNIBAL COUNTS AND CHECK WHETHER THERE IS A LOOP OR NOT
            for(int index = 0; index < possibilities.size(); index++) {
                boolean loop = false;
                //SIGN IS -1 IF BOAT AT WEST, +1 IF BOAT AT EAST
                int newCannibalCount = currentNode.getCannibalCount() + sign * (possibilities.get(index)[0]);
                int newMissionaryCount = currentNode.getMissionaryCount() + sign * (possibilities.get(index)[1]);

                //GOAL STATE
                if(newCannibalCount == 0 && newMissionaryCount == 0){
                    return true;
                }

                //CHECK THE LOOP BY ITERATING THROUGH PREVIOUS STATES
                for (int j = 0; j < previousStates.size(); j++) {
                    if ((newCannibalCount == previousStates.get(j)[0])
                            && (newMissionaryCount == previousStates.get(j)[1])) {
                        loop = true;
                    }
                }
                //IF THERE IS NO LOOP AND STATE IS SAFE, ADD THE NODE AS A CHILD
                //IF PART A THERE IS EQUALITY AS A SAFE STATE
                if(part) {
                    if(!loop && ((newCannibalCount <= newMissionaryCount) || newMissionaryCount == 0)
                            && (newMissionaryCount == 4 || (currentNode.getInitialState()-newCannibalCount <= 4-newMissionaryCount))){
                        Node child = new Node(newCannibalCount , newMissionaryCount,!currentNode.isWest());
                        currentNode.addChild(child);
                    }
                } else { //IF PART A THERE IS NO EQUALITY AS A SAFE STATE
                    if(!loop && ((newCannibalCount < newMissionaryCount) || newMissionaryCount == 0)
                            && (newMissionaryCount == 4 || (currentNode.getInitialState()-newCannibalCount < 4-newMissionaryCount))){
                        Node child = new Node(newCannibalCount , newMissionaryCount,!currentNode.isWest());
                        currentNode.addChild(child);
                    }
                }

            }

            //POP EVERY ELEMENT AT THE QUEUE FOR CREATING THE RANDOMNESS
            int size = queue.size();
            for(int index = 0; index < size; index++) {
                paths.add((Node)queue.remove());
            }

            //ADD THE NEW PATHS INTO EXISTED PATHS AND SHUFFLE THEM RANDOMLY
            children = currentNode.getChildren();
            paths.addAll(children);
            Collections.shuffle(paths);

            //PUT THEM BACK INTO QUEUE AND PRINT THE CURRENT POSITIONS
            System.out.println("\nCURRENT QUEUE POSITIONS:");
            for(int index = 0; index < paths.size(); index++) {
                System.out.print("PATH " + (index+1) + " = ");
                paths.get(index).printPath(paths.get(index));
                System.out.println();
                queue.add(paths.get(index));
            }
            //RECURSIVE CALL
            return search(queue, part);
        }
    }
}
