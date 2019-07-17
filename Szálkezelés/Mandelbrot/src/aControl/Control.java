/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aSurface.MandelbrotPanel;
import java.awt.Graphics;

/**
 *
 * @author b6dmin
 */
public class Control {

    final MainFrame MAINFRAME;
    final MandelbrotPanel MANDELBROTPANEL;

    private final int MAX_ITER = 570;

    Control(MainFrame mf, MandelbrotPanel mp) {
        this.MAINFRAME = mf;
        this.MANDELBROTPANEL = mp;
    }

    private double zx, zy, cX, cY, tmp;

    public void drawMandelbrot(Graphics g, double zoom) {
        int drawingWidth = MANDELBROTPANEL.getWidth();
        int drawingHeight = MANDELBROTPANEL.getHeight();

        if (zoom > MANDELBROTPANEL.getZOOM_MIN()) {
            drawMyBrot(g, zoom, drawingWidth, drawingHeight);
        }
    }

    private void drawMyBrot(Graphics g, double zoom, int width, int height) {

    }
}
