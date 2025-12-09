package wardrobe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main extends Application {

    Stage window;
    Wardrobe wardrobe;
    Scene screen1, screen2, screen3, screen4, screen5;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        wardrobe = new Wardrobe();

        //---------------------------------
        // -------- SCREEN 1 --------------
        //---------------------------------
        BorderPane s1 = new BorderPane();
        s1.setPadding(new Insets(40));
        s1.setStyle("-fx-background-color: #b3bbffff;"); // light pastel blue

        Label welcome = new Label("Welcome");
        welcome.setStyle("-fx-font-size: 40px; -fx-text-fill: #1a1a1a;");

        Button startBtn = new Button("Start!");
        startBtn.setStyle("-fx-background-color: #004c99; -fx-text-fill: white; -fx-font-size: 25px;");
        startBtn.setOnAction(e -> window.setScene(screen2));

        HBox s1Layout = new HBox(40, welcome, startBtn);
        s1Layout.setAlignment(Pos.CENTER);

        s1.setCenter(s1Layout);
        screen1 = new Scene(s1, 800, 500);


        //---------------------------------
        // -------- SCREEN 2 --------------
        //---------------------------------
        VBox s2 = new VBox(40);
        s2.setAlignment(Pos.CENTER);
        s2.setStyle("-fx-background-color: #b3e5ff;");

        Button updateWardrobeBtn = new Button("Update Wardrobe");
        updateWardrobeBtn.setStyle("-fx-background-color: grey; -fx-font-size: 25px;");
        updateWardrobeBtn.setOnAction(e -> window.setScene(screen5));

        Button continueBtn = new Button("Continue");
        continueBtn.setStyle("-fx-background-color: grey; -fx-font-size: 25px;");
        continueBtn.setOnAction(e -> window.setScene(screen3));

        s2.getChildren().addAll(updateWardrobeBtn, continueBtn);
        screen2 = new Scene(s2, 800, 500);


        //---------------------------------
        // -------- SCREEN 3 --------------
        //---------------------------------
        BorderPane s3 = new BorderPane();
        s3.setStyle("-fx-background-color: #b3e5ff;");

        // + button top right
        Button plusBtn = new Button("+");
        plusBtn.setStyle("-fx-background-color: grey; -fx-font-size: 20px;");
        plusBtn.setOnAction(e -> window.setScene(screen5));
        BorderPane.setAlignment(plusBtn, Pos.TOP_RIGHT);
        BorderPane.setMargin(plusBtn, new Insets(10));
        s3.setTop(plusBtn);

        // Two large red circular buttons labeled F and C
        HBox temps = new HBox(40);
        temps.setAlignment(Pos.TOP_CENTER);
        temps.setPadding(new Insets(60, 0, 0, 0));

        Button fBtn = new Button("F");
        fBtn.setShape(new Circle(60));
        fBtn.setMinSize(120, 120);
        fBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 30px;");
        fBtn.setOnAction(e -> showOutfitRecommendation(67, "F")); // 67°F example

        Button cBtn = new Button("C");
        cBtn.setShape(new Circle(60));
        cBtn.setMinSize(120, 120);
        cBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 30px;");
        cBtn.setOnAction(e -> showOutfitRecommendation(19, "C")); // ~19°C

        temps.getChildren().addAll(fBtn, cBtn);
        s3.setCenter(temps);

        screen3 = new Scene(s3, 800, 500);


        //---------------------------------
        // -------- SCREEN 4 --------------
        //---------------------------------
        BorderPane s4 = new BorderPane();
        s4.setStyle("-fx-background-color: #b3e5ff;");
        s4.setPadding(new Insets(20));

        // Top title + home button
        HBox topBar4 = new HBox(20);
        Label chosenLabel = new Label("Chosen outfit");
        chosenLabel.setStyle("-fx-font-size: 32px;");

        Button home4 = new Button("Home");
        home4.setStyle("-fx-background-color: grey;");
        home4.setOnAction(e -> window.setScene(screen1));

        topBar4.getChildren().addAll(chosenLabel, home4);
        s4.setTop(topBar4);

        // TextBox
        TextArea outfitBox = new TextArea();
        outfitBox.setPromptText("Your recommended outfit will appear here");
        outfitBox.setPrefWidth(300);
        outfitBox.setPrefHeight(300);
        outfitBox.setEditable(false);
        outfitBox.setWrapText(true);

        s4.setCenter(outfitBox);

        // Bottom next button
        Button next4 = new Button("Back");
        next4.setStyle("-fx-background-color: grey; -fx-font-size: 25px;");
        next4.setOnAction(e -> window.setScene(screen3));

        BorderPane.setAlignment(next4, Pos.CENTER);
        BorderPane.setMargin(next4, new Insets(20));
        s4.setBottom(next4);

        screen4 = new Scene(s4, 800, 500);


        //---------------------------------
        // -------- SCREEN 5 (WARDROBE) ---
        //---------------------------------
        BorderPane s5 = new BorderPane();
        s5.setStyle("-fx-background-color: #b3e5ff;");
        s5.setPadding(new Insets(20));

        // Home button top right
        Button home5 = new Button("Home");
        home5.setStyle("-fx-background-color: grey;");
        home5.setOnAction(e -> window.setScene(screen1));
        BorderPane.setAlignment(home5, Pos.TOP_RIGHT);
        s5.setTop(home5);

        // Left text list + plus buttons + input fields
        VBox items = new VBox(20);
        items.setPadding(new Insets(40));

        String[] clothing = {"Shoes", "Jeans", "Tops"};

        for (String item : clothing) {
            HBox section = new HBox(15);
            section.setStyle("-fx-border-color: #004c99; -fx-border-width: 1; -fx-padding: 10;");

            Label name = new Label(item);
            name.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            name.setPrefWidth(80);

            TextField colorInput = new TextField();
            colorInput.setPromptText("Color");
            colorInput.setPrefWidth(100);

            Button add = new Button("+");
            add.setStyle("-fx-background-color: #004c99; -fx-text-fill: white; -fx-font-size: 14px;");
            add.setOnAction(e -> {
                String color = colorInput.getText().trim();
                if (!color.isEmpty()) {
                    switch (item) {
                        case "Shoes":
                            wardrobe.addShoes(color);
                            break;
                        case "Jeans":
                            wardrobe.addJeans(color);
                            break;
                        case "Tops":
                            wardrobe.addTops(color);
                            break;
                    }
                    colorInput.clear();
                    updateWardrobeDisplay(s5);
                }
            });

            section.getChildren().addAll(name, colorInput, add);
            items.getChildren().add(section);
        }

        ScrollPane scroll = new ScrollPane(items);
        scroll.setFitToWidth(true);
        s5.setCenter(scroll);

        // Bottom: Display wardrobe
        TextArea wardrobeDisplay = new TextArea();
        wardrobeDisplay.setPromptText("Your wardrobe items will appear here");
        wardrobeDisplay.setPrefHeight(150);
        wardrobeDisplay.setEditable(false);
        wardrobeDisplay.setWrapText(true);
        wardrobeDisplay.setText(wardrobe.getAllItems());

        s5.setBottom(wardrobeDisplay);

        screen5 = new Scene(s5, 800, 600);


        // Show initial screen
        window.setScene(screen1);
        window.setTitle("Weather Wardrobe App");
        window.show();
    }

    private void updateWardrobeDisplay(BorderPane s5) {
        TextArea wardrobeDisplay = (TextArea) s5.getBottom();
        wardrobeDisplay.setText(wardrobe.getAllItems());
    }

    private void showOutfitRecommendation(int temp, String unit) {
        String recommendation = generateOutfitRecommendation(temp, unit);
        
        // Find the outfit text area in screen4 and update it
        BorderPane s4 = (BorderPane) screen4.getRoot();
        TextArea outfitBox = (TextArea) s4.getCenter();
        outfitBox.setText(recommendation);
        
        window.setScene(screen4);
    }

    private String generateOutfitRecommendation(int temp, String unit) {
        // Read weather data from CSV
        WeatherData weather = readWeatherFromCSV("weather_data.csv", "Phoenix, AZ");
        
        StringBuilder outfit = new StringBuilder();
        outfit.append("OUTFIT RECOMMENDATION\n");
        outfit.append("Temperature: ").append(temp).append("°").append(unit).append("\n");
        if (weather != null) {
            outfit.append("Conditions: ").append(weather.conditions).append("\n");
            outfit.append("Wind Speed: ").append(weather.windspeed).append(" mph\n\n");
        } else {
            outfit.append("Conditions: Clear\n");
            outfit.append("Wind Speed: 5.5 mph\n\n");
        }

        // Convert to Fahrenheit for consistent comparison
        int tempF = temp;
        if (unit.equals("C")) {
            tempF = (int)(temp * 9.0 / 5.0 + 32);
        }

        if (tempF < 32) {
        StringBuilder outfit = new StringBuilder();
        outfit.append("OUTFIT RECOMMENDATION\n");
        outfit.append("Temperature: ").append(temp).append("°").append(unit).append("\n\n");

        // Simple logic based on temperature
        if (temp < 32 || (unit.equals("C") && temp < 0)) {
            outfit.append("It's COLD!\n");
            outfit.append("Recommended items:\n");
            outfit.append("- Heavy jacket or coat\n");
            outfit.append("- Long jeans\n");
            outfit.append("- Warm shoes\n");
            outfit.append("- Layers (thermal top)\n");
        } else if (tempF < 60) {
        } else if (temp < 60 || (unit.equals("C") && temp < 15)) {
            outfit.append("It's COOL.\n");
            outfit.append("Recommended items:\n");
            outfit.append("- Light jacket or sweater\n");
            outfit.append("- Jeans or long pants\n");
            outfit.append("- Comfortable shoes\n");
            outfit.append("- Long-sleeve top\n");
        } else if (tempF < 75) {
            outfit.append("It's COMFORTABLE.\n");
        } else if (temp < 75 || (unit.equals("C") && temp < 24)) {
            outfit.append("It's MILD.\n");
            outfit.append("Recommended items:\n");
            outfit.append("- T-shirt or light top\n");
            outfit.append("- Jeans or casual pants\n");
            outfit.append("- Casual shoes\n");
            outfit.append("- Optional light jacket\n");
        } else {
            outfit.append("It's HOT!\n");
            outfit.append("Recommended items:\n");
            outfit.append("- T-shirt or tank top\n");
            outfit.append("- Shorts or light pants\n");
            outfit.append("- Sandals or sneakers\n");
            outfit.append("- Sunglasses recommended\n");
        }

        outfit.append("\n--- Your Wardrobe ---\n");
        outfit.append(wardrobe.getAllItems());

        return outfit.toString();
    }

    // Inner class to hold weather data
    private static class WeatherData {
        String date;
        double temp;
        String conditions;
        double windspeed;

        WeatherData(String date, double temp, String conditions, double windspeed) {
            this.date = date;
            this.temp = temp;
            this.conditions = conditions;
            this.windspeed = windspeed;
        }
    }

    // Method to read weather data from CSV
    private WeatherData readWeatherFromCSV(String filePath, String targetCity) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String todayStr = today.format(formatter);

            // Skip header line
            reader.readLine();

            // Read each data line
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                
                if (parts.length >= 5) {
                    String date = parts[0].trim();
                    String city = parts[1].trim().replaceAll("\"", "");
                    String tempStr = parts[2].trim();
                    String conditions = parts[3].trim().replaceAll("\"", "");
                    String windStr = parts[5].trim();

                    // Match today's date and target city
                    if (date.equals(todayStr) && city.equalsIgnoreCase(targetCity)) {
                        double temp = Double.parseDouble(tempStr);
                        double windspeed = Double.parseDouble(windStr);
                        return new WeatherData(date, temp, conditions, windspeed);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
