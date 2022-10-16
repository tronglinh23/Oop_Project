package Item_Bomb;

import Enemy.Enemy;
import Enemy.Present;
import Enemy.Fast;
import Enemy.Octopus;
import Player.MainPlayer;
import Map.TileMap;
import Others.ImageUtils;
import Others.SoundLoad;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.Random;

/**
 * Create class waveBomb.
 * Handle all functions.
 */
public class WaveBoom {
    private int x;
    private int y;
    private int lengthLeft;
    private int lengthRight;
    private int lengthUp;
    private int lengthDown;
    private Image[] images;
    private double xEnemyDie;
    private double yEnemyDie;

    private int imageIndex = 0;

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

    private Clip soundTouch; // sound when touching enemy

    public WaveBoom(int x, int y, int lengthWave) {
        this.x = x;
        this.y = y;
        this.lengthLeft = this.lengthRight = this.lengthDown = this.lengthUp =  lengthWave;
        soundTouch = SoundLoad.getSoundVolume("src/main/resources/sounds/waveTouch.wav", 0);
    }

    public Rectangle getRect(int x, int y) {
        Rectangle rec = new Rectangle(x + 5, y + 5, Boom.Size - 20, Boom.Size - 20);
        return rec;
    }

    public void draw(ArrayList<TileMap> arrTileMap, GraphicsContext gc) {
        drawMidWave(arrTileMap, gc);
        drawLeftWave(arrTileMap, gc);
        drawRightWave(arrTileMap, gc);
        drawUpWave(arrTileMap, gc);
        drawDownWave(arrTileMap, gc);
        drawEnemyDie(gc);
    }

    /**
     * draw left wave, handle collision with map
     * @param arrTileMap arrTileMap
     * @param gc gcontext
     */
    private void drawLeftWave(ArrayList<TileMap> arrTileMap, GraphicsContext gc) {
        for(int stt = 1 ; stt <= lengthLeft ; stt++) {
            int xLocate = x - stt * Boom.Size + 5;
            int yLocate = y + 5;
            if(stt == lengthLeft) {
                gc.drawImage(WAVE_IMG[1],xLocate, yLocate);
            } else {
                gc.drawImage(WAVE_IMG[0],xLocate,yLocate);
            }
            for (int i = 0; i < arrTileMap.size(); i++) {
                if (getRect(xLocate,yLocate).getBoundsInParent().intersects(arrTileMap.get(i).getRect().getBoundsInParent())) {
                    if (TileMap.levelGame == 0) {
                        if (arrTileMap.get(i).locate_bit == 3 || arrTileMap.get(i).locate_bit == 4
                                || arrTileMap.get(i).locate_bit == 5 || arrTileMap.get(i).locate_bit == 6) {
                            arrTileMap.get(i).locate_bit = 0;
                            lengthLeft -= (lengthLeft - stt);

                        } else if (arrTileMap.get(i).locate_bit == 1 || arrTileMap.get(i).locate_bit == 2
                                || arrTileMap.get(i).locate_bit == 8 || arrTileMap.get(i).locate_bit == 9) {
                            lengthLeft -= (lengthLeft - stt);
                        }
                    } else if (TileMap.levelGame == 1) {
                        if (arrTileMap.get(i).locate_bit == 1 || arrTileMap.get(i).locate_bit == 2
                                || arrTileMap.get(i).locate_bit == 5 ) {
                            arrTileMap.get(i).locate_bit = 0;
                            lengthLeft -= (lengthLeft - stt);

                        } else if (arrTileMap.get(i).locate_bit == 3 || arrTileMap.get(i).locate_bit == 4) {
                            lengthLeft -= (lengthLeft - stt);
                        }
                    }

                }
            }
        }
    }

