package View.ViewManagerButtons;

import Model.InfoLabel;
import Model.SpaceRunnerSubscene;
import View.GameViewManager;
import View.ViewManager;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import static View.ViewManagerButtons.CreditsSubScene.buttonSubSectionText;

public class ScoreButtonSubScene {
    private static int displayPoints;

    public static SpaceRunnerSubscene createScoreButtonSubScene(){
        SpaceRunnerSubscene scoreButtonSubScene = new SpaceRunnerSubscene();
        ViewManager.mainPane.getChildren().add(scoreButtonSubScene);

        scoreButtonSubScene.getPane().getChildren().add(headerText());
        scoreButtonSubScene.getPane().getChildren().add(scoreMainSection());

        return scoreButtonSubScene;
    }

    private static InfoLabel headerText(){

        InfoLabel helpHeader = new InfoLabel("HIGH SCORE");
        helpHeader.setLayoutX(110);
        helpHeader.setLayoutY(25);

        return helpHeader;
    }

    private static GridPane scoreMainSection(){
        GridPane pane = new GridPane();
        pane.setVgap(20);
        pane.setHgap(95);
        if(displayPoints < GameViewManager.getPoints()){
            displayPoints = GameViewManager.getPoints();
        }
        pane.add(text(String.valueOf(displayPoints), 150), 2, 2);
        GameViewManager.setPoints(0);

        pane.setLayoutX(300 - (118 * 2));
        pane.setLayoutY(100);
        return pane;
    }

    private static Text text(String in, int size){
        return buttonSubSectionText(in, size);
    }

}
