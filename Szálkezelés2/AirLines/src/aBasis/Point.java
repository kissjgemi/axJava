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
public class Point {

    private int px;
    private int py;
    private Color color;

    public int getPx() {
        return px;
    }

    public int getPy() {
        return py;
    }

    public Color getColor() {
        return color;
    }

    private static int diameter = 8;

    public static int getDiameter() {
        return diameter;
    }

    public static void setDiameter(int diameter) {
        Point.diameter = diameter;
    }

    public Point(int px, int py, Color color) {
        this.px = px;
        this.py = py;
        this.color = color;
    }

    public void drawPoint(Graphics g) {
        g.setColor(color);
        g.fillOval(px - diameter / 2, py - diameter / 2,
                diameter, diameter);
    }

    public void drawStartPoint(Graphics g) {
        g.setColor(color);
        g.drawOval(px - diameter / 2, py - diameter / 2,
                diameter, diameter);
    }

    @Override
    public String toString() {
        return "Point{" + px + ":" + py + ", " + color + '}';
    }
}
