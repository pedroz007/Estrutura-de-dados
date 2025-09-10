public class AVL {
    private Node root;
    private int size;

    public AVL(){
        this.size = 0;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void add(int value){
        Node newNode = new Node(value);

        if (isEmpty()) this.root = newNode;

        this.size++;

        Node aux = this.root;

        while (aux != null) {
            if (aux.value < value) {
                if (aux.right == null) {
                    aux.right = newNode;
                    newNode.parent = aux;

                    checkBranch(newNode);
                }
                aux = aux.right;
                
            } else{
                if (aux.left == null) {
                    aux.left = newNode;
                    newNode.parent = aux;

                    checkBranch(newNode);
                }

                aux = aux.left;
            }
            
        }
    }

    public Node checkBranch(Node node){
        Node aux = node;

        while (aux != null) {
            if (Math.abs(aux.balance()) > 1) return aux;

            if (aux.parent == null) break;

            aux = aux.parent;
        }

        return null;
    }


class Node{
    int height;
    int value;

    Node parent;
    Node left;
    Node right;
    
    public Node(int value){
        this.value = value;
        this.height = 0;
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
        int left = this.left == null ? -1 : this.left.height();
        int right = this.right == null ? -1 : this.right.height();

        return (left - right) >= 1;
    }

    public boolean isRightPending(){
        int left = this.left == null ? -1 : this.left.height();
        int right = this.right == null ? -1 : this.right.height();

        return (left - right) <= -1;
    }

     public boolean isBalanced(){
        int left = this.left == null ? -1 : this.left.height();
        int right = this.right == null ? -1 : this.right.height();

        int balance = left - right;

        return balance <= 1 && balance >= -1;
    }
} 
}
