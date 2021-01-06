
public class boardinit {//Kommentar
	private int collum, row,num_mines;
	private int [][] grid; 
	
	public boardinit(int collum, int row,int num_mines) {
		this.collum=collum;
		this.row=row;
		this.num_mines=num_mines;
		this.grid= new int[collum][row];
	}
	
	public static void pupulateboard() {
		
	}
	
	public int getCollum() {
		return collum;
	}
	public int getrow() {
		return row;
	}
	public int getmines() {
		return num_mines;
	}
	
	public int getNeighbors(int x, int y) {
		int neighbor = 0;
		neighbor = grid[x-1][y-1]==1 	? neighbor++ : neighbor;
		neighbor = grid[x-1][y]==1 		? neighbor++ : neighbor;
		neighbor = grid[x-1][y+1]==1 	? neighbor++ : neighbor;
		neighbor = grid[x][y-1]==1 		? neighbor++ : neighbor;
		neighbor = grid[x][y+1]==1		? neighbor++ : neighbor;
		neighbor = grid[x+1][y-1]==1 	? neighbor++ : neighbor;
		neighbor = grid[x+1][y]==1 		? neighbor++ : neighbor;
		neighbor = grid[x+1][y+1]==1 	? neighbor++ : neighbor;
		return neighbor;
	}
	
	
	
}

