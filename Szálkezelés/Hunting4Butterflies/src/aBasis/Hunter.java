/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import static aBasis.Global.*;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class Hunter {

    private Image image = new ImageIcon(this.getClass().
            getResource(HUNTER_IMAGE)).getImage();

    private int imageX, imageY;
    private static int faceWidth, faceHeight;

    public static void setFaceWidth(int faceWidth) {
        Hunter.faceWidth = faceWidth;
    }

    public static void setFaceHeight(int faceHeight) {
        Hunter.faceHeight = faceHeight;
    }

    public Hunter(int imageX, int imageY) {
        this.imageX = imageX;
        this.imageY = imageY;
    }

    public void drawImage(Graphics g) {
        g.drawImage(image, imageX - faceWidth / 2, imageY - faceHeight / 2,
                faceWidth, faceHeight, null);
    }

    public boolean hunterCatch(int x, int y) {
        return imageX - faceWidth / 2 <= x
                && x <= imageX + faceWidth / 2
                && imageY - faceHeight / 2 <= y
                && y <= imageY + faceHeight / 2;
    }
}
