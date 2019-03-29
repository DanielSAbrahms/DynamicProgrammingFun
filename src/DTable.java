import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Vector;

public class DTable {
    private int MAX_WEIGHT;
    private int NUMBER_OF_ITEMS;
    private Item[] itemList;
    private float[][] table;

    private void generateTable() {
        for (int row = 0; row <= NUMBER_OF_ITEMS; row++) {
            Item item;
            if (row == 0) {
                item = null;
            } else {
                item = itemList[row-1];
            }
            for (int col = 0; col <= MAX_WEIGHT; col++) {
                float cellValue;
                if (item == null) {
                    cellValue = 0;
                    table[row][col] = cellValue;
                    break;
                }
                if (col < item.getWeight()) {
                    if ((row - 1) < 0) {
                        cellValue = 0;
                    } else {
                        cellValue = table[row-1][col];
                    }
                } else {
                    if ((row - 1) < 0) {
                        cellValue = item.getValue();
                    } else {
                        cellValue = Math.max((item.getValue() + table[row-1][col - item.getWeight()]), table[row-1][col]);
                    }
                }
                table[row][col] = cellValue;
            }
        }
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



    public DTable(int MAX_WEIGHT, int NUMBER_OF_ITEMS, Item[] itemList) {
        this.MAX_WEIGHT = MAX_WEIGHT;
        this.NUMBER_OF_ITEMS = NUMBER_OF_ITEMS;
        this.itemList = itemList;
        Arrays.sort(this.itemList);
        this.table = new float[NUMBER_OF_ITEMS + 1][MAX_WEIGHT + 1];
        this.generateTable();
    }

    public int getMAX_WEIGHT() {
        return MAX_WEIGHT;
    }

    public void setMAX_WEIGHT(int MAX_WEIGHT) {
        this.MAX_WEIGHT = MAX_WEIGHT;
    }

    public int getNUMBER_OF_ITEMS() {
        return NUMBER_OF_ITEMS;
    }

    public void setNUMBER_OF_ITEMS(int NUMBER_OF_ITEMS) {
        this.NUMBER_OF_ITEMS = NUMBER_OF_ITEMS;
    }

    public Item[] getItemList() {
        return itemList;
    }

    public void setItemList(Item[] itemList) {
        this.itemList = itemList;
    }

    public float[][] getTable() {
        return table;
    }

    public void setTable(float[][] table) {
        this.table = table;
    }
}
