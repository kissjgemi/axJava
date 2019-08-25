/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.Sprite;
import aBasis.User;
import aData.ReadUsers;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final ControlPanel CONTROLPANEL;
    private final GraphityPanel GRAPHITYPANEL;

    private List<User> userList;
    private List<User> userGroup;
    private List<String> listDatas;
    private List<Sprite> spriteList;

    private User selectedUser;
    private User uniqueUser;

    private ExecutorService executorService;

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    private void showDialog(String message, String header) {
        JOptionPane.showMessageDialog(MAINFRAME,
                message,
                header,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void dialogUserNotFound() {
        showDialog(DIALOG_USER_NOTFOUND_TXT, DIALOG_HEADER_TXT);
    }

    private void fillList() {
        CONTROLPANEL.fillUserList(listDatas);
    }

    private void listUsers() {
        listDatas.clear();
        userList.forEach((user) -> {
            listDatas.add(user.getUSERNAME());
        });
        fillList();
    }

    void setup() {
        System.out.println("Control.setup()");
        MAINFRAME.setup();
        MAINFRAME.setControl(this);
        Sprite.setControl(this);
        listDatas = new ArrayList<>();
        spriteList = new ArrayList<>();
        userGroup = new ArrayList<>();
        userList = ReadUsers.inputFromFile();
        listUsers();
        executorService = Executors.newFixedThreadPool(MAX_THREAD_NUMBER);
    }

    public void startProlog() {
        System.out.println("Control.startProlog()");

    }

    public void finishProlog() {
        System.out.println("Control.finishProlog()");
        CONTROLPANEL.clearTitleText();
        CONTROLPANEL.setListUser(true);
        GRAPHITYPANEL.setSearchVisible();

    }

    private User searchUserByName(String name) {
        User toReturn = null;
        for (User user : userList) {
            if (user.getUSERNAME().equals(name)) {
                toReturn = user;
                break;
            }
        }
        return toReturn;
    }

    public void clearSelected() {
        selectedUser = null;
        uniqueUser = null;
        CONTROLPANEL.setButtonBroadcast(false);
        CONTROLPANEL.setButtonUnique(false);
    }

    public void startMainProcess(String name) {
        System.out.println("Control.startMainProcess(name)");
        GRAPHITYPANEL.clearSearchTextField();
        User newUser;
        if ((newUser = searchUserByName(name)) != null) {
            startMainProcess(newUser);
        } else {
            dialogUserNotFound();
            listUsers();
        }
    }

    private void listUserGroup(User head) {
        List<String> list = new ArrayList<>();
        userGroup.clear();
        for (User user : userList) {
            if (user.getGROUPNAME() == head.getGROUPNAME()
                    && !user.getUSERNAME().equals(head.getUSERNAME())) {
                list.add(user.getUSERNAME());
                userGroup.add(user);
            }
        }
        CONTROLPANEL.fillUserList(list);
    }

    public void startMainProcess(User usr) {
        System.out.println("Control.startMainProcess(user)");
        if (selectedUser == null) {
            selectedUser = usr;
            CONTROLPANEL.setButtonBroadcast(true);
            listUserGroup(selectedUser);
        } else {
            System.out.println("Message to > " + usr.getUSERNAME());
            CONTROLPANEL.setButtonUnique(true);
            uniqueUser = usr;
        }
    }

    private Sprite initSprite(User sender, User target) {
        Sprite toReturn;
        int index = (int) (Math.random() * SPRITES.length);
        toReturn = new Sprite(SPRITES[index], target.getUSERNAME(),
                sender.getUSERNAME() + "(bit" + index + ")");
        toReturn.setSpriteXY(sender.getUSER_X(), sender.getUSER_Y());
        toReturn.setTargetXY(target.getUSER_X(), target.getUSER_Y());
        toReturn.setfaceDimension(SPRITE_WIDTH, SPRITE_HEIGHT);
        spriteList.add(toReturn);
        return toReturn;
    }

    public void uniqueMainProcess() {
        System.out.println("Control.uniqueMainProcess()");
        Sprite sprite1 = initSprite(selectedUser, uniqueUser);
        Sprite sprite2 = initSprite(uniqueUser, selectedUser);
        executorService.submit(sprite1);
        executorService.submit(sprite2);
    }

    public void broadcastMainProcess() {
        System.out.println("Control.broadcastMainProcess()");
        for (User user : userGroup) {
            Sprite sprite = initSprite(selectedUser, user);
            executorService.submit(sprite);
        }
    }

    public void finishFinale() {
        System.out.println("Control.finishFinale()." + state);
        GRAPHITYPANEL.setSearchInvisible();
        CONTROLPANEL.setTitleInvisible();
        CONTROLPANEL.setButtonActivity(false);

    }

    private void exitPrgram() {
        System.out.println("Control.exitPrgram()");
        System.exit(0);
    }

    private void drawGroups(Graphics g) {
        Color c;
        if (!userList.isEmpty()) {
            for (User user : userList) {
                switch (user.getGROUPNAME()) {
                    case BLUE: {
                        c = Color.BLUE;
                        break;
                    }
                    case RED: {
                        c = Color.RED;
                        break;
                    }
                    case YELLOW: {
                        c = Color.YELLOW;
                        break;
                    }
                    default:
                        c = Color.BLACK;
                }
                g.setColor(c);
                g.fillOval(user.getUSER_X(), user.getUSER_Y(),
                        GROUP_CIRCLE_R * 2, GROUP_CIRCLE_R * 2);
                String str = user.getUSERNAME();
                g.setColor(Color.BLACK);
                int width = g.getFontMetrics().stringWidth(str);
                int height = g.getFontMetrics().getHeight();
                g.drawString(str,
                        user.getUSER_X() - width / 2 + GROUP_CIRCLE_R,
                        user.getUSER_Y() + FACE_SIZE + 3 * height / 4);
            }
        }
    }

    public void drawSelectedBullet(Graphics g) {
        if (selectedUser != null) {
            g.drawImage(selectedUser.getUSER_GROUP(),
                    selectedUser.getUSER_X() - (BULLET_SIZE / 2) + GROUP_CIRCLE_R,
                    selectedUser.getUSER_Y() - (BULLET_SIZE / 2) + GROUP_CIRCLE_R,
                    GRAPHITYPANEL);
        }
    }

    public void drawSprites(Graphics g) {
        for (Sprite sprite : spriteList) {
            sprite.drawGraphic(g);
        }
    }

    public void drawGraphity(Graphics g) {
        //System.out.println("Control.drawGraphity()");
        switch (state) {
            case PROLOG: {
                break;
            }
            case MAIN: {
                drawGroups(g);
                drawSelectedBullet(g);
                drawSprites(g);
                break;
            }
            case EPILOG: {

                break;
            }
            default:

        }
    }

    private boolean logoutClicked(int x, int y) {
        return x > (GRAPHITYPANEL.getGraphityWidth() - 2 * LOGO_HEIGHT)
                && y < LOGO_HEIGHT;
    }

    public void clickOnGraphity(int x, int y, int button) {
        System.out.println("Control.clickOnGraphity() > " + state);
        switch (state) {
            case PROLOG: {
                finishProlog();
                state = PROCESS_STATE.MAIN;
                break;
            }
            case MAIN: {
                if (logoutClicked(x, y)) {
                    finishFinale();
                    state = PROCESS_STATE.EPILOG;
                }
                break;
            }
            case EPILOG: {
                state = PROCESS_STATE.EXIT;
                exitPrgram();
                break;
            }
            default:
                exitPrgram();
        }
        refreshGraphity();
    }

    public void refreshGraphity() {
        GRAPHITYPANEL.repaint();
    }
}
