package edsonaldoradin;

public class Main {

    public static void main(String[] args) {
        // Tamaños de las listas a probar
        int[] listSizes = {1000, 10000, 100000, 1000000};

        // Iterar sobre los tamaños de las listas
        for (int size : listSizes) {
            // Realizar las pruebas para el tamaño actual de la lista
            TestSort.testSort(size);
        }
    }

}