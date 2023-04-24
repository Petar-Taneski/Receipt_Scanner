package local.petartaneski.receiptscanner;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ReceiptScanner {
    public static void main(String[] args){

        ObjectMapper objectMapper = new ObjectMapper();
        List<Item> items = new ArrayList<>();
        try {
            URL url = new URL("https://interview-task-api.mca.dev/qr-scanner-codes/alpha-qr-gFpwhsQ8fkY1");

            items = Arrays.asList(objectMapper.readValue(url, Item[].class));
        }catch(IOException e){
            System.out.println("IOException has occured.\n");
            return;
        }

            Map<Boolean, List<Item>> allItems = items.stream()
                    .collect(Collectors.partitioningBy(Item::isDomestic));

            Collections.sort(allItems.get(true), Comparator.comparing(Item::getName));
            Collections.sort(allItems.get(false), Comparator.comparing(Item::getName));

            System.out.println(". Domestic");
            printItemDetails(allItems.get(true));

            System.out.println("\n. Imported");
            printItemDetails(allItems.get(false));

            double domCost = allItems.get(true).stream()
                    .mapToDouble(Item::getPrice).sum();
            double impCost = allItems.get(false).stream()
                    .mapToDouble(Item::getPrice).sum();

            System.out.printf("Domestic cost: $%.1f\n", domCost);
            System.out.printf("Imported cost: $%.1f\n", impCost);
            System.out.printf("Domestic count: %d\n", allItems.get(true).size());
            System.out.printf("Imported count: %d\n", allItems.get(false).size());
        }


    private static void printItemDetails(List<Item> Items) {

        Items.forEach(i -> {
            System.out.printf("... %s\n", i.getName());
            System.out.printf("    Price: $%.1f\n", i.getPrice());
            System.out.printf("    %s\n", i.getDescription().substring(0, 10) + "...");

            if (i.getWeight() == 0)
                System.out.println("    Weight: N/A");
            else
                System.out.printf("    Weight: %dg", i.getWeight());

        });
    }

}
