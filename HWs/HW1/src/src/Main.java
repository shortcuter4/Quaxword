import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args){
        System.out.println("!!GUIDELINE OF THE OUTPUT!!");
        System.out.println("EACH STATE SHOWS THE NUMBER OF CANNIBALS AND MISSIONARIES AFTER EACH MOVEMENT OF BOAT");
        System.out.println("Ex: \"W:3,4 E:1,0\" MEANS THERE ARE 3 CANNIBALS AND 4 MISSIONARIES IN WEST SIDE (OTHER WORDS: 1 CANNIBALS 0 MISSIONARIES IN EAST SIDE)");
        System.out.println("WE WANT TO GET \"W:0,0 E:4,4\" AS A GOAL STATE (THERE ARE NOBODY IS AT WEST SIDE)");

        boolean west = true;
        final int CANNIBALS = 4;
        Node root = new Node(CANNIBALS,4,west);
        root.setInitialState(root);

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Search nonDeterministic = new Search();
        if(!nonDeterministic.search(queue)) {
            System.out.println("\n\n!!QUEUE HAS BEEN EMPTIED WITHOUT REACHING THE GOAL STATE!!");
        } else {
            System.out.println("!!SUCCESS!!");
        }

    }
}
