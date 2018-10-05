package Model;

public enum SHIP {

    BLUE("/ShipChooser/blue_ship.png", "/ShipChooser/blue_life.png"),
    GREEN("/ShipChooser/green_ship.png", "/ShipChooser/green_life.png"),
    ORANGE("/ShipChooser/orange_ship.png", "/ShipChooser/orange_life.png"),
    RED("/ShipChooser/red_ship.png", "/ShipChooser/red_life.png");

    private String urlShip;
    private String urlLife;

    SHIP(String urlShip, String urlLife) {
        this.urlShip = urlShip;
        this.urlLife = urlLife;
    }

    public String getUrlShip() {
        return this.urlShip;
    }

    public String getUrlLife(){
        return this.urlLife;
    }

}
