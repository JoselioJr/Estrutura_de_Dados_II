import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Random random = new Random();

        int[] vetor = new int[20];

        for(int i=0;i<20;i++){
            vetor[i] = random.nextInt(Integer.MAX_VALUE);
            arvore.inserir(vetor[i]);
        }
        
        System.out.println("Impressão Pré-Ordem:");
        arvore.imprimirPreOrdem();
        System.out.println("\n");
        System.out.println("Impressão Ordem Crescente:");
        arvore.imprimirEmOrdem();
        System.out.println("\n");
        System.out.println("Impressão Pós-Ordem:");
        arvore.imprimirPosOrdem();
        System.out.println("\n");
        System.out.println("Impressão Em Nível:");
        arvore.imprimirEmNivel();
        System.out.println("\n");

        System.out.println("Removendo os números: ");

        for(int i=0;i<5;i++){
            int posicao = random.nextInt(20-i);

            System.out.print(vetor[posicao]+" ");
            arvore.remover(vetor[posicao]);
        }
        
        System.out.println("\n");
        System.out.println("--------------------Impressões após a retirada--------------------");
        System.out.println("Impressão Pré-Ordem:");
        arvore.imprimirPreOrdem();
        System.out.println("\n");
        System.out.println("Impressão Ordem Crescente:");
        arvore.imprimirEmOrdem();
        System.out.println("\n");
        System.out.println("Impressão Pós-Ordem:");
        arvore.imprimirPosOrdem();
        System.out.println("\n");
        System.out.println("Impressão Em Nível:");
        arvore.imprimirEmNivel();
    }
}