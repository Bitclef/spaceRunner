package View.ViewManagerButtons;

import Model.InfoLabel;
import Model.SHIP;
import Model.SpaceRunnerSubscene;
import View.ViewManager;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import static View.ViewManagerButtons.CreditsSubScene.buttonSubSectionText;

public class HelpSubScene {

    public static SpaceRunnerSubscene createHelpButtonSubScene() {
        SpaceRunnerSubscene helpButtonSubScene = new SpaceRunnerSubscene();
        ViewManager.mainPane.getChildren().add(helpButtonSubScene);


        helpButtonSubScene.getPane().getChildren().add(headerText());
        helpButtonSubScene.getPane().getChildren().add(helpMainSection());

        return helpButtonSubScene;
    }

    private static InfoLabel headerText(){

        InfoLabel helpHeader = new InfoLabel("HOW TO PLAY");
        helpHeader.setLayoutX(110);
        helpHeader.setLayoutY(25);

        return helpHeader;
    }

    private static GridPane helpMainSection(){
        GridPane pane = new GridPane();
        pane.setVgap(25);
        pane.setHgap(25);

        pane.add(new ImageView((SHIP.BLUE).getUrlShip()), 0, 0);
        pane.add(text("This is your ship, \n treat her well.", 18), 2,0);
        pane.add(new ImageView("/ShipChooser/meteor_brown.png"), 0, 1);
        pane.add(text("Use the arrow keys to avoid \n meteoroids", 18),2, 1);
        pane.add(new ImageView("/gold_star.png"),0,2);
        pane.add(text("But don't forget to \n grab stars on the way!", 18),2, 2);



        pane.setLayoutX(300 - (118 * 2));
        pane.setLayoutY(100);
        return pane;
    }

    private static Text text(String in, int size){
        return buttonSubSectionText(in, size);
    }
}
