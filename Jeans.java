package wardrobe;

public class Jeans extends OutfitCombos {
    public Jeans() {
        super(0, "", "Jeans");
    }

    public Jeans(int quantity, String color) {
        super(quantity, color, "Jeans");
    }

    @Override
    public String toString() {
        return "Jeans (" + getColor() + ") x" + quantity;
    }
}
