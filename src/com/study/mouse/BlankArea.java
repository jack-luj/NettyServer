package com.study.mouse;

/**
 * Created by jack lu on 2016/3/31.
 */
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;

public class BlankArea extends JLabel {
    Dimension minSize = new Dimension(100, 50);

    public BlankArea(Color color) {
        setBackground(color);
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }
}