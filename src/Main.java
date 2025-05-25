import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        GenerateData data = new GenerateData();

        //Benchmark por Ordenamiento
        //benchmarkOrdenamiento(data);

        //Benchmark por Búsqueda
        benchmarkBusqueda(data);
    }

    public static void benchmarkOrdenamiento(GenerateData data) {
        ArrayList<Game> datos100 = data.generateRandomGames(100);
        ArrayList<Game> datos10k = data.generateRandomGames(10000);
        ArrayList<Game> datos1M = data.generateRandomGames(1000000);

        String[] algoritmos = {"bubbleSort", "insertionSort", "selectionSort", "mergeSort", "quickSort", "collectionsSort"};
        String[] atributos = {"price", "category", "quality"};

        for (String atributo : atributos) {
            System.out.println("Tabla para atributo: " + atributo);

            for (String algoritmo : algoritmos) {
                medirOrdenamiento(datos100, algoritmo, atributo, 100);
                medirOrdenamiento(datos10k, algoritmo, atributo, 10000);
                if (!algoritmo.equals("bubbleSort") && !algoritmo.equals("insertionSort")) {
                    medirOrdenamiento(datos1M, algoritmo, atributo, 1000000);
                }
            }
        }
    }

    private static void medirOrdenamiento(ArrayList<Game> datos, String algoritmo, String atributo, int tamaño) {
        Dataset dataset = new Dataset(new ArrayList<>(datos));
        long inicio = System.currentTimeMillis();
        dataset.sortByAlgorithm(algoritmo, atributo);
        long fin = System.currentTimeMillis();
        System.out.printf("Algoritmo -> " + algoritmo + " | Tamaño -> " + tamaño + " | Tiempo -> " + (fin - inicio) + "ms\n");
    }

    public static void benchmarkBusqueda(GenerateData data) {
        Dataset dataset = new Dataset(data.generateRandomGames(1000000));
        dataset.sortByAlgorithm("quickSort", "price");
        // Medir búsqueda por precio (existente)
        int precioBuscado = dataset.data.get(data.random.nextInt(1000000)).getPrice();
        medirBusqueda("getGamesByPrice", dataset, precioBuscado, 0);

        // Medir búsqueda por rango de precio (existente)
        medirBusqueda("getGamesByPriceRange", dataset, 0, 70000);

        // Medir búsqueda por categoría (existente)
        String categoriaBuscada = dataset.data.get(data.random.nextInt(1000000)).getCategory();
        medirBusqueda("getGamesByCategory", dataset, categoriaBuscada, 0);

        // Medir búsqueda por categoría (existente)
        int calidadBuscada = dataset.data.get(data.random.nextInt(1000000)).getQuality();
        medirBusqueda("getGamesByQuality", dataset, calidadBuscada, 0);
    }

    public static void medirBusqueda(String metodo, Dataset dataset, Object valor, Object valor2) {
        long inicio, fin;

        // Búsqueda lineal
        // Marcar como no ordenado
        inicio = System.currentTimeMillis();
        switch (metodo) {
            case "getGamesByPrice":
                dataset.getGamesByPrice((Integer) valor);
                break;
            case "getGamesByPriceRange":
                dataset.getGamesByPriceRange((Integer) valor, (Integer) valor2);
                break;
            case "getGamesByCategory":
                dataset.getGamesByCategory((String) valor);
                break;
            case "getGamesByQuality":
                dataset.getGamesByQuality((Integer) valor);
                break;
        }
        fin = System.currentTimeMillis();
        System.out.printf(metodo + " (busqueda lineal): " + (fin - inicio) + "ms\n");

        // Búsqueda lineal
        Collections.shuffle(dataset.data);
        dataset.sortedByAttribute = "";
        inicio = System.currentTimeMillis();
        switch (metodo) {
            case "getGamesByPrice":
                dataset.getGamesByPrice((Integer) valor);
                break;
            case "getGamesByPriceRange":
                dataset.getGamesByPriceRange((Integer) valor, (Integer) valor2);
                break;
            case "getGamesByCategory":
                dataset.getGamesByCategory((String) valor);
                break;
            case "getGamesByQuality":
                dataset.getGamesByQuality((Integer) valor);
                break;
        }
        fin = System.currentTimeMillis();
        System.out.printf(metodo + " (busqueda binaria): " + (fin - inicio) + "ms\n");
    }
}