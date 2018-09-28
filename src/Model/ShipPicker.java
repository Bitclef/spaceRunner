package Model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

public class ShipPicker extends VBox {

    private ImageView circleImage;
    private ImageView shipImage;

    private String circleNotChoosen = "View/Resources/ShipChooser/not_chosen_ship.png";
    private String circleChoosen = "View/Resources/ShipChooser/chosen_ship.png";

    private SHIP ship;

    private boolean isCircleChoosen;

    public ShipPicker(@NotNull SHIP ship){
        circleImage = new ImageView(circleNotChoosen);
        shipImage = new ImageView(ship.getUrlShip());
        this.ship = ship;
        isCircleChoosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(shipImage);

    }

    public SHIP getShip(){
        return ship;
    }

    public boolean getIsCircleChoosen(){
        return isCircleChoosen;
    }

    public void setIsCircleChosen(boolean isCircleChooosen){
        this.isCircleChoosen = isCircleChooosen;
        String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
        circleImage.setImage(new Image(imageToSet));
    }


}
