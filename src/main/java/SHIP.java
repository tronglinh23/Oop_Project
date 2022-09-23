public enum SHIP {
    BLUE("spaceshooter/PNG/playerShip3_blue.png","spaceshooter/PNG/playerShip1_blue.png"),
    GREEN("spaceshooter/PNG/playerShip3_green.png","spaceshooter/PNG/playerShip1_green.png"),
    ORANGE("spaceshooter/PNG/playerShip3_orange.png","spaceshooter/PNG/playerShip1_orange.png"),
    RED("spaceshooter/PNG/playerShip3_red.png","spaceshooter/PNG/playerShip1_red.png");

    private String urlship;
    private String urllife;

    private SHIP(String urlship, String urllife) {
        this.urlship = urlship;
        this.urllife = urllife;
    }

    public String getUrl() {
        return this.urlship;
    }
    public String getUrlLife() {
        return this.urllife;
    }
}
