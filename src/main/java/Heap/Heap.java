package Heap;

public class Heap {
    private int[] Heap;
    private int size;

    public Heap(int[] array){
        this.Heap = array;
        this.size = array.length;
        buildHeap(this.Heap);
    }

    private void buildHeap(int[] v){
        for(int i = parent(v.length - 1); i >= 0; i--){
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
            swaap(this.Heap, index, indexMax);
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
            swaap(this.Heap, i, parent(i));
            
            i = parent(i);
        }

    }

    public int[] heapSort(int[] v){
        buildHeap(v);

        for(int i = v.length -1; i > 0; i--){
            swaap(v, i, 0);

            heapify(v, 0, i);
        }

        return v;
    }

    private void heapify(int[] v, int index, int size){
        if(isLeaf(index) || isInvalidIndex(index)) return;

        int max = index;
        int left = left(index);
        int right = right(index);

        if(left < size && v[left] > v[max]){
            max = left;
        }

        if(right < size && v[right] > v[max]){
            max = right;
        }

        if(max != index){
            swaap(v, index, max);
            heapify(v, max, size);
        }
    }

    private void swaap(int[] v , int i, int j){
        int aux = v[i];
        v[i] = v[j];
        v[j] = aux;
    }
}
