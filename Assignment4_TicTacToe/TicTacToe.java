
package Assignment4_TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public List<Integer> compNumbers;
    public List<Integer> humanNumbers; //opponent available numbers
    
    public TicTacToe(){
        compNumbers = new ArrayList<>();
        humanNumbers = new ArrayList<>();
        initList();
    }
    
    public static void main(String [] args){
        TicTacToe t = new TicTacToe();
        Scanner userIn = new Scanner(System.in);
        Node boardNode = new Node(null);
        boolean computerTurn = false;
        boardNode.board.display();
      
        int result;
        
        while (true){
            if (!computerTurn)
                t.getAndApplyUserMove(userIn, boardNode.board);
            else {
                boardNode = Minimax.computeAndApplyMove(boardNode, t.compNumbers, t.humanNumbers);
                t.updateCompNum(boardNode);
            }
            
            boardNode.board.display(); 
            result = boardNode.board.end();
            
            //If there's a winner or a draw, exit
            if (result == 1 || result == 2) break;
            computerTurn = !computerTurn;
        }
        if (result == 1){
            if (computerTurn){
                System.out.println("The computer won!\nYou tried your best. Thank you for playing.");
                System.exit(0);
            } else if (!computerTurn){
                System.out.println("You won! Congratulations.");
                System.exit(0);
            }
        }
        else if (result == 2) {
            System.out.println("tie game. Thank you for playing.");
            System.exit(0);
        }
    }
    
    /**
     * Get user input and validate input
     * Apply move to board and remove that number from available numbers
     * @param userIn
     * @param b 
     */
    public void getAndApplyUserMove(Scanner userIn, Board b){
        int row, col, number;
        boolean validNum;
        
        // Prompt user input until a valid input is made
        do {
            validNum = false;
            System.out.print("Enter your move (x, y, (1-9)): ");
            row = userIn.nextInt();
            col = userIn.nextInt();
            number = userIn.nextInt();

            //Check for input number validity 
            if (number == 1 || number == 3 || number == 5 || number == 7 || number == 9){
                validNum = true;
            }
            
            if (!validNum)
                System.out.println("Please enter a valid number!");
            
            //check if the position has been selected already
            if (b.board[row][col] != 0){
                System.out.println("That position is already used - choose another");
                validNum = false;
            } 
        } while(!validNum);
        
        b.apply(row, col, number);
        
        //Remove the used number from the available numbers
        this.humanNumbers.remove((Object) number);
    }
    
    /**
     * Display the board
     * @param b 
     */
    public void displayBoard(Board b){
        b.display();
    }
    
    /**
     * Initialize available list of numbers and opponent numbers
     */
    private void initList(){
        this.compNumbers.add(2);
        this.compNumbers.add(4);
        this.compNumbers.add(6);
        this.compNumbers.add(8);
        this.humanNumbers.add(1);
        this.humanNumbers.add(3);
        this.humanNumbers.add(5);
        this.humanNumbers.add(7);
        this.humanNumbers.add(9);
    }
    
    /**
     * Remove newly added computer number to the board
     * @param b 
     */
    public void updateCompNum(Node b){
        //Go through numbers in the board
        //If number from compNumbers is in board, remove it from list
        for (int k = 0; k < compNumbers.size(); k++) {
            int num = compNumbers.get(k);
            
            for (int i = 0; i < 3; i++){
                for (int j = 0; j < 3; j++){
                    if (num == b.board.board[i][j]){
                        compNumbers.remove(k);
                    }
                }
            }
        }
    }
    
}
