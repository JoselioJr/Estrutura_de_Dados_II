import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class HeapSort {
    private static void calcHeap(int[] array, int n, int i){
        int raiz = i;
        int esquerda = 2*i + 1;
        int direita = 2*i + 2;
        
        if (esquerda < n && array[esquerda] > array[raiz]){
            raiz = esquerda;
        }
        if (direita < n && array[direita] > array[raiz]){
            raiz = direita;
        }
        
        if (raiz != i){
            int aux = array[i];
            array[i] = array[raiz];
            array[raiz] = aux;
            
            calcHeap(array, n, raiz);
        }
    }

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

        int[] array = lerArquivo("src/dados500_mil.txt");

        if (array != null) {
            Long opInicio = System.currentTimeMillis();
            int n = array.length;
        
            for(int i = n / 2 - 1; i >= 0; i--){
                calcHeap(array, n, i);
            }
            
            for(int i = n-1; i > 0; i--){
                int aux = array[0];
                array[0] = array[i];
                array[i] = aux;
                
                calcHeap(array, i, 0);
            }
            
            for(int i = 0; i < array.length; i++){
                System.out.print(array[i] + " ");
            }

            Long opFim = System.currentTimeMillis();
            calcTempo(opFim - opInicio);
        } else {
            System.out.println("Array n\u00e3o encontrado");
        }
    } 
}