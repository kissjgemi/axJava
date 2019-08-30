/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aBasis;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author b6dmin
 */
public class ZenData implements Serializable{

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
