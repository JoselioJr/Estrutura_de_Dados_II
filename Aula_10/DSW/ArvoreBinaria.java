public class ArvoreBinaria {
    No raiz;

    ArvoreBinaria() {
        this.raiz = null;
    }

    void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    No inserirRecursivo(No no, int valor) {
        if (no == null) {
            no = new No(valor);
            return no;
        }
        if (valor < no.valor) {
            no.esquerdo = inserirRecursivo(no.esquerdo, valor);
        } else if (valor > no.valor) {
            no.direito = inserirRecursivo(no.direito, valor);
        }
        return no;
    }

    void balancearDSW() {
        criarEspinhaDorsal();
        balancearArvore();
    }

    void criarEspinhaDorsal() {
        No temp = raiz;
        No parent = null;

        while (temp != null) {
            if (temp.esquerdo != null) {
                if (parent == null) {
                    raiz = rotacaoDireita(temp);
                    parent = raiz;
                } else {
                    parent.direito = rotacaoDireita(temp);
                }
                temp = parent.direito;
            } else {
                parent = temp;
                temp = temp.direito;
            }
        }
    }

    No rotacaoDireita(No no) {
        No esquerdo = no.esquerdo;
        no.esquerdo = esquerdo.direito;
        esquerdo.direito = no;
        return esquerdo;
    }

    void balancearArvore() {
        int n = contarNos(raiz);
        int m = (int) Math.pow(2, log2(n + 1)) - 1;
        realizarRotacoes(n - m);
        while (m > 1) {
            m /= 2;
            realizarRotacoes(m);
        }
    }

    int contarNos(No no) {
        if (no == null)
            return 0;
        return 1 + contarNos(no.esquerdo) + contarNos(no.direito);
    }

    void realizarRotacoes(int quantidade) {
        No temp = raiz;
        No parent = null;

        for (int i = 0; i < quantidade; i++) {
            if (temp != null && temp.direito != null) {
                if (parent == null) {
                    raiz = rotacaoEsquerda(temp);
                    parent = raiz;
                } else {
                    parent.direito = rotacaoEsquerda(temp);
                }
                temp = parent.direito;
            } else {
                break;
            }
        }
    }

    No rotacaoEsquerda(No no) {
        No direito = no.direito;
        no.direito = direito.esquerdo;
        direito.esquerdo = no;
        return direito;
    }

    int log2(int n) {
        int log = 0;
        while (n > 1) {
            n /= 2;
            log++;
        }
        return log;
    }

    void impressao() {
        impressaoRec(raiz);
    }

    void impressaoRec(No no) {
        if (no != null) {
            impressaoRec(no.esquerdo);
            System.out.print(no.valor + " ");
            impressaoRec(no.direito);
        }
    }
}