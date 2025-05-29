import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GenerateData {
    String[] palabras;
    String[] categorias;
    Random random;

    public GenerateData() {
        this.palabras = new String[]{"Dragon", "Empire", "Quest", "Galaxy", "Legends", "Warrior",
                "Shadow", "Kingdom", "Adventure", "Hero", "Fantasy", "Chronicle"};
        this.categorias = new String[]{"Acción", "Aventura", "Estrategia", "RPG", "Deportes", "Simulación",
                "FPS", "Plataforma", "Puzzle", "MMORPG", "Carreras", "Battle Royale"};
        this.random = new Random();
    }

    public String generateRandomName() {
        return palabras[random.nextInt(palabras.length)] + palabras[random.nextInt(palabras.length)];
    }

    public String generateRandomCategory() {
        return categorias[random.nextInt(categorias.length)];
    }

    public int generateRandomPrice() {
        return random.nextInt(70001);
    }

    public int generateRandomQuality() {
        return random.nextInt(101);
    }

    public ArrayList<Game> generateRandomGames(int n) {
        ArrayList<Game> games = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = generateRandomName();
            String category = generateRandomCategory();
            int price = generateRandomPrice();
            int quality = generateRandomQuality();
            games.add(new Game(name, category, price, quality));
        }
        return games;
    }

    public static void saveToCSV(ArrayList<Game> games) {
        try {
            FileWriter data = new FileWriter("GenerateData1M.txt");
            data.write("Nombre,Categoria,Precio,Calidad\n");
            for (Game game : games) {
                data.write(String.format("%s,%s,%d,%d\n",
                        game.getName(),
                        game.getCategory(),
                        game.getPrice(),
                        game.getQuality()));
            }
            data.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
       GenerateData generateData = new GenerateData();
       long startTime = System.currentTimeMillis();
       ArrayList<Game> games = generateData.generateRandomGames(1000000);
       long endTime = System.currentTimeMillis();
       long elapsedTime = endTime - startTime;
       System.out.println("Elapsed time: " + elapsedTime + " ms");
       for (Game game : games) {
           System.out.println("-------------------------------");
           System.out.println("Name: " + game.getName());
           System.out.println("Category: " + game.getCategory());
           System.out.println("Price: " + game.getPrice());
           System.out.println("Quality: " + game.getQuality());
       }
       saveToCSV(games);
    }
}
