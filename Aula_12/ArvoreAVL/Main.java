import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Random;

public class Main {
    public static int[] lerArquivo(String url) throws IOException {
        Path arquivo = Path.of(url);
        if (Files.notExists(arquivo, new LinkOption[0])) {
            return null;
        } else {
            String valoresString = Files.readString(arquivo);
            String formataValor = valoresString.replace("[", "").replace("]", "").replace(" ", "");
            String[] arrayString = formataValor.split(",");
            int[] arrayInt = new int[arrayString.length];

            for(int i = 0; i < arrayString.length; ++i) {
                arrayInt[i] = Integer.valueOf(arrayString[i]);
            }

            return arrayInt;
        }
    }

    public static void calcTempo(long total) {
        long opMils = total % 1000L;
        total /= 1000L;
        long opHr = total / 3600L;
        total %= 3600L;
        long opMin = total / 60L;
        long opSeg = total % 60L;
        System.out.println("\n\nTempo de execu\u00e7\u00e3o: " + opHr + ":" + opMin + ":" + opSeg + ":" + opMils);
    }
    public static void main(String[] args) throws IOException {
        Random sorteio = new Random();
        AvoreAVL arvore = new AvoreAVL();

        int[] array = lerArquivo("src/dados100_mil.txt");

        if (array != null) {
            Long opInicio = System.currentTimeMillis();

            for(int i=0;i<array.length;i++){
                arvore.inserir(array[i]);
            }

            System.out.println("Árvore após inserções:");
            if (arvore.isAVL()) {
                System.out.println("A árvore é AVL.");
            } else {
                System.out.println("A árvore NÃO é AVL.");
            }
            arvore.imprimirFatorBalanceamento();

            int contar = 0;
            for (int i = 0; i < 50000; i++) {
                int numero = sorteio.nextInt(19999) - 9999;

                if (numero % 3 == 0) {
                    arvore.inserir(numero);
                } else if (numero % 5 == 0) {
                    arvore.remover(numero);
                } else {
                    if (arvore.pesquisar(numero)) {
                        contar++;
                    }
                }
            }

            System.out.println("\nÁrvore após remoções:");
            if (arvore.isAVL()) {
                System.out.println("A árvore é AVL.");
            } else {
                System.out.println("A árvore NÃO é AVL.");
            }
            arvore.imprimirFatorBalanceamento();
            System.out.println("\nNúmeros não múltiplos de 3 ou 5 que aparecem na árvore: "+contar);

            Long opFim = System.currentTimeMillis();
            calcTempo(opFim - opInicio);
        } else {
            System.out.println("Array n\u00e3o encontrado");
        }
    }
}