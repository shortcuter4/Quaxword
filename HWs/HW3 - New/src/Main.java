public class Main {
    public static void main(String[] args) {
        TTTBoardState blank = new TTTBoardState();

        System.out.println("Click on GUI to continue");
        TTTWindow mainWindow = new TTTWindow();
        mainWindow.startGame();
    }
}
