package dummies;

import java.util.Arrays;

public class WorkWith2DArrays {
	
	public static void main(String[] args) {
		
		char[][] board = new char[3][3];
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				board[i][j] = '-';
			}
		}
		
		System.out.println(Arrays.deepToString(board));
	}
}
