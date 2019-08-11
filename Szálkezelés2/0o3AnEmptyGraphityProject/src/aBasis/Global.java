/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public final class Global {

    public Global() {
        loadSprites();
    }

    public static final String CODE_PAGE = "UTF-8";

    // sql-derby --- 
    public static final String SQL_SOURCE = "/aData/datas.sql";
    //MAVEN: "/sql/datas.sql"
    public static final String DB_NAME = "STARWARS";
    public static final String DB_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static final String DB_URL = "jdbc:derby:" + DB_NAME;
    public static final String DB_USER = ";user=starwars";
    public static final String DB_PSWD = ";password=starwars";
    public static final String CREATE_TRUE = ";create=true";
    public static final String[] DB_TABLE
            = {"STARBUGS", "ID", "RACER", "AUTHOR", "TYPE"};
    public static final String DB_DIR = "/src/derby";
    // --- sql-derby

    public static final String SOURCES_URL = "/aSource/";
    public static List<Image> spriteImages = new ArrayList<>();

    public static int raceType = 0;

    public static final String[] TYPE_A
            = {
                "sprite_null.png",
                "droid1.png",
                "droid2.png",
                "droid3.png",
                "droid4.png",
                "droid5.png",
                "droid6.png",
                "droid7.png",
                "droid8.png",
                "droid9.png",
                "darth_vader.png",
                "stormtrooper.png",
                "yoda.png"
            };

    public static final String[] TYPE_B
            = {
                "ship1.png",
                "ship2.png",
                "ship3.png",
                "ship4.png",
                "ship5.png",
                "ship6.png",
                "ship7.png",
                "ship8.png",
                "ship9.png",
                "ship10.png",
                "ship11.png",
                "ship12.png",};

    public void loadSprites() {
        System.out.println("loadSprites()");
        for (int ii = 0; ii < TYPE_A.length; ii++) {
            spriteImages.add(new ImageIcon(this.getClass().
                    getResource((SOURCES_URL + TYPE_A[ii]))).getImage());
        }
        for (int ii = 0; ii < TYPE_B.length; ii++) {
            spriteImages.add(new ImageIcon(this.getClass().
                    getResource((SOURCES_URL + TYPE_B[ii]))).getImage());
        }
    }

    public static final int SPRITE_WIDTH = 100;
    public static final int SPRITE_HEIGHT = 100;
    public static final long SPRITE_SLEEPTIME = 16;
    public static final int SPRITE_STEP = 1;
    public static final long SPRITES_TIMELINE_GAP = 2500;

    public static enum PROCESS_STATE {
        PROLOG, MAIN, FINALE, EXIT
    }

    public static PROCESS_STATE state = PROCESS_STATE.PROLOG;

    public static final String REGEX = ";";
    public static final String DATA_SOURCE = "/aData/datas.txt";

    //lokalitások
    public static final String BUNDLE = "aLocality/Bundle";
    public static Locale locale;
    public static ResourceBundle rBundle;

    //controlPanel
    public static final int CONTROL_WIDTH = 250;
    public static final int CONTROL_HEIGHT = 700;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 750;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITY_BG
            = SOURCES_URL + "mainGraphity.jpg";
    public static final String START_BG
            = SOURCES_URL + "startGrafity.gif";
    public static final String FINAL_BG
            = SOURCES_URL + "finalGraphity.gif";

}
