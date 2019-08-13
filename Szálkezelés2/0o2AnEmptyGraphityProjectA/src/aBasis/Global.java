/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author b6dmin
 */
public class Global {

    public static enum PROCESS_STATE {
        PROLOG, MAIN, FINALE, EXIT
    }
    
    public static PROCESS_STATE state = PROCESS_STATE.PROLOG;

    public static final String CODE_PAGE = "UTF-8";
    public static final String REGEX = ",";

    public static final String SOURCES_URL = "/aSource/";
    public static final String DATA_SOURCE = "/aData/datas.txt";

    //lokalitások
    public static final String BUNDLE = "aLocality/Bundle";
    public static Locale locale;
    public static ResourceBundle rBundle;

    //controlPanel
    public static final int CONTROL_WIDTH = 180;
    public static final int CONTROL_HEIGHT = 470;

    //graphityPanel
    public static final int GRAPHITY_WIDTH = 750;
    public static final int GRAPHITY_HEIGHT = CONTROL_HEIGHT;
    public static final String GRAPHITY_BG
            = SOURCES_URL + "mainGraphity.png";
    public static final String START_BG
            = SOURCES_URL + "startGrafity.png";
    public static final String FINAL_BG
            = SOURCES_URL + "finalGraphity.gif";

}