    /**
     * draw right wave, handle collision with map
     * @param arrTileMap arrTileMap
     * @param gc gcontext
     */
    private void drawRightWave(ArrayList<TileMap> arrTileMap, GraphicsContext gc) {
        for(int stt = 1 ; stt <= lengthRight ; stt++) {
            int xLocate = x + stt * Boom.Size + 5;
            int yLocate = y + 5;
            if(stt == lengthRight) {
                gc.drawImage(WAVE_IMG[3],xLocate, yLocate);
            } else {
                gc.drawImage(WAVE_IMG[2],xLocate, yLocate);
            }
            for (int i = 0; i < arrTileMap.size(); i++) {
                if (getRect(xLocate,yLocate).getBoundsInParent().intersects(arrTileMap.get(i).getRect().getBoundsInParent())) {
                    if (TileMap.levelGame == 0) {
                        if (arrTileMap.get(i).locate_bit == 3 || arrTileMap.get(i).locate_bit == 4
                                || arrTileMap.get(i).locate_bit == 5 || arrTileMap.get(i).locate_bit == 6) {
                            arrTileMap.get(i).locate_bit = 0;
                            lengthRight -= (lengthRight - stt);

                        } else if (arrTileMap.get(i).locate_bit == 1 || arrTileMap.get(i).locate_bit == 2
                                || arrTileMap.get(i).locate_bit == 8 || arrTileMap.get(i).locate_bit == 9) {
                            lengthRight -= (lengthRight - stt);
                        }
                    } else if (TileMap.levelGame == 1) {
                        if (arrTileMap.get(i).locate_bit == 1 || arrTileMap.get(i).locate_bit == 2
                                || arrTileMap.get(i).locate_bit == 5 ) {
                            arrTileMap.get(i).locate_bit = 0;
                            lengthRight -= (lengthRight - stt);

                        } else if (arrTileMap.get(i).locate_bit == 3 || arrTileMap.get(i).locate_bit == 4) {
                            lengthRight -= (lengthRight - stt);
                        }
                    }
                }
            }
        }
    }


    private void drawMidWave(ArrayList<TileMap> arrTileMap, GraphicsContext gc) {
        gc.drawImage(WAVE_IMG[4],x + 5 ,y + 5);
    }

