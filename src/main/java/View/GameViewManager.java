package View;

import Model.SHIP;
import Model.SmallInfoLabel;
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

    private ImageView star;
    private SmallInfoLabel pointsLabel;
    private ImageView[] playerLives;
    private int playerLife;
    private static int points = 0;
    private final static String GOLD_STAR_IMAGE = "/gold_star.png";

    private final static int STAR_RADIUS = 12;
    private final static int SHIP_RADIUS = 27;
    private final static int METEOR_RADIUS = 27;

    public GameViewManager() {
        initializeStage();
        createKeyListeners();
        randomPositionGenerator = new Random();
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int points){
        GameViewManager.points = points;
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
        createGameElements(chosenShip);

        createGameLoop();
        gameStage.show();
    }

    private void createGameElements(SHIP chosenShip) {
        playerLife = 2;
        star = new ImageView(GOLD_STAR_IMAGE);
        setNewElementPosition(star);
        gamePane.getChildren().add(star);
        pointsLabel = new SmallInfoLabel("POINTS : 00");
        pointsLabel.setLayoutX(460);
        pointsLabel.setLayoutY(20);
        gamePane.getChildren().add(pointsLabel);
        playerLives = new ImageView[3];

        for(int i = 0; i < playerLives.length; i++){
            playerLives[i] = new ImageView(chosenShip.getUrlLife());
            playerLives[i].setLayoutX(455 + (i * 50));
            playerLives[i].setLayoutY(80);
            gamePane.getChildren().add(playerLives[i]);
        }

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
        gameAsteroidsElement(brownMeteors);
        gameAsteroidsElement(grayMeteors);

        star.setLayoutY(star.getLayoutY() + 7);
        star.setRotate(star.getRotate() + 3);

    }

    private void gameAsteroidsElement(ImageView[] fallingObject) {
        boolean leftRight = false;
        for (ImageView object : fallingObject) {
            object.setLayoutY(object.getLayoutY() + 10);
            if(leftRight){
                object.setRotate(object.getRotate() + 5);
                leftRight = false;
            }else{
                object.setRotate(object.getRotate() - 5);
                leftRight = true;
            }
        }
    }

    private void checkIfElementsAreBelowTheShipAndRelocate() {

        if(star.getLayoutY() > 1200){
            setNewElementPosition(star);
        }

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
                checkIfElementsCollide();
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

    private void checkIfElementsCollide(){

        if(SHIP_RADIUS + STAR_RADIUS > calculateDistance(ship.getLayoutX() + 49, star.getLayoutX() + 15, ship.getLayoutY() + 3, star.getLayoutY() + 15)){
            setNewElementPosition(star);
            points++;
            String textToSet = "POINTS : ";
            if(points < 10){
                textToSet += "0";
            }
            pointsLabel.setText(textToSet + points);
        }

        for (ImageView brownMeteor : brownMeteors) {
            if (METEOR_RADIUS + SHIP_RADIUS > calculateDistance(ship.getLayoutX() + 49, brownMeteor.getLayoutX() + 20, ship.getLayoutY() + 37, brownMeteor.getLayoutY() + 20)) {
                removeLife();
                setNewElementPosition(brownMeteor);
            }
        }

        for (ImageView grayMeteors : grayMeteors) {
            if (METEOR_RADIUS + SHIP_RADIUS > calculateDistance(ship.getLayoutX() + 49, grayMeteors.getLayoutX() + 20, ship.getLayoutY() + 37, grayMeteors.getLayoutY() + 20)) {
                removeLife();
                setNewElementPosition(grayMeteors);
            }
        }

    }

    private void removeLife(){
        gamePane.getChildren().remove(playerLives[playerLife]);
        playerLife--;
        if(playerLife < 0){
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
