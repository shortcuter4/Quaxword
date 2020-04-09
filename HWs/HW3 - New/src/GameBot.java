import java.util.ArrayList;

enum Player {
	X, O;
}

public class GameBot {
	private TTTBoardState game;
	public static int turn = 1;
	public GameBot(TTTBoardState game) {
		this.game = game;
	}

	public int makeNextMove(Player currentPlayer) {
		// we use alpha beta prunning to determine the best move possible.
		EvaluatedBoard bestMove = getBestMovePossible(game, currentPlayer == Player.X, Integer.MIN_VALUE,
				Integer.MAX_VALUE,currentPlayer == Player.X);
		return bestMove.makeMove(game);
	}
	public int playerMakeNextMove(Player currentPlayer, int place) {
		// we use alpha beta prunning to determine the best move possible.

		ArrayList<TTTBoardState> children = new ArrayList<TTTBoardState>();	//CHANGED
		game.getChildBoardsFull(children);	//CHANGED

		TTTBoardState bestNode;

		bestNode = children.get(place);

		EvaluatedBoard bestMove =new EvaluatedBoard(bestNode, game.getBoardScore());

		return bestMove.playerMakeMove(game,place);
	}
	//we use alpha beta prunning to determine the best move possible.
	public static EvaluatedBoard getBestMovePossible(TTTBoardState state, boolean maximizing, int alpha, int beta, boolean isPlayer) {

		ArrayList<TTTBoardState> children = new ArrayList<TTTBoardState>();	//CHANGED
		state.getChildBoards(children);	//CHANGED

		int bestStateVal  = 0;
		TTTBoardState bestNode = null;

		//System.out.println(state.getTurn());

		if (children.size() == 0 || state.checkGameOver() != GameState.ONGOING)
		{
			return new EvaluatedBoard(state, state.getBoardScore());
		}

		if(turn <= 3 || isPlayer)
		{
			if (maximizing) {
				bestStateVal = Integer.MIN_VALUE;
				for (int i = 0; i < children.size(); i++) {
					int stateVal = (new EvaluatedBoard(state, state.getBoardScore())).getValue();
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
					int stateVal = (new EvaluatedBoard(state, state.getBoardScore())).getValue();
					if (stateVal < bestStateVal) {
						bestNode = children.get(i);
						bestStateVal = stateVal;
					}
					beta = Math.min(bestStateVal, beta);
					if (alpha >= beta)
						break;
				}
			}
		}
		else
		{
			if (maximizing) {
				bestStateVal = Integer.MIN_VALUE;
				for (int i = 0; i < children.size(); i++) {
					int stateVal = getBestMovePossible(children.get(i), !maximizing, alpha, beta, false).getValue();
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
					int stateVal = getBestMovePossible(children.get(i), !maximizing, alpha, beta, false).getValue();
					if (stateVal < bestStateVal) {
						bestNode = children.get(i);
						bestStateVal = stateVal;
					}
					beta = Math.min(bestStateVal, beta);
					if (alpha >= beta)
						break;
				}
			}

		}
		return new EvaluatedBoard(bestNode, bestStateVal);
	}
}
