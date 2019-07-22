/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.Butterfly;
import static aBasis.Global.*;
import aBasis.Hunter;
import aBasis.Player;
import aSurface.GamePanel;
import aSurface.PlayersPanel;
import aSurface.ResultsPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b6dmin
 */
public class Control implements Runnable {

    private final GamePanel GAMEPANEL;
    private final PlayersPanel PLAYERSPANEL;
    private final ResultsPanel RESULTSPANEL;

    public Control(GamePanel gp, PlayersPanel pp, ResultsPanel rp) {
        this.GAMEPANEL = gp;
        this.PLAYERSPANEL = pp;
        this.RESULTSPANEL = rp;
    }

    private List<Image> images = new ArrayList<>();
    private List<Butterfly> butterflies = new ArrayList<>();
    private Hunter hunter;
    private Player player;
    private List<Player> playersList = new ArrayList<>();

    public void setupGame() {
        Hunter.setFaceHeight(HUNTER_HEIGHT);
        Hunter.setFaceWidth(HUNTER_WIDTH);

        Butterfly.setAreaWidth(GAMEPANEL.getWidth());
        Butterfly.setAreaHeight(GAMEPANEL.getHeight());
    }

    public void startGame(Player player) {
        this.player = player;
        hunter = new Hunter(GAMEPANEL.getWidth() - HUNTER_WIDTH / 2,
                GAMEPANEL.getHeight() - HUNTER_HEIGHT / 2);
        refreshScreen();

        Thread thread = new Thread(this);
        thread.start();
    }

    private void stopGame() {
        butterflies.clear();
        hunter = null;
        refreshScreen();
    }

    public void drawGame(Graphics g) {
        butterflies.forEach((butterfly) -> {
            butterfly.drawImage(g);
        });
        if (hunter != null) {
            hunter.drawImage(g);
        }
    }

    public void refreshScreen() {
        GAMEPANEL.repaint();
    }

    private void butterflyCatch(int netMiddleX, int netMiddleY) {
        butterflies.forEach((butterfly) -> {
            butterfly.butterflyCatch(netMiddleX, netMiddleY);
        });
    }

    public void moveHunter(int x, int y) {
        if (hunter != null && hunter.hunterCatch(x, y)) {
            hunter.setImageX(x);
            hunter.setImageY(y);

            int netMiddleX = x - HUNTER_WIDTH / 4;
            int netMiddleY = y - HUNTER_HEIGHT / 4;

            butterflyCatch(netMiddleX, netMiddleY);
        }
    }

    public void mouseDragged(int x, int y) {
        moveHunter(x, y);
    }

    @Override
    public void run() {
        long remainingTime = GAME_TIME * 1000;
        while (remainingTime >= 0) {
            try {
                RESULTSPANEL.writeTime(remainingTime / 1000);
                startButterfly();
                Thread.sleep(REBIRTH_TIME);
                remainingTime -= REBIRTH_TIME;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        RESULTSPANEL.finishGame();
        PlayersPanel.finishGame(player);
        stopGame();
    }

    private void startButterfly() {
        int imageWidth = (int) (Math.random()
                * (FACE_SIZE_MAX - FACE_SIZE_MIN) + FACE_SIZE_MIN);
        int imageHeight = (int) (Math.random()
                * (FACE_SIZE_MAX - FACE_SIZE_MIN) + FACE_SIZE_MIN);

        int imageX = (int) (Math.random() * (GAMEPANEL.getWidth() - imageWidth));
        int imageY = (int) (Math.random() * (GAMEPANEL.getHeight() - imageHeight));

        long time = (int) (Math.random() * (TIME_MAX - TIME_MIN) + TIME_MIN);

        int imageIndex = (int) (Math.random() * images.size());
        Image image = images.get(imageIndex);

        int dx = (Math.random() < 0.5) ? 1 : -1;
        int dy = (Math.random() < 0.5) ? 1 : -1;

        Butterfly butterfly = new Butterfly(image, imageX, imageY,
                imageWidth, imageHeight, dx, dy, time, this);

        butterflies.add(butterfly);
        butterfly.start();
    }

    public void deleteButterfly(Butterfly b) {
        butterflies.remove(b);
        player.getPoints(1);
        RESULTSPANEL.writeScore(player.getScore());
    }
}
