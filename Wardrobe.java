//import java.util.*;
import java.util.*;

public class Wardrobe {
    private List<Shoes> shoes;
    private List<Jeans> jeans;
    private List<Tops> tops;

    public Wardrobe() {
        shoes = new ArrayList<>();
        jeans = new ArrayList<>();
        tops = new ArrayList<>();
    package wardrobe;
    }

    import java.util.*;

    public class Wardrobe {
        // Check if we already have this color
        for (Shoes S : shoes) {
            if (S.getColor().equalsIgnoreCase(color)) {
                S.setQuantity(S.getQuantity() + 1);
                return;
            }
        }
        // If not, create new shoes entry
        shoes.add(new Shoes(1, color));
    }

    public List<Shoes> getShoes() {
        return shoes;
    }

    public void removeShoes(String color) {
        shoes.removeIf(s -> s.getColor().equalsIgnoreCase(color));
    }

    // Jeans methods
    public void addJeans(String color) {
        for (Jeans j : jeans) {
            if (j.getColor().equalsIgnoreCase(color)) {
                j.setQuantity(j.getQuantity() + 1);
                return;
            }
        }
        jeans.add(new Jeans(1, color));
    }

    public List<Jeans> getJeans() {
        return jeans;
    }

    public void removeJeans(String color) {
        jeans.removeIf(j -> j.getColor().equalsIgnoreCase(color));
    }

    // Tops methods
    public void addTops(String color) {
        for (Tops t : tops) {
            if (t.getColor().equalsIgnoreCase(color)) {
                t.setQuantity(t.getQuantity() + 1);
                return;
            }
        }
        tops.add(new Tops(1, color));
    }

    public List<Tops> getTops() {
        return tops;
    }

    public void removeTops(String color) {
        tops.removeIf(t -> t.getColor().equalsIgnoreCase(color));
    }

    // Get all items as a string
    public String getAllItems() {
        StringBuilder sb = new StringBuilder();
        
        if (!shoes.isEmpty()) {
            sb.append("SHOES:\n");
            for (Shoes s : shoes) {
                sb.append("  - ").append(s.toString()).append("\n");
            }
        }
        
        if (!jeans.isEmpty()) {
            sb.append("JEANS:\n");
            for (Jeans j : jeans) {
                sb.append("  - ").append(j.toString()).append("\n");
            }
        }
        
        if (!tops.isEmpty()) {
            sb.append("TOPS:\n");
            for (Tops t : tops) {
                sb.append("  - ").append(t.toString()).append("\n");
            }
        }
        
        return sb.length() == 0 ? "No items in wardrobe" : sb.toString();
    }
}
