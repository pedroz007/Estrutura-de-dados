package Heap;

public class Heap {
    private int[] Heap;
    private int size;

    public Heap(int[] array){
        this.Heap = array;
        this.size = array.length;
        this.buildHeap();
    }

    private void buildHeap(){
        for(int i = parent(this.size - 1); i >= 0; i--){
            heapifyDow(i);
        }
    }

    private boolean isFull(){
        return this.size == this.Heap.length;
    }

    private int parent(int index){
        return (index - 1 ) / 2;
    }

    private int left(int index){
        return (2 * index) + 1;
    }

    private int right(int index){
        return (2 * index) + 2;
    }

    private int max(int index, int left, int right){
        int max = this.Heap[index];

        if (max < this.Heap[left] && max > this.Heap[right]) {
            return left;
        }

        if (max > this.Heap[left] && max < this.Heap[right]) {
            return right;
        }

        return index;

    }

    private boolean isEmpty(){
        return this.size == 0;
    }

    private boolean isLeaf(int index){
        return (2 * index) + 1 >= size && index < size;
    }

    private void heapifyDow(int index){
        if (isLeaf(index) || isInvalidIndex(index)) {
            throw new IllegalArgumentException();
        }

        int indexMax = max(index, left(index), right(index));

        if (indexMax != index) {
            swaap(index, indexMax);
            heapifyDow(indexMax);
        }

    }

    private boolean isInvalidIndex(int index){
        return index < 0 || index >= size;
    }

    public int extractMax(){

        if (isEmpty()) {
            throw new IllegalArgumentException("HEAP VÁZIO");
        }

        int max = this.Heap[0];
        this.Heap[0] = this.Heap[size-1];
        this.size -= 1;

        heapifyDow(0);

        return max;
    }

    public void add(int value){
        if (isFull()) {
            return;
        }

        this.Heap[size] = value;
        int i = this.size;

        this.size += 1;

        while (i > 0 && parent(i) < this.Heap[i]) {
            swaap(i, parent(i));
            
            i = parent(i);
        }

    }

    private void swaap(int i, int j){
        int aux = this.Heap[i];
        this.Heap[i] = this.Heap[j];
        this.Heap[j] = aux;
    }
}
