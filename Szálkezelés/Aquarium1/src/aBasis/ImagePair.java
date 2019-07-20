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

    private final Image LeftView;
    private final Image RightView;

    public Image getLeftView() {
        return LeftView;
    }

    public Image getRightView() {
        return RightView;
    }

    public ImagePair(Image LeftView, Image RightView) {
        this.LeftView = LeftView;
        this.RightView = RightView;
    }

}
