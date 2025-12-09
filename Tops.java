package wardrobe;

public class Tops extends OutfitCombos {
    public Tops() {
        super(0, "", "Tops");
    }

    public Tops(int quantity, String color) {
        super(quantity, color, "Tops");
    }

    @Override
    public String toString() {
        return "Tops (" + getColor() + ") x" + quantity;
    }
}

