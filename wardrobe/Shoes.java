package wardrobe;

public class Shoes {
    private int quantity;
    private String color;

    public Shoes(int quantity, String color) {
        this.quantity = quantity;
        this.color = color;
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int q) { this.quantity = q; }
    public String getColor() { return color; }
    public void setColor(String c) { this.color = c; }

    @Override
    public String toString() {
        return quantity + "x " + color + " shoes";
    }
}
