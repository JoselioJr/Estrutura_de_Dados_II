class ArvoreRubroNegro {
    private No raiz;
    private No folha;

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    public No getFolha() {
        return folha;
    }

    public void setFolha(No folha) {
        this.folha = folha;
    }

    public ArvoreRubroNegro() {
        folha = new No(0);
        folha.cor = 0;
        folha.esquerda = null;
        folha.direita = null;
        raiz = folha;
    }

    public void preOrdem() {
        imprimirPreOrdem(this.raiz);
    }

    private void imprimirPreOrdem(No no) {
        if (no != folha) {
            System.out.print(no.data + " ");
            imprimirPreOrdem(no.esquerda);
            imprimirPreOrdem(no.direita);
        }
    }

    private void rotacaoEsquerda(No x) {
        No y = x.direita;
        x.direita = y.esquerda;
        if (y.esquerda != folha) {
            y.esquerda.pai = x;
        }
        y.pai = x.pai;
        if (x.pai == null) {
            this.raiz = y;
        } else if (x == x.pai.esquerda) {
            x.pai.esquerda = y;
        } else {
            x.pai.direita = y;
        }
        y.esquerda = x;
        x.pai = y;
    }

    private void rotacaoDireita(No x) {
        No y = x.esquerda;
        x.esquerda = y.direita;
        if (y.direita != folha) {
            y.direita.pai = x;
        }
        y.pai = x.pai;
        if (x.pai == null) {
            this.raiz = y;
        } else if (x == x.pai.direita) {
            x.pai.direita = y;
        } else {
            x.pai.esquerda = y;
        }
        y.direita = x;
        x.pai = y;
    }

    public void inserir(int chave) {
        No no = new No(chave);
        no.pai = null;
        no.data = chave;
        no.esquerda = folha;
        no.direita = folha;
        no.cor = 1;

        No y = null;
        No x = this.raiz;

        while (x != folha) {
            y = x;
            if (no.data < x.data) {
                x = x.esquerda;
            } else {
                x = x.direita;
            }
        }

        no.pai = y;
        if (y == null) {
            raiz = no;
        } else if (no.data < y.data) {
            y.esquerda = no;
        } else {
            y.direita = no;
        }

        if (no.pai == null) {
            no.cor = 0;
            return;
        }

        if (no.pai.pai == null) {
            return;
        }

        fixarInsercao(no);
    }

    private void fixarInsercao(No k) {
        No u;
        while (k.pai.cor == 1) {
            if (k.pai == k.pai.pai.direita) {
                u = k.pai.pai.esquerda;
                if (u.cor == 1) {
                    u.cor = 0;
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.esquerda) {
                        k = k.pai;
                        rotacaoDireita(k);
                    }
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    rotacaoEsquerda(k.pai.pai);
                }
            } else {
                u = k.pai.pai.direita;
                if (u.cor == 1) {
                    u.cor = 0;
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    k = k.pai.pai;
                } else {
                    if (k == k.pai.direita) {
                        k = k.pai;
                        rotacaoEsquerda(k);
                    }
                    k.pai.cor = 0;
                    k.pai.pai.cor = 1;
                    rotacaoDireita(k.pai.pai);
                }
            }
            if (k == raiz) {
                break;
            }
        }
        raiz.cor = 0;
    }

    private void troca(No u, No v) {
        if (u.pai == null) {
            raiz = v;
        } else if (u == u.pai.esquerda) {
            u.pai.esquerda = v;
        } else {
            u.pai.direita = v;
        }
        v.pai = u.pai;
    }

    public void remover(int data) {
        reajustaRemocao(this.raiz, data);
    }

    private void reajustaRemocao(No no, int chave) {
        No z = folha;
        No x, y;
        while (no != folha) {
            if (no.data == chave) {
                z = no;
            }

            if (no.data <= chave) {
                no = no.direita;
            } else {
                no = no.esquerda;
            }
        }

        if (z == folha) {
            System.out.println("Não foi possível encontrar o valor na árvore");
            return;
        }

        y = z;
        int corOriginal = y.cor;
        if (z.esquerda == folha) {
            x = z.direita;
            troca(z, z.direita);
        } else if (z.direita == folha) {
            x = z.esquerda;
            troca(z, z.esquerda);
        } else {
            y = menorValor(z.direita);
            corOriginal = y.cor;
            x = y.direita;
            if (y.pai == z) {
                x.pai = y;
            } else {
                troca(y, y.direita);
                y.direita = z.direita;
                y.direita.pai = y;
            }

            troca(z, y);
            y.esquerda = z.esquerda;
            y.esquerda.pai = y;
            y.cor = z.cor;
        }

        if (corOriginal == 0) {
            fixarRemocao(x);
        }
    }

    private void fixarRemocao(No x) {
        No s;
        while (x != raiz && x.cor == 0) {
            if (x == x.pai.esquerda) {
                s = x.pai.direita;
                if (s.cor == 1) {
                    s.cor = 0;
                    x.pai.cor = 1;
                    rotacaoEsquerda(x.pai);
                    s = x.pai.direita;
                }

                if (s.esquerda.cor == 0 && s.direita.cor == 0) {
                    s.cor = 1;
                    x = x.pai;
                } else {
                    if (s.direita.cor == 0) {
                        s.esquerda.cor = 0;
                        s.cor = 1;
                        rotacaoDireita(s);
                        s = x.pai.direita;
                    }

                    s.cor = x.pai.cor;
                    x.pai.cor = 0;
                    s.direita.cor = 0;
                    rotacaoEsquerda(x.pai);
                    x = raiz;
                }
            } else {
                s = x.pai.esquerda;
                if (s.cor == 1) {
                    s.cor = 0;
                    x.pai.cor = 1;
                    rotacaoDireita(x.pai);
                    s = x.pai.esquerda;
                }

                if (s.direita.cor == 0 && s.direita.cor == 0) {
                    s.cor = 1;
                    x = x.pai;
                } else {
                    if (s.esquerda.cor == 0) {
                        s.direita.cor = 0;
                        s.cor = 1;
                        rotacaoEsquerda(s);
                        s = x.pai.esquerda;
                    }

                    s.cor = x.pai.cor;
                    x.pai.cor = 0;
                    s.esquerda.cor = 0;
                    rotacaoDireita(x.pai);
                    x = raiz;
                }
            }
        }
        x.cor = 0;
    }

    private No menorValor(No no) {
        while (no.esquerda != folha) {
            no = no.esquerda;
        }
        return no;
    }

    public void imprimirArvore() {
        impresao(this.raiz, "", true);
    }

    private void impresao(No raiz, String indent, boolean last) {
        if (raiz != folha) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sCor = raiz.cor == 1 ? "RED" : "BLACK";
            System.out.println(raiz.data + "(" + sCor + ")");
            impresao(raiz.esquerda, indent, false);
            impresao(raiz.direita, indent, true);
        }
    }

    public boolean pesquisa(No no, int valor) {
        if (no == null || no.data == 0) {
            return false;
        }
        if (no.data == valor) {
            return true;
        } else if (valor < no.data) {
            return pesquisa(no.esquerda, valor);
        } else {
            return pesquisa(no.direita, valor);
        }
    }
}