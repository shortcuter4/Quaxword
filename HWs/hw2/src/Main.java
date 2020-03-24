import java.util.*;

public class Main {
    public static void main(String[] args) {
/*
        OUR PROGRAM WORKING BASICALLY LIKE THIS:
        FIRST GENERATE 30 RANDOM ARRAY
        THEN SHOW THE GRAPH OF THEIR HEURISTIC COUNT(WE HAVE USED H1)
        THEN SHOW THE TRACE OF FIRST TWO PUZZLE #1 AND #2
        FUNCTION CLASS HAVE ALL THE FUNCTIONS
        ASTAR CLASS HEURISTIC CALCULATION FUNCTION
        DRAW GRAPH CLASS IS FOR DRAWING THE GRAPH(KINDA BOOTSTRAP CLASS)
*/
        int[][] randoms = new int[30][9]; //30 RANDOM ARRAY
        Functions f = new Functions();
        f.randomGenerator(randoms);
        DrawGraph.createAndShowGui(randoms); //SHOW THE GRAPH
        int position = 4; //POSITION MEANS: 0 = RIGHT, 1 = LEFT, 2 = DOWN, 3 = UP, 4 = NULL
        for (int x = 0; x < 2; x++) {
            ArrayList<int []> set = new ArrayList<>(); // SET FOR CHECKING LOOPS
            int[] numbers = randoms[x]; //GET THE Xth INDEX OF RANDOM GENERATED ARRAY
            set.add(numbers); //ADD THIS TO SET FIR INTIALIZE IT
            System.out.println("INITIAL STATE ");
            f.print(numbers);
            Queue<int[]> queue = new LinkedList<>(); //QUEUE FOR ASTAR
            queue.add(numbers);
            int state = 1;
            boolean check = false;
            while (!check) { // THIS LOOP WILL OPERATE UNTIL IT REACHES THE GOAL
                int count[] = {0, 0, 0, 0};
                int[] firstPath = queue.remove();
                int[] dummy = new int[9];
                f.equalizer(firstPath, dummy); //EQUALIZER FUNCTION MAKES TWO DIFFERENT ARRAY'S ELEMENTS EXACTLY SAME
                int[][] possibilities = new int[4][9]; //POSSIBILITES ARRAY INCLUDES EVERY ARRAY AFTER THE SINGLE MOVEMENT

                //THESE 4 IFs CHECK IF THE CURRENT ARRAY CAN MOVE ANY DIRECTION
                //AND IF SO IT ADDS TO POSSBILITIES
                if (f.moveRight(dummy)) {
                    count[0] = AStar.aStar(dummy, state);
                    f.equalizer(dummy, possibilities[0]);
                    f.equalizer(firstPath, dummy);
                }

                if (f.moveLeft(dummy)) {
                    count[1] = AStar.aStar(dummy, state);
                    f.equalizer(dummy, possibilities[1]);
                    f.equalizer(firstPath, dummy);
                }

                if (f.moveDown(dummy)) {
                    count[2] = AStar.aStar(dummy, state);
                    f.equalizer(dummy, possibilities[2]);
                    f.equalizer(firstPath, dummy);
                }

                if (f.moveUp(dummy)) {
                    count[3] = AStar.aStar(dummy, state);
                    f.equalizer(dummy, possibilities[3]);
                    f.equalizer(firstPath, dummy);
                }
                //FIND MINIMUM FUNCTION CHECKS THE MINIMUM F = G + H
                position = f.findMinimum(count, position);

                // THIS WHILE OPERATES UNTIL THERE IS NO LOOP
                while(!f.avoidLoop(set,possibilities[position])) {
                    count[position] = 1000000;
                    position = f.findMinimum(count, position);
                }

                //ADD THE CURRENT POSITION TO SETT
                set.add(possibilities[position]);
                System.out.println(f.direction(position));
                f.print(possibilities[position]);


                //CHECK WHETHER THE DESIRED GOAL IS FOUND OR NOT
                if (f.checkGoal(possibilities[position])) {
                    System.out.println("\nYOU MADE IT!! STATES COUNT: " + state + "\n" );
                    check = true;
                } else {
                    //INCREASE THE STATE ADD THE MINIMUM VALUE TO QUEUE
                    state++;
                    queue.add(possibilities[position]);
                }
            }
        }
    }
}
