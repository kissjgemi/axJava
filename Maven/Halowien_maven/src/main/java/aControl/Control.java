/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.PlayMusic;
import aSurface.Battle;
import aSurface.Hallowien;
import aSurface.Languages;
import aSurface.Warriors;
import aSurface.Welcome;
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

    void start() {
        System.out.println("Control.start");
        try {
            playSong();
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
