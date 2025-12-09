package wardrobe;

public class OutfitCombos {
    private int quantity;
    private String color;
    private String type;

    public OutfitCombos() {
        this.quantity = 0;
        this.color = "";
        this.type = "";
    }

    public OutfitCombos(int quantity, String color, String type) {
        this.quantity = quantity;
        this.color = color;
        this.type = type;
    }

//getter methods
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

public String getType() {
        return type;
    }

//setter methods
    public void setColor(String color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

//overriding/polymorphism
    public String toString() {
        return type + " (" + color + ") x" + quantity;
    }
}