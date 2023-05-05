package app.commands.tcp;

import commands.tcp.out.RequestTcpRoute;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTcpRouteTest {



    private static final int TIMEOUT_SECONDS = 2;

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteTooManyArgs(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "test", "test", "test", "11:15", "test", "test", "test", "test"};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> requestTcpRoute.commandBuilder(args), "Too many args");
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteNotTime(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "test", "test", "test", "abc"};
        assertThrows(IllegalArgumentException.class, () -> requestTcpRoute.commandBuilder(args), "Not hours in arg[3]");
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteDistanceNotOk(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "DISTANCE"};
        assertNotEquals("ROUTE;STATIONB;STATIONA;11:12;DISTANCE;", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteTimeNotOk(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "TEMPS"};
        assertNotEquals("ROUTE;STATIONB;STATIONB;12:12;TEMPS;", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteDistanceFootNotOk(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "DISTANCE", "FOOT"};
        assertNotEquals("ROUTE;STATIONA;STATIONB;11:12;DISTANCE;", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteTimeFootNotOk(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "TEMPS", "FOOT"};
        assertNotEquals("ROUTE;STATIONA;STATIONB;11:12;DISTANCE;FOOT", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteDistance(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "DISTANCE"};
        assertEquals("ROUTE;STATIONA;STATIONB;11:12;DISTANCE;", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteTime(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "TEMPS"};
        assertEquals("ROUTE;STATIONA;STATIONB;11:12;TEMPS;", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteDistanceFoot(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "DISTANCE", "FOOT"};
        assertEquals("ROUTE;STATIONA;STATIONB;11:12;DISTANCE;FOOT", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteTimeFoot(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "TEMPS", "FOOT"};
        assertEquals("ROUTE;STATIONA;STATIONB;11:12;TEMPS;FOOT", requestTcpRoute.commandBuilder(args));
    }

}
