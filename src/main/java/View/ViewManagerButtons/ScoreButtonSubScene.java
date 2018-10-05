package View.ViewManagerButtons;

import Model.InfoLabel;
import Model.SpaceRunnerSubscene;
import View.ViewManager;

public class ScoreButtonSubScene {

    public static SpaceRunnerSubscene createScoreButtonSubScene(){
        SpaceRunnerSubscene scoreButtonSubScene = new SpaceRunnerSubscene();
        ViewManager.mainPane.getChildren().add(scoreButtonSubScene);

        scoreButtonSubScene.getPane().getChildren().add(headerText());

        return scoreButtonSubScene;
    }

    private static InfoLabel headerText(){

        InfoLabel helpHeader = new InfoLabel("HIGH SCORE");
        helpHeader.setLayoutX(110);
        helpHeader.setLayoutY(25);

        return helpHeader;
    }

}
