import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.util.concurrent.TimeUnit;

public class GameGUI {
	private JFrame frame;
	private JPanel main_panel;
	private ImagePanel panels[];
	private JPanel text_panel;
	private JTextArea text_area;
	private CurrentBoard game;
	private GameBot bot;
	private boolean stopGame;
	private boolean turn;
	private int lastMove;
	private BorderLayout borderLayout;

	public GameGUI() {
		frame = new JFrame();
		borderLayout = new BorderLayout();
		frame.setLayout(borderLayout);
		frame.setTitle("Click on table to continue.");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		game = new CurrentBoard();
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

		
	}
	
	public void startGame() {
		stopGame = false;
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
					}
					if (game.checkGameOver() == GameState.TIE) {
						frame.setTitle("The game is a tie");
						stopGame = true;
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
