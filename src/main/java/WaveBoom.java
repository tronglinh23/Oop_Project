import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class WaveBoom {
    private int x;
    private int y;
    private int lengthLeft;
    private int lengthRight;
    private int lengthUp;
    private int lengthDown;

    private double xEnemyDie;

    private double yEnemyDie;

    private int imageIndex=0;


    private final Image[] WAVE_IMG = {ImageUtils.loadImage("src/main/resources/images/bombbang_left_1.png"),
                                        ImageUtils.loadImage("src/main/resources/images/bombbang_left_2.png"),
                                        ImageUtils.loadImage("src/main/resources/images/bombbang_right_1.png"),
                                        ImageUtils.loadImage("src/main/resources/images/bombbang_right_2.png"),
                                        ImageUtils.loadImage("src/main/resources/images/bombbang_mid_2.png"),
                                        ImageUtils.loadImage("src/main/resources/images/bombbang_down_1.png"),
                                        ImageUtils.loadImage("src/main/resources/images/bombbang_down_2.png"),
                                        ImageUtils.loadImage("src/main/resources/images/bombbang_up_1.png"),
                                        ImageUtils.loadImage("src/main/resources/images/bombbang_up_2.png")
    };

    public final Image[] ENEMY_DIE={
            ImageUtils.loadImage("src/main/resources/images/boss_die_1.png"),
            ImageUtils.loadImage("src/main/resources/images/boss_die_2.png"),
            ImageUtils.loadImage("src/main/resources/images/boss_die_3.png"),
            ImageUtils.loadImage("src/main/resources/images/boss_die_4.png")
    };

    public WaveBoom(int x, int y, int lengthWave) {
        this.x = x;
        this.y = y;
        this.lengthLeft = this.lengthRight = this.lengthDown = this.lengthUp =  lengthWave;
    }

    public Rectangle getRect(int x, int y) {
        Rectangle rec = new Rectangle(x + 5, y + 5, Boom.Size - 10, Boom.Size - 10);
        return rec;
    }

    public void draw(ArrayList<TileMap> arrTileMap, GraphicsContext gc) {
        drawLeftWave(arrTileMap, gc);
        drawRightWave(arrTileMap, gc);
        drawMidWave(arrTileMap, gc);
        drawDownWave(arrTileMap, gc);
        drawUpWave(arrTileMap, gc);
        if (xEnemyDie!=0 || yEnemyDie!=0) {
            Image image= ENEMY_DIE[imageIndex/50%ENEMY_DIE.length];
            gc.drawImage(image, xEnemyDie, yEnemyDie);
        }
    }

    private void drawLeftWave(ArrayList<TileMap> arrTileMap, GraphicsContext gc) {
        for(int stt = 1 ; stt <= lengthLeft ; stt++) {
            int xLocate = x - stt * Boom.Size + 5;
            int yLocate = y + 10;
            if(stt == lengthLeft) {
                gc.drawImage(WAVE_IMG[1],xLocate, yLocate);
            } else {
                gc.drawImage(WAVE_IMG[0],xLocate,yLocate);
            }
            for (int i = 0; i < arrTileMap.size(); i++) {
                if (getRect(xLocate,yLocate).getBoundsInParent().intersects(arrTileMap.get(i).getRect().getBoundsInParent())) {
                    if (arrTileMap.get(i).locate_bit == 2 || arrTileMap.get(i).locate_bit == 4
                            || arrTileMap.get(i).locate_bit == 5) {
                        arrTileMap.remove(i);
                        lengthLeft = lengthLeft - (lengthLeft - stt);

                    } else if (arrTileMap.get(i).locate_bit != 2 && arrTileMap.get(i).locate_bit != 4
                            && arrTileMap.get(i).locate_bit != 5 && arrTileMap.get(i).locate_bit != 0) {
                        lengthLeft = lengthLeft - (lengthLeft - stt);
                    }
                }
            }
        }
    }

    private void drawRightWave(ArrayList<TileMap> arrTileMap, GraphicsContext gc) {
        for(int stt = 1 ; stt <= lengthRight ; stt++) {
            int xLocate = x + stt * Boom.Size - 5;
            int yLocate = y + 10;
            if(stt == lengthRight) {
                gc.drawImage(WAVE_IMG[3],xLocate, yLocate, Boom.Size + 10, Boom.Size);
            } else {
                gc.drawImage(WAVE_IMG[2],xLocate, yLocate);
            }
            for (int i = 0; i < arrTileMap.size(); i++) {
                if (getRect(xLocate,yLocate).getBoundsInParent().intersects(arrTileMap.get(i).getRect().getBoundsInParent())) {
                    if (arrTileMap.get(i).locate_bit == 2 || arrTileMap.get(i).locate_bit == 4
                            || arrTileMap.get(i).locate_bit == 5 ) {
                        arrTileMap.remove(i);
                        lengthRight = lengthRight - (lengthRight - stt);

                    } else if (arrTileMap.get(i).locate_bit != 2 && arrTileMap.get(i).locate_bit != 4
                            && arrTileMap.get(i).locate_bit != 5 && arrTileMap.get(i).locate_bit != 0) {
                        lengthRight = lengthRight - (lengthRight - stt);
                    }
                }
            }
        }
    }


    private void drawMidWave(ArrayList<TileMap> arrTileMap, GraphicsContext gc) {
        gc.drawImage(WAVE_IMG[4],x + 5, y + 5);
    }

    private void drawDownWave(ArrayList<TileMap> arrTileMap, GraphicsContext gc) {
        for(int stt = 1 ; stt <= lengthDown ; stt++) {
            int xLocate = x + 5;
            int yLocate = y + stt * Boom.Size - 5;
            if(stt == lengthDown) {
                gc.drawImage(WAVE_IMG[6],xLocate, yLocate + 5, Boom.Size, Boom.Size + 10);
            } else {
                gc.drawImage(WAVE_IMG[5],xLocate, yLocate);
            }
            for (int i = 0; i < arrTileMap.size(); i++) {
                if (getRect(xLocate,yLocate).getBoundsInParent().intersects(arrTileMap.get(i).getRect().getBoundsInParent())) {
                    if (arrTileMap.get(i).locate_bit == 2 || arrTileMap.get(i).locate_bit == 4
                            || arrTileMap.get(i).locate_bit == 5 ) {
                        arrTileMap.remove(i);
                        lengthDown = lengthDown - (lengthDown - stt);

                    } else if (arrTileMap.get(i).locate_bit != 2 && arrTileMap.get(i).locate_bit != 4
                            && arrTileMap.get(i).locate_bit != 5 && arrTileMap.get(i).locate_bit != 0) {
                        lengthDown = lengthDown - (lengthDown - stt);
                    }
                }
            }
        }
    }

    private void drawUpWave(ArrayList<TileMap> arrTileMap, GraphicsContext gc) {
        for(int stt = 1 ; stt <= lengthUp ; stt++) {
            int xLocate = x + 5;
            int yLocate = y - stt * Boom.Size + 5;
            if(stt == lengthUp) {
                gc.drawImage(WAVE_IMG[8],xLocate, yLocate);
            } else {
                gc.drawImage(WAVE_IMG[7],xLocate, yLocate);
            }
            for (int i = 0; i < arrTileMap.size(); i++) {
                if (getRect(xLocate + 5,yLocate + 10).getBoundsInParent().intersects(arrTileMap.get(i).getRect().getBoundsInParent())) {
                    if (arrTileMap. get(i).locate_bit == 2 || arrTileMap.get(i).locate_bit == 4
                            || arrTileMap.get(i).locate_bit == 5 ) {
                        arrTileMap.remove(i);
                        lengthUp = lengthUp - (lengthUp - stt);

                    } else if (arrTileMap.get(i).locate_bit != 2 && arrTileMap.get(i).locate_bit != 4
                            && arrTileMap.get(i).locate_bit != 5 && arrTileMap.get(i).locate_bit != 0) {
                        lengthUp = lengthUp - (lengthUp - stt);
                    }
                }
            }

        }
    }

    public void checkExplodeBoom_Boom(ArrayList<Boom> arrBomb, ArrayList<Long> timeBombStart) {
        for (int bomb = 0 ; bomb < arrBomb.size() ; bomb++) {
            if(getRect(x,y).getBoundsInParent().intersects(arrBomb.get(bomb).getRect().getBoundsInParent())) {
                timeBombStart.set(bomb, (long) 0 );
            }
            for(int stt = 1 ; stt <= lengthLeft ; stt++) {
                int xLocate = x - stt * Boom.Size + 5;
                int yLocate = y + 10;
                if(getRect(xLocate,yLocate).getBoundsInParent()
                        .intersects(arrBomb.get(bomb).getRect().getBoundsInParent())) {
                    timeBombStart.set(bomb , (long) 0);
                    break;
                }
            }
            for(int stt = 1 ; stt <= lengthRight ; stt++) {
                int xLocate = x + stt * Boom.Size + 5;
                int yLocate = y + 10;
                if(getRect(xLocate, yLocate).getBoundsInParent()
                        .intersects(arrBomb.get(bomb).getRect().getBoundsInParent())) {
                    timeBombStart.set(bomb , (long) 0);
                    break;
                }
            }
            for(int stt = 1 ; stt <= lengthDown ; stt++) {
                int xLocate = x + 5;
                int yLocate = y + stt * Boom.Size - 5;
                if(getRect(xLocate,yLocate).getBoundsInParent()
                        .intersects(arrBomb.get(bomb).getRect().getBoundsInParent())) {
                    timeBombStart.set(bomb , (long) 0);
                    break;
                }
            }
            for(int stt = 1 ; stt <= lengthUp ; stt++) {
                int xLocate = x + 5;
                int yLocate = y - stt * Boom.Size + 5;
                if(getRect(xLocate, yLocate).getBoundsInParent()
                        .intersects(arrBomb.get(bomb).getRect().getBoundsInParent())) {
                    timeBombStart.set(bomb , (long) 0);
                    break;
                }
            }
        }
    }

    public void checkExplodeBoom_Enemy(ArrayList<Enemy> arrEnemy) {
        for (int i = 0; i < arrEnemy.size(); i++) {
            try {
                if (getRect(x,y).getBoundsInParent().intersects(arrEnemy.get(i).getRect().getBoundsInParent())) {
                    xEnemyDie = arrEnemy.get(i).getX();
                    yEnemyDie = arrEnemy.get(i).getY();
                    arrEnemy.remove(i);
                }
                for (int j = 1; j <= lengthLeft; j++) {
                    int xRaw = x - j * Boom.Size + 5;
                    int yRaw = y + 5;
                    if (getRect(xRaw, yRaw).getBoundsInParent().intersects(arrEnemy.get(i).getRect().getBoundsInParent())) {
                        xEnemyDie = arrEnemy.get(i).getX();
                        yEnemyDie = arrEnemy.get(i).getY();
                        arrEnemy.remove(i);
                    }
                }

                for (int j = 1; j <= lengthRight; j++) {
                    int xRaw = x + j * Boom.Size + 5;
                    int yRaw = y + 5;
                    if (getRect(xRaw,yRaw).getBoundsInParent().intersects(arrEnemy.get(i).getRect().getBoundsInParent())) {
                        xEnemyDie = arrEnemy.get(i).getX();
                        yEnemyDie = arrEnemy.get(i).getY();
                        arrEnemy.remove(i);
                    }
                }

                for (int j = 1; j <= lengthUp; j++) {
                    int xRaw = x + 5;
                    int yRaw = y - j * Boom.Size + 5;
                    if (!getRect(xRaw,yRaw).getBoundsInParent().intersects(arrEnemy.get(i).getRect().getBoundsInParent())) {
                        xEnemyDie = arrEnemy.get(i).getX();
                        yEnemyDie = arrEnemy.get(i).getY();
                        arrEnemy.remove(i);
                    }
                }

                for (int j = 1; j <= lengthDown; j++) {
                    int xRaw = x + 5;
                    int yRaw = y + j * Boom.Size + 5;
                    if (getRect(xRaw,yRaw).getBoundsInParent().intersects(arrEnemy.get(i).getRect().getBoundsInParent())) {
                        xEnemyDie = arrEnemy.get(i).getX();
                        yEnemyDie = arrEnemy.get(i).getY();
                        arrEnemy.remove(i);
                    }
                }

            } catch (IndexOutOfBoundsException e) {
            }
        }
    }
}
