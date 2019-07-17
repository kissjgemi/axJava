/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aControl;

import aSurface.SnowFlakePanel;
import java.awt.Graphics;

/**
 *
 * @author b6dmin
 */
public class Control {

    final MainFrame MAINFRAME;
    final SnowFlakePanel SNOWFLAKEPANEL;

    Control(MainFrame mf, SnowFlakePanel sp) {
        this.MAINFRAME = mf;
        this.SNOWFLAKEPANEL = sp;
    }

    private void snowFlakeLine(Graphics g, int level,
            int xfirst, int yfirst, int xlast, int ylast) {
        int deltaX, deltaY, x2, x3, x4, y2, y3, y4;

        if (level == 0) {
            g.drawLine(xfirst, yfirst, xlast, ylast);
        } else {
            deltaX = xlast - xfirst;
            deltaY = ylast - yfirst;

            x2 = xfirst + deltaX / 3;
            y2 = yfirst + deltaY / 3;
            x3 = (int) (0.5 * (xfirst + xlast) + Math.sqrt(3) * (yfirst - ylast) / 6);
            y3 = (int) (0.5 * (yfirst + ylast) + Math.sqrt(3) * (xlast - xfirst) / 6);
            x4 = xfirst + 2 * deltaX / 3;
            y4 = yfirst + 2 * deltaY / 3;

            snowFlakeLine(g, level - 1, xfirst, yfirst, x2, y2);
            snowFlakeLine(g, level - 1, x2, y2, x3, y3);
            snowFlakeLine(g, level - 1, x3, y3, x4, y4);
            snowFlakeLine(g, level - 1, x4, y4, xlast, ylast);
        }
    }

    final int margin = 30;
    final int lowerMargin = 180;

    public void drawFlake(Graphics g, int level) {
        int drawingWidth = SNOWFLAKEPANEL.getWidth() - 2 * margin;
        int drawingHeigth = SNOWFLAKEPANEL.getHeight() - margin - lowerMargin;

        int x1 = margin;
        int x2 = margin + drawingWidth;
        int x3 = margin + drawingWidth / 2;

        int y1 = margin + drawingHeigth;
        int y2 = margin + drawingHeigth;
        int y3 = margin;

        if (level > -1) {
            snowFlakeLine(g, level, x1, y1, x2, y2);
            snowFlakeLine(g, level, x2, y2, x3, y3);
            snowFlakeLine(g, level, x3, y3, x1, y1);
        }
    }
}
