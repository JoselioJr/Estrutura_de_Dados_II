import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random sorteio = new Random();
        AvoreAVL arvore = new AvoreAVL();
        ArrayList<Integer> numeros = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            int numero = sorteio.nextInt(1001) - 500;
            arvore.inserir(numero);
            numeros.add(numero);
        }

        System.out.println("Árvore após inserções:");
        if (arvore.isAVL()) {
            System.out.println("A árvore é AVL.");
        } else {
            System.out.println("A árvore NÃO é AVL.");
        }
        arvore.imprimirFatorBalanceamento();

        for (int i = 0; i < 20; i++) {
            int numero = numeros.get(sorteio.nextInt(numeros.size()));
            arvore.remover(numero);
            numeros.remove((Integer) numero);
        }

        System.out.println("\nÁrvore após remoções:");
        if (arvore.isAVL()) {
            System.out.println("A árvore é AVL.");
        } else {
            System.out.println("A árvore NÃO é AVL.");
        }
        arvore.imprimirFatorBalanceamento();
    }
}