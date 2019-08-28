/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.MB6Client;
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

    private String serverIP;
    private int serverPort;
    private String userName;

    private List<MB6Client> clientList;

    public List<MB6Client> getClientList() {
        return clientList;
    }

    private MB6Client mb6Client;

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
        MB6Client.setControl(this);
        clientList = new ArrayList<>();
        executorService = Executors.newFixedThreadPool(MAX_THREAD_NUMBER);
    }

    public void connect(String ip, String port, String user) {
        if (ip.isEmpty() || ip.equals("localhost")) {
            ip = "127.0.0.1";
        }
        if (port.isEmpty()) {
            port = "44444";
        }
        if (user.isEmpty()) {
            user = "anonymous";
        }
        serverIP = ip;
        serverPort = Integer.valueOf(port);
        userName = user;
        mb6Client = new MB6Client(serverIP, serverPort, userName, "hu");
        clientList.add(mb6Client);
        executorService.submit(mb6Client);
    }

    public void sendMessage(String text) {
        mb6Client.setMessage(text);
    }

    public void abortConnection(String abortConnect) {
        mb6Client.setOnline(false);
    }

    public void clientClosed(MB6Client client) {
        clientList.remove(client);
    }

    public void closeClients() {
        for (MB6Client client : clientList) {
            System.out.println("GOING OFFLINE> " + client.getUser());
            client.setOnline(false);
        }
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
