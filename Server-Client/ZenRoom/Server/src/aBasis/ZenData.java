/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class ZenData implements Serializable {

    private final ImageIcon zenFace;
    private final String zenUserName;

    public ImageIcon getZenFace() {
        return zenFace;
    }

    public String getZenUserName() {
        return zenUserName;
    }

    public ZenData(ImageIcon zenFace, String zenUserName) {
        this.zenFace = zenFace;
        this.zenUserName = zenUserName;
    }
}
