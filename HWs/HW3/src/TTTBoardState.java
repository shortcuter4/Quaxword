import java.util.ArrayList;
/**
 * @author Ege Aydin
 * @author Onur Kirmizi
 * @author Denizhan Soydas
 * @author Ali Ozer
 * @author Sina Sahan
 * In this class, we keep the states of the Tic Tac Toe Board.
 *
 */

enum Piece {
	X, O, EMPTY;
}

enum GameState {
	X_WON, O_WON, TIE, ONGOING;
}

public class TTTBoardState {
	private ArrayList<Piece> board;

	//constructor
	public TTTBoardState() {
		board = new ArrayList<Piece>();
		//turn =  1;
		for (int i = 0; i < 16; i++)
			board.add(Piece.EMPTY);
	}
	//public Piece getBoardWithNum(int i){ return board.get(i);}
	//constructor
	public TTTBoardState(ArrayList<Piece> board) {
		this.board = board;
	}
	//getter for Pieces.
	public ArrayList<Piece> getBoard() {
		return this.board;
	}

	//public int getTurn() {return this.turn;}

	//if place is empty, put piece into i
	public TTTBoardState copyAndPutPiece(int i, Piece p) {
		ArrayList<Piece> boardCpy = new ArrayList<Piece>(board);

		if (boardCpy.get(i) == Piece.EMPTY) {
			boardCpy.set(i, p);
		}
		return new TTTBoardState(boardCpy);
	}
	/**
	 * 
	 * @param i the place to put Piece.
	 * @param p the Piece that is desired to put.
	 */
	public void putPiece(int i, Piece p) {
		if (board.get(i) == Piece.EMPTY) {
			board.set(i, p);
		}
		GameBot.turn++;
	}
	/**
	 * We get the numbers of the Os and Xs in a line,
	 * we'll calculate the state value of the game.
	 * @param line the line to calculate the values.
	 * @return the number of the Xs and Os on that line.
	 */
	public int[] getLineProperties(int line) // column0:0, column1:1, column2:2, row0:3, row1:4, row2:5, diag\:6, diag/:7
	{
		int[] result = { 0, 0 }; // number of X, number of O
		if (line < 4) {
			for (int i = 0; i < 4; i++) {
				if (board.get(line * 4 + i) == Piece.X)
					result[0]++;
				else if (board.get(line * 4 + i) == Piece.O)
					result[1]++;
			}
		} else if (line < 8) {
			for (int i = 0; i < 4; i++) {
				if (board.get(line - 4 + i * 4) == Piece.X)
					result[0]++;
				else if (board.get(line - 4 + i * 4) == Piece.O)
					result[1]++;
			}

		} else if (line < 12 ) {
			for (int i = 0; i < 4; i++) {
				if (board.get(line - 8 + i * 4) == Piece.X)
					result[0]++;
				else if (board.get(line - 8 + i * 4) == Piece.O)
					result[1]++;
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (board.get(line - 12 + i * 4) == Piece.X)
					result[0]++;
				else if (board.get(line - 12 + i * 4) == Piece.O)
					result[1]++;
			}
		}
		return result;
	}
	/**
	 * this method checks whether the game is finished or not.
	 * @return simply returns a gamestate 
	 */
	public GameState checkGameOver() {

		if (board.get(0) == board.get(5) && board.get(5) == board.get(10) && board.get(10) == board.get(15))
			return board.get(0) == Piece.O ? GameState.O_WON
					: board.get(0) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		if (board.get(3) == board.get(6) && board.get(6) == board.get(9)  && board.get(9) == board.get(12))
			return board.get(3) == Piece.O ? GameState.O_WON
					: board.get(3) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		for (int i = 0; i < 4; i++)
			if (board.get(i * 4) == board.get(i * 4 + 1) && board.get(i * 4 + 1) == board.get(i * 4 + 2) && board.get(i * 4 + 2) == board.get(i * 4 + 3))
				return board.get(i * 4) == Piece.O ? GameState.O_WON
						: board.get(i * 4) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		for (int i = 0; i < 4; i++)
			if (board.get(i) == board.get(i + 4) && board.get(i + 4) == board.get(i + 8) && board.get(i + 8) == board.get(i + 12))
				return board.get(i) == Piece.O ? GameState.O_WON
						: board.get(i) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		/*
		if (board.get(0) == board.get(5) && board.get(5) == board.get(10))
			return board.get(0) == Piece.O ? GameState.O_WON
					: board.get(0) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		if (board.get(2) == board.get(5) && board.get(5) == board.get(8))
			return board.get(2) == Piece.O ? GameState.O_WON
					: board.get(2) == Piece.X ? GameState.X_WON : GameState.ONGOING;

		if (board.get(1) == board.get(6) && board.get(6) == board.get(11))
			return board.get(1) == Piece.O ? GameState.O_WON
					: board.get(1) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		if (board.get(3) == board.get(6) && board.get(6) == board.get(9))
			return board.get(3) == Piece.O ? GameState.O_WON
					: board.get(3) == Piece.X ? GameState.X_WON : GameState.ONGOING;

		if (board.get(4) == board.get(9) && board.get(9) == board.get(14))
			return board.get(4) == Piece.O ? GameState.O_WON
					: board.get(4) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		if (board.get(6) == board.get(9) && board.get(9) == board.get(12))
			return board.get(6) == Piece.O ? GameState.O_WON
					: board.get(6) == Piece.X ? GameState.X_WON : GameState.ONGOING;

		if (board.get(5) == board.get(10) && board.get(10) == board.get(15))
			return board.get(5) == Piece.O ? GameState.O_WON
					: board.get(5) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		if (board.get(7) == board.get(10) && board.get(10) == board.get(13))
			return board.get(7) == Piece.O ? GameState.O_WON
					: board.get(7) == Piece.X ? GameState.X_WON : GameState.ONGOING;

		for (int i = 0; i < 4; i++)
			if (board.get(i * 4) == board.get(i * 4 + 1) && board.get(i * 4 + 1) == board.get(i * 4 + 2))
				return board.get(i * 4) == Piece.O ? GameState.O_WON
						: board.get(i * 4) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		for (int i = 0; i < 4; i++)
			if (board.get(i * 4 + 1) == board.get(i * 4 + 2) && board.get(i * 4 + 2) == board.get(i * 4 + 3))
				return board.get(i * 4 + 1) == Piece.O ? GameState.O_WON
						: board.get(i * 4 + 1) == Piece.X ? GameState.X_WON : GameState.ONGOING;

		for (int i = 0; i < 4; i++)
			if (board.get(i) == board.get(i + 4) && board.get(i + 4) == board.get(i + 8))
				return board.get(i) == Piece.O ? GameState.O_WON
						: board.get(i) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		for (int i = 4; i < 8; i++)
			if (board.get(i) == board.get(i + 4) && board.get(i + 4) == board.get(i + 8))
				return board.get(i) == Piece.O ? GameState.O_WON
						: board.get(i) == Piece.X ? GameState.X_WON : GameState.ONGOING;

		 */

		boolean tie = true;
		for (int i = 0; i < 16; i++)
			tie = tie && board.get(i) != Piece.EMPTY;

		return tie ? GameState.TIE : GameState.ONGOING;
	}
	/**
	 * We return the child boards in this class.
	 * @return the ArrayList of all Child Boards.
	 */
	public ArrayList<Integer> getChildBoards(ArrayList<TTTBoardState> children) {  //CHANGED
		ArrayList<Integer> emptyPlaces = new ArrayList<Integer>();
		int totalX = getLineProperties(0)[0] + getLineProperties(1)[0] + getLineProperties(2)[0] + getLineProperties(3)[0];
		int totalO = getLineProperties(0)[1] + getLineProperties(1)[1] + getLineProperties(2)[1] + getLineProperties(3)[1];

		for (int i = 0; i < 16; i++) {
			if (board.get(i) == Piece.EMPTY) {
				// if totalX == totalO, its turn of X. else, O.
				if (totalX == totalO)
					children.add(copyAndPutPiece(i, Piece.X));
				else
					children.add(copyAndPutPiece(i, Piece.O));
				emptyPlaces.add(i);
			}
		}
		return emptyPlaces;
	}
	public ArrayList<Integer> getChildBoardsFull(ArrayList<TTTBoardState> children) {  //CHANGED
		ArrayList<Integer> emptyPlaces = new ArrayList<Integer>();
		int totalX = getLineProperties(0)[0] + getLineProperties(1)[0] + getLineProperties(2)[0] + getLineProperties(3)[0];
		int totalO = getLineProperties(0)[1] + getLineProperties(1)[1] + getLineProperties(2)[1] + getLineProperties(3)[1];

		for (int i = 0; i < 16; i++) {

				// if totalX == totalO, its turn of X. else, O.
				if (totalX == totalO)
					children.add(copyAndPutPiece(i, Piece.X));
				else
					children.add(copyAndPutPiece(i, Piece.O));
				emptyPlaces.add(i);

		}
		return emptyPlaces;
	}
	//gets the score of the current board.
	public int getBoardScore() {
		int[] result = { 0, 0 };// X score, O score

		for (int i = 0; i < 16; i++) {
			int[] property = getLineProperties(i);
			if (property[0] == 0 || property[1] == 0) {
				result[0] += property[0];
				result[1] += property[1];
			}
		}
		GameState state = checkGameOver();
		if (state != GameState.ONGOING) {
			if (state == GameState.X_WON) {
				result[0] = Integer.MAX_VALUE;
				result[1] = 0;
			} else if (state == GameState.O_WON) {
				result[1] = 0;
				result[0] = Integer.MIN_VALUE;
			} else {
				result[0] = 0;
				result[1] = 0;
			}
		}
		return result[0] - result[1]; // as ans->inf X gets a better board. as ans->-inf O gets a better board
	}
	
