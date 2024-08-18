class AvoreAVL {
    private No raiz;

    private int altura(No N) {
        if (N == null)
            return 0;
        return N.getAltura();
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private No rotacaoLL(No A) {
        No B = A.getFilhoEsquerda();
        A.setFilhoEsquerda(B.getFilhoDireita());
        B.setFilhoDireita(A);

        A.setAltura(max(altura(A.getFilhoEsquerda()), altura(A.getFilhoDireita())) + 1);
        B.setAltura(max(altura(B.getFilhoEsquerda()), altura(B.getFilhoDireita())) + 1);

        return B;
    }

    private No rotacaoRR(No A) {
        No B = A.getFilhoDireita();
        A.setFilhoDireita(B.getFilhoEsquerda());
        B.setFilhoEsquerda(A);

        A.setAltura(max(altura(A.getFilhoEsquerda()), altura(A.getFilhoDireita())) + 1);
        B.setAltura(max(altura(B.getFilhoEsquerda()), altura(B.getFilhoDireita())) + 1);;

        return B;
    }

    private No rotacaoLR(No no) {
        no.setFilhoEsquerda(rotacaoRR(no.getFilhoEsquerda()));
        return rotacaoLL(no);
    }

    private No rotacaoRL(No no) {
        no.setFilhoDireita(rotacaoLL(no.getFilhoDireita()));
        return rotacaoRR(no);
    }

    private int getBalance(No N) {
        if (N == null)
            return 0;
        return altura(N.getFilhoEsquerda()) - altura(N.getFilhoDireita());
    }

    public No inserir(No no, int valor) {
        if (no == null)
            return (new No(valor));

        if (valor < no.getValor())
            no.setFilhoEsquerda(inserir(no.getFilhoEsquerda(), valor));
        else if (valor > no.getValor())
            no.setFilhoDireita(inserir(no.getFilhoDireita(), valor));
        else
            return no;

        no.setAltura(1 + max(altura(no.getFilhoEsquerda()), altura(no.getFilhoDireita())));

        int balance = getBalance(no);

        if (balance > 1 && valor < no.getFilhoEsquerda().getValor()){
            return rotacaoLL(no);
        }
           
        if (balance < -1 && valor > no.getFilhoDireita().getValor()){
            return rotacaoRR(no);
        }

        if (balance > 1 && valor > no.getFilhoEsquerda().getValor()) {
            return rotacaoLR(no);
        }

        if (balance < -1 && valor < no.getFilhoDireita().getValor()) {
            return rotacaoRL(no);
        }

        return no;
    }

    public No remover(No no, int valor) {
        if (no == null)
            return no;

        if (valor < no.getValor())
            no.setFilhoEsquerda(remover(no.getFilhoEsquerda(), valor));
        else if (valor > no.getValor())
            no.setFilhoDireita(remover(no.getFilhoDireita(), valor));
        else {
            if ((no.getFilhoEsquerda() == null) || (no.getFilhoDireita() == null)) {
                No temp = null;
                if (temp == no.getFilhoEsquerda())
                    temp = no.getFilhoDireita();
                else
                    temp = no.getFilhoEsquerda();

                if (temp == null) {
                    temp = no;
                    no = null;
                } else
                    no = temp;
            } else {
                No temp = getMinValueNode(no.getFilhoDireita());
                no.setValor(temp.getValor());
                no.setFilhoDireita(remover(no.getFilhoDireita(), temp.getValor()));
            }
        }

        if (no == null)
            return no;

        no.setAltura(max(altura(no.getFilhoEsquerda()), altura(no.getFilhoDireita())) + 1);;

        int balance = getBalance(no);

        if (balance > 1 && getBalance(no.getFilhoEsquerda()) >= 0)
            return rotacaoLL(no);

        if (balance > 1 && getBalance(no.getFilhoEsquerda()) < 0) {
            no.setFilhoEsquerda(rotacaoRR(no.getFilhoEsquerda()));
            return rotacaoLL(no);
        }

        if (balance < -1 && getBalance(no.getFilhoDireita()) <= 0)
            return rotacaoRR(no);

        if (balance < -1 && getBalance(no.getFilhoDireita()) > 0) {
            no.setFilhoDireita(rotacaoLL(no.getFilhoDireita()));
            return rotacaoRR(no);
        }

        return no;
    }

    No getMinValueNode(No node) {
        No current = node;
        while (current.getFilhoEsquerda() != null)
            current = current.getFilhoEsquerda();
        return current;
    }

    public void imprimirFatorBalanceamento(No no) {
        if (no != null) {
            imprimirFatorBalanceamento(no.getFilhoEsquerda());
            System.out.println("Valor: " + no.getValor() + ", Fator de Balanceamento: " + getBalance(no));
            imprimirFatorBalanceamento(no.getFilhoDireita());
        }
    }

    public boolean isAVL(No no) {
        if (no == null) return true;

        int balance = getBalance(no);

        if (balance > 1 || balance < -1)
            return false;

        return isAVL(no.getFilhoEsquerda()) && isAVL(no.getFilhoDireita());
    }

    public void inserir(int valor) {
        raiz = inserir(raiz, valor);
    }

    public void remover(int valor) {
        raiz = remover(raiz, valor);
    }

    public void imprimirFatorBalanceamento() {
        imprimirFatorBalanceamento(raiz);
    }

    public boolean isAVL() {
        return isAVL(raiz);
    }
}