/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Bird;
import aBasis.Feeder;
import static aBasis.Global.*;
import aBasis.ImagePair;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
class BirdFactory extends Thread {

    private static BirdFactory instance;
    private static final List<ImagePair> birdFaces = new ArrayList<>();
    private static long sleepTime;
    private static boolean active;

    public static void setActive(boolean b) {
        BirdFactory.active = b;
    }

    private List<Feeder> targets;
    private final List<Bird> birds;

    public List<Bird> getBirds() {
        return birds;
    }

    private BirdFactory() {
        setBirdFaces();
        sleepTime = BIRD_BIRTH_GAP;
        this.birds = new ArrayList<>();
        this.targets = new ArrayList<>();
    }

    public static BirdFactory getInstance() {
        if (instance == null) {
            instance = new BirdFactory();
        }
        return instance;
    }

    public void setTargets(List<Feeder> list) {
        targets = list;
    }

    private void setBirdFaces() {
        for (int ii = 0; ii < BIRD_NR; ii++) {
            Image LeftView = new ImageIcon(this.
                    getClass().getResource(BIRD_LEFT + ii + ".png")).getImage();
            Image RightView = new ImageIcon(this.
                    getClass().getResource(BIRD_RIGHT + ii + ".png")).getImage();
            System.out.println("add: > madar" + ii + ".png");
            birdFaces.add(new ImagePair(LeftView, RightView));
        }
    }

    public Bird getObject() {
        int firstx = 0;
        int firsty = (int) (Math.random() * (GRAPHITY_HEIGHT / 2));
        if (Math.random() < 0.5) {
            firstx = (int) (Math.random() * (GRAPHITY_WIDTH - BIRD_FACE_SIZE));
            firsty = BIRD_FACE_SIZE;
        } else {
            if (Math.random() < 0.5) {
                firstx = GRAPHITY_WIDTH - BIRD_FACE_SIZE;
            }
        }
        int index = (int) (Math.random() * targets.size());
        Feeder target = targets.get(index);
        int lastx = target.getDx();
        int lasty = target.getDy();
        index = (int) (Math.random() * birdFaces.size());
        Bird bird = new Bird(birdFaces.get(index), firstx, firsty, lastx, lasty);
        bird.setTarget(target);
        return bird;
    }

    public void removeBird(Bird bird) {
        birds.remove(bird);
        //bird = null;
    }

    @Override
    public void run() {
        Bird bird;
        while (active) {
            bird = getObject();
            bird.setSpeed(BIRD_SPEED);
            birds.add(bird);
            bird.start();
            sleepThread(sleepTime);
        }
    }

    private void sleepThread(long gap) {
        try {
            Thread.sleep(gap);
        } catch (InterruptedException ex) {
            Logger.getLogger(BirdFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
