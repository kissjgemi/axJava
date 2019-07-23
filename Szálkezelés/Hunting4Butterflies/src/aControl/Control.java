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
import aDataAccess.MyDAO;
import aSurface.GamePanel;
import aSurface.PlayersPanel;
import aSurface.ResultsPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author b6dmin
 */
public class Control implements Runnable {

    private final GamePanel GAMEPANEL;
    private final PlayersPanel PLAYERSPANEL;
    private final ResultsPanel RESULTSPANEL;
    private final MainFrame MAINFRAME;

    public Control(GamePanel gp, PlayersPanel pp,
            ResultsPanel rp, MainFrame mf) {
        this.GAMEPANEL = gp;
        this.PLAYERSPANEL = pp;
        this.RESULTSPANEL = rp;
        this.MAINFRAME = mf;
        System.out.println("Control()>" + this.RESULTSPANEL.getBounds());
    }

    private List<Image> images = new ArrayList<>();
    private final List<Butterfly> butterflies = new ArrayList<>();
    private Hunter hunter;
    private Player player;
    private List<Player> playersList = new ArrayList<>();

    public void setupGame() {
        System.out.println("c.setupGame()> Hunter("
                + HUNTER_WIDTH + ":" + HUNTER_HEIGHT + ") on Field("
                + GAMEPANEL.getWidth() + ":" + GAMEPANEL.getHeight() + ").");
        Hunter.setFaceHeight(HUNTER_HEIGHT);
        Hunter.setFaceWidth(HUNTER_WIDTH);

        Butterfly.setAreaWidth(GAMEPANEL.getWidth());
        Butterfly.setAreaHeight(GAMEPANEL.getHeight());
    }

    public void startGame(Player player) {
        System.out.println("c.startGame(): > " + player);
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

    private long remainingTime = 0;

    @Override
    public void run() {
        remainingTime = GAME_TIME * 1000;
        while (remainingTime >= 0) {
            try {
                System.out.println("c.run()> idő:" + remainingTime + " millisec.");
                RESULTSPANEL.writeTime(remainingTime / 1000);
                startButterfly();
                Thread.sleep(REBIRTH_TIME);
                remainingTime -= REBIRTH_TIME;
            } catch (InterruptedException e) {
                System.out.println("run-error: " + e.getMessage());
            }
        }
        RESULTSPANEL.finishGame();
        PLAYERSPANEL.finishGame(player);
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

    public int getPlayerScore() {
        if (player == null) {
            return 0;
        }
        return player.getScore();
    }

    public long getRemainingTime() {
        return remainingTime;
    }

    public void deleteButterfly(Butterfly b) {
        butterflies.remove(b);
        player.addPoints(1);
        RESULTSPANEL.writeScore(player.getScore());
    }

    public void putDatas(List<Image> images, List<Player> players) {
        System.out.print("c.putDatas: ");
        this.images = images;
        System.out.print(this.images.size() + " db lepke és ");
        this.playersList = players;
        System.out.print(this.playersList.size() + " játékos ");
        PLAYERSPANEL.listPlayers(playersList);
        System.out.println("játszik.");
    }

    public void setLocaleBundle() {
        System.out.println("c.setLocaleBundle: " + locale);
        MAINFRAME.setTextLocale();
        MAINFRAME.repaint();
        RESULTSPANEL.setTextLocale();
        RESULTSPANEL.repaint();
        PLAYERSPANEL.setTextLocale();
        PLAYERSPANEL.repaint();
        Player.setPointTxt(rBundle.getString("POINT_TXT"));
    }

    public void saveDatas(List<Player> list) {
        MyDAO dao = MyDAO.getInstance();
        list.forEach((p) -> {
            try {
                if (playersList.contains(p)) {
                    System.out.println("update");
                    dao.update(p);
                } else {
                    System.out.println("create");
                    dao.create(p);
                    playersList.add(p);
                }
            } catch (Exception ex) {
                System.out.println("Error-saveDatas: " + ex.getMessage());
            }
        });
    }
}
