/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aDatas;

import aBasis.Line;
import aBasis.Point;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b6dmin
 */
public class AirLinesDatas {

    //mainFrame
    public static final String MAINFRAME_TITLE = "Lines in the Air";

    //pointList
    public static final Color pointInitColor = Color.BLACK;
    public static final int POINT_NR = 10;
    public static final int SPEED = 1;
    public static boolean onOff = false;
    public static List<Point> pointList = new ArrayList<>();
    public static List<Line> lineList = new ArrayList<>();

    public static void initPoints(int x_max, int y_max) {
        for (int ii = 0; ii < POINT_NR; ii++) {
            int x = (int) (Math.random() * x_max);
            int y = (int) (Math.random() * y_max);
            pointList.add(new Point(x, y, pointInitColor));
        }
    }

    public static void initLines() {
        for (int ii = 0; ii < pointList.size() - 1; ii++) {
            for (int jj = ii + 1; jj < pointList.size(); jj++) {
                lineList.add(new Line(pointList.get(ii).getPx(),
                        pointList.get(ii).getPy(),
                        pointInitColor,
                        pointList.get(jj).getPx(),
                        pointList.get(jj).getPy()));
            }
        }
    }

    //runnable
    public static final int FRAME_PS = 10;
    public static final long SLEEP_TIME = (long) (1000. / FRAME_PS);
    public static final int RESTART_IN_MILLIS = 100;
    public static final double RED_CHANCES = 0.4;
    public static final double BLUE_CHANCES = 0.3;
}
