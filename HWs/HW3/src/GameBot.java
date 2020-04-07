import java.util.ArrayList;

enum Player {
	X, O;
}
/**
 * @author Ege Aydin
 * @author Onur Kirmizi
 * @author Denizhan Soydas
 * @author Ali Ozer
 * @author Sina Sahan
 * In this class, we make the A.I. Part of the program.
 *
 */
public class GameBot {
	private TTTBoardState game;

	public GameBot(TTTBoardState game) {
		this.game = game;
	}

	public int makeNextMove(Player currentPlayer) {
		// we use alpha beta prunning to determine the best move possible.
		EvaluatedBoard bestMove = getBestMovePossible(game, currentPlayer == Player.X, Integer.MIN_VALUE,
				Integer.MAX_VALUE);
		return bestMove.makeMove(game);
	}
	//we use alpha beta prunning to determine the best move possible.
	public static EvaluatedBoard getBestMovePossible(TTTBoardState state, boolean maximizing, int alpha, int beta) {

		ArrayList<TTTBoardState> children = new ArrayList<TTTBoardState>();	//CHANGED
		state.getChildBoards(children);	//CHANGED
		if (children.size() == 0 || state.checkGameOver() != GameState.ONGOING)
		{
			//System.out.println("Test1");
			return new EvaluatedBoard(state, state.getBoardScore());
		}

		int bestStateVal;
		TTTBoardState bestNode = null;
		if (maximizing) {
			bestStateVal = Integer.MIN_VALUE;
			for (int i = 0; i < children.size(); i++) {
				int stateVal = getBestMovePossible(children.get(i), !maximizing, alpha, beta).getValue();
				if (stateVal > bestStateVal) {
					bestNode = children.get(i);
					bestStateVal = stateVal;
				}
				alpha = Math.max(bestStateVal, alpha);
				if (alpha >= beta)
					break;
			}
		} else {
			bestStateVal = Integer.MAX_VALUE;
			for (int i = 0; i < children.size(); i++) {
				int stateVal = getBestMovePossible(children.get(i), !maximizing, alpha, beta).getValue();
				if (stateVal < bestStateVal) {
					bestNode = children.get(i);
					bestStateVal = stateVal;
				}
				beta = Math.min(bestStateVal, beta);
				if (alpha >= beta)
					break;
			}
		}
		return new EvaluatedBoard(bestNode, bestStateVal);
	}
}
