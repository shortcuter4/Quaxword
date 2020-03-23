import java.util.ArrayList;
import java.util.Set;

public class Functions {
    public static void randomGenerator(int [][]randoms) {
        for (int i = 0; i< 30; i++) {
            int[] numbers = {1,2,3,4,5,6,7,8,0};
            for(int j = 0; j< 10; j++) {
                int x = ((int)(Math.random()*10)) % 4;
                if(x == 0) {
                    moveRight(numbers);
                }
                if(x == 1) {
                    moveLeft(numbers);
                }
                if(x == 2) {
                    moveDown(numbers);
                }
                if(x == 3) {
                    moveUp(numbers);
                }
            }
            randoms[i] = numbers;
        }
    }
    public static boolean moveDown(int[] numbers){
        for(int i = 0; i< numbers.length; i++) {
            if(i+3 < numbers.length && numbers[i+3] == 0) {
                int dummy = numbers[i];
                numbers[i] = numbers[i+3];
                numbers[i+3] = dummy;
                return true;
            }
        }
        return false;
    }

    public static boolean moveUp(int[] numbers){
        for(int i = 0; i< numbers.length; i++) {
            if(i-3 >=0 && numbers[i-3] == 0) {
                int dummy = numbers[i];
                numbers[i] = numbers[i-3];
                numbers[i-3] = dummy;
                return true;
            }
        }
        return false;
    }

    public static boolean moveRight(int[] numbers){
        for(int i = 0; i< numbers.length; i++) {
            if(i%3 != 2 && numbers[i+1] == 0) {
                int dummy = numbers[i];
                numbers[i] = numbers[i+1];
                numbers[i+1] = dummy;
                return true;
            }
        }
        return false;
    }

    public static boolean moveLeft(int[] numbers){
        for(int i = 0; i< numbers.length; i++) {
            if(i%3 != 0 && numbers[i-1] == 0) {
                int dummy = numbers[i];
                numbers[i] = numbers[i-1];
                numbers[i-1] = dummy;
                return true;
            }
        }
        return false;
    }

    public static boolean checkGoal(int [] numbers) {
        int count = 0;
        for(int i = 0; i< numbers.length; i++) {
            if(numbers[i] != 0 && numbers[i] == i+1) {
                count++;
            }
        }
        if(count == 8) {
            return true;
        }
        return false;
    }

    public static void print(int [] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            if (i != 0 && i % 3 == 0) {
                System.out.println();
            }
            if (numbers[i] != 0) {
                System.out.print(numbers[i] + " ");
            } else {
                System.out.print("  ");
            }
        }
        System.out.println();
    }

    public static void equalizer(int [] first, int[] second) {
        for (int i = 0; i < first.length; i++) {
            second[i] = first [i];
        }
    }

    public static int findMinimum(int [] count, int previous) {
        int x = 1000000;
        int position = 4;
        for(int i = 0 ; i< 4; i++) {
            if(count[i] != 0 && x > count[i]) {
                if((i == 0 && previous != 1) || (i == 1 && previous != 0) ||
                        (i == 2 && previous != 3) || (i == 3 && previous != 2) ) {
                    x = count[i];
                    position = i;
                }
            }
        }
        return position;
    }

    public static String direction (int direction) {
        if(direction == 0) {
            return "  v\n  v\nMOVE RIGHT\n  v\n  v";
        }
        if(direction == 1) {
            return "  v\n  v\nMOVE LEFT\n  v\n  v";
        }
        if(direction == 2) {
            return "  v\n  v\nMOVE DOWN\n  v\n  v";
        }
        if(direction == 3) {
            return "  v\n  v\nMOVE UP\n  v\n  v";
        }
        return "";
    }


    public static boolean avoidLoop(ArrayList<int []> set, int [] candidate) {
        for(int i = 0; i < set.size(); i++) {
            int count = 0;
            for(int j = 0; j< 9; j++) {
                if(candidate[j] == set.get(i)[j])
                    count++;
            }
            if(count == 9) {
                return false;
            }
        }

        return true;
    }

}
