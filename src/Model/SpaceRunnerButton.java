package Model;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Font;

public class SpaceRunnerButton extends Button {
    private final String FONT_PATH = "Resources/font.ttf";
    private final String BUTTON_PRESSED = "-fx-background-color: transparent; -fx-background-image: url('/Model/Resources/yellow_button_pressed.png');";
    private final String BUTTON_NOT_PRESSED = "-fx-background-color: transparent; -fx-background-image: url('/Model/Resources/yellow_button.png');";

    public SpaceRunnerButton(String text) {

        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_NOT_PRESSED);
        initializeButtonListeners();
    }

    private void setButtonFont() {
            setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 23));
    }

    private void setBUTTON_PRESSED() {
        setStyle(BUTTON_PRESSED);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setBUTTON_NOT_PRESSED() {
        setStyle(BUTTON_NOT_PRESSED);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {

        setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setBUTTON_PRESSED();
            }
        });

        setOnMouseReleased(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setBUTTON_NOT_PRESSED();
            }
        });

        setOnMouseEntered(event -> setEffect(new DropShadow()));

        setOnMouseExited(event -> setEffect(null));
    }
}
