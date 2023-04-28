package vue.panel;

import app.App;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.*;
import utils.Observable;
import utils.Observer;
import vue.utils.Props;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MapJPanel extends JPanel {

    private final JXMapViewer viewer;
    private final Set<Waypoint> positionsTrajet;
    private final WaypointPainter<Waypoint> waypointPainter;
    private BufferedImage cursor_image;

    private double paris_latitude = 48.8588548, paris_longitude = 2.347035;

    public MapJPanel(){
        viewer  = new JXMapViewer();
        positionsTrajet = new HashSet<>();
        waypointPainter = new WaypointPainter<>();
        setPreferredSize(new Dimension(800, 900));
        setMinimumSize(new Dimension(800, 900));
        setMaximumSize(new Dimension(800, 900));
        setLayout(new GridLayout(1, 1));
        setupMapViewer();
        setupWayPointViewer();
        add(viewer);

        try {
            URL url = App.class.getResource(Props.cursorImage);
            if (url != null) cursor_image = ImageIO.read(new File(url.getFile()));
        }catch (IOException e) {
            System.out.println("Fichier introuvable");
        }
    }

    private void setupWayPointViewer() {
        waypointPainter.setRenderer((g, map, waypoint) -> {
            Point2D p = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());
            g.drawImage(cursor_image, (int) p.getX(), (int)p.getY(), this);
        });

        waypointPainter.setWaypoints(positionsTrajet);
        viewer.setOverlayPainter(waypointPainter);
    }

    private void setupMapViewer() {
        GeoPosition position = new GeoPosition(paris_latitude, paris_longitude);
        DefaultTileFactory tileFactory = new DefaultTileFactory(new OSMTileFactoryInfo());
        viewer.setTileFactory(tileFactory);
        viewer.setZoom(8);
        viewer.setAddressLocation(position);
        MouseInputListener mn = new PanMouseInputListener(viewer);
        viewer.addMouseListener(mn);
        viewer.addMouseMotionListener(mn);
        viewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(viewer));
    }


    public void clearPoint(){
        positionsTrajet.clear();
    }

    public void addPoint(double l, double L){
        GeoPosition position = new GeoPosition(l, L);
    }

    public void Test(){
        positionsTrajet.add(new DefaultWaypoint(new GeoPosition(48.8588548, 2.347035)));
        positionsTrajet.add(new DefaultWaypoint(new GeoPosition(48.5488548, 2.476034)));
        positionsTrajet.add(new DefaultWaypoint(new GeoPosition(48.8528548, 2.347023)));

    }
}

