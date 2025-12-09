package wardrobe;

public class Shoes extends OutfitCombos {
    public Shoes() {
        super(0, "", "Shoes");
    }

    public Shoes(int quantity, String color) {
        super(quantity, color, "Shoes");
    }

    @Override
    public String toString() {
        return "Shoes (" + getColor() + ") x" + quantity;
    }
}

//whenever the user clicks the plus button on the shoes
//then there will be a shoe that is added to the shoe list and they will add the colors
