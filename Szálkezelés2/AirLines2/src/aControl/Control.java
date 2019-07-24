/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Line;
import aBasis.LineInBlue;
import aBasis.LineInGreen;
import aBasis.LineInRed;
import static aDatas.AirLinesDatas.*;
import aSurface.MainPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author b6dmin
 */
public class Control extends Thread {

    private final MainFrame MAINFRAME;
    private final MainPanel MAINPANEL;

    Control(MainFrame mf, MainPanel mp) {
        this.MAINFRAME = mf;
        this.MAINPANEL = mp;
    }

    public void refreshDrawing() {
        MAINPANEL.repaint();
    }

    void setup() {
        System.out.println("Control.start()");
        MAINFRAME.setControl();
        Line.setSpeed(SPEED);
        Line.setControl(this);
    }

    public void drawSartPoints(Graphics g) {
        //System.out.println("Control.drawSartPoints(): > " + pointList.size());
        pointList.forEach((point) -> {
            point.drawStartPoint(g);
        });
    }

    public void drawSartLines(Graphics g) {
        //System.out.println("Control.drawSartLines(): > " + lineList.size());
        lineList.forEach((line) -> {
            line.drawLine(g);
        });
    }

    private final List<Thread> movingLines = new ArrayList<>();
    private int threadNr = 0;

    public void drawPointOnLine(Graphics g) {
        for (int ii = movingLines.size() - 1; ii >= 0; ii--) {
            Thread movingLine = movingLines.get(ii);
            if (movingLine.getState() == Thread.State.TERMINATED) {
                System.out.println("Threat deleted: > "
                        + movingLine.getName());
                movingLines.remove(movingLine);
            } else {
                ((Line) movingLine).drawPointOnLine(g);
            }
        }
    }

    private int randomNr(int max, int min) {
        return (int) (Math.random() * (max - min) + min);
    }

    public void doOnOff() {
        onOff = !onOff;
        if (onOff) {
            threadNr = 0;
            pointList.clear();
            lineList.clear();
            initPoints(MAINPANEL.getWidth(), MAINPANEL.getHeight());
            initLines();
            refreshDrawing();
        }
    }

    private int maxThreads = 10;

    private ExecutorService es = Executors.newFixedThreadPool(maxThreads);

    @Override
    public void run() {
        while (true) {
            while (threadNr < maxThreads) {
                if (onOff) {
                    threadNr++;
                    int p2;
                    int p1 = p2 = randomNr(pointList.size(), 0);
                    while (p1 == p2) {
                        p2 = randomNr(pointList.size(), 0);
                    }
                    Thread newLine;
                    double newChances = Math.random();
                    if (newChances < RED_CHANCES) {
                        movingLines.add(newLine = new LineInRed(
                                pointList.get(p1).getPx(),
                                pointList.get(p1).getPy(),
                                pointList.get(p2).getPx(),
                                pointList.get(p2).getPy()));
                    } else if (newChances < BLUE_CHANCES + RED_CHANCES) {
                        movingLines.add(newLine = new LineInBlue(
                                pointList.get(p1).getPx(),
                                pointList.get(p1).getPy(),
                                pointList.get(p2).getPx(),
                                pointList.get(p2).getPy()));
                    } else {
                        movingLines.add(newLine = new LineInGreen(
                                pointList.get(p1).getPx(),
                                pointList.get(p1).getPy(),
                                pointList.get(p2).getPx(),
                                pointList.get(p2).getPy()));
                    }

                    newLine.setName(newLine.getClass().getSimpleName()
                            + threadNr);
                    try {
                        //newLine.start();
                        es.execute(newLine);
                        System.out.println("Threat started: > "
                                + newLine.getName());
                        System.out.println("Threads numbers: "
                                + movingLines.size());
                        Thread.sleep((long) (Math.random()
                                * RESTART_IN_MILLIS + RESTART_IN_MILLIS / 2));
                    } catch (InterruptedException e) {
                        System.out.println("error in threat: "
                                + newLine.getName());
                    }
                }
            }
            es.shutdown();
            while (!es.isTerminated()) {
            }
            threadNr = 0;
            movingLines.clear();
            if (maxThreads > 2) {
                maxThreads--;
            } else if (maxThreads <= 2) {
                maxThreads = 10;
            }
            es = Executors.newFixedThreadPool(maxThreads);
            System.out.println("Executor terminared!");
        }
    }
}
