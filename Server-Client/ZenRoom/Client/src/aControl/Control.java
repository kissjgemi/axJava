/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.ZenClient;
import static aGlobal.Global.*;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final ControlPanel CONTROLPANEL;
    private final GraphityPanel GRAPHITYPANEL;

    private String serverIP;
    private int serverPort;
    private String userName;

    private ImageIcon avatar;

    private List<ZenClient> clientList;

    public List<ZenClient> getClientList() {
        return clientList;
    }

    private ZenClient mb6Client;

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

    public void dialogNotFound() {
        showDialog(DIALOG_NOTFOUND_TXT, DIALOG_HEADER_TXT);
    }

    void setup() {
        System.out.println("Control.setup()");
        MAINFRAME.setup();
        MAINFRAME.setControl(this);
        ZenClient.setControl(this);
        clientList = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(MAX_THREAD_NUMBER);
    }

    public void connect(String ip, String port, String user, ImageIcon icon) {
        if (ip.isEmpty() || ip.equals("localhost")) {
            ip = "127.0.0.1";
        }
        if (port.isEmpty()) {
            port = "44444";
        }
        if (user.isEmpty()) {
            user = "Anonymous";
        }
        serverIP = ip;
        serverPort = Integer.valueOf(port);
        userName = user;
        mb6Client = new ZenClient(serverIP, serverPort, userName, icon, "hu");
        clientList.add(mb6Client);
        executorService.submit(mb6Client);
    }

    public void sendMessage(String text) {
        mb6Client.setMessage(text);
    }

    public void lastUser(String str) {
        CONTROLPANEL.setUser(str);
    }

    public void writeMessage(String str) {
        GRAPHITYPANEL.addToList(str);
    }

    public void abortConnection(String abortConnect) {
        mb6Client.setOnline(false);
    }

    public void clientClosed(ZenClient client) {
        clientList.remove(client);
    }

    public void lastAvatar(ImageIcon icon) {
        avatar = icon;
        GRAPHITYPANEL.setAvatar(avatar);
    }

    public void closeClients() {
        clientList.stream().map((client) -> {
            System.out.println("Control GOING OFFLINE> " + client.getUser());
            return client;
        }).forEachOrdered((ZenClient client) -> {
            client.setOnline(false);
        });
    }

    public void exitPrgram() {
        System.out.println("Control.exitPrgram()");
        System.exit(0);
    }

    public void drawGraphity(Graphics g) {
        //System.out.println("Control.drawGraphity()");
        switch (state) {
            case PROLOG: {
                break;
            }
            case MAIN: {

                break;
            }
            case EPILOG: {

                break;
            }
            default:

        }
    }

    public void clickOnGraphity(int x, int y, int button) {
        System.out.println("Control.clickOnGraphity() > " + state);
        switch (state) {
            case PROLOG: {
                CONTROLPANEL.setButtonConnect(true);
                state = PROCESS_STATE.MAIN;
                break;
            }
            case MAIN: {

                state = PROCESS_STATE.EPILOG;
                break;
            }
            case EPILOG: {
                state = PROCESS_STATE.EXIT;
                closeClients();
                break;
            }
            default:
                if (clientList.isEmpty()) {
                    exitPrgram();
                }
        }
        refreshGraphity();
    }

    public void refreshGraphity() {
        GRAPHITYPANEL.repaint();
    }
}
