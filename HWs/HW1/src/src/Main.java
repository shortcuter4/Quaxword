import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args){
        final int CANNIBALS_PART_A = 4; //PART-A = 4 , PART-B = 2
        final int CANNIBALS_PART_B = 2;
        System.out.println("!!GUIDELINE OF THE OUTPUT!!");
        System.out.println("EACH STATE SHOWS THE NUMBER OF CANNIBALS AND MISSIONARIES AFTER EACH MOVEMENT OF BOAT");
        System.out.println("\">>\" INDICATES TO BOAT MOVEMENT. WHETHER FROM WEST TO EAST OR EAST TO WEST. INITIALLY BOAT IS AT WEST");
        System.out.println("Ex: \"W:3,4 E:1,0\" MEANS THERE ARE 3 CANNIBALS AND 4 MISSIONARIES IN WEST SIDE (OTHER WORDS: 1 CANNIBALS 0 MISSIONARIES IN EAST SIDE)");
        System.out.println("WE WANT TO GET \"W:0,0 E:4,4\" AS A GOAL STATE (THERE ARE NOBODY IS AT WEST SIDE)\n");

        System.out.println("********** PART A *********");
        //BOAT IS AT WEST INITIALLY
        boolean west = true;
        boolean partA = true;

        //CREATE THE ROOT NODE AND ADD IT INTO QUEUE
        Node root = new Node(CANNIBALS_PART_A,4, west);
        root.setInitialState(root);

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Search nonDeterministic = new Search();

        if(!nonDeterministic.search(queue, partA)) {
            System.out.println("\n!!QUEUE HAS BEEN EMPTIED WITHOUT REACHING THE GOAL STATE!!");
        } else {
            System.out.println("!!SUCCESS!!");
        }

        System.out.println("\n\n********** PART B *********");
        Node rootB = new Node(CANNIBALS_PART_B,4, west);
        rootB.setInitialState(rootB);
        Queue<Node> queueB = new LinkedList<>();
        queueB.add(rootB);
        if(!nonDeterministic.search(queueB, !partA)) {
            System.out.println("\n!!QUEUE HAS BEEN EMPTIED WITHOUT REACHING THE GOAL STATE!!");
        } else {
            System.out.println("!!SUCCESS!!");
        }

    }
}
