import java.util.ArrayList;
/**
 * @author Ege Aydin
 * @author Onur Kirmizi
 * @author Denizhan Soydas
 * @author Ali Ozer
 * @author Sina Sahan
 * @version 1.3
 * In this class, we launch the program.
 *
 */
public class Main {
    public static void main(String[] args) {
        TTTBoardState blank = new TTTBoardState();

        System.out.println("Click on GUI to continue");
        TTTWindow mainWindow = new TTTWindow();
        mainWindow.startGame();
    }
}
