import java.util.ArrayList;
import java.util.*;

public class Dataset {
    ArrayList<Game> data;
    String sortedByAttribute;

    Dataset(ArrayList<Game> data) {
        this.data = data;
    }

    public ArrayList<Game> getGamesByPrice(int price){
        ArrayList<Game> games = new ArrayList<Game>();
        int left = 0, right = data.size() - 1, middle = 0;
        boolean found = false;

        if (sortedByAttribute.equals("price")) {
            while (left <= right) { //busqueda binaria
                middle = (left + right) / 2;

                if (data.get(middle).getPrice() == price) {
                    found = true;
                    break;
                } else if (data.get(middle).getPrice() > price) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }

            if (!found) { //comprobar que el precio se encuentra
                return games;
            }

            int index = middle;

            while (index >= 0 && data.get(index).getPrice() == price) { //recorre hasta la izquierda pa encontrar la primera posicion
                index--;
            }

            index++;

            while (index < data.size() && data.get(index).getPrice() == price) { //agrega juegos desde middle hacia la derecha
                games.add(data.get(index));
                index++;
            }

            return games;

        }else {
            for (Game game: data) { //agregar juegos en orden lineal
                if (game.getPrice() == price) {
                    games.add(game);
                }
            }

            return games;
        }

    }

    public ArrayList<Game> getGamesByPriceRange(int lowerPrice, int highPrice){
        ArrayList<Game> games = new ArrayList<>();

        if (sortedByAttribute.equals("price")){
            int left = 0, right = data.size() - 1, middle, start = -1;

            while (left <= right) {
                middle = (left + right) / 2;
                if(data.get(middle).getPrice() >= lowerPrice){
                    start = middle;
                    right = middle -1;

                }else{
                    left = middle + 1;
                }
            }

            left = 0; right = data.size()-1;
            int End = -1;

            while(left <=right){
                middle = (left + right) / 2;

                if(data.get(middle).getPrice() <= highPrice){
                    End = middle;
                    left = middle +1;

                }else{
                    right = middle-1;
                }
            }

            if(start == -1 || End == -1 ){
                return games;
            }

            for(int i = start; i <= End; i++){
                games.add(data.get(i));
            }
            return games;

        }else{

            for(Game info: data){
                if(info.getPrice()>= lowerPrice && info.getPrice() <= highPrice){
                    games.add(info);
                }
            }
            return games;
        }

    }

    public ArrayList<Game> getGamesByCategory(String category){
        ArrayList<Game> games = new ArrayList<>();

        if (sortedByAttribute.equals("category")) {
            int left = 0, right = data.size() - 1, middle = 0, index = 0;
            boolean found = false;

            while(left<=right){
                middle= (left + right)/2;
                String nameCopy = data.get(middle).getCategory();

                index = nameCopy.compareTo(category);

                if(index ==0){
                    found = true;
                    break;
                } else if (index < 0){
                    left = middle +1;

                } else {
                    right = middle -1;
                }

            }

            if(!found){
                return games;
            }

            int aux = middle;

            while(aux >= 0 && data.get(aux).getCategory().equals(category)) {
                aux--;
            }
            aux++;

            while(aux < data.size() && data.get(aux).getCategory().equals(category)){
                games.add(this.data.get(aux));
                aux++;
            }
            return games;

        }else{
            for(Game info : data){
                if(info.getCategory().equals(category)){
                    games.add(info);
                }
            }
            return games;
        }
    }

    public ArrayList<Game> getGamesByQuality(int quality){
        ArrayList<Game> games = new ArrayList<>();

        if (sortedByAttribute.equals("quality")){
            int left = 0, right = data.size() - 1, middle =0;
            boolean found = false;

            while(left <= right){
                middle = (left + right)/2;
                if(data.get(middle).getQuality() == quality){
                    found = true;
                    break;
                }else if(data.get(middle).getQuality() > quality){
                    right = middle - 1;
                }else{
                    left = middle + 1;
                }

            }

            if(!found){
                return games;
            }

            int index = middle;
            while(index>=0 && data.get(index).getQuality() == quality){
                index--;
            }
            index++;

            while(index<data.size() && data.get(index).getQuality() == quality){
                games.add(this.data.get(index));
                index++;
            }

            return games;


        }else{
            for(Game info: data){
                if(info.getQuality() == quality){
                    games.add(info);
                }

            }
            return games;
        }


    }

    public void sortByAlgorithm(String algorithm, String attribute) {
        String validAttribute = Arrays.asList("price", "category", "quality").contains(attribute)
                ? attribute : "price";

        switch (algorithm) {
            case "bubbleSort":
                SortingAlgorithms.bubbleSort(data, validAttribute);
                break;
            case "insertionSort":
                SortingAlgorithms.insertionSort(data, validAttribute);
                break;
            case "selectionSort":
                SortingAlgorithms.selectionSort(data, validAttribute);
                break;
            case "mergeSort":
                SortingAlgorithms.mergeSort(data, validAttribute, 0, data.size() - 1);
                break;
            case "quickSort":
                SortingAlgorithms.quickSort(data, validAttribute, 0, data.size() - 1);
                break;
            default: // Ordenamiento usando Collections.sort()
                Comparator<Game> comparator;
                switch (validAttribute) {
                    case "category":
                        comparator = Comparator.comparing(Game::getCategory);
                        break;
                    case "quality":
                        comparator = Comparator.comparingInt(Game::getQuality);
                        break;
                    default: // "price" por defecto
                        comparator = Comparator.comparingInt(Game::getPrice);
                }
                Collections.sort(data, comparator);
        }
        sortedByAttribute = validAttribute;
    }
}
