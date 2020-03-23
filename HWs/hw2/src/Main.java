import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[][] randoms = new int[30][9];
        Functions f = new Functions();
        f.randomGenerator(randoms);
        DrawGraph.createAndShowGui(randoms);
        int position = 4;
        for (int x = 0; x < 2; x++) {
            ArrayList<int []> set = new ArrayList<>();
            int[] numbers = randoms[x];
            set.add(numbers);
            System.out.println("INITIAL STATE ");
            f.print(numbers);
            Queue<int[]> queue = new LinkedList<>();
            queue.add(numbers);
            int state = 1;
            boolean check = false;
            while (!check) {
                int count[] = {0, 0, 0, 0};
                int[] firstPath = queue.remove();
                int[] dummy = new int[9];
                f.equalizer(firstPath, dummy);
                int[][] possibilities = new int[4][9];
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
                position = f.findMinimum(count, position);

                while(!f.avoidLoop(set,possibilities[position])) {
                    count[position] = 1000000;
                    position = f.findMinimum(count, position);
                }

                set.add(possibilities[position]);
                System.out.println(f.direction(position));
                f.print(possibilities[position]);


                if (f.checkGoal(possibilities[position])) {
                    System.out.println("\nYOU MADE IT!! STATES COUNT: " + state + "\n" );
                    check = true;
                } else {
                    state++;
                    queue.add(possibilities[position]);
                }
            }
        }
    }
}
