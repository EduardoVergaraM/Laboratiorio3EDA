import java.util.ArrayList;
import java.util.Collections;

public class SortingAlgorithms {

    public static void bubbleSort(ArrayList<Game> data, String attribute) {
        for (int i = 0; i < data.size() - 1; i++) {
            for (int j = 0; j < data.size() - i - 1; j++) {
                if (compare(data.get(j), data.get(j + 1), attribute) > 0) {
                    Collections.swap(data, j, j + 1);
                }
            }
        }
    }

    public static void insertionSort(ArrayList<Game> data, String attribute) {
        for (int i = 1; i < data.size(); i++) {
            Game key = data.get(i);
            int j = i - 1;
            while (j >= 0 && compare(data.get(j), key, attribute) > 0) {
                data.set(j + 1, data.get(j));
                j--;
            }
            data.set(j + 1, key);
        }
    }

    public static void selectionSort(ArrayList<Game> data, String attribute) {
        for (int i = 0; i < data.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < data.size(); j++) {
                if (compare(data.get(j), data.get(minIdx), attribute) < 0) {
                    minIdx = j;
                }
            }
            Collections.swap(data, i, minIdx);
        }
    }

    public static void mergeSort(ArrayList<Game> data, String attribute, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(data, attribute, left, mid);
            mergeSort(data, attribute, mid + 1, right);
            merge(data, attribute, left, mid, right);
        }
    }

    public static void merge(ArrayList<Game> data, String attribute, int left, int mid, int right) {
        ArrayList<Game> temp = new ArrayList<>();
        int i = left, j = mid + 1;

        while (i <= mid && j <= right) {
            if (compare(data.get(i), data.get(j), attribute) <= 0) {
                temp.add(data.get(i++));
            } else {
                temp.add(data.get(j++));
            }
        }

        while (i <= mid) temp.add(data.get(i++));
        while (j <= right) temp.add(data.get(j++));

        for (int k = 0; k < temp.size(); k++) {
            data.set(left + k, temp.get(k));
        }
    }

    public static void quickSort(ArrayList<Game> data, String attribute, int low, int high) {
        if (low < high && high - low > 0) {
            int partitionIdx = partition(data, attribute, low, high);
            quickSort(data, attribute, low, partitionIdx - 1);
            quickSort(data, attribute, partitionIdx + 1, high);
        }
    }

    public static int partition(ArrayList<Game> data, String attribute, int low, int high) {
        Game pivot = data.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(data.get(j), pivot, attribute) <= 0) {
                i++;
                Collections.swap(data, i, j);
            }
        }
        if (i + 1 <= high) {
            Collections.swap(data, i + 1, high);
        }
        return i + 1;
    }

    public static void countingSort(ArrayList<Game> data) {
        int max = 100;
        ArrayList<ArrayList<Game>> count = new ArrayList<>(max + 1);

        for (int i = 0; i <= max; i++) {
            count.add(new ArrayList<>());
        }

        for (Game game : data) {
            count.get(game.getQuality()).add(game);
        }

        data.clear();
        for (ArrayList<Game> bucket : count) {
            data.addAll(bucket);
        }
    }

    // Método para comparar juegos
    public static int compare(Game a, Game b, String attribute) {
        return switch (attribute) {
            case "price" -> Integer.compare(a.getPrice(), b.getPrice());
            case "category" -> a.getCategory().compareTo(b.getCategory());
            case "quality" -> Integer.compare(a.getQuality(), b.getQuality());
            default -> 0;
        };
    }
}
