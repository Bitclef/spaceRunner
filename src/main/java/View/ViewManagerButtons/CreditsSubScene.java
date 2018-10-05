package View.ViewManagerButtons;

import Model.InfoLabel;
import Model.SpaceRunnerSubscene;
import View.ViewManager;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class CreditsSubScene {

    public static SpaceRunnerSubscene createCreditsSubScene(){
        SpaceRunnerSubscene creditsSubScene = new SpaceRunnerSubscene();
        ViewManager.mainPane.getChildren().add(creditsSubScene);

        creditsSubScene.getPane().getChildren().add(headerText());
        creditsSubScene.getPane().getChildren().add(creditsMainSection());

        return creditsSubScene;
    }

    private static InfoLabel headerText(){

        InfoLabel helpHeader = new InfoLabel("CREDITS");
        helpHeader.setLayoutX(110);
        helpHeader.setLayoutY(25);

        return helpHeader;
    }

    private static GridPane creditsMainSection(){
        GridPane pane = new GridPane();
        pane.setHgap(50);

        pane.add(text("Made by Following \n JavaCraving youtube tutorial \n \n \n ", 25),0,0);
        pane.add(text("Heavily modified and \n JFX 11 compatible by \n Martin Archer! (Bitclef)", 25), 0, 1);


        pane.setLayoutX(300 - (118 * 2));
        pane.setLayoutY(100);
        return pane;
    }

    private static Text text(String in, int size){
        return buttonSubSectionText(in, size);
    }

    static Text buttonSubSectionText(String in, int size) {
        Text text = new Text();

        text.setText(in);
        text.setTextAlignment(TextAlignment.CENTER);

        text.setFont(Font.loadFont(HelpSubScene.class.getResourceAsStream("/font.ttf"), size));

        return text;
    }

}
