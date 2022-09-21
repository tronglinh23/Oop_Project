public enum SHIP {
    BLUE("spaceshooter/PNG/playerShip3_blue.png"),
    GREEN("spaceshooter/PNG/playerShip3_green.png"),
    ORANGE("spaceshooter/PNG/playerShip3_orange.png"),
    RED("spaceshooter/PNG/playerShip3_red.png");

    private String urlship;

    private SHIP(String urlship) {
        this.urlship = urlship;
    }

    public String getUrl() {
        return this.urlship;
    }
}
