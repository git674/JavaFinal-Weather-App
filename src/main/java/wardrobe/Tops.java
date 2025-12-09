package wardrobe;

public class Tops extends OutfitCombos {
    public String toString() {
        return quantity + "x " + color + " tops";
    }

    public String suggestOutfit() {
        return "T-Shirt or Light Shirt";
    }
}
