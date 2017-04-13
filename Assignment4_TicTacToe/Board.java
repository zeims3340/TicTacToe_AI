
package Assignment4_TicTacToe;

public class Board {
    public int board[][]; 
    
    public Board(){
        board = new int[3][3];
        
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = 0;
            }
        }
    }
    
    public void display(){
        StringBuilder boardStr = new StringBuilder();
        boardStr.append("+-----+-----+-----+\n");
        boardStr.append("|  ");
        boardStr.append(display1(0, 0));
        boardStr.append("  |  ");
        boardStr.append(display1(0, 1));
        boardStr.append("  |  ");
        boardStr.append(display1(0, 2));
        boardStr.append("  |\n");
        boardStr.append("|-----+-----+-----|\n");
        boardStr.append("|  ");
        boardStr.append(display1(1, 0));
        boardStr.append("  |  ");
        boardStr.append(display1(1, 1));
        boardStr.append("  |  ");
        boardStr.append(display1(1, 2));
        boardStr.append("  |\n");
        boardStr.append("|-----+-----+-----|\n");
        boardStr.append("|  ");
        boardStr.append(display1(2, 0));
        boardStr.append("  |  ");
        boardStr.append(display1(2, 1));
        boardStr.append("  |  ");
        boardStr.append(display1(2, 2));
        boardStr.append("  |\n");
        boardStr.append("+-----+-----+-----+\n");
        System.out.print(boardStr.toString());
    }
    
    private Object display1(int row, int col){
        if (board[row][col] == 0){
            return " ";
        }
        else 
            return board[row][col];
    }
    
    
    public void apply(int row, int col, int number){
        board[row][col] = number;
    }
    
    public int end(){
        int sum;
        
        //Check for a draw
        sum = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                sum += board[i][j];
            }
        }
        if (sum == 45){
            return 2;
        }
        
        //Check horizontally for a winner
        for (int i = 0; i < 3; i++){
            sum = 0;
            for (int j = 0; j < 3; j++){
                sum += board[i][j];
            }
            if (sum == 15){
                return 1;
            }
        }
        
        //Check vertically for a winner
        for (int i = 0; i < 3; i++){
            sum = 0;
            for (int j = 0; j < 3; j++){
                sum += board[j][i];
            }
            if (sum == 15){
                return 1;
            }
        }
        
        //Check diagnally for a winner
        if ((board[0][0] + 
             board[1][1] + 
             board[2][2] == 15) || 
             board[0][2] + 
             board[1][1] + 
             board[2][0] == 15)
             return 1;
        
        return 0;
    } 
}