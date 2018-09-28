package Model;

public enum SHIP {

    BLUE("View/Resources/ShipChooser/blue_ship.png"),
    GREEN("View/Resources/ShipChooser/green_ship.png"),
    ORANGE("View/Resources/ShipChooser/orange_ship.png"),
    RED("View/Resources/ShipChooser/red_ship.png");

    private String urlShip;

    SHIP(String urlShip){
        this.urlShip = urlShip;
    }

    public String getUrlShip(){
        return this.urlShip;
    }

}
