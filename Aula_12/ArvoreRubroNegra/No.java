class No {
    int data;
    No pai;
    No esquerda;
    No direita;
    int cor;

    public No(int data) {
        this.data = data;
        esquerda = direita = pai = null;
        this.cor = 1;
    }
}