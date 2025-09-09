package estruturaslineares.pilhas;

public class Pilha {

    private int topo;
    private int[] pilha;
    public static final int TAMANHO_DEFULT = 15;

    public Pilha(int tamanho){
        this.pilha = new int[tamanho];
        this.topo = -1;
    }

    public Pilha(){
        this(TAMANHO_DEFULT);
    }

    
    
}
