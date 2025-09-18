package java.pv;

public class PV {
    private int size;
    private Node root;

    private void checkCase(Node node){
        if(case1(node)) {
            node.isBlack = true;
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

        }

        else{

        }


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

        boolean zig = (avo.value > node.value) && (node.parent.left == null);
        boolean zag = (avo.value < node.value) && (node.parent.right == null);

        return zig && zag;

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
}
}
