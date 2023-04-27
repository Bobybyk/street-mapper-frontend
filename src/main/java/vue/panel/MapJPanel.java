package vue.panel;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.util.Set;

public class MapJPanel extends JPanel {

    private final JXMapViewer viewer;

    public MapJPanel(){
        viewer  = new JXMapViewer();
        setPreferredSize(new Dimension(800, 900));
        setMinimumSize(new Dimension(800, 900));
        setMaximumSize(new Dimension(800, 900));
        GeoPosition position = new GeoPosition(48.8588548, 2.347035);
        DefaultTileFactory tileFactory = new DefaultTileFactory(new OSMTileFactoryInfo());
        viewer.setTileFactory(tileFactory);
        setLayout(new GridLayout(1, 1));
        viewer.setZoom(8);
        viewer.setAddressLocation(position);
        MouseInputListener mn = new PanMouseInputListener(viewer);
        viewer.addMouseListener(mn);
        viewer.addMouseMotionListener(mn);
        viewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(viewer));
        add(viewer);
    }


    public void clearPoint(){

    }

    public void addPoint(GeoPosition position){
        DefaultWaypoint defaultWaypoint= new DefaultWaypoint(position);
        WaypointPainter<DefaultWaypoint> waypointWaypointPainter = new WaypointPainter<>();
        waypointWaypointPainter.setWaypoints((Set<? extends DefaultWaypoint>) defaultWaypoint);
        viewer.setOverlayPainter(waypointWaypointPainter);
    }
}
