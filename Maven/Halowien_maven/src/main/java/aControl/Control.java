/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Ensign;
import aBasis.Fighter;
import aBasis.Paladin;
import aBasis.PaladinCommander;
import aBasis.PlayMusic;
import aBasis.Praetorian;
import aBasis.PraetorianCommander;
import aSurface.Battle;
import aSurface.Hallowien;
import aSurface.Languages;
import aSurface.Warriors;
import aSurface.Welcome;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author linuxuser
 */
public class Control {

    MainFrame MAINFRAME;
    Welcome WELCOME;
    Hallowien HALLOWIEN;
    Warriors WARRIORS;
    Battle BATTLE;
    Languages LANGUAGES;

    public Control(MainFrame mf, Welcome we,
            Hallowien ha, Warriors wa, Battle ba, Languages la) {
        this.MAINFRAME = mf;
        this.WELCOME = we;
        this.HALLOWIEN = ha;
        this.WARRIORS = wa;
        this.BATTLE = ba;
        this.LANGUAGES = la;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public void setLocaleBundle(Locale locale, ResourceBundle rbundle) {
        MAINFRAME.setTextLocale(rbundle);
        WELCOME.setTextLocale(rbundle);
        WARRIORS.setTextLocale(rbundle);
        BATTLE.setTextLocale(rbundle);
    }

    private final boolean LOOP = true;
    private final String MUSIC_SOURCE = "/music/flowers.mp3";
    private PlayMusic playMusic = new PlayMusic();
    ;
    private int startFrame;

    public void playSong() {
        if (!playMusic.isAlive()) {
            System.out.println("Control.playSong()");
            if (!playMusic.isPaused()) {
                startFrame = 0;
            }
            playMusic = new PlayMusic();
            playMusic.setPausedOnFrame(startFrame);
            playMusic.setMusic(MUSIC_SOURCE);
            PlayMusic.setFrameRate(24);
            PlayMusic.setLooping(LOOP);
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

    private void listFighters(boolean sort) {
        System.out.println("Control.listFighters");
        WARRIORS.listFighters(fighterList, sort);
    }

    List<Fighter> fighterList;

    private final int RADIUS = 10;
    private final Color PRAETORIAN_COLOR = Color.YELLOW;
    private final Color PRAETORIAN_COMMANDER_COLOR = Color.GREEN;
    private final Color PALADIN_COLOR = Color.RED;
    private final Color PALADIN_COMMANDER_COLOR = Color.BLUE;
    private final Color ENSIGN_COLOR = Color.PINK;

    private final double CHANCE2WOUND = 0.4;

    private final double TILE_SCREEN = 0.5;
    private int heightOfDrawing;

    private void setting() {
        Fighter.setRadius(RADIUS);
        Praetorian.setMyColor(PRAETORIAN_COLOR);
        PraetorianCommander.setMyColor(PRAETORIAN_COMMANDER_COLOR);
        Paladin.setMyColor(PALADIN_COLOR);
        PaladinCommander.setMyColor(PALADIN_COMMANDER_COLOR);
        Ensign.setMyColor(ENSIGN_COLOR);
        this.heightOfDrawing = (int) (BATTLE.getHeight() * TILE_SCREEN);
    }

    void start() {
        System.out.println("Control.start");
        try {
            playSong();
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
        DatabaseInit myDatabaseInit = new DatabaseInit();
        fighterList = new ArrayList<>();
        setting();
        fighterList = myDatabaseInit.openDataBase();
        listFighters(false);
        MAINFRAME.enableHalloWienPanel(true);
        MAINFRAME.LOADED = true;
    }

    List<Fighter> fightersInBattle = new ArrayList<>();

    public void beginBattle(List<Fighter> selectedFighters) {
        fightersInBattle = selectedFighters;
        for (Fighter fighter : fightersInBattle) {
            fighter.setFig_x((int) (Math.random() * (BATTLE.getWidth()
                    - 2 * Fighter.getRadius()) + Fighter.getRadius()));
            fighter.setFig_y((int) (Math.random() * (BATTLE.getHeight()
                    - heightOfDrawing - Fighter.getRadius()) + heightOfDrawing));
        }
        BATTLE.repaint();
    }

    public void paintFigthers(Graphics g) {
        System.out.println("Control.paintFigthers: " + fightersInBattle.size());
        for (Fighter fighter : fightersInBattle) {
            fighter.drawOnField(g);
        }
    }

    public void clickedOnBattlefield(int x, int y) {
        for (Fighter fighter : fightersInBattle) {
            if (fighter.isClicked(x, y)) {
                System.out.print(fighter.getNAME());
                if (Math.random() < CHANCE2WOUND) {
                    System.out.println(" megsérült!");
                    fighter.wounded();
                } else {
                    System.out.println(" támadásba lendült!");
                    fighter.attack();
                }
                WARRIORS.refreshFigthersList(fighter);
                if (!fighter.isAlive()) {
                    System.out.println("\nSebesülése halálos volt.\n");
                    fightersInBattle.remove(fighter);
                    BATTLE.repaint();
                }
                break;
            }
        }
    }
}
