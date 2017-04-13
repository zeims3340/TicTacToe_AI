
package Assignment4_TicTacToe;

//A tree consisting of the entire map of moves for Tic-Tac-Toe
import java.util.ArrayList;
import java.util.List;

public class Tree{
    public Node root;
    boolean computerMove;
    //Copies of the available numbers so that the arrays can be changed without
    //changing the original array lists
    //public ArrayList<Integer> compNum;
    //public ArrayList<Integer> humanNum; //opponent available numbers
    
    public Tree(Node b, List<Integer> comp, List<Integer> human){
        //compNum = comp;
        //humanNum = human;
        computerMove = true;
        setRoot(b);
        createTree(b, computerMove, comp, human);
    }
    
    public void setRoot(Node root)
    {
       this.root = root;
    }
    
    public Node getRoot(){
        return this.root;
    }
    
    
    /*
        Returns true if the tree is empty.
        @param: none
        @return: true if tree is empty, false otherwise
    */
    public boolean empty(){
        return (this.root == null);
    }
    
    /**
     * Create a tree of all possible moves given a state of the tic-tac-toe board.
     * 
     * Each available number for player 1 is inserted into an empty position.
     * @param b
     * @param computerTurn
     * @param p1
     * @param p2
     * @return 
     */
    public Node createTree(Node b, boolean computerTurn, List<Integer> p1, List<Integer> p2){
        int end = b.board.end();
        if (end == 1 || end == 2){
            return b;
        }
        else {
            //Create copies of the arraylists of available numbers
            List<Integer> player = new ArrayList<>(p1);
            List<Integer> otherPlayer = new ArrayList<>(p2);

            int k;
            while (player.size() > 0) {
                k = player.get(0);

                //Check each position in the board for open spaces
                for (short i = 0; i < 3; i++){
                    for (short j = 0; j < 3; j++){
                        if (b.board.board[i][j] == 0){ // <- open position
                            //Copy parent node's board 
                            Node n = new Node(b, "");
                            //Add an available number 
                            n.board.board[i][j] = k;
                            //Recursively add nodes 
                            b.children.add(createTree(n, !computerTurn, otherPlayer, player.subList(1, player.size())));
                        }
                    }
                }

                //remove current available number
                player.remove(0);
            }
        }
        return b;
    }
}


