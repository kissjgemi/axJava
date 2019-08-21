/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Global {

    public Global() {
    }

    public static final String CODE_PAGE = "UTF-8";

    public static enum PROCESS_STATE {
        PROLOG, MAIN, EPILOG, EXIT
    }

    public static boolean isPrologFinished = false;
    public static boolean isMainFinished = false;
    public static boolean isEpilogFinished = false;

    public static PROCESS_STATE state = PROCESS_STATE.PROLOG;

    public static final String MAINFRAME_TITLE = "Mikulás 3 - Online";
    public static final String CONTROL_LBL_TITLE = "Online";
    public static final String CONTROL_LBL_CHOOSE = "Választék";
    public static final String CONTROL_BTN_ORDER = "Rendel";
    public static final String CONTROL_LBL_BILL = "Számla";
    public static final String CONTROL_BTN_FINISH = "Befejez";
    public static final String CONTROL_BTN_SAVE = "Fájlba";
    public static final String CONTROL_CHOOSE_TXT = " Válassz!";

    public static final String SOURCES_URL = "/aSource/";
    public static final String SPRITES_URL = SOURCES_URL + "sprites/";

    public static List<Image> getSpriteImages() {
        List<Image> toReturn = new ArrayList<>();
        getResourceFiles().forEach((str) -> {
            System.out.println(">> " + str);
            toReturn.add(new ImageIcon(
                    Global.class.getResource(SPRITES_URL + str)).getImage());
        });
        return toReturn;
    }

    private static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private static InputStream getResourceAsStream(String resource) {
        final InputStream in
                = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? Global.class.getResourceAsStream(resource) : in;
    }

    public static List<String> getResourceFiles() {
        List<String> filenames = new ArrayList<>();
        try (
                InputStream in
                = getResourceAsStream(SPRITES_URL);
                BufferedReader br
                = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
                System.out.println("> " + resource);
            }
        } catch (IOException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filenames;
    }

    //controlPanel
    public static final int CONTROL_WIDTH = 200;
    public static final int CONTROL_HEIGHT = 500;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 550;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITYPROLOG_BG
            = SOURCES_URL + "graphity1Prolog.gif";
    public static final Image GRAPHITY_PROLOG = new ImageIcon(
            Global.class.getResource(GRAPHITYPROLOG_BG)).getImage();
    public static final String GRAPHITYMAIN_BG
            = SOURCES_URL + "graphity2Main.gif";
    public static final Image GRAPHITY_MAIN = new ImageIcon(
            Global.class.getResource(GRAPHITYMAIN_BG)).getImage();
    public static final String GRAPHITYFINALE_BG
            = SOURCES_URL + "graphity3Finale.gif";
    public static final Image GRAPHITY_FINALE = new ImageIcon(
            Global.class.getResource(GRAPHITYFINALE_BG)).getImage();

    public static final String ACTOR_URL = SOURCES_URL + "actor.gif";
    public static final Image ACTOR = new ImageIcon(
            Global.class.getResource(ACTOR_URL)).getImage();
    public static final int ACTOR_WIDTH = 120;
    public static final int ACTOR_HEIGHT = 120;
    public static final int ACTOR_START_X = 0;
    public static final int ACTOR_START_Y = 0;
    public static final int ACTOR_TARGET_X = GRAPHITY_WIDTH - ACTOR_WIDTH;
    public static final int ACTOR_TARGET_Y = GRAPHITY_HEIGHT - ACTOR_HEIGHT;

    public static final int SPRITE_WIDTH = 80;
    public static final int SPRITE_HEIGHT = 80;
    public static final int SPRITE_START_X = GRAPHITY_WIDTH - SPRITE_WIDTH;
    public static final int SPRITE_START_Y = SPRITE_HEIGHT;
    public static final int SPRITE_TARGET_X = SPRITE_WIDTH;
    public static final int SPRITE_TARGET_Y = SPRITE_HEIGHT;
    public static final long SPRITE_SLEEPTIME_MAX = 5;
    public static final long SPRITE_SLEEPTIME_MIN = 5;
}
