/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Objects;

/**
 *
 * @author b6dmin
 */
public class Fighter implements Comparable<Fighter> {

    private final String NAME;
    private final int ordinalNumber;
    private boolean alive = true;
    private int extentOfDamage;
    private Color color;
    private int fig_x, fig_y;

    public String getNAME() {
        return NAME;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public int getExtentOfDamage() {
        return extentOfDamage;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setFig_x(int fig_x) {
        this.fig_x = fig_x;
    }

    public void setFig_y(int fig_y) {
        this.fig_y = fig_y;
    }

    private static int radius;

    public static void setRadius(int radius) {
        Fighter.radius = radius;
    }

    public static int getRadius() {
        return radius;
    }

    private static int lastNumber;

    public static void setLastNumber(int lastNumber) {
        Fighter.lastNumber = lastNumber;
    }

    public Fighter(String nev) {
        this.NAME = nev;
        lastNumber++;
        ordinalNumber = lastNumber;
    }

    public String rank() {
        return "fighter";
    }

    public void attack() {
        if (alive) {
            extentOfDamage++;
        }
    }

    public void wounded() {
        if (alive) {
            extentOfDamage--;
        }
        if (extentOfDamage < 0) {
            alive = false;
        }
    }

    public boolean isClicked(int x, int y) {
        double distance = Math.sqrt(
                (x - fig_x) * (x - fig_x) + (y - fig_y) * (y - fig_y));
        System.out.println("distance: " + distance);
        return distance < radius;
    }

    @Override
    public String toString() {
        return String.format("%2d. %s (%d sebzés)",
                ordinalNumber, NAME, extentOfDamage);
    }

    public void drawOnField(Graphics g) {
        g.setColor(color);
        g.fillOval(fig_x - radius, fig_y - radius, 2 * radius, 2 * radius);
    }

    protected void setFighterColor(Color c) {
        this.color = c;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fighter other = (Fighter) obj;
        if (this.ordinalNumber != other.ordinalNumber) {
            return false;
        }
        if (!Objects.equals(this.NAME, other.NAME)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Fighter o) {
        if (o.getExtentOfDamage() == this.extentOfDamage) {
            return ordinalNumber - o.getOrdinalNumber();
        }
        return o.getExtentOfDamage() - this.extentOfDamage;
    }
}
