public class Item implements Comparable<Item> {
    private int weight;
    private float value;

    public Item() {
        weight = -1;
        value = -1;
    }

    public Item(int weight, float value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public int compareTo(Item o) {
        if (o.getValue() < this.getValue()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "V: " + this.getValue() + ", W: " + this.getWeight();
    }
}
