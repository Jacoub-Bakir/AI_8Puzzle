
package ai_8_puzzle;

import java.util.ArrayList;


public class Node {
    
    int blank_X_Position;
    int blank_Y_Position;
    int[][] state;
    String parentAction;
    int heuristic;
    int parentHeuristic ;
    
    public Node(int[][] node){
        
        this.state = new int[node.length][];
        for(int i=0;i<node.length;i++){
            this.state[i]=node[i].clone();
        }
        getBlank_Position();
        this.parentAction = null;
        this.heuristic = Node.heuristic(this);
        this.parentHeuristic = parentHeuristic;  
        
    }
    
    public Node(int[][] node,int parentHeuristic){
        
        this.state = new int[node.length][];
        for(int i=0;i<node.length;i++){
            this.state[i]=node[i].clone();
        }
        getBlank_Position();
        this.parentAction = null;
        this.heuristic = Node.heuristic(this);
        this.parentHeuristic = parentHeuristic;  
        
    }

    public void getBlank_Position() {
        
        for(int j=0;j<3;j++){
            int i=0;
            while (i < 3) {

                if (this.state[j][i] == 0) {
                    this.blank_X_Position = j;
                    this.blank_Y_Position = i;
                    break;
                }
                else {
                    i = i + 1;
                }
            }
        }
        
        
    }

    public ArrayList<Node> getChilds(){
        
        ArrayList<Node> childLists =new ArrayList<>();
        
        if(this.blank_X_Position==0 && this.blank_Y_Position==0){
            
            if(this.parentAction==null || !this.parentAction.equals("left"))
                childLists.add(goRight(this));
            
            if(this.parentAction==null || !this.parentAction.equals("up"))
                childLists.add(goDown(this));
            
        }
        
        if(this.blank_X_Position==0 && this.blank_Y_Position==1){
            
            if(this.parentAction==null || !this.parentAction.equals("left"))
                childLists.add(goRight(this));
            
            if(this.parentAction==null || !this.parentAction.equals("right"))
                childLists.add(goLeft(this));
            
            if(this.parentAction==null || !this.parentAction.equals("up"))
                childLists.add(goDown(this));
            
        }
        
        if(this.blank_X_Position==0 && this.blank_Y_Position==2){
            
            if(this.parentAction==null || !this.parentAction.equals("right"))
                childLists.add(goLeft(this));
            
            if(this.parentAction==null || !this.parentAction.equals("up"))
                childLists.add(goDown(this));
            
        }
        
        if(this.blank_X_Position==1 && this.blank_Y_Position==0){
            
            if(this.parentAction==null || !this.parentAction.equals("left"))
                childLists.add(goRight(this));
            
            if(this.parentAction==null || !this.parentAction.equals("down"))
                childLists.add(goUp(this));
            
            if(this.parentAction==null || !this.parentAction.equals("up"))
                childLists.add(goDown(this));
            
        }
        
        if(this.blank_X_Position==1 && this.blank_Y_Position==1){
            
            if(this.parentAction==null || !this.parentAction.equals("left"))
                childLists.add(goRight(this));
            
            if(this.parentAction==null || !this.parentAction.equals("right"))
                childLists.add(goLeft(this));
            
            if(this.parentAction==null || !this.parentAction.equals("up"))
                childLists.add(goDown(this));
            
            if(this.parentAction==null || !this.parentAction.equals("down"))
                childLists.add(goUp(this));
            
        }
        
        if(this.blank_X_Position==1 && this.blank_Y_Position==2){
            
            if(this.parentAction==null || !this.parentAction.equals("right"))
                childLists.add(goLeft(this));
            
            if(this.parentAction==null || !this.parentAction.equals("up"))
                childLists.add(goDown(this));
            
            if(this.parentAction==null || !this.parentAction.equals("down"))
                childLists.add(goUp(this));
            
        }
        
        if(this.blank_X_Position==2 && this.blank_Y_Position==0){
            
            if(this.parentAction==null || !this.parentAction.equals("left"))
                childLists.add(goRight(this));
            
            if(this.parentAction==null || !this.parentAction.equals("down"))
                childLists.add(goUp(this));
            
        }
        
        if(this.blank_X_Position==2 && this.blank_Y_Position==1){
            
            if(this.parentAction==null || !this.parentAction.equals("left"))
                childLists.add(goRight(this));
            
            if(this.parentAction==null || !this.parentAction.equals("right"))
                childLists.add(goLeft(this));
            
            if(this.parentAction==null || !this.parentAction.equals("down"))
                childLists.add(goUp(this));
            
        }
        
        if(this.blank_X_Position==2 && this.blank_Y_Position==2){
            
            if(this.parentAction==null || !this.parentAction.equals("right"))
                childLists.add(goLeft(this));
            
            if(this.parentAction==null || !this.parentAction.equals("down"))
                childLists.add(goUp(this));
            
        }
        
      return childLists;
    }
    
