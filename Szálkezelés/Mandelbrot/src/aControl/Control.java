/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aSurface.MandelbrotPanel;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author b6dmin
 */
public class Control {

    final MainFrame MAINFRAME;
    final MandelbrotPanel MANDELBROTPANEL;

    private final int MAX_ITER = 570;
    private final int SHIFTING_X = 1000;
    private final int SHIFTING_Y = 200;

    Control(MainFrame mf, MandelbrotPanel mp) {
        this.MAINFRAME = mf;
        this.MANDELBROTPANEL = mp;
    }

    private double zx, zy, cX, cY, tmp;

    public void drawMandelbrot(Graphics g, double zoom) {
        int drawingWidth = MANDELBROTPANEL.getWidth();
        int drawingHeight = MANDELBROTPANEL.getHeight();

        if (zoom >= MANDELBROTPANEL.getZOOM_MIN()) {
            drawMyBrot(g, zoom, drawingWidth, drawingHeight);
        }
    }

    private void drawMyBrot(Graphics g, double zoom, int width, int height) {
        for (int y = -SHIFTING_Y; y < height - SHIFTING_Y; y++) {
            for (int x = -SHIFTING_X; x < width - SHIFTING_X; x++) {
                zx = zy = 0;
                cX = (x - width / 2) / zoom;
                cY = (y - height / 2) / zoom;
                int iter = MAX_ITER;
                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }
                g.setColor(new Color((iter | (iter << 4)) * 4));
                g.drawLine(x + SHIFTING_X, y + SHIFTING_Y,
                        x + SHIFTING_X, y + SHIFTING_Y);
            }
        }
    }
}
