/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.util.Objects;

/**
 *
 * @author b6dmin
 */
public class Player implements Comparable<Player> {

    private final String NAME;
    private int score;
    private static String pointTxt = "pont";

    public static void setPointTxt(String pointTxt) {
        Player.pointTxt = pointTxt;
    }

    public String getNAME() {
        return NAME;
    }

    public int getScore() {
        return score;
    }

    public Player(String n) {
        this.NAME = n;
    }

    public Player(String n, int s) {
        this.NAME = n;
        this.score = s;
    }

    public void addPoints(int points) {
        score += points;
    }

    @Override
    public String toString() {
        return this.NAME + " " + score + " " + Player.pointTxt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.NAME);
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
        final Player other = (Player) obj;
        if (!Objects.equals(this.NAME, other.NAME)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Player o) {
        return o.getScore() - this.getScore();
    }

}
