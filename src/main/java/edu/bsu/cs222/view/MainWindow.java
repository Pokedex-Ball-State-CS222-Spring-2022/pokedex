package edu.bsu.cs222.view;

import edu.bsu.cs222.model.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainWindow extends Application {
    private final TextField searchInput = new TextField();
    private final Button searchButton = new Button("Search");
    private final ComboBox<String> gameSelection = new ComboBox<>();
    private final Text type = new Text("Type: ");
    private final Text stats = new Text("Stats:\nHP 40  Atk 45  Def 80\nSp 90   Spd 120");
    private final ScrollPane lowerPortion = new ScrollPane();
    private final PokemonProcessor pokemonProcessor = new PokemonProcessor();
    private Pokemon currentPokemon;

    @Override
    public void start(Stage primaryStage) {
        setUpWindowBasics(primaryStage);
        setUpSizesAndFonts();
        primaryStage.show();
    }

    private void setUpWindowBasics(Stage primaryStage) {
        primaryStage.setTitle("Pokedex");
        primaryStage.setScene(new Scene(createMainWindow()));
        primaryStage.getIcons().add(new Image("pokeball.png"));
    }

    private void setUpSizesAndFonts() {
        lowerPortion.setPrefViewportHeight(300);
        lowerPortion.setPrefViewportWidth(700);
        type.setFont(Font.font("Verdana", 25));
        stats.setFont(Font.font("Verdana", 25));
    }

    private Parent createMainWindow() {
        VBox mainWindow = new VBox();
        mainWindow.getChildren().addAll(
                createSearchBar(),
                createUpperPortion(),
                createDropDownMenu(),
                lowerPortion
        );

        return mainWindow;
    }

    private Parent createSearchBar() {
        HBox searchBar = new HBox();
        setUpGameSelection();
        setUpButton();
        searchBar.getChildren().addAll(
                searchInput,
                gameSelection,
                searchButton
        );
        return searchBar;
    }

    private Parent createUpperPortion() {
        HBox upperPortion = new HBox();
        upperPortion.getChildren().addAll(
                createImageDisplay(),
                createPokeFacts()
        );

        return upperPortion;
    }

    private Parent createDropDownMenu() {
        ComboBox<String> dropDownMenu = new ComboBox<>();
        dropDownMenu.setPrefWidth(700);
        dropDownMenu.getItems().addAll(
                "Moveset"
        );
        dropDownMenu.getSelectionModel().selectFirst();
        return dropDownMenu;
    }

    private void setUpGameSelection() {
        gameSelection.getItems().addAll(
                "yellow"
        );
        gameSelection.getSelectionModel().selectFirst();
    }

    private void setUpButton() {
        searchButton.setOnAction(e -> {
            searchInput.setDisable(true);
            searchButton.setDisable(true);
            search();
            Platform.runLater(() -> {
                searchInput.setDisable(false);
                searchButton.setDisable(false);
            });
        });
    }

    private void search() {
        try {
            currentPokemon = pokemonProcessor.process(searchInput.getText(), gameSelection.getValue());
        }
        catch(RuntimeException doesNotExist) {
            ErrorWindow noExistence = new ErrorWindow("This Pokemon doesn't exist");
            noExistence.display();
        }
    }

    private ImageView createImageDisplay() {
        ImageView pokemonImage = new ImageView();
        pokemonImage.setFitHeight(300);
        pokemonImage.setFitWidth(300);
        return pokemonImage;
    }

    private Parent createPokeFacts() {
        VBox pokeFacts = new VBox();
        pokeFacts.getChildren().addAll(
                type,
                stats
        );

        return pokeFacts;
    }
}
