
package Assignment4_TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class Minimax {
    
    public static Node computeAndApplyMove(Node b, 
                             List<Integer> comp, List<Integer> human){
        //clear map of children moves (not the best solution, but it 
        //has to do for this assignment
        b.children.clear();
        
        //Create a map of the possible moves
        Tree map = new Tree(b, comp, human);
        value(b, true);
        
        //int max = Integer.MIN_VALUE;
        //int index = 0;
        List<Integer> ones = new ArrayList<>();
        List<Integer> zeroes = new ArrayList<>();
        List<Integer> negs = new ArrayList<>();
        List<Integer> bestList;
        
        for (int i = 0; i < b.children.size(); i++){
            int nextValue = b.children.get(i).value;
            System.out.println("next value = " + nextValue);
            switch (nextValue){
                case 1:
                    ones.add(i);
                case 0:
                    zeroes.add(i);
                case -1:
                    negs.add(i);
            }
        }
        
        int randomIndex = 0;
//        System.out.println(ones.size());
//        System.out.println(zeroes.size());
//        System.out.println(negs.size());
        bestList = getRandomIndex(ones, zeroes, negs, randomIndex);
        //For the best possible moves, choose one at random to vary gameplay
        return b.children.get(bestList.get(randomIndex));
    }
    
    
    private static int value(Node b, boolean compTurn){
        //If there's a winner or a draw
        int status = b.board.end();
        if (status == 1 || status == 2){
            return utility(compTurn, status);
        }
        //MAX in the computer's perspective
        else if (compTurn){
            return maxValue(b, compTurn);
        }
        //MIN in the computer's perspective
        else
            return minValue(b, compTurn);
    }
    
    private static int utility(boolean compTurn, int status){
        if (status == 1){
            if (compTurn) {
                return 1;
            }
            if (!compTurn) {
                return -1;
            }
        }
        return 0;
    }
    
    private static int maxValue(Node b, boolean compTurn){
        int m = Integer.MIN_VALUE;
        int v;
        
        for (Node s : b.children){
            v = value(s, !compTurn);
            s.value = v;
            m = Math.max(m, v);
        }
        return m;
    }
    
    private static int minValue(Node b, boolean compTurn){
        int m = Integer.MAX_VALUE;
        int v;
        
        for (Node s : b.children){
            v = value(s, !compTurn);
            s.value = v;
            m = Math.min(m, v);
        }
        return m;
    }
    
    /**
     * Determines which list of values to use and then generates a random
     * index to use within that list. 
     * 
     * @param ones
     * @param zeroes
     * @param negs
     * @param randomIndex
     * @return the list of values to use with the random number
     */
    private static List<Integer> getRandomIndex(List<Integer> ones, List<Integer> zeroes, List<Integer> negs, int randomIndex){
        if (ones.size() > 0){
            randomIndex = (int) (Math.random() * ones.size());
            return ones;
        }
        else if (zeroes.size() > 0){
            randomIndex = (int) (Math.random() * zeroes.size());
            return zeroes;
        }
        else {
            randomIndex = (int) (Math.random() * negs.size());
            return negs;
        }
    }
}
