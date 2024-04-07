package edsonaldoradin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
public class MergeSortForkJoin {
    // Pool compartido para reutilizar hilos
    private static final ForkJoinPool pool = new ForkJoinPool();

    // Método para ordenar la lista de manera paralela
    public static void parallelSort(int[] array) {
        // Iniciar la tarea de ordenamiento paralelo
        pool.invoke(new MergeSortForkJoinTask(array, 0, array.length));
    }

    // Método para obtener el número de hilos activos utilizados
    public static int getActiveThreads() {
        return ForkJoinPool.commonPool().getParallelism();
    }

    // Clase para la tarea de ordenamiento paralelo
    private static class MergeSortForkJoinTask extends RecursiveAction {
        // Umbral para la división de tareas
        private static final int THRESHOLD = 10000;
        private final int[] array;
        private final int start;
        private final int end;

        // Constructor de la tarea
        public MergeSortForkJoinTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        // Método para realizar el ordenamiento paralelo
        @Override
        protected void compute() {
            // Verificar si el tamaño de la sub-lista es menor o igual al umbral
            if (end - start <= THRESHOLD) {
                // Ordenamiento secuencial si el tamaño de la sub-lista es menor o igual al umbral
                Arrays.sort(array, start, end);
            } else {
                // Dividir la tarea en dos sub-tareas
                int mid = start + (end - start) / 2;
                MergeSortForkJoinTask leftTask = new MergeSortForkJoinTask(array, start, mid);
                MergeSortForkJoinTask rightTask = new MergeSortForkJoinTask(array, mid, end);
                // Invocar ambas sub-tareas de manera paralela
                invokeAll(leftTask, rightTask);
                // Fusionar los resultados de las sub-tareas
                merge(mid);
            }
        }

        // Método para fusionar dos sub-listas ordenadas
        private void merge(int mid) {
            // Verificar si las sub-listas ya están ordenadas
            if (array[mid - 1] <= array[mid]) {
                return; // No se necesita fusión
            }
            // Crear un arreglo temporal para almacenar la fusión
            int[] temp = new int[end - start];
            int i = start;
            int j = mid;
            int k = 0;
            // Fusionar las sub-listas ordenadas en el arreglo temporal
            while (i < mid && j < end) {
                temp[k++] = array[i] <= array[j] ? array[i++] : array[j++];
            }
            // Copiar los elementos restantes de la primera sub-lista, si es que quedan
            System.arraycopy(array, i, array, start + k, mid - i);
            // Copiar los elementos del arreglo temporal de vuelta al arreglo original
            System.arraycopy(temp, 0, array, start, k);
        }
    }

}
