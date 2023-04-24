package local.petartaneski.receiptscanner;

public class Item {

    private String name;
    private boolean domestic;
    private double price;
    private int weight;
    private String description;

    public Item() {
    }

    public Item(String name, boolean domestic, double price, int weight, String description) {
        this.name = name;
        this.domestic = domestic;
        this.price = price;
        this.weight = weight;
        this.description = description;
    }

    //Constructor without weight
    public Item(String name, boolean domestic, double price, String description) {
        this.name = name;
        this.domestic = domestic;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public boolean isDomestic() {
        return domestic;
    }

    public double getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

}
