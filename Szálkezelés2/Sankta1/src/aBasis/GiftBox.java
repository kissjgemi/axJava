/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author b6dmin
 */
public class GiftBox {

    private final Image image;
    private final String name;
    private final int imgX;
    private final int imgY;

    public String getName() {
        return name;
    }

    public int getImgX() {
        return imgX;
    }

    public int getImgY() {
        return imgY;
    }

    public GiftBox(Image image, String name, int imgX, int imgY) {
        this.image = image;
        this.name = name;
        this.imgX = imgX;
        this.imgY = imgY;
    }

    public void drawGraphic(Graphics g) {
        g.drawImage(image, imgX, imgY, GIFTBOX_SIZE, GIFTBOX_SIZE, null);
    }
}
