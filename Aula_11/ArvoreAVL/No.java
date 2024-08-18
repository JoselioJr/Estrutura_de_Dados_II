class No {
    private int valor;
    private int altura;
    private No filhoEsquerda;
    private No filhoDireita;
    

    public No(int valor, int altura, No filhoEsquerda, No filhoDireita) {
        this.valor = valor;
        this.altura = altura;
        this.filhoEsquerda = filhoEsquerda;
        this.filhoDireita = filhoDireita;
    }
    
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public No getFilhoEsquerda() {
        return filhoEsquerda;
    }

    public void setFilhoEsquerda(No filhoEsquerda) {
        this.filhoEsquerda = filhoEsquerda;
    }

    public No getFilhoDireita() {
        return filhoDireita;
    }

    public void setFilhoDireita(No filhoDireita) {
        this.filhoDireita = filhoDireita;
    }

    No(int valor) {
        this.valor = valor;
        this.altura = 1;
    }
}