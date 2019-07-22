/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Fish;
import static aBasis.Global.*;
import aBasis.PlayMusic;
import aDataAccess.DatabaseInit;
import aSurface.ControllerPanel;
import aSurface.GraphicPanel;
import aSurface.StartPanel;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author b6dmin
 * @param <T>
 */
public class Control<T> implements Runnable {

    private static Control instance;

    private StartPanel STARTPANEL;

    public void setSTARTPANEL(StartPanel s) {
        this.STARTPANEL = s;
    }

    private ControllerPanel<T> CONTROLLERPANEL;
    private GraphicPanel GRAPHICPANEL;

    public void setCONTROLLERPANEL(ControllerPanel c) {
        this.CONTROLLERPANEL = c;
    }

    public void setGRAPHICPANEL(GraphicPanel g) {
        this.GRAPHICPANEL = g;
    }

    public static Control getInstance() {
        if (instance == null) {
            instance = new Control();
        }
        return instance;
    }

    private Control() {
    }

    List<T> fishList = new CopyOnWriteArrayList<>();

    void start() {
        CONTROLLERPANEL.listFishes(fishList);
        Fish.setAquariumHeigth(GRAPHICPANEL.getHeight());
        Fish.setAquariumWidth(GRAPHICPANEL.getWidth());
        Fish.setWaterLevel((int) (GRAPHICPANEL.getHeight() * WATER_AIR_RATIO));
        Fish.setTurnLimit(TURN_LIMIT);
    }

    private PlayMusic playMusic = new PlayMusic();
    private int startFrame;

    public void playSong() {
        if (!playMusic.isAlive()) {
            System.out.println("Control.playSong()");
            if (!playMusic.isPaused()) {
                startFrame = 0;
            }
            playMusic = new PlayMusic();
            playMusic.setControl(this);
            playMusic.setPausedOnFrame(startFrame);
            playMusic.setMusic(MUSIC_URL);
            PlayMusic.setFrameRate(24);
            playMusic.start();
        }
    }

    public void noSong() {
        if (playMusic.isAlive()) {
            System.out.println("Control.noSong()");
            startFrame += playMusic.noPlay();
        }
    }

    public void playOnOff() {
        System.out.println("Control.playOnOff()" + playMusic.isAlive());
        if (playMusic.isAlive()) {
            noSong();
        } else {
            playSong();
        }
    }

    public void setStartSoundEvent(boolean state) {
        PlayMusic.setLooping(LOOP);
    }

    public void setFinishSoundEvent(boolean state) {
        CONTROLLERPANEL.setMusicButton(state);
    }

    public void setPauseSoundEvent(boolean state) {
        CONTROLLERPANEL.setMusicButton(state);
    }

    List<Fish> fishInWaterList = new CopyOnWriteArrayList<>();

    public void objectProcessing(T o) {
        if (o instanceof Fish) {
            Fish fish = (Fish) o;
            int kX = (int) (Math.random()
                    * (Fish.getAquariumWidth() - fish.getFaceWidth() - STEP_MAX));
            int kY = (int) (Math.random()
                    * (Fish.getAquariumHeigth() - Fish.getWaterLevel()
                    - fish.getFaceHeight() - STEP_MAX) + Fish.getWaterLevel());
            long time = (long) (Math.random() * (TIME_MAX - TIME_MIN) + TIME_MIN);
            int newStepX = (int) (STEP_MAX * Math.random() + 1);
            int stepx = (Math.random() < 0.5) ? newStepX : -newStepX;
            int newStepY = (int) (stepx * Math.random() + 1);
            int stepy = (Math.random() < 0.5) ? newStepY : -newStepY;

            fish.setFish(time, true, stepx, stepy);
            GRAPHICPANEL.setComponent(fish, kX, kY);

            if (!fishInWaterList.isEmpty()) {
                fish.setSwiming(fishInWaterList.get(0).isSwiming());
            } else {
                fish.setSwiming(true);
            }
            fishInWaterList.add(fish);
            fishList.remove((T) fish);
            fish.start();
        }
    }

    public void hitFish(int x, int y) {
        boolean hitOneFish = false;
        for (Fish fish : fishInWaterList) {
            if (fish.isThisFish(x, y)) {
                System.out.println("fish: " + fish);
                fishInWaterList.remove(fish);
                GRAPHICPANEL.deleteComponent(fish);
                fishList.add((T) fish);
                CONTROLLERPANEL.listFishes(fishList);
                hitOneFish = true;
                break;
            }
        }
        if (!hitOneFish) {
            fishInWaterList.forEach((fish) -> {
                fish.changeState();
            });
        }
    }

    @Override
    public void run() {
        DatabaseInit myDatabaseInit = new DatabaseInit();
        fishList = new CopyOnWriteArrayList<>();
        fishList = myDatabaseInit.openDataBase();
        STARTPANEL.setStateReady();
    }
}
