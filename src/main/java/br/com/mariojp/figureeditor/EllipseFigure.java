package br.com.mariojp.figureeditor;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class EllipseFigure implements Figure {
    private Ellipse2D ellipse;
    private Color fillColor;
    private Color borderColor;
    private Stroke borderStroke;

    public EllipseFigure(double x, double y, double width, double height,
                         Color fillColor, Color borderColor, Stroke borderStroke) {
        this.ellipse = new Ellipse2D.Double(x, y, width, height);
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.borderStroke = borderStroke;
    }

    @Override
    public void draw(Graphics2D g2) {
        Color originalColor = g2.getColor();
        Stroke originalStroke = g2.getStroke();

        g2.setColor(fillColor);
        g2.fill(ellipse);

        g2.setColor(borderColor);
        g2.setStroke(borderStroke);
        g2.draw(ellipse);

        g2.setColor(originalColor);
        g2.setStroke(originalStroke);
    }

    @Override
    public boolean contains(Point p) {
        return ellipse.contains(p);
    }

    @Override
    public void move(int dx, int dy) {
        ellipse.setFrame(ellipse.getX() + dx, ellipse.getY() + dy,
                         ellipse.getWidth(), ellipse.getHeight());
    }

    @Override
    public Rectangle getBounds() {
        return ellipse.getBounds();
    }
}
