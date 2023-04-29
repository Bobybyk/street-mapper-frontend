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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.spec.RSAOtherPrimeInfo;
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
    private GeoPosition pos1, pos2;

    private final double paris_latitude = 48.8588548, paris_longitude = 2.347035;
    private final int zoom = 8;

    public MapJPanel(){
        viewer  = new JXMapViewer();
        positionsTrajet = new HashSet<>();
        waypointPainter = new WaypointPainter<>();
        final Dimension d = new Dimension(800, 900);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setLayout(new GridLayout(1, 1));
        setupMapViewer();
        setupWayPointViewer();
        eventMouseListener();
        loadImage();
        testCursor();
        add(viewer);
    }

    private void eventMouseListener() {

        viewer.addMouseListener(new MouseInputListener(){
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                Graphics2D g2d =  (Graphics2D) viewer.getGraphics();
                g2d.setStroke(new BasicStroke(30));
                Point2D pointp1 = viewer.getTileFactory().geoToPixel(pos1, viewer.getZoom());
                Point2D pointp2= viewer.getTileFactory().geoToPixel(pos1, viewer.getZoom());
                g2d.drawLine((int) pointp1.getX(), (int) pointp1.getY(), (int) pointp2.getX(), (int) pointp2.getY());
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
              /*  clearPoint();
                 pos1 = viewer.getTileFactory().pixelToGeo(mouseEvent.getPoint(), viewer.getZoom());
                pos2 = viewer.getTileFactory().pixelToGeo(mouseEvent.getPoint(), viewer.getZoom());
                update();*/
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
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
     * Fonction qui permet d'init correctement la MapViewer
     */
    private void setupMapViewer() {

        GeoPosition position = new GeoPosition(paris_latitude, paris_longitude);
        DefaultTileFactory tileFactory = new DefaultTileFactory(new OSMTileFactoryInfo());
        viewer.setTileFactory(tileFactory);
        viewer.setZoom(zoom);
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
    private void update(){
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
        GeoPosition position = new GeoPosition(station.getCoordinate().getLatitude(), station.getCoordinate().getLongitude());
        positionsTrajet.add(new WaypointStation(position, station.getName()));
        update();
    }

    private void loadImage() {
        try {
            URL url = App.class.getResource(Props.cursorImage);
            if (url != null) cursor_image = ImageIO.read(new File(url.getFile()));
        }catch (IOException e) {
            System.out.println("Fichier introuvable");
        }
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

