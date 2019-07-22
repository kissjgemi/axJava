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

    public void hunterMoving(int x, int y) {

    }

    public void deleteButterfly(Butterfly aThis) {

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

    }

    private void stopGame() {

    }
}
