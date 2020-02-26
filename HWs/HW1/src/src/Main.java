import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args){
        boolean west = true;
        Node root = new Node(4,4,west);
        root.setInitialState(root);

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Search nonDeterministic = new Search();
        nonDeterministic.search(queue);
        
    }
}
