/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.*;

/**
 *
 * @author linuxuser
 */
public class PlayMusic extends Thread {

    private String musicFileUrl;
    private InputStream inStream;
    private AdvancedPlayer myPlayer;

    private static double frameRate;
    private static boolean looping;

    private int pausedOnFrame;
    private boolean paused;

    public static void setLooping(boolean looping) {
        PlayMusic.looping = looping;
    }

    public static void setFrameRate(double frameRate) {
        PlayMusic.frameRate = frameRate;
    }

    public void setPausedOnFrame(int pausedOnFrame) {
        this.pausedOnFrame = pausedOnFrame;
    }

    public boolean isPaused() {
        return paused;
    }

    public int getPausedOnFrame() {
        return pausedOnFrame = 0;
    }

    public PlayMusic() {
        myPlayer = null;
    }

    public void setMusic(String mFileUrl) {
        try {
            this.musicFileUrl = mFileUrl;
            inStream = this.getClass().getResourceAsStream(musicFileUrl);
            myPlayer = new AdvancedPlayer(inStream);
            myPlayer.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent evt) {
                    super.playbackFinished(evt);
                    pausedOnFrame = (int) (evt.getFrame() / frameRate);
                    System.out.print("playbackFinished");
                }
            });
        } catch (JavaLayerException e) {
            Logger.getLogger(PlayMusic.class.getName()).log(Level.SEVERE, null, e);
            killPlayer();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("StartAtFrame > " + pausedOnFrame);
            paused = false;
            myPlayer.play(pausedOnFrame, Integer.MAX_VALUE);
        } catch (JavaLayerException e) {
            Logger.getLogger(PlayMusic.class.getName()).log(Level.SEVERE, null, e);
            killPlayer();
        } finally {
            if (!paused) {
                pausedOnFrame = 0;
            }
            System.out.println("FinallyAtFrame> " + pausedOnFrame);
        }
    }

    public int noPlay() {
        paused = true;
        killPlayer();
        return pausedOnFrame;
    }

    private void killPlayer() {
        synchronized (this) {
            if (myPlayer != null) {
                myPlayer.stop();
                myPlayer = null;
            }
        }
    }
}
