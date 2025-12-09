package wardrobe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Wardrobe wardrobe;
    Scene screen1, screen2, screen3, screen4, screen5;

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        wardrobe = new Wardrobe();

        // SCREEN 1
        BorderPane s1 = new BorderPane();
        s1.setPadding(new Insets(40));
        s1.setStyle("-fx-background-color: #b3bbffff;");

        Label welcome = new Label("Welcome");
        welcome.setStyle("-fx-font-size: 40px; -fx-text-fill: #1a1a1a;");

        Button startBtn = new Button("Start!");
        startBtn.setStyle("-fx-background-color: #004c99; -fx-text-fill: white; -fx-font-size: 25px;");
        startBtn.setOnAction(e -> window.setScene(screen2));

        HBox s1Layout = new HBox(40, welcome, startBtn);
        s1Layout.setAlignment(Pos.CENTER);
        s1.setCenter(s1Layout);
        screen1 = new Scene(s1, 800, 500);

        // SCREEN 2
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

        // SCREEN 3
        BorderPane s3 = new BorderPane();
        s3.setStyle("-fx-background-color: #b3e5ff;");

        Button plusBtn = new Button("+");
        plusBtn.setStyle("-fx-background-color: grey; -fx-font-size: 20px;");
        plusBtn.setOnAction(e -> window.setScene(screen5));
        BorderPane.setAlignment(plusBtn, Pos.TOP_RIGHT);
        BorderPane.setMargin(plusBtn, new Insets(10));
        s3.setTop(plusBtn);

        HBox temps = new HBox(40);
        temps.setAlignment(Pos.TOP_CENTER);
        temps.setPadding(new Insets(60, 0, 0, 0));

        Button fBtn = new Button("F");
        fBtn.setShape(new Circle(60));
        fBtn.setMinSize(120, 120);
        fBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 30px;");
        fBtn.setOnAction(e -> showOutfitRecommendation(67, "F"));

        Button cBtn = new Button("C");
        cBtn.setShape(new Circle(60));
        cBtn.setMinSize(120, 120);
        cBtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 30px;");
        cBtn.setOnAction(e -> showOutfitRecommendation(19, "C"));

        temps.getChildren().addAll(fBtn, cBtn);
        s3.setCenter(temps);
        screen3 = new Scene(s3, 800, 500);

        // SCREEN 4
        BorderPane s4 = new BorderPane();
        s4.setStyle("-fx-background-color: #b3e5ff;");
        s4.setPadding(new Insets(20));

        HBox topBar4 = new HBox(20);
        Label chosenLabel = new Label("Chosen outfit");
        chosenLabel.setStyle("-fx-font-size: 32px;");

        Button home4 = new Button("Home");
        home4.setStyle("-fx-background-color: grey;");
        home4.setOnAction(e -> window.setScene(screen1));

        topBar4.getChildren().addAll(chosenLabel, home4);
        s4.setTop(topBar4);

        TextArea outfitBox = new TextArea();
        outfitBox.setPromptText("Your recommended outfit will appear here");
        outfitBox.setPrefWidth(300);
        outfitBox.setPrefHeight(300);
        outfitBox.setEditable(false);
        outfitBox.setWrapText(true);
        s4.setCenter(outfitBox);

        Button next4 = new Button("Back");
        next4.setStyle("-fx-background-color: grey; -fx-font-size: 25px;");
        next4.setOnAction(e -> window.setScene(screen3));
        BorderPane.setAlignment(next4, Pos.CENTER);
        BorderPane.setMargin(next4, new Insets(20));
        s4.setBottom(next4);
        screen4 = new Scene(s4, 800, 500);

        // SCREEN 5 (WARDROBE)
        BorderPane s5 = new BorderPane();
        s5.setStyle("-fx-background-color: #b3e5ff;");
        s5.setPadding(new Insets(20));

        Button home5 = new Button("Home");
        home5.setStyle("-fx-background-color: grey;");
        home5.setOnAction(e -> window.setScene(screen1));
        BorderPane.setAlignment(home5, Pos.TOP_RIGHT);
        s5.setTop(home5);

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
                        case "Shoes": wardrobe.addShoes(color); break;
                        case "Jeans": wardrobe.addJeans(color); break;
                        case "Tops": wardrobe.addTops(color); break;
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

        TextArea wardrobeDisplay = new TextArea();
        wardrobeDisplay.setPromptText("Your wardrobe items will appear here");
        wardrobeDisplay.setPrefHeight(150);
        wardrobeDisplay.setEditable(false);
        wardrobeDisplay.setWrapText(true);
        wardrobeDisplay.setText(wardrobe.getAllItems());
        s5.setBottom(wardrobeDisplay);
        screen5 = new Scene(s5, 800, 600);

        window.setScene(screen1);
        window.setTitle("Weather Wardrobe App");
        window.show();
    }

    private void updateWardrobeDisplay(BorderPane s5) {
        TextArea wardrobeDisplay = (TextArea) s5.getBottom();
        wardrobeDisplay.setText(wardrobe.getAllItems());
    }

    private void showOutfitRecommendation(String unit) {
        WeatherData weather = readWeatherFromCSV(weather_data.csv, "Phoenix, AZ");
        String recommendation = generateOutfitRecommendation(weather, unit);
        BorderPane s4 = (BorderPane) screen4.getRoot();
        TextArea outfitBox = (TextArea) s4.getCenter();
        outfitBox.setText(recommendation);
        window.setScene(screen4);
    }

//main recommmendation logic
    private String generateOutfitRecommendation(WeatherData weather, String unit) {
        StringBuilder outfit = new StringBuilder();
        outfit.append("OUTFIT RECOMMENDATION\n");

        int tempF;
        if (weather != null) {
            tempF = (int) weather.temp;
            outfit.append("Date: ").append(weather.date).append("\n");
            outfit.append("Temperature: ").append(weather.temp).append("°").append(unit).append("\n");
            outfit.append("Conditions: ").append(weather.conditions).append("\n");
            outfit.append("Wind Speed: ").append(weather.windspeed).append(" mph\n\n");
        } else {
            outfit.append("Weather data not available.\n");
        }

        //convert to fahreheit if chosen
        if (unit.equals("C")) {
            tempF = (int) (weather.temp * 9.0 / 5.0 + 32);
        }
        
        if (temp < 32 || (unit.equals("C") && temp < 0)) {
            outfit.append("It's COLD!\n");
            outfit.append("Recommended items:\n- Heavy jacket or coat\n- Long jeans\n- Warm shoes\n- Layers (thermal top)\n");
        } else if (temp < 60 || (unit.equals("C") && temp < 15)) {
            outfit.append("It's COOL.\n");
            outfit.append("Recommended items:\n- Light jacket or sweater\n- Jeans or long pants\n- Comfortable shoes\n- Long-sleeve top\n");
        } else if (temp < 75 || (unit.equals("C") && temp < 24)) {
            outfit.append("It's MILD.\n");
            outfit.append("Recommended items:\n- T-shirt or light top\n- Jeans or casual pants\n- Casual shoes\n- Optional light jacket\n");
        } else {
            outfit.append("It's HOT!\n");
            outfit.append("Recommended items:\n- T-shirt or tank top\n- Shorts or light pants\n- Sandals or sneakers\n- Sunglasses recommended\n");
        }

        outfit.append("\n--- Your Wardrobe ---\n");
        outfit.append(wardrobe.getAllItems());
        return outfit.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
