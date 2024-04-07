package edsonaldoradin;
import java.util.Random;
public class ListaAleatorio {
    // Método para generar una lista de números aleatorios
    public static int[] generateRandomList(int size) {
        Random random = new Random();
        int[] list = new int[size];
        for (int i = 0; i < size; i++) {
            // Generar números aleatorios entre -100000 y 100000
            list[i] = random.nextInt(200001) - 100000;
        }
        return list;
    }
}
