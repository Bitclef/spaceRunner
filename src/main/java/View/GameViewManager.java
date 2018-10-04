package View;

import Model.SHIP;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;


public class GameViewManager {

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;
    private final static String METEOR_BROWN_IMAGE = "/ShipChooser/meteor_brown.png";
    private final static String METEOR_GRAY_IMAGE = "/ShipChooser/meteor_gray.png";
    private final String BACKGROUND_IMAGE = "/blue.png";
    Random randomPositionGenerator;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;
    private ImageView ship;
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;
    private GridPane gridPane1;
    private GridPane gridPane2;
    private ImageView[] brownMeteors;
    private ImageView[] grayMeteors;

    public GameViewManager() {
        initializeStage();
        createKeyListeners();
        randomPositionGenerator = new Random();
    }

    private void createKeyListeners() {

        gameScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                isLeftKeyPressed = true;
            } else if (event.getCode() == KeyCode.RIGHT) {
                isRightKeyPressed = true;
            }
        });

        gameScene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                isLeftKeyPressed = false;
            } else if (event.getCode() == KeyCode.RIGHT) {
                isRightKeyPressed = false;
            }
        });

    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);

    }

    public void createNewGame(Stage menuStage, SHIP chosenShip) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createShip(chosenShip);
        createGameElements();

        createGameLoop();
        gameStage.show();
    }

    private void createGameElements() {
        brownMeteors = new ImageView[5];
        for (int i = 0; i < brownMeteors.length; i++) {
            brownMeteors[i] = new ImageView(METEOR_BROWN_IMAGE);
            setNewElementPosition(brownMeteors[i]);
            gamePane.getChildren().add(brownMeteors[i]);
        }

        grayMeteors = new ImageView[5];
        for (int i = 0; i < grayMeteors.length; i++) {
            grayMeteors[i] = new ImageView(METEOR_GRAY_IMAGE);
            setNewElementPosition(grayMeteors[i]);
            gamePane.getChildren().add(grayMeteors[i]);
        }
    }

    private void moveGameElements() {

        for (ImageView brownMeteor : brownMeteors) {
            brownMeteor.setLayoutY(brownMeteor.getLayoutY() + 10);
            brownMeteor.setRotate(brownMeteor.getRotate() + 5);
        }

        for (ImageView grayMeteor : grayMeteors) {
            grayMeteor.setLayoutY(grayMeteor.getLayoutY() + 10);
            grayMeteor.setRotate(grayMeteor.getRotate() + 5);
        }

    }

    private void checkIfElementsAreBelowTheShipAndRelocate() {

        for (ImageView brownMeteors : brownMeteors) {
            if (brownMeteors.getLayoutY() > 900)
                setNewElementPosition(brownMeteors);
        }

        for (ImageView grayMeteors : grayMeteors) {
            if (grayMeteors.getLayoutY() > 900)
                setNewElementPosition(grayMeteors);
        }
    }

    private void setNewElementPosition(ImageView image) {
        image.setLayoutX(randomPositionGenerator.nextInt(580));
        image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
    }

    private void createShip(SHIP chosenShip) {
        ship = new ImageView(chosenShip.getUrlShip());
        ship.setLayoutX(GAME_WIDTH >> 1);
        ship.setLayoutY(GAME_HEIGHT - 90);
        gamePane.getChildren().add(ship);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBackground();
                moveGameElements();
                checkIfElementsAreBelowTheShipAndRelocate();
                moveShip();

            }
        };
        gameTimer.start();
    }

    private void moveShip() {

        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() > -20) {
                ship.setLayoutX(ship.getLayoutX() - 7);
            }
        }
        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() < 522) {
                ship.setLayoutX(ship.getLayoutX() + 7);
            }

        }
        if (!isLeftKeyPressed && !isRightKeyPressed || isLeftKeyPressed && isRightKeyPressed) {
            if (angle < 0)
                angle += 5;
            else if (angle > 0)
                angle -= 5;
            ship.setRotate(angle);
        }

    }

    private void createBackground() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) {
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
            GridPane.setConstraints(backgroundImage2, i % 3, i / 3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }

        gridPane2.setLayoutY(-1024);

        gamePane.getChildren().addAll(gridPane1, gridPane2);
    }

    private void moveBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 1);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 1);

        if (gridPane1.getLayoutY() >= 1024) {
            gridPane1.setLayoutY(-1024);
        }

        if (gridPane2.getLayoutY() >= 1024) {
            gridPane2.setLayoutY(-1024);
        }
    }

}
