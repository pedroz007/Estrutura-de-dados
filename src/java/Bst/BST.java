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

    public Node getRoot(){
        return this.root;
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

    public int search(int value){
        if (isEmpty()) throw new IllegalArgumentException();

        return search(root, value).value;
    }

    private Node search(Node current, int value){
        if (current == null) {
            throw new IllegalArgumentException();
        }

        if(current.value == value) return current;

        if (current.value > value) {
            return search(current.left, value);
        }

        else{
            return search(current.rigth, value);
        }
    }

    public int romeve(int valor){
        return -1;
    }

    private int remove(Node node){
        if  (node == null) throw new IllegalArgumentException();

        if  (node.isLeaf()) caseOne(node);

        else if (node.hasOnlyLeftChild() || node.hasOnlyRightChild()) {
            caseTwo(node);
        }
    }

    private void caseTwo(Node node){
        if (node == null) throw new IllegalArgumentException();

        if (node.hasOnlyLeftChild()) {
            node.left.parent = node.parent;

            if (node.parent.value > node.value) {
                node.parent.left = node.left;

            } else{
                node.parent.rigth = node.left;
            }
            
        } else{
            node.rigth.parent = node.parent;

            if (node.parent.value > node.value) {
                node.parent.left = node.rigth;

            } else{
                node.parent.rigth = node.rigth;
            }
        }
    }

    private void caseOne(Node node){
        if (node == null) throw new IllegalArgumentException();

        if (node.parent.value > node.value) {
            node.parent.left = null;

        } else{
            node.parent.rigth = null;
        }
    }

    public boolean equals(BST bst){
        if (bst.size != this.size) return false;

        return compare(root, bst.getRoot());
    }

    private boolean compare(Node current1, Node current2){

        if (current1 != null && current2 != null) {

            if (current1.value != current2.value) {
                return false;
            }

            compare(current1.left, current2.left);
            compare(current1.rigth, current2.rigth);

        } else if (current1 == null && current2 == null) {
            return true;
        }

        return false;
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

    public boolean hasOnlyLeftChild(){
        return (this.left != null && this.rigth == null);
    }

    public boolean hasOnlyRightChild(){
        return (this.left == null && this.rigth != null);
    }

}
    
}
