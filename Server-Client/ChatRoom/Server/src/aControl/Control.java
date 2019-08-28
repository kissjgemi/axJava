/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.ChatServer;
import aBasis.ChatServerThread;
import aBasis.ChatUser;
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

    private List<ChatUser> userList;

    private ChatServer mb6Server;

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
        ChatServer.setControl(this);
        ChatServerThread.setControl(this);
        userList = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(MAX_THREAD_NUMBER);

    }

    public void startServer() {
        mb6Server = ChatServer.getInstance(SERVER_PORT_TXT);
        executorService.submit(mb6Server);
        GRAPHITYPANEL.addToList(SERVER_STARTED);
    }

    public void stopServer() {
        GRAPHITYPANEL.addToList(SERVER_STOPPED);
        mb6Server.stopServer();
    }

    public void listMessage(ChatUser user) {
        GRAPHITYPANEL.addToList(user.getUserName()
                + " > "
                + user.getLastMessage());
    }

    public void broadCast(ChatUser user) {
        mb6Server.broadCast(user.getUserName()
                + " > "
                + user.getLastMessage());
    }

    public void addUser(ChatUser user) {
        userList.add(user);
        CONTROLPANEL.fillUserList(userList);
    }

    public void threadFinished(ChatUser user) {
        userList.remove(user);
        CONTROLPANEL.fillUserList(userList);
    }

    public void serverFinished(ChatServer server) {

    }

    public void drawGraphity(Graphics g) {

    }

    public void clickOnGraphity(int x, int y, int button) {

    }

    public void refreshGraphity() {

    }
}
