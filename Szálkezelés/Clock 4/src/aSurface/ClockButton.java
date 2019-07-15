/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aSurface;

import aControl.Control;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author b6dmin
 */
public class ClockButton extends javax.swing.JButton {

    Control control;

    public void setControl(Control control) {
        this.control = control;
    }

    public ClockButton() {
        super.setPreferredSize(new Dimension(80, 80));
        super.setMaximumSize(new Dimension(80, 80));
        super.setMinimumSize(new Dimension(80, 80));
        super.setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(getParent().getForeground());
        } else {
            g.setColor(getParent().getBackground());
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        super.paintComponent(g);
        //System.out.println("cButton paintComponent");
        if (control != null) {
            control.drawOnClockButton(g);
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.DARK_GRAY);
        } else {
            g.setColor(Color.GRAY);
        }
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    Shape shape;

    @Override
    public boolean contains(int x, int y) {
        // If the button has changed size,  make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}
