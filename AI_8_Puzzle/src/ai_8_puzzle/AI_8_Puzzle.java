
package ai_8_puzzle;

import java.util.ArrayList;

public class AI_8_Puzzle {

   
    public static void main(String[] args) {
        //1 , 2 , 3 , 0 , 4 , 5 , 7 , 8 , 6 solved
        //1 , 2 , 3 , 4 , 0 , 5 , 7 , 8 , 6 solved
        //{{1,8,2},{0,4,3},{7,6,5}} solved
        //{{1,8,2},{4,5,6},{7,0,3}} solved
        //{{1,8,3},{0,2,7},{4,5,6}} solved
        //{{1,2,3},{4,5,7},{8,6,0}} solved
        //{{1,2,3},{5,6,8},{4,0,7}} solved
        //{{1,2,3},{5,7,6},{0,4,8}} solved
        //{{1,2,3},{5,0,6},{4,7,8}} solved
        //{{2,4,3},{0,1,6},{7,5,8}} solved
        //{{1,2,3},{5,7,6},{4,0,8}} solved
        //{{2,8,4},{7,1,5},{3,6,0}} solved
        //{{4,6,1},{7,5,3},{8,2,0}} solved
        //{{6,1,3},{4,5,2},{7,0,8}} solved
        //{{6,1,3},{4,0,5},{7,8,2}} solved
        
        int[][] x= {{4,6,1},{7,5,3},{8,2,0}};
        Node node = new Node(x); 
        
        System.out.println("table first state : ");
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(x[i][j]+" , ");
            }
        }
        System.out.println("heuristic = "+node.heuristic);
        Node decision = node;
        while(decision.heuristic!=0){
        System.out.println("table state childs : "); 
        
        ArrayList<Node> childLists = decision.getChilds();
        
        for(int i=0;i<childLists.size();i++){
            
            for(int k=0;k<3;k++){
                for(int j=0;j<3;j++){ 
                    System.out.print(childLists.get(i).state[k][j]+" , ");
                }
            }
            System.out.print("heuristic = "+childLists.get(i).heuristic);
            System.out.println("  ");
        }
        
        decision = node.bestNode(childLists);
        System.out.println("table best state : ");
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(decision.state[i][j]+" , ");
            }
        }
            System.out.println(" ");
        }
      }
    
}