	public int[] getChildEval() {  //CHANGED (NEW METHOD)
		int totalX = getLineProperties(0)[0] + getLineProperties(1)[0] + getLineProperties(2)[0] + getLineProperties(3)[0];;
		int totalO = getLineProperties(0)[1] + getLineProperties(1)[1] + getLineProperties(2)[1] + getLineProperties(3)[1];
		boolean XPlaysNext = totalX!=totalO;
		
		ArrayList<TTTBoardState> children = new ArrayList<TTTBoardState>();
		ArrayList<Integer> emptyPlaces = this.getChildBoards(children);
		int[] childEval = {16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16};

		for (int i=0;i<emptyPlaces.size();i++) {
			int evaluation = GameBot.getBestMovePossible(children.get(i), XPlaysNext, Integer.MIN_VALUE, Integer.MAX_VALUE,false).getValue();
			childEval[emptyPlaces.get(i)] = evaluation==Integer.MAX_VALUE?1:evaluation==Integer.MIN_VALUE?-1:0;
			//System.out.println("Loading wait...");
		}
		/*
		for(int i=0;i<9;i++)
			System.out.print(childEval[i]+" ,");
		System.out.println(" Test");

		 */
		return childEval;
	}

	@Override
	public String toString() {
		String ans = board.toString().replace("EMPTY", " ");
		ans = ans.substring(1, ans.length() - 1);
		return "\n" + ans.substring(0, ans.length() / 4 - 1) + "\n"
				+ ans.substring(ans.length() / 4 + 1, 2 * ans.length() / 4) + "\n"
				+ ans.substring(2 * ans.length() / 4 + 2, ans.length()) + "\n";

	}
	public String getChildEvalAsSquare() {
		int[] childs = getChildEval();
		String result = "\n";
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(childs[i*4 + j] >= - 1 && childs[i*4+j] <= +1)
					result = result + childs[i* 4 + j] + "    ";
				else
					result = result + "-" + "    ";
			}
			result = result + "\n";
		}
		System.out.println("Virtual values are: " +result);
		return "Virtual values are: " +result;
		
	}

}
