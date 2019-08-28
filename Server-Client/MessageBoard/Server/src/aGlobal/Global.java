/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aGlobal;

import java.awt.Image;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Global {

    public static final String REGEX = ";";
    public static final String DATA_SOURCE = "/aData/datas.txt";

    public static final String SOURCES_URL = "/aSource/";

    public static final Charset CODE_PAGE = StandardCharsets.UTF_8;
    public static final String MODE_R = "r";
    public static final String MODE_RW = "rw";

    public static final int MAX_THREAD_NUMBER = 30;

    public static final long THREAD_SLEEPTIME = 10;

    public static enum PROCESS_STATE {
        PROLOG, MAIN, EPILOG, EXIT
    }

    public static boolean isPrologFinished = false;
    public static boolean isMainFinished = false;
    public static boolean isEpilogFinished = false;

    public static PROCESS_STATE state = PROCESS_STATE.PROLOG;

    public static final String DIALOG_HEADER_TXT = "Hiba";
    public static final String DIALOG_NOTFOUND_TXT = "Nincs ilyen!";

    public static final String SERVER_IP_TXT = getMyLocalHost();
    public static final String SERVER_PORT_TXT = "44444";
    public static final String CLIENT_CONNECTED = "kapcsolódott";
    public static final String CLIENT_HAS_LEFT = "kilépett";

    //mainframe
    public static final String MAINFRAME_TITLE = "MessageBoard - Server";

    //controlPanel
    public static final int CONTROL_WIDTH = 300;
    public static final int CONTROL_HEIGHT = 600;

    public static final String CONTROL_LBL_TITLE = "SERVER";
    public static final String CONTROL_LBL_SERVER_IP = "IP: " + SERVER_IP_TXT;
    public static final String CONTROL_LBL_SERVER_PORT = "Port: " + SERVER_PORT_TXT;
    public static final String CONTROL_LBL_USERLIST = "Bejelentkezve:";
    public static final String CONTROL_BTN_START_FALSE = "Start";
    public static final String CONTROL_BTN_START_TRUE = "Stop";

    public static final String GRAPHITY_PROLOG_TXT = "Click to be on Board";

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 600;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITYPROLOG_BG
            = SOURCES_URL + "aGraphity1Prolog.jpg";
    public static final Image GRAPHITY_PROLOG = new ImageIcon(
            Global.class.getResource(GRAPHITYPROLOG_BG)).getImage();
    public static final String GRAPHITYMAIN_BG
            = SOURCES_URL + "aGraphity2Main.jpg";
    public static final Image GRAPHITY_MAIN = new ImageIcon(
            Global.class.getResource(GRAPHITYMAIN_BG)).getImage();
    public static final String GRAPHITYFINALE_BG
            = SOURCES_URL + "aGraphity3Finale.jpg";
    public static final Image GRAPHITY_FINALE = new ImageIcon(
            Global.class.getResource(GRAPHITYFINALE_BG)).getImage();

    public static final String LOGO_URL = SOURCES_URL + "aLogo.jpg";
    public static final Image LOGO = new ImageIcon(
            Global.class.getResource(LOGO_URL)).getImage();
    public static final int LOGO_WIDTH = GRAPHITY_WIDTH;
    public static final int LOGO_HEIGHT = 40;
    public static final int LOGO_X = 0;
    public static final int LOGO_Y = 0;

    public static final String ACTOR2_URL = SOURCES_URL + "actor2.gif";

    public static final String DECOR_URL = SOURCES_URL + "aDecor.jpg";
    public static final Image DECOR = new ImageIcon(
            Global.class.getResource(DECOR_URL)).getImage();
    public static final int DECOR_WIDTH = 420;
    public static final int DECOR_HEIGHT = 150;
    public static final int DECOR_X = (GRAPHITY_WIDTH - DECOR_WIDTH) / 2;
    public static final int DECOR_Y = (GRAPHITY_HEIGHT - DECOR_HEIGHT) / 2;

    private static String getMyIP() {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        } catch (SocketException | UnknownHostException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "unknow";
    }

    private static String getMyLocalHost() {
        try {
            InetAddress ipAddr = InetAddress.getLocalHost();
            return ipAddr.getHostAddress();
        } catch (UnknownHostException ex) {
            System.out.println("ip address:" + ex.getMessage());
        }
        return "unknow";
    }
}
