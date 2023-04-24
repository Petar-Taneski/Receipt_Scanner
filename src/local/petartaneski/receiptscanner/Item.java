package local.petartaneski.receiptscanner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private String name;
    private Origin origin;
    private double price;
    private int weight;
    private String description;

    @JsonCreator
    public Item(@JsonProperty("name") String name, @JsonProperty("domestic") Origin origin, @JsonProperty("price") double price, @JsonProperty("weight") int weight, @JsonProperty("description") String description) {
        this.name = name;
        this.origin = Origin.DOMESTIC;
        this.price = price;
        this.weight = weight;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public Origin getOrigin() {
        return origin;
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
