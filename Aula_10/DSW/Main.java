import java.util.Random;

class Main{
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Random random = new Random();

        for(int i=0;i<100;i++){
            int num = random.nextInt(101);
            arvore.inserir(num);
        }

        System.out.println("Impressão sem balanceamento DSW:");
        arvore.impressao();
        System.out.println("\n");

        arvore.balancearDSW();

        System.out.println("Impressão após balanceamento DSW:");
        arvore.impressao();
        System.out.println("\n");

        for(int i=0;i<20;i++){
            int num = random.nextInt(101);
            arvore.inserir(num);
        }

        System.out.println("Impressão sem balanceamento DSW:");
        arvore.impressao();
        System.out.println("\n");

        arvore.balancearDSW();

        System.out.println("Impressão após balanceamento DSW:");
        arvore.impressao();
    }
}