package vue.panel;

import app.App;
import app.map.Station;
import console.Debug;
import console.DebugList;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.*;

import vue.composant.FlatComboBox;
import vue.composant.FlatJButtonRound;
import vue.composant.FlatJRadioButton;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private GeoPosition pos1, pos2;
    private final FlatComboBox comboBoxdepart, comboBoxarrive;
    private final FlatJRadioButton trackerButton;
    private final FlatJButtonRound clearButton;

    private final double paris_latitude = 48.8588548, paris_longitude = 2.347035;
    private final int zoom = 6;

    public MapJPanel(FlatComboBox comboBoxdepart,FlatComboBox comboBoxarrive){
        this.viewer  = new JXMapViewer();
        this.positionsTrajet = new HashSet<>();
        this.waypointPainter = new WaypointPainter<>();
        this.comboBoxdepart = comboBoxdepart;
        this.comboBoxarrive = comboBoxarrive;
        this.trackerButton = new FlatJRadioButton(Props.placerMarquer, Color.red, Color.BLACK);
        this.clearButton = new FlatJButtonRound(15,Props.nettoyerMap);

        final Dimension d = new Dimension(800, 900);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setupMapViewer();
        setupWayPointViewer();
        eventMouseListener();
        loadImage();
        eventButtonListener();
        styleComposant();
        GroupLayout layoutPrincpal = new GroupLayout(viewer);

        layoutPrincpal.setHorizontalGroup(
                layoutPrincpal.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layoutPrincpal.createSequentialGroup().addGap(20).addComponent(false, trackerButton, 200, 200, 200))
                        .addGroup(layoutPrincpal.createSequentialGroup().addGap(240).addComponent(false, clearButton, 200, 200, 200))

        );
        /* Y */
        layoutPrincpal.setVerticalGroup(
                layoutPrincpal.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layoutPrincpal.createSequentialGroup().addGap(20).addComponent(false, trackerButton, 50, 50, 50))
                        .addGroup(layoutPrincpal.createSequentialGroup().addGap(20).addComponent(false, clearButton, 50, 50, 50))
        );
        setLayout(new GridLayout(1, 1));
        viewer.setLayout(layoutPrincpal);
        add(viewer);
    }

    /**
     * Style des composants graphiques sur la map
     */
    private void styleComposant() {
        clearButton.setBackground(Color.white);
        clearButton.setForeground(Color.BLACK);
        clearButton.setFont(BuilderJComposant.lemontRegularFont(18f));
        clearButton.setFocusPainted(false);
        trackerButton.setOpaque(true);
        trackerButton.setFont(BuilderJComposant.lemontRegularFont(18f));
        trackerButton.setBackground(Color.white);
        trackerButton.setForeground(Color.BLACK);
        trackerButton.setBorderPainted(true);
        trackerButton.setBorder(new FlatJButtonRound.RoundBorder(10));
    }

    /**
     * Event de la souris lié à la map
     */
    private void eventMouseListener() {
        viewer.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (trackerButton.isSelected()) {
                    if (pos1 == null) {
                        pos1 = viewer.convertPointToGeoPosition(mouseEvent.getPoint());
                        addPoint(new GeoPosition(pos1.getLatitude(), pos1.getLongitude()), Props.poseA);
                        comboBoxdepart.setText(pos1.toString().replace("[", "(").replace("]", ")"));
                        update();
                    } else {
                        if (pos2 == null) {
                            pos2 = viewer.convertPointToGeoPosition(mouseEvent.getPoint());
                            addPoint(new GeoPosition(pos2.getLatitude(), pos2.getLongitude()), Props.poseB);
                            comboBoxarrive.setText(pos2.toString().replace("[", "(").replace("]", ")"));
                            trackerButton.setSelected(false);
                            update();
                        } else {
                            pos1 = null;
                            pos2 = null;
                            clearPoint();
                        }
                    }
                    update();
                }
            }

        });
    }

    private void eventButtonListener(){
        clearButton.addActionListener(e->{
            this.trackerButton.setSelected(false);
            this.pos1 = null;
            this.pos2 = null;
            clearPoint();
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
        waypointPainter.setRenderer((graphics2D, map, waypoint) -> {
            Point2D p = map.getTileFactory().geoToPixel(waypoint.getPosition(), map.getZoom());
            graphics2D.drawImage(cursor_image,(int) p.getX()-25, (int)p.getY()-50, this);
            graphics2D.setFont(BuilderJComposant.lemontRegularFont(20));
            graphics2D.setColor(Color.black);
            graphics2D.drawString(waypoint.getNameStation(), (int) p.getX()-30, (int)p.getY()-80);
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
        GeoPosition position = new GeoPosition(station.getCoordinate().getLongitude(), station.getCoordinate().getLatitude());
        positionsTrajet.add(new WaypointStation(position, station.getName()));
        update();
    }

    /**
     * Fonction qui ajout un point à la liste
     * @param position position locale
     * @param msg Position à afficher
     */
    public void addPoint(GeoPosition position, String msg){
        positionsTrajet.add(new WaypointStation(position, msg));
        update();
    }

    private void loadImage() {
        try {
            URL url = App.class.getResource(Props.cursorImage);
            if (url != null) cursor_image = ImageIO.read(new File(url.getFile()));
        }catch (IOException e) {
            Debug.print(DebugList.ERROR,"Fichier introuvable");
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

