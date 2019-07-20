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
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author b6dmin
 */
public class Control implements Runnable {

    private static Control instance;

    private StartPanel STARTPANEL;

    public void setSTARTPANEL(StartPanel s) {
        this.STARTPANEL = s;
    }

    private ControllerPanel CONTROLLERPANEL;
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

    List<Fish> fishList = new CopyOnWriteArrayList<>();

    void start() {
        CONTROLLERPANEL.listFishes(fishList);
        Fish.setAquariumHeigth(GRAPHICPANEL.getHeight());
        Fish.setAquariumWidth(GRAPHICPANEL.getWidth());
        Fish.setWaterLevel((int) (GRAPHICPANEL.getHeight() * WATER_AIR_RATIO));
        Fish.setTurnLimit(TURN_LIMIT);
    }

    private PlayMusic playMusic = new PlayMusic();
    private final boolean LOOP = true;
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

    public void setStartSoundEvent(boolean state) {
        PlayMusic.setLooping(LOOP);
    }

    public void setFinishSoundEvent(boolean state) {
        CONTROLLERPANEL.setMusicButton(state);
    }

    public void setPauseSoundEvent(boolean state) {
        CONTROLLERPANEL.setMusicButton(state);
    }

    public void playOnOff() {
        System.out.println("Control.playOnOff()" + playMusic.isAlive());
        if (playMusic.isAlive()) {
            noSong();
        } else {
            playSong();
        }
    }

    public void refreshPanel() {
        GRAPHICPANEL.repaint();
    }

    List<Fish> fishInWaterList = new CopyOnWriteArrayList<>();

    public void swimFish(Fish fish) {
        int imageX = (int) (Math.random()
                * (Fish.getAquariumWidth() - fish.getFaceWidth() - STEP_MAX));
        int imageY = (int) (Math.random()
                * (Fish.getAquariumHeigth() - Fish.getWaterLevel()
                - fish.getFaceHeight() - STEP_MAX) + Fish.getWaterLevel());
        long time = (long) (Math.random() * (TIME_MAX - TIME_MIN) + TIME_MIN);
        int newStepX = (int) (STEP_MAX * Math.random() + 1);
        int stepx = (Math.random() < 0.5) ? newStepX : -newStepX;
        int newStepY = (int) (stepx * Math.random() + 1);
        int stepy = (Math.random() < 0.5) ? newStepY : -newStepY;

        fish.setFish(imageX, imageY, time, true, stepx, stepy, this);

        if (!fishInWaterList.isEmpty()) {
            fish.setSwiming(fishInWaterList.get(0).isSwiming());
        } else {
            fish.setSwiming(true);
        }

        fishInWaterList.add(fish);
        fish.start();
        refreshPanel();
    }

    public void drawFishes(Graphics g) {
        fishInWaterList.forEach((fish) -> {
            fish.drawFish(g);
        });
    }

    public void hitFish(int x, int y) {
        boolean hitOneFish = false;
        for (Fish fish : fishInWaterList) {
            if (fish.isThisFish(x, y)) {
                takeOut(fish);
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

    private void takeOut(Fish fish) {
        fish.setActive(false);
        fishInWaterList.remove(fish);
        refreshPanel();
    }

    @Override
    public void run() {
        DatabaseInit myDatabaseInit = new DatabaseInit();
        fishList = new ArrayList<>();
        fishList = myDatabaseInit.openDataBase();
        STARTPANEL.setStateReady();
    }
}
