
public class NewBoard {
	private CurrentBoard board;
	private int value;
	
	public NewBoard(CurrentBoard board, int value) {
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
	
	public int makeMove(CurrentBoard game) {

		for (int a=0; a< 4; a++) {
			for(int b = 0; b< 4; b++)
			{
				System.out.print("\t " +  game.getBoard().get(b + a*4)); //board.getBoard().get(a);
			}
			System.out.println();
		}

		for (int i=0; i< 16; i++) {
			if(board.getBoard().get(i) != game.getBoard().get(i)) {
				game.putPiece(i, board.getBoard().get(i));
				return i;
			}
		}
		return -1;
	}
	public int playerMakeMove(CurrentBoard game, int i) {
		for (int a=0; a< 4; a++) {
			for(int b = 0; b< 4; b++)
			{
				System.out.print("\t" +  game.getBoard().get(b + a*4)); //board.getBoard().get(a);
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
