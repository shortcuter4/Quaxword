import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {1,2,3,0,4,6,7,5,8};
        Functions f = new Functions();
        System.out.println("INITIAL STATE");
        f.print(numbers);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(numbers);
        int state = 1;
        while (true) {
            int count[] = {0,0,0,0};
            int [] firstPath = queue.remove();
            int [] dummy = new int[9];
            int [][] possibilities = new int [4][9];
            if(f.moveRight(dummy)) {
                count[0] = AStar.aStar(dummy,state);
                f.equalizer(dummy, possibilities[0]);
                f.equalizer(firstPath, dummy);
            }

            if(f.moveLeft(dummy)) {
                count[1] = AStar.aStar(dummy,state);
                f.equalizer(dummy, possibilities[1]);
                f.equalizer(firstPath, dummy);
            }

            if(f.moveDown(dummy)) {
                count[2] = AStar.aStar(dummy,state);
                f.equalizer(dummy, possibilities[2]);
                f.equalizer(firstPath, dummy);
            }

            if(f.moveUp(dummy)) {
                count[3] = AStar.aStar(dummy,state);
                f.equalizer(dummy, possibilities[3]);
                f.equalizer(firstPath, dummy);
            }
            int position = f.findMinimum(count);
            System.out.println(f.direction(position));
            f.print(possibilities[position]);

            
            if(f.checkGoal(possibilities[position])) {
                System.out.println("\n\nYOU SON OF A BITCH YOU MADE IT!!");
                break;
            } else {
                state++;
                queue.add(possibilities[position]);
            }
        }
    }

}
