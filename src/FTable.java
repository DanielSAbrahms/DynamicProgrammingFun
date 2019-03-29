import java.util.Arrays;
import java.util.Vector;

public class FTable {
    private float[][] table;
    private int MAX_WEIGHT;
    private int NUMBER_OF_ITEMS;
    private Item[] itemList;
    private int[] weightList;
    private float[] valList;


    public float getMaxTotalValue(int num, int weight, int[] weightList, float[] valList) {
        if (num == 0 || weight == 0) {
            return 0;
        }
        if (weightList[num - 1] > weight) {
            return getMaxTotalValue(num-1, weight - weightList[num-1], weightList, valList);
        }
        if (table[num][weight] != -1){
            return table[num][weight];
        } else {
            float max = Math.max(valList[num - 1] + getMaxTotalValue(num-1, weight - weightList[num-1], weightList, valList),
                    getMaxTotalValue(num -1, weight, weightList, valList));
            table[num][weight] = max;
            return max;
        }
    }

    public float solve() {
        return getMaxTotalValue(NUMBER_OF_ITEMS, MAX_WEIGHT, weightList, valList);
    }

    public Item[] getOptimalItems() {
        Vector<Item> items = new Vector<Item>();
        int col = MAX_WEIGHT;
        int row = NUMBER_OF_ITEMS;
        while (row > 0) {
            if (table[row][col] != table[row - 1][col]) {
                Item newItem = (itemList[row - 1]);
                items.add(newItem);
                col -= newItem.getWeight();
            }
            row -= 1;

        }
        return items.toArray(new Item[items.size()]);
    }




    public FTable(int MAX_WEIGHT, int NUMBER_OF_ITEMS, Item[] itemList) {
        this.itemList = itemList;
        this.MAX_WEIGHT = MAX_WEIGHT;
        this.NUMBER_OF_ITEMS = NUMBER_OF_ITEMS;
        this.table = new float[NUMBER_OF_ITEMS + 1][MAX_WEIGHT + 1];
        this.weightList = new int[NUMBER_OF_ITEMS];
        this.valList = new float[NUMBER_OF_ITEMS];
        // Runtime: n
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            weightList[i] = itemList[i].getWeight();
            valList[i] = itemList[i].getValue();
        }
        // Runtime: 2n
        for (int i = 0; i <= NUMBER_OF_ITEMS; i++) {
            for (int j = 0; j <= MAX_WEIGHT; j++) {
                table[i][j] = -1;
            }
        }
        this.solve();

    }

    public void printTable() {
        for (int i = 0; i <= NUMBER_OF_ITEMS; i++) {
            for (int j = 0; j <= MAX_WEIGHT; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }

    public float percentageOFTableUsed() {
        int cells = 0;
        int usedCell = 0;
        for (int i = 0; i <= NUMBER_OF_ITEMS; i++) {
            for (int j = 0; j <= MAX_WEIGHT; j++) {
                cells++;
                if (table[i][j] != -1) usedCell++;
            }
        }
        return ((float)usedCell/cells) * 100;
    }
}
