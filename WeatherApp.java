//reads the csv and gets the temperature for a specific city
import java.io.BufferedReader; //reads text from CSV
import java.io.FileReader; //opens CSV for reading
import java.io.IOException; //handles IO exceptions

public class WeatherApp {
    public static void main(String[] args) {
        // Define the city you want to look up
        String city = "Phoenix, AZ";
        String csvFile = "weather_data.csv"; // Path to your manually-updated CSV file
        
        double temp = readWeatherFromCSV(csvFile, city);
        
        System.out.println("Temperature in " + city + ": " + temp + "°C");

        if(temp<10){
            System.out.println("It's cold! Wear a jacket.");
        } else if(temp>=10 && temp<20){
            System.out.println("It's cool! A sweater should be fine.");
        } else {
            System.out.println("It's warm! A t-shirt and jeans are perfect.");
        }


    // Reads CSV file and gets the temperature for a specific city
    public static double readWeatherFromCSV(String filePath, String targetCity) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            // Skip header line (e.g., "City,Temperature,Humidity")
            line = reader.readLine();
            
            // Read each data line
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length >= 3) {
                    String city = parts[1].trim().replaceAll("\"", ""); // Remove quotes
                    String tempStr = parts[2].trim();
                    
                    // Match the target city (case-insensitive)
                    if (city.equalsIgnoreCase(targetCity)) {
                        return Double.parseDouble(tempStr);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
            e.printStackTrace();
        }
        return -1; // City not found
    }
}