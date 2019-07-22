/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

/**
 *
 * @author b6dmin
 */
public class Player implements Comparable<Player> {

    private final String NAME;
    private int score;

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

    public void getPoints(int points) {
        score += points;
    }

    @Override
    public int compareTo(Player o) {
        return 0;
    }

}
