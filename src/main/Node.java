package main;

import main.Status;
public class Node {

    public Node[] child;
    public int childCount;
    private String vertex;
    public Status state;

    public Node(String vertex) { this.vertex = vertex; }

    public Node(String vertex, int children) {
        this.vertex = vertex;
        childCount = 0;
        child = new Node[children];
    }

    public void addChildNode(Node adj) {
        adj.state = Status.Unvisited;
        if(childCount < 30) {
            this.child[childCount] = adj;
            childCount++;
        }
    }
    
    public int getChildCount() { return childCount; }
    
    public void setChildCount(int s) { childCount = s; }

    public Node[] getChild() { return child; }

    public String getVertex() { return vertex; }

}
