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
	public void setValue(int newValue)
	{
		this.value = newValue;
	}
	
	public int makeMove(TTTBoardState game) {

		for (int a=0; a< 4; a++) {
			for(int b = 0; b< 4; b++)
			{
				System.out.print("\t " +  board.getBoard().get(b + a)); //board.getBoard().get(a);
			}
			System.out.println();
		}

		for (int i=0; i< 16; i++) {

			//System.out.println(this.value);
			//board.getBoard().toString();
			if(board.getBoard().get(i) != game.getBoard().get(i)) {
				game.putPiece(i, board.getBoard().get(i));
				return i;
			}
		}
		return -1;
	}
	public int playerMakeMove(TTTBoardState game, int i) {
		//board.getBoard().toString();
		//System.out.println(this.value);
		for (int a=0; a< 4; a++) {
			for(int b = 0; b< 4; b++)
			{
				System.out.print("\t" +  board.getBoard().get(b + a)); //board.getBoard().get(a);
			}
			System.out.println();
		}
		if(board.getBoard().get(i) != game.getBoard().get(i)) {
			game.putPiece(i, board.getBoard().get(i));
			return i;
		}
		System.out.println("error");
		return -1;
	}
}
