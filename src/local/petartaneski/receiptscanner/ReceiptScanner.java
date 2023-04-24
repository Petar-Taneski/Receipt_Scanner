package local.petartaneski.receiptscanner;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReceiptScanner {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Item> items = new ArrayList<>();
        try {
            URL url = new URL("https://interview-task-api.mca.dev/qr-scanner-codes/beta-qr-Q2JwyUHL5ALv");
            items = Arrays.asList(objectMapper.readValue(url, Item[].class));

        } catch (UnknownHostException e) {

            System.out.println("Unknown host " + e.getMessage());
            return;
        }

        Map<Origin, List<Item>> allItems = items.stream()
                .collect(Collectors.groupingBy(Item::getOrigin));

        List<Item> mixed = allItems.get(Origin.MIXED) == null ? new ArrayList<>() : allItems.get(Origin.MIXED);
        List<Item> domestic = allItems.get(Origin.DOMESTIC) == null ? new ArrayList<>() : allItems.get(Origin.DOMESTIC);
        List<Item> imported = allItems.get(Origin.IMPORTED) == null ? new ArrayList<>() : allItems.get(Origin.IMPORTED);

        double domCost = 0;
        double impCost = 0;
        double mixCost = 0;

        Collections.sort(domestic, Comparator.comparing(Item::getName));
        Collections.sort(imported, Comparator.comparing(Item::getName));
        Collections.sort(mixed, Comparator.comparing(Item::getName));
        
        domCost = domestic.stream()
                .mapToDouble(Item::getPrice).sum();
        impCost = imported.stream()
                .mapToDouble(Item::getPrice).sum();
        mixCost = mixed.stream()
                .mapToDouble(Item::getPrice).sum();
        
        if(domestic.size()!=0) {
            System.out.println(". Domestic");
            printItemDetails(domestic);
        }

        if(imported.size()!=0) {
            System.out.println("\n. Imported");
            printItemDetails(imported);
        }

        if(mixed.size()!=0) {
            System.out.println("\n. Mixed");
            printItemDetails(mixed);
        }

        System.out.printf("\nDomestic cost: $%.1f\n", domCost);
        System.out.printf("Imported cost: $%.1f\n", impCost);
        System.out.printf("Mixed cost: $%.1f\n", mixCost);

        System.out.printf("Domestic count: %d\n", domestic.size());
        System.out.printf("Imported count: %d\n", imported.size());
        System.out.printf("Mixed count: %d\n", mixed.size());

    }
    private static void printItemDetails(List<Item> Items) {

            Items.forEach(i -> {
            System.out.printf("... %s\n", i.getName());
            System.out.printf("    Price: $%.1f\n", i.getPrice());
            System.out.printf("    %s\n", i.getDescription().substring(0, 10) + "...");

            if (i.getWeight() <= 0)
                System.out.println("    Weight: N/A");
            else
                System.out.printf("    Weight: %dg", i.getWeight());

        });
    }

}
