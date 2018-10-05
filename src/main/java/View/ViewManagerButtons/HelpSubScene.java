package View.ViewManagerButtons;

import Model.InfoLabel;
import Model.SpaceRunnerSubscene;
import View.ViewManager;

public class HelpSubScene {

    public static SpaceRunnerSubscene createHelpButtonSubScene() {
        SpaceRunnerSubscene helpButtonSubScene = new SpaceRunnerSubscene();
        ViewManager.mainPane.getChildren().add(helpButtonSubScene);


        helpButtonSubScene.getPane().getChildren().add(headerText());

        return helpButtonSubScene;
    }

    private static InfoLabel headerText(){

        InfoLabel helpHeader = new InfoLabel("HOW TO PLAY");
        helpHeader.setLayoutX(110);
        helpHeader.setLayoutY(25);

        return helpHeader;
    }
}
