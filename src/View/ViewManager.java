package View;

import Model.*;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {
    private static final int WIDTH = 1024, HEIGHT = 768;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    private SpaceRunnerSubscene creditsSubScene;
    private SpaceRunnerSubscene helpSubScene;
    private SpaceRunnerSubscene scoreSubScene;
    private SpaceRunnerSubscene shipChooserScene;

    private SpaceRunnerSubscene sceneToHide;

    private List<SpaceRunnerButton> menuButtons;

    private List<ShipPicker> shipsList;
    private SHIP chosenShip;

    public ViewManager(){
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        createButton();
        createBackground();
        createLogo();
        createSubScenes();


    }

    private void showSubScene(SpaceRunnerSubscene subScene){
        if(sceneToHide != null){
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;

    }

    private void createSubScenes(){
        creditsSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(creditsSubScene);

        helpSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(helpSubScene);

        scoreSubScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(scoreSubScene);

        createShipChooserSubScene();

    }

    private void createShipChooserSubScene() {
        shipChooserScene = new SpaceRunnerSubscene();
        mainPane.getChildren().add(shipChooserScene);

        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP!");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(25);
        shipChooserScene.getPane().getChildren().add(chooseShipLabel);
        shipChooserScene.getPane().getChildren().add(createShipsToChoose());
        shipChooserScene.getPane().getChildren().add(createButtonToStart());
    }

    private HBox createShipsToChoose(){
        HBox box = new HBox();
        box.setSpacing(20);
        shipsList = new ArrayList<>();
        for(SHIP ship : SHIP.values()){
            ShipPicker shipToPick = new ShipPicker(ship);
            shipsList.add(shipToPick);
            box.getChildren().add(shipToPick);
            shipToPick.setOnMouseClicked(event -> {
                for(ShipPicker ship1 : shipsList){
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

    private SpaceRunnerButton createButtonToStart(){
        SpaceRunnerButton startButton = new SpaceRunnerButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);
        return startButton;
    }

    public Stage getMainStage(){
        return mainStage;
    }

    private void addMenuButton(SpaceRunnerButton button){
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);

    }

    private void createButton() {
        createStartButton();
        createScoreButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createStartButton(){
        SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(actionEvent -> showSubScene(shipChooserScene));
    }

    private void createScoreButton(){
        SpaceRunnerButton scoreButton = new SpaceRunnerButton("SCORE");
        addMenuButton(scoreButton);

        scoreButton.setOnAction(actionEvent -> showSubScene(scoreSubScene));
    }

    private void createHelpButton(){
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton((helpButton));

        helpButton.setOnAction(actionEvent -> showSubScene(helpSubScene));
    }

    private void createCreditsButton(){
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(actionEvent -> showSubScene(creditsSubScene));
    }

    private void createExitButton(){
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(event -> mainStage.close());
    }

    private void createBackground(){
        Image backgroundImage = new Image("View/Resources/blue.png", 256, 256, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo(){
        ImageView logo = new ImageView("View/Resources/logo.png");
        logo.setLayoutX(300);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(event -> logo.setEffect(new DropShadow(50, Color.BLUEVIOLET)));
        logo.setOnMouseExited(event ->  logo.setEffect(null));
        mainPane.getChildren().add(logo);
    }

}
