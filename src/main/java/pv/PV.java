package pv;

public class PV {
    private int size;
    private Node root;

    public void checkCase(Node node){
    
        if(case1(node)) {
            node.isBlack = true;
            this.root = node;
        }

        else if(case2(node)){

        }

        else if (case3(node)){
            Node pai = node.parent;
            Node avo = pai.parent;
            Node tio = avo.value > node.value ? avo.right : avo.left;

            pai.isBlack = true;
            tio.isBlack = true;
            avo.isBlack = false;
            checkCase(avo);     
        }

        else if (case4(node)){

            if (node.parent.value < node.value) {
                rotateLeft(node.parent);
                case5(node);

            } else{
                rotateRight(node.parent);
                case5(node);
            }
        }

        else case5(node);
    }

    private boolean case1(Node node){
        return node.parent == null;
    }

    private boolean case2(Node node){
        return node.parent.isBlack;
    }

    private boolean case3(Node node){
        Node avo = node.parent.parent;
        Node tio = avo.value > node.value ? avo.right : avo.left;

        if(tio == null) return false;

        return !node.parent.isBlack && !tio.isBlack;
    }

    private boolean case4(Node node){
        Node avo = node.parent.parent;
        Node tio = avo.value > node.value ? avo.right : avo.left;

        if(tio != null && !tio.isBlack) throw new IllegalArgumentException();

        boolean zig = (avo.value > node.value) && (node.parent.left == null);
        boolean zag = (avo.value < node.value) && (node.parent.right == null);

        return zig || zag;

    }

    private void case5(Node node){
        Node avo = node.parent.parent;

        if (avo.value > node.value) {
            rotateRight(avo);
        } else{
            rotateLeft(avo);
        }

        avo.isBlack = false;
        node.parent.isBlack = true;
    }

    private void rotateRight(Node node){
        Node newRoot = node.left;
        newRoot.parent = node.parent;

        node.left = newRoot.right;
        newRoot.right = node;

        node.parent = newRoot;

        if (newRoot.parent != null) {
            if (newRoot.parent.value > newRoot.value) {
                newRoot.parent.left = newRoot;

            } else{
                newRoot.parent.right = newRoot;
            }
        } 

        else this.root = newRoot;
    }

    private void rotateLeft(Node node){
        Node newRoot = node.right;
        newRoot.parent = node.parent;

        node.right = newRoot.left;
        newRoot.left = node;

        node.parent = newRoot;

        if (newRoot.parent != null) {
            if (newRoot.parent.value > newRoot.value) {
                newRoot.parent.left = newRoot;

            } else{
                newRoot.parent.right = newRoot;
            }
        } 

        else this.root = newRoot;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

class Node{
    boolean isBlack;
    int value;

    Node parent;
    Node left;
    Node right;

    public Node(int value){
        this.value = value;
        this.isBlack = false;
    }

    public int height(){
         if (this.left == null && this.right == null) return 0;

        else if(this.left != null && this.right == null) return 1 + this.left.height();

        else if(this.left == null && this.right != null) return 1 + this.right.height();

        return 1 + Math.max(this.left.height(), this.right.height());
    }

    public int balance(){
        int left = this.left == null ? -1 : this.left.height();
        int right = this.right == null ? -1 : this.right.height();

        return left - right;
    }

    public boolean isLeftPending(){
        return (this.balance()) >= 1;
    }

    public boolean isRightPending(){
        return (this.balance()) <= -1;
    }
}
}