    private void drawDownWave(ArrayList<TileMap> arrTileMap, GraphicsContext gc) {
        for(int stt = 1 ; stt <= lengthDown ; stt++) {
            int xLocate = x + 5;
            int yLocate = y + stt * Boom.Size + 5;
            if(stt == lengthDown) {
                gc.drawImage(WAVE_IMG[6],xLocate, yLocate);
            } else {
                gc.drawImage(WAVE_IMG[5],xLocate, yLocate);
            }
            for (int i = 0; i < arrTileMap.size(); i++) {
                if (getRect(xLocate,yLocate).getBoundsInParent().intersects(arrTileMap.get(i).getRect().getBoundsInParent())) {
                    if (TileMap.levelGame == 0) {
                        if (arrTileMap.get(i).locate_bit == 3 || arrTileMap.get(i).locate_bit == 4
                                || arrTileMap.get(i).locate_bit == 5 || arrTileMap.get(i).locate_bit == 6) {
                            arrTileMap.get(i).locate_bit = 0;
                            lengthDown -= (lengthDown - stt);

                        } else if (arrTileMap.get(i).locate_bit == 1 || arrTileMap.get(i).locate_bit == 2
                                || arrTileMap.get(i).locate_bit == 8 || arrTileMap.get(i).locate_bit == 9) {
                            lengthDown -= (lengthDown - stt);
                        }
                    } else if (TileMap.levelGame == 1) {
                        if (arrTileMap.get(i).locate_bit == 1 || arrTileMap.get(i).locate_bit == 2
                                || arrTileMap.get(i).locate_bit == 5) {
                            arrTileMap.get(i).locate_bit = 0;
                            lengthDown -= (lengthDown - stt);

                        } else if (arrTileMap.get(i).locate_bit == 3 || arrTileMap.get(i).locate_bit == 4) {
                            lengthDown -= (lengthDown - stt);
                        }
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
                if (getRect(xLocate,yLocate).getBoundsInParent().intersects(arrTileMap.get(i).getRect().getBoundsInParent())) {
                    if (TileMap.levelGame == 0) {
                        if (arrTileMap.get(i).locate_bit == 3 || arrTileMap.get(i).locate_bit == 4
                                || arrTileMap.get(i).locate_bit == 5 || arrTileMap.get(i).locate_bit == 6) {
                            arrTileMap.get(i).locate_bit = 0;
                            lengthUp -= (lengthUp - stt);

                        } else if (arrTileMap.get(i).locate_bit == 1 || arrTileMap.get(i).locate_bit == 2
                                || arrTileMap.get(i).locate_bit == 8 || arrTileMap.get(i).locate_bit == 9) {
                            lengthUp -= (lengthUp - stt);
                        }
                    } else if (TileMap.levelGame == 1) {
                        if (arrTileMap.get(i).locate_bit == 1 || arrTileMap.get(i).locate_bit == 2
                                || arrTileMap.get(i).locate_bit == 5) {
                            arrTileMap.get(i).locate_bit = 0;
                            lengthUp -= (lengthUp - stt);

                        } else if (arrTileMap.get(i).locate_bit == 3 || arrTileMap.get(i).locate_bit == 4) {
                            lengthUp -= (lengthUp - stt);
                        }
                    }
                }
            }

        }
    }

    /**
     * check collision wave bomb - player.
     * set time player die.
     * @param mainPlayer mainPlayer
     * @param time time
     */
    public void checkBoom_Player(MainPlayer mainPlayer, long time) {
        if (getRect(x  + 5,y + 5).getBoundsInParent().intersects(mainPlayer.getRect().getBoundsInParent())) {
            mainPlayer.setIsDie(true,time);
        }
        for (int j = 1; j <= lengthLeft; j++) {
            int xRaw = x - j * Boom.Size + 5;
            int yRaw = y + 5;
            if (getRect(xRaw + 5, yRaw + 5).getBoundsInParent().intersects(mainPlayer.getRect().getBoundsInParent())) {
                mainPlayer.setIsDie(true,time);
            }
        }

        for (int j = 1; j <= lengthRight; j++) {
            int xRaw = x + j * Boom.Size + 5;
            int yRaw = y + 5;
            if (getRect(xRaw,yRaw).getBoundsInParent().intersects(mainPlayer.getRect().getBoundsInParent())) {
                mainPlayer.setIsDie(true,time);
            }
        }

        for (int j = 1; j <= lengthUp; j++) {
            int xRaw = x + 5;
            int yRaw = y - j * Boom.Size + 5;
            if (getRect(xRaw,yRaw).getBoundsInParent().intersects(mainPlayer.getRect().getBoundsInParent())) {
                mainPlayer.setIsDie(true,time);
            }
        }

        for (int j = 1; j <= lengthDown; j++) {
            int xRaw = x + 5;
            int yRaw = y + j * Boom.Size + 5;
            if (getRect(xRaw, yRaw).getBoundsInParent().intersects(mainPlayer.getRect().getBoundsInParent())) {
                mainPlayer.setIsDie(true,time);
            }
        }
    }

    /**
     * check wave bomb to bomb.
     * set time Bomb explode.
     * @param arrBomb arrBomb
     * @param timeBombStart timeBombStart
     */
    public void checkExplodeBoom_Boom(ArrayList<Boom> arrBomb, ArrayList<Long> timeBombStart) {
        for (int bomb = 0 ; bomb < arrBomb.size() ; bomb++) {
            if(getRect(x + 5, y + 5).getBoundsInParent().intersects(arrBomb.get(bomb).getRect().getBoundsInParent())) {
                timeBombStart.set(bomb, (long) 0 );
            }
            for(int stt = 1 ; stt <= lengthLeft ; stt++) {
                int xLocate = x - stt * Boom.Size + 5;
                int yLocate = y + 5;
                if(getRect(xLocate,yLocate).getBoundsInParent()
                        .intersects(arrBomb.get(bomb).getRect().getBoundsInParent())) {
                    timeBombStart.set(bomb , (long) 0);
                    break;
                }
            }
            for(int stt = 1 ; stt <= lengthRight ; stt++) {
                int xLocate = x + stt * Boom.Size + 5;
                int yLocate = y + 5;
                if(getRect(xLocate, yLocate).getBoundsInParent()
                        .intersects(arrBomb.get(bomb).getRect().getBoundsInParent())) {
                    timeBombStart.set(bomb , (long) 0);
                    break;
                }
            }
            for(int stt = 1 ; stt <= lengthDown ; stt++) {
                int xLocate = x + 5;
                int yLocate = y + stt * Boom.Size + 5;
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

    /**
     * check wave bomb to enemy, decrease life enemy.
     * @param arrEnemy arrEnemy
     * @param arrItemGame arrItemGame
     */
    public void checkExplodeBoom_Enemy(ArrayList<Enemy> arrEnemy, ArrayList<ItemGame> arrItemGame) {
        for (int i = 0; i < arrEnemy.size(); i++) {
            try {
                if ((System.nanoTime() - arrEnemy.get(i).getTimeDie()) / Math.pow(10,9) >= Enemy.timeImmortality) {
                    if (getRect(x, y).getBoundsInParent().intersects(arrEnemy.get(i).getRect().getBoundsInParent())) {
                        xEnemyDie = arrEnemy.get(i).getX();
                        yEnemyDie = arrEnemy.get(i).getY();
                        soundTouch.start();
                        arrEnemy.get(i).decreaseLife();
                        arrEnemy.get(i).setTimeDie(System.nanoTime());
                    } else {
                        for (int j = 1; j <= lengthLeft; j++) {
                            int xRaw = x - j * Boom.Size + 5;
                            int yRaw = y + 5;
                            if (getRect(xRaw, yRaw).getBoundsInParent().intersects(arrEnemy.get(i).getRect().getBoundsInParent())) {
                                xEnemyDie = arrEnemy.get(i).getX();
                                yEnemyDie = arrEnemy.get(i).getY();
                                soundTouch.start();
                                arrEnemy.get(i).decreaseLife();
                                arrEnemy.get(i).setTimeDie(System.nanoTime());
                                break;
                            }
                        }

                        for (int j = 1; j <= lengthRight; j++) {
                            int xRaw = x + j * Boom.Size + 5;
                            int yRaw = y + 5;
                            if (getRect(xRaw, yRaw).getBoundsInParent().intersects(arrEnemy.get(i).getRect().getBoundsInParent())) {
                                xEnemyDie = arrEnemy.get(i).getX();
                                yEnemyDie = arrEnemy.get(i).getY();
                                soundTouch.start();
                                arrEnemy.get(i).decreaseLife();
                                arrEnemy.get(i).setTimeDie(System.nanoTime());
                                break;
                            }
                        }

                        for (int j = 1; j <= lengthUp; j++) {
                            int xRaw = x + 5;
                            int yRaw = y - j * Boom.Size + 5;
                            if (getRect(xRaw, yRaw).getBoundsInParent().intersects(arrEnemy.get(i).getRect().getBoundsInParent())) {
                                xEnemyDie = arrEnemy.get(i).getX();
                                yEnemyDie = arrEnemy.get(i).getY();
                                soundTouch.start();
                                arrEnemy.get(i).decreaseLife();
                                arrEnemy.get(i).setTimeDie(System.nanoTime());
                                break;
                            }
                        }

                        for (int j = 1; j <= lengthDown; j++) {
                            int xRaw = x + 5;
                            int yRaw = y + j * Boom.Size + 5;
                            if (getRect(xRaw, yRaw).getBoundsInParent().intersects(arrEnemy.get(i).getRect().getBoundsInParent())) {
                                xEnemyDie = arrEnemy.get(i).getX();
                                yEnemyDie = arrEnemy.get(i).getY();
                                soundTouch.start();
                                arrEnemy.get(i).decreaseLife();
                                arrEnemy.get(i).setTimeDie(System.nanoTime());
                                break;
                            }
                        }
                    }

                    if (arrEnemy.get(i).getLifeEnemy() <= 0) {
                        arrEnemy.get(i).setIsDie(true);
                        if (arrEnemy.get(i) instanceof Fast) {
                            images = Fast.MY_FAST_DIE;
                        } else if (arrEnemy.get(i) instanceof Octopus) {
                            images = Octopus.MY_OCTOPUS_DIE;
                        } else if (arrEnemy.get(i) instanceof Present) {
                            images = Present.MY_ITEM_ENEMY_DIE;
                            Random random = new Random();
                            int x = (int)arrEnemy.get(i).getX() + Boom.Size/2;
                            int y = (int)arrEnemy.get(i).getY() + Boom.Size/2;
                            x = x - x % Boom.Size;
                            y = y - y % Boom.Size;
                            arrItemGame.add(new ItemGame(x,y,random.nextInt(4)));
                        }
                        arrEnemy.remove(i);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }
    public void drawEnemyDie(GraphicsContext gc) {
        if (images != null) {
            gc.drawImage(images[imageIndex / 10 % images.length], xEnemyDie, yEnemyDie);
            imageIndex++;
        }
    }
}
