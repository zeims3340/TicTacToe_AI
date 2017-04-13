
package Assignment4_TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class Node{
    public Node parent;
    public Board board;
    public List<Node> children; 
    int value;
    
    /*
        Constructor
        @param: parent name
        @param: node name
        @param: f(n) value
    */
    public Node(Node parent){
        this.parent = parent;
        children = new ArrayList<>();
        this.board = new Board();
    }
    
    /**
     * Copy constructor
     * @param original
     * @param n, to be different from the constructor
     */
    public Node(Node original, String n){
        this.parent = original;
        this.board = new Board();
        
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                this.board.board[i][j] = original.board.board[i][j];
            }
        }
        
        children = new ArrayList<>();
    }
}
