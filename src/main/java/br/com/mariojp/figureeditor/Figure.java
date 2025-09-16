package br.com.mariojp.figureeditor;

import java.awt.*;

public interface Figure {
    void draw(Graphics2D g2);
    boolean contains(Point p);
    void move(int dx, int dy);
    Rectangle getBounds();
}
