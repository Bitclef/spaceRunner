package Model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ShipPicker extends VBox {

    private ImageView circleImage;
    private ImageView shipImage;

    private String circleChosen = "View/Resources/ShipChooser/not_chosen_ship.png";
    private String circleNotChosen = "View/Resources/ShipChooser/chosen_ship.png";

    private SHIP ship;

    private boolean isCircleChosen;

    public ShipPicker(SHIP ship){
        circleImage = new ImageView(circleNotChosen);
        shipImage = new ImageView(ship.getUrlShip());
        this.ship = ship;
        isCircleChosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(shipImage);

    }

    public SHIP getShip(){
        return ship;
    }

    public boolean getIsCircleChosen(){
        return isCircleChosen;
    }

    public void setIsCircleChosen(boolean isCircleChosen){
        this.isCircleChosen = isCircleChosen;
        String imageToSet = this.isCircleChosen ? circleChosen : circleNotChosen;
        circleImage.setImage(new Image(imageToSet));
    }


}
