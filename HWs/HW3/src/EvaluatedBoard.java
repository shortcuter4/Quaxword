/**
 * @author Ege Aydin
 * @author Onur Kirmizi
 * @author Denizhan Soydas
 * @author Ali Ozer
 * @author Sina Sahan
 * We make soem movements on the board in this class.
 * 
 */
public class EvaluatedBoard {
	private TTTBoardState board;
	private int value;
	
	public EvaluatedBoard(TTTBoardState board, int value) {
		this.board = board;
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public int makeMove(TTTBoardState game) {
		for (int i=0; i<16; i++) {
			if(board.getBoard().get(i) != game.getBoard().get(i)) {
				game.putPiece(i, board.getBoard().get(i));
				return i;
			}
		}
		return -1;
	}
}
