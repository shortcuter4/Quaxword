public class Main {
    public static void main(String[] args) {
        CurrentBoard blank = new CurrentBoard();

        System.out.println("Click on GUI to continue");
        GameGUI mainWindow = new GameGUI();
        mainWindow.startGame();
    }
}
