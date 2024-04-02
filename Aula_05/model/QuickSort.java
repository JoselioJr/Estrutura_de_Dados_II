package model;
public class QuickSort{
    public static void main(String[] args){
        int[] lista = {5, 6, 15, 1, 32, 4, 0};

        System.out.println("Lista original(Desordenada): ");
        for(int i=0; i<lista.length;i++){
            System.out.print(+lista[i]+" ");
        }

        quickSort(lista, 0, lista.length-1);
        System.out.println("\nLista ordenada: ");
        printArray(lista);
    }

    public static void quickSort(int[] lista, int menor, int maior){
        if (menor<maior){
            int pi = quebra(lista, menor, maior);

            quickSort(lista, menor, pi-1);
            quickSort(lista, pi+1, maior);
        }
    }

    public static int quebra(int[] lista, int menor, int maior){
        int pivo=lista[maior];
        int memo=(menor-1);

        for (int i=menor;i<maior;i++){
            if (lista[i]<pivo) {
                memo++;

                int temp=lista[memo];
                lista[memo]=lista[i];
                lista[i]=temp;
            }
        }

        int temp=lista[memo+1];
        lista[memo+1]=lista[maior];
        lista[maior]=temp;

        return (memo+1);
    }

    public static void printArray(int[] lista){
        for (int i=0;i<lista.length;i++){
            System.out.print(lista[i]+" ");
        }
        System.out.println();
    }
}