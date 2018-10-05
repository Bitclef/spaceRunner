package View.ViewManagerButtons;

import Model.InfoLabel;
import Model.SpaceRunnerSubscene;
import View.ViewManager;

public class CreditsSubScene {

    public static SpaceRunnerSubscene createCreditsSubScene(){
        SpaceRunnerSubscene creditsSubScene = new SpaceRunnerSubscene();
        ViewManager.mainPane.getChildren().add(creditsSubScene);

        creditsSubScene.getPane().getChildren().add(headerText());

        return creditsSubScene;
    }

    private static InfoLabel headerText(){

        InfoLabel helpHeader = new InfoLabel("CREDITS");
        helpHeader.setLayoutX(110);
        helpHeader.setLayoutY(25);

        return helpHeader;
    }

}
