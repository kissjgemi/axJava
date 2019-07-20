/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import aControl.Control;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 *
 * @author b6dmin
 */
public class PlayMusic extends Thread {

    private Control c;

    public void setControl(Control c) {
        this.c = c;
    }

    private String musicFileUrl;
    private InputStream inStream;
    private AdvancedPlayer myPlayer;

    private static double frameRate;
    private static boolean looping;

    private int pausedOnFrame;
    private boolean paused;
    private boolean finished;

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

    public boolean isFinished() {
        return finished;
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
                    System.out.println("playbackFinished");
                }
            });
        } catch (JavaLayerException e) {
            e.getMessage();
            killPlayer();
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("StartAtFrame > " + pausedOnFrame);
            paused = false;
            finished = false;
            c.setStartSoundEvent(!finished);
            c.setFinishSoundEvent(finished);
            myPlayer.play(pausedOnFrame, Integer.MAX_VALUE);
        } catch (JavaLayerException e) {
            e.getMessage();
            killPlayer();
        } finally {
            System.out.println("stoppedAtFrame> " + pausedOnFrame);
            if (!paused) {
                pausedOnFrame = 0;
                finished = true;
                c.setFinishSoundEvent(finished);
                System.out.println("finally finished: " + finished);
            }
        }
    }

    public int noPlay() {
        paused = true;
        c.setPauseSoundEvent(paused);
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
