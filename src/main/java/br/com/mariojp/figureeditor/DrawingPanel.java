package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

class DrawingPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_SIZE = 60;
    private final List<Figure> figures = new ArrayList<>();
    private Point startDrag = null;

    DrawingPanel() {
        setBackground(Color.WHITE);
        setOpaque(true);
        setDoubleBuffered(true);

        var mouse = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1 && startDrag == null) {
                    addDefaultEllipse(e.getPoint());
                    repaint();
                }
            }
        };
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    private void addDefaultEllipse(Point p) {
        int size = Math.max(Math.min(DEFAULT_SIZE, DEFAULT_SIZE), 10);
        double x = p.x - size / 2.0;
        double y = p.y - size / 2.0;

        Color fillColor = new Color(30, 144, 255);
        Color borderColor = new Color(0, 0, 0, 70);
        Stroke borderStroke = new BasicStroke(1.2f);

        Figure newEllipse = new EllipseFigure(x, y, size, size, fillColor, borderColor, borderStroke);
        figures.add(newEllipse);
    }

    void clear() {
        figures.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Figure figure : figures) {
            figure.draw(g2);
        }
        g2.dispose();
    }
}
