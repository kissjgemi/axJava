/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.GiftBox;
import static aBasis.Global.*;
import aBasis.House;
import aBasis.Sankta;
import aData.FileInput;
import aData.InputData;
import aSurface.ControlPanel;
import aSurface.GraphityPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Control {

    private final MainFrame MAINFRAME;
    private final ControlPanel CONTROLPANEL;
    private final GraphityPanel GRAFITYPANEL;

    private final Image sanktaImage = new ImageIcon(this.getClass().
            getResource(sanktaGraphic)).getImage();
    private final Image houseImage = new ImageIcon(this.getClass().
            getResource(HouseGraphuc)).getImage();
    private final Image[] giftBoxImages
            = {new ImageIcon(this.getClass().
                        getResource(giftGrapghic1)).getImage(),
                new ImageIcon(this.getClass().
                        getResource(giftGrapghic2)).getImage(),
                new ImageIcon(this.getClass().
                        getResource(giftGrapghic3)).getImage()};

    private Sankta sankta;
    private boolean isStarted;

    public void setLocaleBundle() {
        MAINFRAME.setTextLocale();
        CONTROLPANEL.setTextLocale();
        GRAFITYPANEL.setTextLocale();
    }

    public Control(MainFrame mf, ControlPanel cp, GraphityPanel gp) {
        this.MAINFRAME = mf;
        this.CONTROLPANEL = cp;
        this.GRAFITYPANEL = gp;
    }

    private List<String> nameList = new ArrayList<>();
    private final List<String> receiverList = new ArrayList<>();
    private final List<House> houseList = new ArrayList<>();
    private final List<GiftBox> giftBoxList = new ArrayList<>();

    void setup() {
        System.out.println("Control.setup()");
        nameList = inputFromFile(nameList);
        CONTROLPANEL.fillAskList(nameList);
    }

    public void startSanktaTour() {
        sankta = new Sankta(sanktaImage, this);
        sankta.setHouses(houseList);
        CONTROLPANEL.setButtonActivity(false);
        sankta.start();
        isStarted = true;
        System.out.println("Control.startSanktaTour()");
        refreshGrafity();
    }

    public void finishTour() {
        System.out.println("Control.finishTour()");
        sankta = null;
        refreshGrafity();
    }

    public void drawGrafity(Graphics g) {
        houseList.forEach((house) -> {
            house.drawGraphic(g);
        });
        giftBoxList.forEach((giftBox) -> {
            giftBox.drawGraphic(g);
        });
        if (isStarted && sankta != null) {
            sankta.drawGraphic(g);
        }
    }

    public void refreshGrafity() {
        GRAFITYPANEL.repaint();
    }

    public void addHouse(int x, int y) {
        String name = null;
        if (y > HORIZON) {
            name = randomName();
        }
        if (name != null) {
            houseList.add(new House(houseImage, name, x, y));
            refreshGrafity();
        }
    }

    private String randomName() {
        String name = null;
        System.out.println("nevek lista mérete: " + nameList.size());
        if (nameList.size() > 0) {
            int index = (int) (Math.random() * nameList.size());
            name = nameList.get(index);
            nameList.remove(name);
        }
        return name;
    }

    public void giveGift(House house) {
        int index = (int) (Math.random() * giftBoxImages.length);
        giftBoxList.add(new GiftBox(giftBoxImages[index], house.getName(),
                house.getImgX() + HOUSE_SIZE / 2, house.getImgY()));
        receiverList.add(house.getName());
        CONTROLPANEL.fillReceiveList(receiverList);
        refreshGrafity();
    }

    private void addData(String[] datas, List list) {
        list.add(datas[0]);
        System.out.println("OK > " + datas[0]);
    }

    private void fillList(String[] datas, List list) {
        switch (datas.length) {
            case 1:
                addData(datas, list);
                break;
            default:
                System.out.println("Nem megfelelő adatsor: ");
                System.out.println(Arrays.toString(datas));
        }
    }

    public List inputFromFile(List list) {
        list.clear();
        InputData inputData = new FileInput(DATA_SOURCE);
        String datas[];
        try {
            for (Object o : inputData.inputList()) {
                datas = (String[]) o;
                fillList(datas, list);
            }
        } catch (NumberFormatException e) {
            System.out.println("Hibás adatfile - " + e.getMessage());
        } catch (Exception e) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, e);
        }
        return list;
    }
}
