/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.MB6Server;
import aBasis.MB6ServerThread;
import aBasis.MB6User;
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

    private List<MB6User> userList;

    private MB6Server mb6Server;

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
        MB6Server.setControl(this);
        MB6ServerThread.setControl(this);
        userList = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(MAX_THREAD_NUMBER);

    }

    public void startServer() {
        mb6Server = MB6Server.getInstance(SERVER_PORT_TXT);
        executorService.submit(mb6Server);
    }

    public void firstMessage() {
        CONTROLPANEL.changeLogo();
    }

    public void stopServer() {
        mb6Server.stopServer();
    }

    public void addMessage(MB6User user) {
        GRAPHITYPANEL.addToList(user.getUserName()
                + " > "
                + user.getLastMessage());
    }

    public void addUser(MB6User user) {
        userList.add(user);
        CONTROLPANEL.fillUserList(userList);
    }

    public void threadFinished(MB6User user) {
        userList.remove(user);
        CONTROLPANEL.fillUserList(userList);
    }

    public void serverFinished(MB6Server server) {

    }

    public void drawGraphity(Graphics g) {

    }

    public void clickOnGraphity(int x, int y, int button) {

    }

    public void refreshGraphity() {

    }
}
