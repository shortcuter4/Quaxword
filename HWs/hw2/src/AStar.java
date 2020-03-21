public class AStar {
    public static  int aStar(int [] numbers, int state) {
        return state+findH1(numbers);
    }

    public static int findH1(int [] numbers) {
        int count = 0;
        for(int i = 0 ; i< numbers.length; i++) {
            if(numbers[i] != i+1) {
                count++;
            }
        }

        return count;
    }
}
