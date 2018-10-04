package Model;

public enum SHIP {

    BLUE("/ShipChooser/blue_ship.png"),
    GREEN("/ShipChooser/green_ship.png"),
    ORANGE("/ShipChooser/orange_ship.png"),
    RED("/ShipChooser/red_ship.png");

    private String urlShip;

    SHIP(String urlShip) {
        this.urlShip = urlShip;
    }

    public String getUrlShip() {
        return this.urlShip;
    }

}