    public static int heuristic(Node node){
        int counter=0;
        for(int i=0;i<node.state.length;i++){
            for(int j=0;j<node.state.length;j++){
                if(node.state[i][j]!=0){
                    int actualX = 0;
                    int actualY = node.state[i][j]- 1;
                    if(node.state[i][j]>3){
                        actualX++;
                        actualY= actualY % 3;
                        if(node.state[i][j]>6)
                            actualX++;
                    }
                    counter  = counter + Math.abs( i - actualX )+ Math.abs(j - actualY);
                }
            }
        }
        
        
        return counter;
    }
    
    public Node bestNode(ArrayList<Node> childLists){
        
        int bestIndexNode = 0;
        ArrayList<Node> sameChildsHeuristic = new ArrayList<>();
        
        for(int i=1;i<childLists.size();i++){
             
            if(childLists.get(i).heuristic < childLists.get(bestIndexNode).heuristic){ 
               bestIndexNode = i;
            }
        }
        
        for(int i=0;i<childLists.size();i++){ 
            if(childLists.get(i).heuristic == childLists.get(bestIndexNode).heuristic){
              sameChildsHeuristic.add(childLists.get(i));
            }
        }
        Node bestNode = null;
        if(sameChildsHeuristic.size() == 1){
            bestNode = childLists.get(bestIndexNode);
        }else{
            bestNode = getBestDecision(sameChildsHeuristic);
        }
        
        
    return bestNode;
    }
    
    public Node getBestDecision(ArrayList<Node> sameChildsHeuristic){
        int bestIndexNode = 0;
        for(int i =0 ; i<sameChildsHeuristic.get(0).state.length;i++){
            for(int j =0 ; j<sameChildsHeuristic.get(0).state.length;j++){
                if(sameChildsHeuristic.get(0).state[i][j] != sameChildsHeuristic.get(1).state[i][j] && sameChildsHeuristic.get(0).state[i][j] != 0 && sameChildsHeuristic.get(1).state[i][j]!=0){
                    if(sameChildsHeuristic.get(0).state[i][j] > sameChildsHeuristic.get(1).state[i][j] && sameChildsHeuristic.get(0).parentHeuristic < sameChildsHeuristic.get(0).heuristic){
                        bestIndexNode = 1;
                    }
                }
            }
        }
        Node bestNode = sameChildsHeuristic.get(bestIndexNode);
        return bestNode;
    }
            
    public Node goRight(Node node){
        
        Node x_child = new Node(node.state,node.heuristic);
        /*for(int i=0;i<state.state.length;i++){
            x_child.state[i]=state.state[i].clone();
        }*/

        //make the change to the child table
        x_child.state[x_child.blank_X_Position][x_child.blank_Y_Position]= x_child.state[x_child.blank_X_Position][x_child.blank_Y_Position+ 1];
        x_child.state[x_child.blank_X_Position][x_child.blank_Y_Position+ 1]= 0;
        x_child.blank_Y_Position++;
        x_child.heuristic = Node.heuristic(x_child);
        x_child.parentAction = "right";
        
    return x_child;
    }
    
    public Node goLeft(Node node){
        
        Node x_child = new Node(node.state,node.heuristic);
        /*for(int i=0;i<state.state.length;i++){
            x_child.state[i]=state.state[i].clone();
        }*/

        //make the change to the child table
        x_child.state[x_child.blank_X_Position][x_child.blank_Y_Position]= x_child.state[x_child.blank_X_Position][x_child.blank_Y_Position- 1];
        x_child.state[x_child.blank_X_Position][x_child.blank_Y_Position- 1]= 0;
        x_child.blank_Y_Position--;
        x_child.heuristic = Node.heuristic(x_child);
        x_child.parentAction = "left"; 
        
    return x_child;
    }
    
    public Node goUp(Node node){
        
        Node x_child = new Node(node.state,node.heuristic);
        /*for(int i=0;i<state.length;i++){
            x_child[i]=state[i].clone();
        }*/

        //make the change to the child table
        x_child.state[x_child.blank_X_Position][x_child.blank_Y_Position]= x_child.state[x_child.blank_X_Position-1][x_child.blank_Y_Position];
        x_child.state[x_child.blank_X_Position-1][x_child.blank_Y_Position]= 0;
        x_child.blank_X_Position--;
        x_child.heuristic = Node.heuristic(x_child);
        x_child.parentAction = "up";
        
    return x_child;
    }
    
    public Node goDown(Node node){
        
        Node x_child = new Node(node.state,node.heuristic);
        /*for(int i=0;i<state.length;i++){
            x_child[i]=state[i].clone();
        }*/

        //make the change to the child table
        x_child.state[x_child.blank_X_Position][x_child.blank_Y_Position]= x_child.state[x_child.blank_X_Position+1][x_child.blank_Y_Position];
        x_child.state[x_child.blank_X_Position+1][x_child.blank_Y_Position]= 0;
        x_child.blank_X_Position++;
        x_child.heuristic = Node.heuristic(x_child);
        x_child.parentAction = "down"; 
        
    return x_child;
    }
    
}
