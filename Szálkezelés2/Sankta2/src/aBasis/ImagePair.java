/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Image;

/**
 *
 * @author b6dmin
 */
public class ImagePair {

    private final Image UpwardsView;
    private final Image DownwardsView;

    public Image getUpwardsView() {
        return UpwardsView;
    }

    public Image getDownwardsView() {
        return DownwardsView;
    }

    public ImagePair(Image upwardsView, Image downwardsView) {
        this.UpwardsView = upwardsView;
        this.DownwardsView = downwardsView;
    }

}
