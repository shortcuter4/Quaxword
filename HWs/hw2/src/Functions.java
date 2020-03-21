public class Functions {
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

    public static int findMinimum(int [] count) {
        int x = 1000000;
        int position = 0;
        for(int i = 0 ; i< 4; i++) {
            if(count[i] != 0 && x > count[i]) {
                x = count[i];
                position = i;
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

}
