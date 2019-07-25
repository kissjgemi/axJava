/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aBasis.AirPlane;
import aBasis.City;
import static aBasis.Global.*;
import aBasis.ImagePair;
import aData.FileInput;
import aData.InputData;
import aSurface.ControlPanel;
import aSurface.GraphicPanel;
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
    private final GraphicPanel GRAPHICPANEL;
    private final ControlPanel CONTROLPANEL;

    private final Image airplaneRight
            = new ImageIcon(this.getClass().
                    getResource(AIRPLANE_RIGHT)).getImage();
    private final Image airplaneLeft
            = new ImageIcon(this.getClass().
                    getResource(AIRPLANE_LEFT)).getImage();
    private final ImagePair airplaneImages
            = new ImagePair(airplaneRight, airplaneLeft);

    public void setLocaleBundle() {
        System.out.println("Control.setLocale()");
        MAINFRAME.setTextLocale();
        GRAPHICPANEL.setTextLocale();
        CONTROLPANEL.setTextLocale();
    }

    Control(GraphicPanel gp, ControlPanel cp, MainFrame mf) {
        this.MAINFRAME = mf;
        this.GRAPHICPANEL = gp;
        this.CONTROLPANEL = cp;
    }

    private City departureCity;
    private City destinationCity;
    private AirPlane airPlane;

    private List<City> cityList = new ArrayList<>();

    public void setup() {
        System.out.println("Control.setup()");
        City.setCityColor(CITY_COLOR);
        City.setCityRadius(CITY_RADIUS);
        cityList = inputFromFile(cityList);
        CONTROLPANEL.fillDepartureList(cityList);
        CONTROLPANEL.fillDestinationList(cityList);
    }

    public void drawGraphic(Graphics g) {
        if (departureCity != null) {
            System.out.println("Control.drawGraphic() departure > "
                    + departureCity.toString());
            departureCity.drawGraphic(g);
        }
        if (destinationCity != null) {
            System.out.println("Control.drawGraphic() destination > "
                    + destinationCity.toString());
            destinationCity.drawGraphic(g);
        }
        if (departureCity != null
                && destinationCity != null && airPlane != null) {
            System.out.println("Control.drawGraphic() > airplane");
            airPlane.drawGraphic(g);
        }
    }

    public void startFly(City departure, City destination) {
        this.departureCity = departure;
        this.destinationCity = destination;
        System.out.println("Control.drawGraphic() departure > "
                + departureCity.toString());
        System.out.println("Control.drawGraphic() destination > "
                + destinationCity.toString());
        airPlane = new AirPlane(airplaneImages,
                FACE_WIDTH, FACE_HEIGHT,
                departureCity.getAx(), departureCity.getAy(),
                destinationCity.getAx(), destinationCity.getAy(),
                SLEEP_TIME, BLEND_TIME,
                this);
        airPlane.start();
        CONTROLPANEL.setButtonActivity(false);
        priceCalculation();
    }

    private void priceCalculation() {
        System.out.println("Control.priceCalculation()");
        double x = departureCity.getAx() - destinationCity.getAx();
        double y = departureCity.getAy() - destinationCity.getAy();
        to_pay = (int) (Math.sqrt(x * x + y * y) * KM_PRICE);
        CONTROLPANEL.setPayText();
    }

    public void refreshGraphic() {
        System.out.println("Control.refreshGraphic()");
        GRAPHICPANEL.repaint();
    }

    public void flightFinished(AirPlane aThis) {
        System.out.println("Control.flightFinished()");
        airPlane = null;
        refreshGraphic();
        CONTROLPANEL.setButtonActivity(true);
        to_pay = 0;
        CONTROLPANEL.setPayText();
    }

    public void setDepartureCity(City city) {
        System.out.println("Control.setDepartureCity() > " + city.toString());
        this.departureCity = city;
        refreshGraphic();
    }

    public void setDestinationCity(City city) {
        System.out.println("Control.setDestinationCity() > " + city.toString());
        this.destinationCity = city;
        refreshGraphic();
    }

    private void addData(String[] datas, List list) {
        list.add(new City(datas[0],
                Integer.valueOf(datas[1]),
                Integer.valueOf(datas[2])));
        System.out.println("OK > "
                + datas[0] + "("
                + Integer.valueOf(datas[1]) + ":"
                + Integer.valueOf(datas[2]) + ")");
    }

    private void fillList(String[] datas, List list) {
        switch (datas.length) {
            case 3:
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
