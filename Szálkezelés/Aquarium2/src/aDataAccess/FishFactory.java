/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aDataAccess;

import aBasis.Fish;
import static aBasis.Global.*;
import aBasis.ImagePair;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
class FishFactory {

    private static FishFactory instance;

    private FishFactory() {
    }

    public static FishFactory getInstance() {
        if (instance == null) {
            instance = new FishFactory();
        }
        return instance;
    }

    public Fish getFish(String name, int number) {
        int imageWidth, imageHeigth;
        Image leftImage, rightImage;

        leftImage = new ImageIcon(this.getClass().
                getResource(FISHES_URL + number + "_BAL.png")).getImage();
        rightImage = new ImageIcon(this.getClass().
                getResource(FISHES_URL + number + "_JOBB.png")).getImage();
        imageWidth = (int) (Math.random()
                * (UPPER_LIMIT - LOWER_LIMIT) + LOWER_LIMIT);
        imageHeigth = (int) (Math.random()
                * (UPPER_LIMIT - LOWER_LIMIT) + LOWER_LIMIT);

        return new Fish(name,
                imageWidth, imageHeigth, new ImagePair(leftImage, rightImage));
    }
}
