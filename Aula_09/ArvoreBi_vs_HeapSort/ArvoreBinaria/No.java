public class No {
    int valor;
    No esquerda;
    No direita;

    public No(int valor){
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }

    public int getValor() {
        return this.valor;
    }

    public No getEsquerda() {
        return this.esquerda;
    }

    public No getDireita() {
        return this.direita;
    }

    public void setEsquerda(No no) {
        this.esquerda = no;
    }

    public void setDireita(No no) {
        this.direita = no;
    }
}