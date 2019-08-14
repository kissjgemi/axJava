/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import static aBasis.Global.*;
import aBasis.Slideshow;
import aBasis.Sprite;
import aDataAccess.MyDFAO;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private List<Sprite> inputList;
    private List<Sprite> selectedList;
    private List<Sprite> tributeList;

    Slideshow show;

    public void setLocaleBundle() {
        MAINFRAME.setTextLocale();
        CONTROLPANEL.setTextLocale();
    }

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAPHITYPANEL = gp;
    }

    public void setup() {
        System.out.println("Control.setup()");
        inputList = new ArrayList<>();
        selectedList = new ArrayList<>();
        tributeList = new ArrayList<>();
        MAINFRAME.setup();
        CONTROLPANEL.setup();
        GRAPHITYPANEL.setup();
        Sprite.setControl(this);
        Sprite.setSpriteImage(new ImageIcon(
                this.getClass().getResource(SPRITE_SOURCE)).getImage());
        setLocaleBundle();
        inputFromFile();
        Slideshow.setList(inputList);
        Slideshow.setControl(this);
    }

    private void showDialog(String message, String header) {
        JOptionPane.showMessageDialog(GRAPHITYPANEL,
                message,
                header,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void dialogEmpty() {
        showDialog(rBundle.getString("MSG_EMPTY"),
                rBundle.getString("MSG_HEADER"));
    }

    public void emptyVoid() {
        System.out.println("Control.emptyVoid()");
    }

    public void startProlog() {
        System.out.println("Control.startProlog()");
    }

    public void finishProlog() {
        System.out.println("Control.finishProlog()");
    }

    private String convert(String s) {
        if (s.equals("peace") || s.equals("béke")) {
            s = "béke";
        }
        if (s.equals("literature") || s.equals("irodalom")) {
            s = "irodalmi";
        }
        if (s.equals("physics") || s.equals("fizika")) {
            s = "fizikai";
        }
        if (s.equals("medical") || s.equals("orvostudomány")) {
            s = "orvosi";
        }
        if (s.equals("chemistry") || s.equals("kémia")) {
            s = "kémiai";
        }
        if (s.equals("economic") || s.equals("közgazdaság")) {
            s = "közgazdasági";
        }
        return s;
    }

    public void startMainProcess(String s) {
        System.out.println("Control.startMainProcess()");
        s = convert(s);
        CONTROLPANEL.clearList();
        int n = 0;
        selectedList = new ArrayList<>();
        for (Sprite sprite : inputList) {
            if (sprite.getTYPE().equals(s)) {
                sprite.setFaceXY(
                        IMG_MARGIN, n * (IMG_MARGIN + IMG_SIZE));
                selectedList.add(sprite);
                if (!tributeList.contains(sprite) && !sprite.isTributed()) {
                    sprite.setStartXY(IMG_MARGIN * 2
                            + IMG_SIZE, n * (IMG_MARGIN + IMG_SIZE));
                }
                CONTROLPANEL.add2List(sprite);
                n++;
            }
        }
        refreshGraphity();
        CONTROLPANEL.setButtonTribute(!selectedList.isEmpty());
    }

    public void finishMainProcess() {
        System.out.println("Control.finishMainProcess()");
        selectedList.forEach((Sprite sprite) -> {
            if (!tributeList.contains(sprite) && !sprite.isTributed()) {
                tributeList.add(sprite);
            }
            if (!sprite.isAlive()) {
                sprite.start();
            }
        });
        CONTROLPANEL.setButtonFinish(true);
    }

    public void startFinale() {
        System.out.println("Control.startFinale()");
        CONTROLPANEL.clearList();
        CONTROLPANEL.setButtonActivity(false);
        CONTROLPANEL.setButtonFinish(true);
        state = PROCESS_STATE.FINALE;
        show = new Slideshow();
        show.start();
    }

    public void finishFinale() {
        System.out.println("Control.finishFinale()");
    }

    public void spriteFinished(Sprite s) {
        s = null;
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
                selectedList.forEach((sprite) -> {
                    sprite.drawFace(g);
                    if (!sprite.isTributed() && !sprite.isAlive()) {
                        sprite.drawGraphic(g);
                    }
                });
                tributeList.forEach((Sprite sprite) -> {
                    if (sprite != null) {
                        sprite.drawGraphic(g);
                    }
                });
                break;
            }
            case FINALE: {
                if (show.isLoaded()) {
                    show.drawBigGraphic(g);
                }
                tributeList.forEach((sprite) -> {
                    if (sprite != null) {
                        sprite.drawGraphic(g);
                    }
                });
                break;
            }
            default:
                exitPrgram();
        }
    }

    public void clickOnGraphity(int x, int y, int button) {
        System.out.println("Control.clickOnGraphity() > " + state);
        switch (state) {
            case PROLOG: {
                state = PROCESS_STATE.MAIN;
                CONTROLPANEL.setComboList(true);
                break;
            }
            case MAIN: {
                break;
            }
            case FINALE: {
                exitPrgram();
                break;
            }
            default:
                exitPrgram();
        }
        refreshGraphity();
    }

    public void refreshGraphity() {
        GRAPHITYPANEL.repaint();
    }

    private void addDataCase4(String[] datas) {
        Sprite s = new Sprite(
                datas[0],
                Integer.valueOf(datas[1]),
                datas[2],
                SOURCES_URL + datas[3]);
        inputList.add(s);
        System.out.println("OK > " + Arrays.toString(datas));
    }

    private void fillList(String[] datas) {
        switch (datas.length) {
            case 4:
                addDataCase4(datas);
                break;
            default:
                System.out.println("Nem megfelelő adatsor: ");
                System.out.println(Arrays.toString(datas));
        }
    }

    public void inputFromFile() {
        inputList.clear();
        MyDFAO inputData = new MyDFAO(DATA_SOURCE);
        String datas[];
        try {
            for (Object o : inputData.inputList()) {
                datas = (String[]) o;
                fillList(datas);
            }
        } catch (NumberFormatException e) {
            System.out.println("Hibás adatfile - " + e.getMessage());

        } catch (Exception e) {
            Logger.getLogger(Control.class
                    .getName()).log(Level.SEVERE, null, e);
        }
    }
}
