package main;

import java.util.*;
import main.Status;

public class GraphDriver {
	
    public void dfsRecursive(Node root, String goal) {   
        //Avoid infinite loops, they're no fun
    	//System.out.println("I've reached this far");
        if(root == null) return;
        if(root.state == Status.Visited) {
        	return;
        }
        
        //print
        
        System.out.print(root.getVertex()+"\t" );
        
        
        //mark it
        root.state = Status.Visited;
        

        //check all of the children
        for(Node n: root.getChild()) {
        	
        	//if a child is unvisited, then call the function again.
        	
        	if(n.state == Status.Unvisited) {
            	
            	//System.out.print("I've reached this far");
            	
        		//recursion is fun!
            	dfsRecursive(n, goal);  	
            	
            }
        }
    }
    
    public void dfsStack(Node root, String goal) {
    	  //if empty graph, return
    	  if (null == root) {
    	    return; //
    	  }
    	  Stack<Node> stack = new Stack<Node>();
    	  //add source to stack
    	  stack.push(root);
    	  while (!stack.isEmpty()) {
    	    Node currentNode = stack.pop();
    	    System.out.print(currentNode.getVertex() + "\t");
    	    currentNode.state = Status.Visited;
    	    //check if we reached out target node
    	    if (currentNode.getVertex().equals(goal)) {
    	      return; // we have found our target node V.
    	    }
    	    //for each child node "n," push into the stack
    	    //for (Node n : currentNode.getChild()) {
    	    	Node[] n = currentNode.getChild();
    	      for (int i = 0; i < currentNode.getChildCount(); i++) {
    	    	  
    	    	if (n[i].state == Status.Unvisited) {
    	        stack.push(n[i]);
    	      }
    	    }
    	  }
    	}

    public void bfs(Node root, String goal) {
        //Since queue is an interface
    	//Implement type-safety
        Queue<Node> queue = new LinkedList<Node>();

        if(root == null) return;

        root.state = Status.Visited;
        //Adds previous to the end of queue
        queue.add(root);

        while(!queue.isEmpty()) {
            //removes from front of queue
            Node r = queue.remove(); 
            //prints out removed node
            System.out.print(r.getVertex() + "\t");
            
            //Define goal
            if(r.getVertex().equals(goal)) return;
            

            //Visit child first before "grand child"
            Node[] n = r.getChild();
            for(int i = 0; i < r.getChildCount(); i++) {
                if(n[i].state == Status.Unvisited) {
                    queue.add(n[i]);
                    n[i].state = Status.Visited;
                }
            }
        }


    }

    public static Graph createNewGraph() {
    	//create a new graph to place nodes
        Graph g = new Graph(); 
        //create an array of nodes 
        //to hold location
        Node[] location = new Node[12];

        //implement file reader later
        location[0] = new Node("Macau", 3);
        location[1] = new Node("China", 3);
        location[2] = new Node("HongKong", 2);
        location[3] = new Node("Taiwan", 1);
        location[4] = new Node("Japan", 1);
        location[5] = new Node("Korea", 1);
        location[6] = new Node("India", 1);
        location[7] = new Node("France", 2);
        location[8] = new Node("Russia", 0);
        location[9] = new Node("USA", 0);
        location[10] = new Node("Netherlands", 0);
        location[11] = new Node("England", 0);

        location[0].addChildNode(location[1]);
        location[0].addChildNode(location[2]);
        location[0].addChildNode(location[3]);

        location[1].addChildNode(location[4]);
        location[1].addChildNode(location[6]);
        location[1].addChildNode(location[7]);

    
        location[2].addChildNode(location[4]);
        location[2].addChildNode(location[5]);
        
        location[3].addChildNode(location[6]);
        
        location[4].addChildNode(location[9]);
        
        location[5].addChildNode(location[10]);

        location[6].addChildNode(location[8]);
        
        location[7].addChildNode(location[11]);
        location[7].addChildNode(location[9]);
       
        
        
        for (int i = 0; i < 11; i++) g.addNode(location[i]);
    
        return g;
    }
    
    public static Graph createNewDataGraph() {
    	//create a new graph to place nodes
        Graph g = new Graph(); 
        //create an array of nodes 
        //to hold location
        Node[] location = new Node[6];

        //implement file reader later
        location[0] = new Node("Madison", 2);
        location[1] = new Node("Chicago", 4);
        location[2] = new Node("Rockford", 3);
        location[3] = new Node("Milwaukee", 2);
        location[4] = new Node("Des Plaines", 3);
        location[5] = new Node("Springfield", 2);

        //Madison -> Chicago
        location[0].addChildNode(location[1]);
        //Madison -> Rockford
        location[0].addChildNode(location[2]);
        
        //Chicago -> Rockford
        location[1].addChildNode(location[2]);
        //Chicago -> Des Plaines
        location[1].addChildNode(location[4]);

        //Rockford -> Des Plaines
        location[2].addChildNode(location[4]);
        //Rockford -> Springfield
        location[2].addChildNode(location[5]);
        
        //Des Plaines -> Milwaukee
        location[4].addChildNode(location[3]);
        
        //Springfield -> Milwaukee
        location[5].addChildNode(location[3]);
       
       
        
        for (int i = 0; i < 5; i++) g.addNode(location[i]);
    
        return g;
    }

    public static void main(String[] args) {

        Graph dfsGraph = createNewGraph();
        GraphDriver s = new GraphDriver();

        System.out.println("<~~Depth First Search~~>");
        System.out.println();
        s.dfsStack(dfsGraph.getNode()[0], "USA");
        System.out.println();
        System.out.println();
        Graph bfsGraph = createNewGraph();
        System.out.println("<~~Breadth First Search~~>");
        System.out.println();
        s.bfs(bfsGraph.getNode()[0], "USA");
        
        Graph dfsDataGraph = createNewDataGraph();
        
        System.out.println();
        System.out.println();
        System.out.println("//--Depth First Search--\\\\");
        System.out.println();
        s.dfsStack(dfsDataGraph.getNode()[0], "Milwaukee");
        System.out.println();
        System.out.println();
        Graph bfsDataGraph = createNewDataGraph();
        System.out.println("//--Breadth First Search--\\\\");
        System.out.println();
        s.bfs(bfsDataGraph.getNode()[0], "Milwaukee");

    }

}
