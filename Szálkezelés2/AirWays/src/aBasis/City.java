/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author b6dmin
 */
public class City {

    private static int cityRadius;
    private static Color cityColor;

    private String cityName;
    private int ax, ay;

    public int getAx() {
        return ax;
    }

    public int getAy() {
        return ay;
    }

    public static void setCityRadius(int cityRadius) {
        City.cityRadius = cityRadius;
    }

    public static void setCityColor(Color cityColor) {
        City.cityColor = cityColor;
    }

    public City(String cityName, int ax, int ay) {
        this.cityName = cityName;
        this.ax = ax;
        this.ay = ay;
    }

    public void drawGraphic(Graphics g) {
        g.setColor(cityColor);
        g.fillOval(ax - cityRadius / 2, ay - cityRadius / 2,
                cityRadius, cityRadius);
    }

    @Override
    public String toString() {
        return cityName;
    }
}
