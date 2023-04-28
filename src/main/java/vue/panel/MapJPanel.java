package vue.panel;

import app.App;
import app.map.Station;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.*;

import vue.utils.BuilderJComposant;
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

/**
 * Class MapJpanel est une class jpanel incluant la map JXmapViewer (qui est un composant jpanel)
 * cette class contient les points placés
 */
public class MapJPanel extends JPanel {

    private final JXMapViewer viewer;
    private final Set<WaypointStation> positionsTrajet;
    private final WaypointPainter<WaypointStation> waypointPainter;
    private BufferedImage cursor_image;

    private final double paris_latitude = 48.8588548, paris_longitude = 2.347035;

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
        testCursor();

        try {
            URL url = App.class.getResource(Props.cursorImage);
            if (url != null) cursor_image = ImageIO.read(new File(url.getFile()));
        }catch (IOException e) {
            System.out.println("Fichier introuvable");
        }
    }


    /**
     * Cette fonction permet de setup la wayPointViewer
     */
    private void setupWayPointViewer() {
        waypointPainter.setRenderer((g, map, waypoint) -> {
            Point2D p = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());
            g.drawImage(cursor_image, (int) p.getX(), (int)p.getY(), this);
            g.setFont(BuilderJComposant.lemontRegularFont(20));
            g.setColor(Color.black);
            g.drawString(waypoint.getNameStation(), (int) p.getX(), (int)p.getY());
        });
        viewer.setOverlayPainter(waypointPainter);
    }


    /**
     * Fonction de test d'affichage simple
     */
    private void testCursor(){
        GeoPosition position = new GeoPosition(paris_latitude,paris_longitude);
        positionsTrajet.add(new WaypointStation(position, "Station de test"));
        waypointPainter.setWaypoints(positionsTrajet);
    }


    /**
     * Fonction qui permet d'init correctement la MapViewer
     */
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

    /**
     * Cette fonction supprime tous les points
     */
    public void clearPoint(){
        positionsTrajet.clear();
        update();
    }

    /**
     * Fonction de mise à jour graphique
     */
    public void update(){
        waypointPainter.setWaypoints(positionsTrajet);
        repaint();
        revalidate();
        viewer.repaint();
        viewer.revalidate();
    }

    /**
     * Fonction qui ajout un point à la liste
     * @param station est l'objet station afin de recuperer les coordonnées et le nom de la station
     */
    public void addPoint(Station station){
        System.out.println(station.getCoordinate().getLatitude() + "  " + station.getCoordinate().getLongitude() );
        GeoPosition position = new GeoPosition(station.getCoordinate().getLongitude(), station.getCoordinate().getLatitude());
        positionsTrajet.add(new WaypointStation(position, station.getName()));
        update();
    }

    /**
     * Class interne : Cette classe permet de stocker l'attribut nameStation
     * en plus des coordonnées de Londitude et Latitude
     */
    private static class WaypointStation extends DefaultWaypoint {

        private final String nameStation;

        WaypointStation(GeoPosition position, String nameStation){
            super(position);
            this.nameStation = nameStation;
        }

        public String getNameStation() {
            return nameStation;
        }
    }
}

