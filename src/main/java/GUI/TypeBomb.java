package GUI;

public enum TypeBomb {
    BOMBMAY("Bomb/BigBoomBongMay_2.png", 1),
    BOMBD("Bomb/boom_D_1.png", 2),
    BOMDLUA("Bomb/boom8.png", 3);

    private String bombType;
    private int type;
    private TypeBomb(String bombType, int type) {
        this.bombType = bombType;
        this.type = type;
    }

    public String getPath() {
        return this.bombType;
    }

    public int getType() {
        return this.type;
    }
}