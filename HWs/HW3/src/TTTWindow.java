import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
/**
 * @author Ege Aydin
 * @author Onur Kirmizi
 * @author Denizhan Soydas
 * @author Ali Ozer
 * @author Sina Sahan
 *
 * In this class, we open a JPanel to paint the grids of Tic Tac Toe on the GUI.
 *
 */
public class TTTWindow {
	private JFrame frame;
	private JPanel main_panel;
	private ImagePanel panels[];
	private JPanel text_panel;
	private JTextArea text_area;
	private TTTBoardState game;
	private GameBot bot;
	private boolean stopGame;
	private boolean turn;
	private int lastMove;
	private BorderLayout borderLayout;

	private TTTWindow newGameWindow;
	int p1score, p2score;

	public TTTWindow(int p1newscore, int p2newscore) {
		frame = new JFrame();
		borderLayout = new BorderLayout();
		frame.setLayout(borderLayout);
		frame.setTitle("Click on table to continue.");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(-150 + dim.width/2-frame.getSize().width/2, dim.height/3-frame.getSize().height/2);

		turn = false;
		main_panel = new JPanel();
		main_panel.setLayout(new GridLayout(4, 4));
		main_panel.setVisible(true);
		//BorderFactory.createLineBorder(Color.BLACK, 1);
		main_panel.setBackground(Color.WHITE);
		panels = new ImagePanel[16];

		for (int i = 0; i < 16; i++) {
			panels[i] = new ImagePanel(i);
			main_panel.add(panels[i]);
			panels[i].addMouseListener(new MyMouseListener(i));
		}

		game = new TTTBoardState();
		bot = new GameBot(game);
		game.getChildEval();	//CHANGED

		main_panel.setSize(450, 450);
		text_panel = new JPanel();
		text_area = new JTextArea();
		text_area.setText(game.getChildEvalAsSquare());
		text_area.setEditable(false);
		text_panel.add(text_area);
		text_area.setSize(450,100);
		text_area.setBackground(frame.getBackground());
		text_panel.setSize(450,100);
		frame.add(main_panel, BorderLayout.CENTER);
		frame.add(text_panel,BorderLayout.SOUTH);
		frame.setSize(450,550);

		p1score = p1newscore;
		p2score = p2newscore;


	}

	public void startGame(TTTWindow newTTwindow) {
		stopGame = false;
		newGameWindow = newTTwindow;
	}

	public class MyMouseListener implements MouseListener {

		public int place;
		public MyMouseListener(int i)
		{
			place = i;
		}

		public void mouseClicked(MouseEvent e) {
			if (stopGame == false) {
				panels[lastMove].reSetImage();

				lastMove = bot.playerMakeNextMove(Player.X, place);

				System.out.println("Player X moved to " + lastMove);

				panels[lastMove].setXImage();
				SwingUtilities.updateComponentTreeUI(frame);
				turn = true;
				frame.setTitle("O is playing...");
				if (game.checkGameOver() == GameState.X_WON) { // we check whether X wins
					frame.setTitle("X won");
					stopGame = true;

					//update score for player 1, delay 2 seconds for player read score title, remove current frame and open new windows for new game, transfer scores
					//game finishes if any player has 3 scores
					p1score++;

					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}

					if(!(p1score==3 || p2score==3)) {
						frame.setVisible(false);
						newGameWindow = new TTTWindow(p1score, p2score);
					}
					else{
						frame.setTitle("p1: " + p1score + " - " + p2score + " :p2");
					}
					System.out.println("p1: " + p1score + " - " + p2score + " :p2");

				}
				game.getChildEval();  //CHANGED
				text_area.setText(game.getChildEvalAsSquare());
				text_area.setEditable(false);
				text_area.setBackground(frame.getBackground());
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				if (stopGame == false)
				{
					panels[lastMove].reSetImage();
					lastMove = bot.makeNextMove(Player.O);
					System.out.println("Player O make move to : " + lastMove);
					panels[lastMove].setOImage();
					SwingUtilities.updateComponentTreeUI(frame);
					turn = false;
					frame.setTitle("X is playing...");
					if (game.checkGameOver() == GameState.O_WON) { // we check whether X wins
						frame.setTitle("O won");
						stopGame = true;

						//update score for player 2, delay 2 seconds for player read score title, remove current frame and open new windows for new game, transfer scores
						//game finishes if any player has 3 scores
						p2score++;

						try {
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}

						if(!(p1score==3 || p2score==3)) {
							frame.setVisible(false);
							newGameWindow = new TTTWindow(p1score, p2score);
						}
						else{
							frame.setTitle("p1: " + p1score + " - " + p2score + " :p2");
						}
						System.out.println("p1: " + p1score + " - " + p2score + " :p2");


					}
					if (game.checkGameOver() == GameState.TIE) {
						frame.setTitle("The game is a tie");
						stopGame = true;

						//dont change scores, delay 2 seconds for player read score title, remove current frame and open new windows for new game, transfer scores
						//game finishes if any player has 3 scores
						try {
							TimeUnit.SECONDS.sleep(2);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}

						frame.setVisible(false);
						newGameWindow = new TTTWindow(p1score, p2score);
					}
					game.getChildEval();  //CHANGED
					text_area.setText(game.getChildEvalAsSquare());
					text_area.setEditable(false);
					text_area.setBackground(frame.getBackground());
				}
			}
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}

	}

}