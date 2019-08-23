/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Global {

    public Global() {
    }

    public static final String CODE_PAGE = "UTF-8";
    public static final String MODE_R = "r";
    public static final String MODE_RW = "rw";

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

    public static String SAVE_DIRECTORY = "src/aData/";
    public static String SAVE_URL = SAVE_DIRECTORY + "bill.txt";

    private static void deleteFile() {
        File file = new File(SAVE_URL);
        if (file.exists()) {
            file.delete();
        }
    }

    private static void makeDirectory() {
        File dir = new File(SAVE_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
            System.out.println("> " + dir + " is created.");
        }
    }

    private static void appendFile(String str) {
        File file = new File(SAVE_URL);
        long fileLength = file.length();
        try {
            RandomAccessFile f = new RandomAccessFile(SAVE_URL, MODE_RW);
            f.seek(fileLength);
            f.write((str + "\n").getBytes());
            f.close();
        } catch (IOException e) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static void writeFile(List<Sprite> list) {
        makeDirectory();
        deleteFile();
        list.forEach((sprite) -> {
            appendFile(sprite.toString());
        });
    }

    public static final String SOURCES_URL = "/aSource/";
    public static final String SPRITES_URL = SOURCES_URL + "sprites/";
    public static final String SPRITES_PATH = "aSource/sprites/";
    private static final File FOLDER = new File("../src/aSource/sprites/");

    public static List<Image> getSpriteImages() {
        List<Image> toReturn = new ArrayList<>();
        getResourceFiles().forEach((str) -> {
            System.out.println(">> " + str);
            toReturn.add(new ImageIcon(
                    Global.class.getResource(SPRITES_URL + str)).getImage());
        });
        return toReturn;
    }

    public static List<String> getResourceFiles() {
        System.out.println("Global.getResourceFiles()");
        List<String> filenames = new ArrayList<>();
        try (
                InputStream in = Global.class.getResourceAsStream(SPRITES_URL);
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
        if (filenames.isEmpty()) {
            CodeSource src = Global.class.getProtectionDomain().getCodeSource();
            if (src != null) {
                ZipInputStream zip = null;
                try {
                    URL jar = src.getLocation();
                    zip = new ZipInputStream(jar.openStream());
                    while (true) {
                        ZipEntry e = zip.getNextEntry();
                        if (e == null) {
                            break;
                        }
                        String name = e.getName();
                        if (name.startsWith(SPRITES_PATH)) {
                            System.out.println("> " + name);
                            String sub = name.substring(
                                    name.lastIndexOf(File.separator) + 1);
                            System.out.println(File.separator + ": " + sub);
                            if (sub.length() > 0) {
                                filenames.add(sub);
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (zip != null) {
                            zip.close();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                filenames = listFilesForFolder(FOLDER);
            }
        }
        return filenames;
    }

    public static List<String> listFilesForFolder(File folder) {
        System.out.println("Global.listFilesForFolder");
        List<String> filenames = new ArrayList<>();
        try {
            System.out.println("> " + folder.getCanonicalPath());
            File[] list = folder.listFiles();
            if (list == null) {

            } else {
                for (File fileEntry : list) {
                    if (fileEntry.isDirectory()) {
                        listFilesForFolder(fileEntry);
                    } else {
                        filenames.add(fileEntry.getName());
                    }
                }
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
    public static final String ACTOR_NAME = "mikulás";
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
