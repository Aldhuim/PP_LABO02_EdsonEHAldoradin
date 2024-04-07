package edsonaldoradin;
import java.util.Arrays;

public class TestSort {
    // Método para realizar el benchmarking del algoritmo de ordenamiento
    public static void testSort(int size) {
        // Generar lista de números aleatorios
        int[] randomList = ListaAleatorio.generateRandomList(size);

        // Medir el tiempo de ejecución del ordenamiento secuencial
        long startTimeSequential = System.currentTimeMillis();
        Arrays.sort(randomList);
        long endTimeSequential = System.currentTimeMillis();
        long sequentialTime = endTimeSequential - startTimeSequential;

        // Crear una copia de la lista aleatoria para ordenamiento paralelo
        int[] parallelList = Arrays.copyOf(randomList, randomList.length);

        // Medir el tiempo de ejecución del ordenamiento paralelo
        long startTimeParallel = System.currentTimeMillis();
        MergeSortForkJoin.parallelSort(parallelList);
        long endTimeParallel = System.currentTimeMillis();
        long parallelTime = endTimeParallel - startTimeParallel;

        // Obtener el número de hilos activos utilizados
        int activeThreads = MergeSortForkJoin.getActiveThreads();

        // Calcular aceleración y eficiencia
        double acceleration = (double) sequentialTime / parallelTime;
        double efficiency = acceleration / activeThreads;

        // Imprimir los resultados
        System.out.printf("Tamaño de la lista: %d, Tiempo secuencial: %d ms, Tiempo paralelo: %d ms, Hilos activos: %d, Aceleración: %.2f, Eficiencia: %.2f%% %n",
                size, sequentialTime, parallelTime, activeThreads, acceleration, efficiency * 100);
    }
}
