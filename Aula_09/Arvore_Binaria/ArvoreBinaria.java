import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria(){
        this.raiz = null;
    }

    void inserir(int valor){
        this.raiz = inserirNo(this.raiz, valor);
    }

    private No inserirNo(No no, int valor){
        if (no == null) {
            return new No(valor);
        }else if(valor < no.getValor()){
            no.setEsquerda(inserirNo(no.getEsquerda(), valor));
        }else if(valor > no.getValor()){
            no.setDireita(inserirNo(no.getDireita(), valor));
        }
        return no;
    }

    boolean pesquisar(int valor){
        return pesquisarNo(this.raiz, valor);
    }

    private boolean pesquisarNo(No no, int valor){
        if(no == null){
            return false;
        }else if (valor == no.getValor()){
            return true;
        }else if (valor < no.getValor()){
            return pesquisarNo(no.getEsquerda(), valor);
        }else{
            return pesquisarNo(no.getDireita(), valor);
        }
    }

    void remover(int valor){
        this.raiz = removerNo(this.raiz, valor);
    }

    private No removerNo(No no, int valor){
        if(no == null){
            return no;
        }if(valor < no.getValor()){
            no.setEsquerda(removerNo(no.getEsquerda(), valor));
        }else if(valor > no.getValor()){
            no.setDireita(removerNo(no.getDireita(), valor));
        }else if (no.getEsquerda() == null) {
                No temp = no.getDireita();
                no = null;
                return temp;
        }else if(no.getDireita() == null){
            No temp = no.getEsquerda();
            no = null;
            return temp;
        }else{
            No temp = minimoValorNo(no.getDireita());
            no.valor = temp.getValor();
            no.setDireita(removerNo(no.getDireita(), temp.getValor()));
        }
        return no;
    }

    private No minimoValorNo(No no){
        No atual = no;
        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }
        return atual;
    }

    void imprimirEmOrdem(){
        imprimirEmOrdemNo(this.raiz);
    }

    private void imprimirEmOrdemNo(No no){
        if(no != null){
            imprimirEmOrdemNo(no.getEsquerda());
            System.out.print(no.getValor()+" ");
            imprimirEmOrdemNo(no.getDireita());
        }
    }

    void imprimirPreOrdem(){
        imprimirPreOrdemNo(this.raiz);
    }

    private void imprimirPreOrdemNo(No no){
        if(no != null){
            System.out.print(no.getValor()+" ");
            imprimirPreOrdemNo(no.getEsquerda());
            imprimirPreOrdemNo(no.getDireita());
        }
    }

    void imprimirPosOrdem(){
        imprimirPosOrdemNo(this.raiz);
    }

    private void imprimirPosOrdemNo(No no){
        if(no != null){
            imprimirPosOrdemNo(no.getDireita());
            imprimirPosOrdemNo(no.getEsquerda());
            System.out.print(no.getValor()+" ");
        }
    }

    void imprimirEmNivel(){
        imprimirEmNivelNo(this.raiz);
    }

    private void imprimirEmNivelNo(No no){
        if(no != null){
            Queue<No> fila = new LinkedList<>();

            fila.add(this.raiz);

            while (!fila.isEmpty()) {
                No tempNo = fila.poll();

                System.out.print(tempNo.getValor()+" ");

                if (tempNo.getEsquerda() != null) {
                    fila.add(tempNo.getEsquerda());
                }
                if (tempNo.getDireita() != null) {
                    fila.add(tempNo.getDireita());
                }
            }
        }
    }
}