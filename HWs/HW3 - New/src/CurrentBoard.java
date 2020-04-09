import java.util.ArrayList;

enum Piece {
	X, O, EMPTY;
}

enum GameState {
	X_WON, O_WON, TIE, ONGOING;
}

public class CurrentBoard {
	private ArrayList<Piece> board;

	//constructor
	public CurrentBoard() {
		board = new ArrayList<Piece>();
		for (int i = 0; i < 16; i++)
			board.add(Piece.EMPTY);
	}

	public CurrentBoard(ArrayList<Piece> board) {
		this.board = board;
	}
	//getter for Pieces.
	public ArrayList<Piece> getBoard() {
		return this.board;
	}



	//if place is empty, put piece into i
	public CurrentBoard copyAndPutPiece(int i, Piece p) {
		ArrayList<Piece> boardCpy = new ArrayList<Piece>(board);

		if (boardCpy.get(i) == Piece.EMPTY) {
			boardCpy.set(i, p);
		}
		return new CurrentBoard(boardCpy);
	}

	public void putPiece(int i, Piece p) {
		if (board.get(i) == Piece.EMPTY) {
			board.set(i, p);
		}
		GameBot.turn++;
	}


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

	public GameState checkGameOver() {

		if (board.get(0) == board.get(5) && board.get(5) == board.get(10) && board.get(10) == board.get(15))
			return board.get(0) == Piece.O ? GameState.O_WON
					: board.get(0) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		if (board.get(3) == board.get(6) && board.get(6) == board.get(9)  && board.get(9) == board.get(12))
			return board.get(3) == Piece.O ? GameState.O_WON
					: board.get(3) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		for (int i = 0; i < 4; i++)
			if (board.get(i*4) != Piece.EMPTY && board.get(i * 4) == board.get(i * 4 + 1) && board.get(i * 4 + 1) == board.get(i * 4 + 2) && board.get(i * 4 + 2) == board.get(i * 4 + 3))
				return board.get(i * 4) == Piece.O ? GameState.O_WON
						: board.get(i * 4) == Piece.X ? GameState.X_WON : GameState.ONGOING;
		for (int i = 0; i < 4; i++)
			if (board.get(i*4) != Piece.EMPTY && board.get(i) == board.get(i + 4) && board.get(i + 4) == board.get(i + 8) && board.get(i + 8) == board.get(i + 12))
				return board.get(i) == Piece.O ? GameState.O_WON
						: board.get(i) == Piece.X ? GameState.X_WON : GameState.ONGOING;


		boolean tie = true;
		for (int i = 0; i < 16; i++)
			tie = tie && board.get(i) != Piece.EMPTY;

		return tie ? GameState.TIE : GameState.ONGOING;
	}

	public ArrayList<Integer> getChildBoards(ArrayList<CurrentBoard> children) {  //CHANGED
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
	public ArrayList<Integer> getChildBoardsFull(ArrayList<CurrentBoard> children) {  //CHANGED
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
		
		ArrayList<CurrentBoard> children = new ArrayList<CurrentBoard>();
		ArrayList<Integer> emptyPlaces = this.getChildBoards(children);
		int[] childEval = {16,16,16,16,16,16,16,16,16,16,16,16,16,16,16,16};

		for (int i=0;i<emptyPlaces.size();i++) {
			int evaluation = GameBot.getBestMovePossible(children.get(i), XPlaysNext, Integer.MIN_VALUE, Integer.MAX_VALUE,false).getValue();
			childEval[emptyPlaces.get(i)] = evaluation==Integer.MAX_VALUE?1:evaluation==Integer.MIN_VALUE?-1:0;
			//System.out.println("Loading wait...");
		}

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
