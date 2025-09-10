package Bst;

import java.util.ArrayDeque;
import java.util.Deque;

public class BST {
    private Node root;
    private int size;

    public BST(){
        this.size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int value){
        Node newNode = new Node(value);
        
        if (isEmpty()) {
            this.root = newNode;
            return;
        }

        this.size++;

        Node current = this.root;

        while (current != null) {
            if (current.value > value) {
                if (current.left == null) {
                    current.left = newNode;
                    return;
                }

                current = current.left;
                
            } else if(current.value < value) {
                if (current.rigth == null) {
                    current.rigth = newNode;
                    return;
                }

                current = current.rigth;
            } else {
                return;
            }
            
        }
       
    }

    public boolean equals(BST bst){
        if (bst.isEmpty() && !this.isEmpty()) return false;

        else if(!bst.isEmpty() && this.isEmpty()) return false;

        return true;
    }

    public void bfs(){
        if (isEmpty()) return;

        Deque<Node> queue = new ArrayDeque<Node>();

        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.removeFirst();

            System.out.println(current.value);

            if (current.left != null) {
                queue.addLast(current.left);
            }

            if (current.rigth != null) {
                queue.addLast(current.rigth);
            }
        }
    }

    public int height(){
        return height(root);
    }

    private int height(Node current){
        if (current == null) return -1;

        if (current.isLeaf()) return 0;

        return 1 + Math.max(height(current.left), height(current.rigth));
    }

    public int countingLeafs(){
        return counting(root);
    }

    private int counting(Node current){
        if (current == null) return 0;

        if(current.isLeaf()) return 1;

        return counting(current.left) + counting(current.rigth);

    }


class Node {
    int value;

    Node parent;
    Node left;
    Node rigth;

    public Node(int v){
        this.value = v;
    }

    public boolean isLeaf(){
        return this.left == null && this.rigth == null;
    }

}
    
}
