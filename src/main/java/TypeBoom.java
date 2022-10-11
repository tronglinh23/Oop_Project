public class TypeBoom {
    public enum SHIP {
        BOMBMAY("Bomb/BigBoomBongMay_3.png"),
        BOMBD("Bomb/boom_D_1.png"),
        BOMDLUA("Bomb/boom1.png");

        private String BOMBTYPE;

        private SHIP(String BOMBTYPE) {
            this.BOMBTYPE = BOMBTYPE;
        }

        public String getUrl() {
            return this.BOMBTYPE;
        }
    }

}
