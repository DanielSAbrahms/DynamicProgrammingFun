import java.io.*;
import java.util.Vector;

public class KnapsackDynamicProgramming {
    public static void main (String[] args) throws Exception {
        File file = new File("src/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        Vector<Item> items = new Vector<>();

        String st;
        int lineNumber = 1;
        char algorithmType = 'd';
        int maxWeight = 0;
        int numberItems = 0;
        int newItemWeight = -1;
        while ((st = br.readLine()) != null) {
            if (lineNumber == 1) algorithmType = st.charAt(0);
            else if (lineNumber == 2) maxWeight = Integer.parseInt(st);
            else if (lineNumber == 3) numberItems = Integer.parseInt(st);
            else if (lineNumber % 2 == 0) {
                newItemWeight = Integer.parseInt(st);
            } else {
                assert (newItemWeight > 0);
                float newItemValue = Float.parseFloat(st);
                Item newItem = new Item(newItemWeight, newItemValue);
                items.add(newItem);
            }
            lineNumber++;
        }
        Item[] itemArray = items.toArray(new Item[0]);
        float totalVal = 0;
        int totalWeight = 0;
        if (algorithmType == 'd') {
            long startTime = System.nanoTime();
            DTable t = new DTable(maxWeight, numberItems, itemArray);
            Item[] optimalItems = t.getOptimalItems();
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Time for D Function: " + totalTime);
            for (int i = 0; i < optimalItems.length; i++) {
                System.out.println(optimalItems[i]);
                totalVal += optimalItems[i].getValue();
                totalWeight += optimalItems[i].getWeight();
            }

        } else {
            long startTime = System.nanoTime();
            FTable t = new FTable(maxWeight, numberItems, itemArray);
            Item[] optimalItems = t.getOptimalItems();
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Time for F Function: " + totalTime);
            for (int i = 0; i < optimalItems.length; i++) {
                System.out.println(optimalItems[i]);
                totalVal += optimalItems[i].getValue();
                totalWeight += optimalItems[i].getWeight();
            }
            System.out.println("Percentage of Table Used: %" + t.percentageOFTableUsed());

        }

        System.out.println("\nTotal Weight: " + totalWeight);
        System.out.println("Total Value: " + totalVal);
    }
}
