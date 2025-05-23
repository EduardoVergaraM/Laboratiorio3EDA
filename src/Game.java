public class Game {
    String name;
    String category;
    int price;
    int quality;

    Game(String name, String category, int price, int quality){
        this.name = name;
        this.category = category;
        this.price = price;
        this.quality = quality;
    }

    String getName(){return name;}
    String getCategory(){return category;}
    int getPrice(){return price;}
    int getQuality(){return quality;}

}
