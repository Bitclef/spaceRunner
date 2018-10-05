package View;

import Model.*;
import View.ViewManagerButtons.CreditsSubScene;
import View.ViewManagerButtons.HelpSubScene;
import View.ViewManagerButtons.ScoreButtonSubScene;
import View.ViewManagerButtons.ShipChooserSubScene;
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
    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    public static AnchorPane mainPane;
    public static Stage mainStage;

    private SpaceRunnerSubscene sceneToHide;

    private List<SpaceRunnerButton> menuButtons;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        Scene mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        createButton();
        createBackground();
        createLogo();


    }

    private void showSubScene(SpaceRunnerSubscene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;

    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(SpaceRunnerButton button) {
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

    private void createStartButton() {
        SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(actionEvent -> showSubScene(ShipChooserSubScene.createShipChooserSubScene()));
    }

    private void createScoreButton() {
        SpaceRunnerButton scoreButton = new SpaceRunnerButton("SCORE");
        addMenuButton(scoreButton);

        scoreButton.setOnAction(actionEvent -> showSubScene(ScoreButtonSubScene.createScoreButtonSubScene()));
    }

    private void createHelpButton() {
        SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
        addMenuButton((helpButton));

        helpButton.setOnAction(actionEvent -> showSubScene(HelpSubScene.createHelpButtonSubScene()));
    }

    private void createCreditsButton() {
        SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(actionEvent -> showSubScene(CreditsSubScene.createCreditsSubScene()));
    }

    private void createExitButton() {
        SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(event -> mainStage.close());
    }

    private void createBackground() {
        Image backgroundImage = new Image("/blue.png", 256, 256, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {
        ImageView logo = new ImageView("/logo.png");
        logo.setLayoutX(300);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(event -> logo.setEffect(new DropShadow(50, Color.BLUEVIOLET)));
        logo.setOnMouseExited(event -> logo.setEffect(null));
        mainPane.getChildren().add(logo);
    }

}
