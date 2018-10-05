package View.ViewManagerButtons;

import Model.*;
import View.GameViewManager;
import View.ViewManager;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class ShipChooserSubScene {

    private static List<ShipPicker> shipsList;
    private static SHIP chosenShip;

    public static SpaceRunnerSubscene createShipChooserSubScene() {
        SpaceRunnerSubscene shipChooserScene = new SpaceRunnerSubscene();
        ViewManager.mainPane.getChildren().add(shipChooserScene);

        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP!");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(25);
        shipChooserScene.getPane().getChildren().add(chooseShipLabel);
        shipChooserScene.getPane().getChildren().add(createShipsToChoose());
        shipChooserScene.getPane().getChildren().add(createButtonToStart());

        return shipChooserScene;
    }

    private static HBox createShipsToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);
        shipsList = new ArrayList<>();
        for (SHIP ship : SHIP.values()) {
            ShipPicker shipToPick = new ShipPicker(ship);
            shipsList.add(shipToPick);
            box.getChildren().add(shipToPick);
            shipToPick.setOnMouseClicked(event -> {
                for (ShipPicker ship1 : shipsList) {
                    ship1.setIsCircleChosen(false);
                }
                shipToPick.setIsCircleChosen(true);
                chosenShip = shipToPick.getShip();
            });
        }
        box.setLayoutX(300 - (118 * 2));
        box.setLayoutY(100);
        return box;
    }

    private static SpaceRunnerButton createButtonToStart() {
        SpaceRunnerButton startButton = new SpaceRunnerButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);

        startButton.setOnAction(event -> {
            if (chosenShip != null) {
                GameViewManager gameManager = new GameViewManager();
                gameManager.createNewGame(ViewManager.mainStage, chosenShip);
            }
        });

        return startButton;
    }

}
