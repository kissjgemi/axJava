/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.ZenServer;
import aBasis.ZenServerThread;
import aBasis.ZenUser;
import static aGlobal.Global.*;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
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

    private List<ZenUser> userList;

    private ZenServer mb6Server;

    public ExecutorService executorService;

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

    public void dialogNotFound() {
        showDialog(DIALOG_NOTFOUND_TXT, DIALOG_HEADER_TXT);
    }

    void setup() {
        System.out.println("Control.setup()");
        MAINFRAME.setup();
        MAINFRAME.setControl(this);
        ZenServer.setControl(this);
        ZenServerThread.setControl(this);
        userList = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(MAX_THREAD_NUMBER);

    }

    public void startServer() {
        mb6Server = ZenServer.getInstance(SERVER_PORT_TXT);
        executorService.submit(mb6Server);
        GRAPHITYPANEL.addToList(SERVER_STARTED);
    }

    public void stopServer() {
        GRAPHITYPANEL.addToList(SERVER_STOPPED);
        mb6Server.stopServer();
    }

    public void listMessage(ZenUser user) {
        GRAPHITYPANEL.addToList(user.getUserData().getZenUserName()
                + " > "
                + user.getLastMessage());
    }

    public void broadCast(ZenUser user) {
        mb6Server.broadCast(user.getUserData().getZenUserName()
                + " > "
                + user.getLastMessage());
    }

    public void broadCastAvatar(ZenUser user) {
        mb6Server.broadCastAvatar(user.getUserData());
    }

    public void addUser(ZenUser user) {
        userList.add(user);
        CONTROLPANEL.fillUserList(userList);
    }

    public void threadFinished(ZenUser user) {
        userList.remove(user);
        CONTROLPANEL.fillUserList(userList);
    }

    public void serverFinished(ZenServer server) {

    }

    public void drawGraphity(Graphics g) {

    }

    public void clickOnGraphity(int x, int y, int button) {

    }

    public void refreshGraphity() {

    }
}
