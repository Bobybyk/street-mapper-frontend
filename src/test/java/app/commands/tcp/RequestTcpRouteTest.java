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
    public void testRequestRouteRouteDistanceNotOk(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "DISTANCE"};
        assertNotEquals("ROUTE;STATIONB;STATIONA;11:12;DISTANCE", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteRouteTimeNotOk(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "TIME"};
        assertNotEquals("ROUTE;STATIONB;STATIONB;1212;TIME", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteRouteDistanceFootNotOk(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "DISTANCE", "FOOT"};
        assertSame("ROUTE;STATIONA;STATIONB;11:12;DISTANCE", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteRouteTimeFootNotOk(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "TIME", "FOOT"};
        assertNotEquals("ROUTE;STATIONA;STATIONB;11:12;DISTANCE;FOOT", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteRouteDistance(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "DISTANCE"};
        assertSame("ROUTE;STATIONA;STATIONB;11:12;DISTANCE", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteRouteTime(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "TIME"};
        assertSame("ROUTE;STATIONA;STATIONB;11:12;TIME", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteRouteDistanceFoot(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "DISTANCE", "FOOT"};
        assertSame("ROUTE;STATIONA;STATIONB;11:12;DISTANCE;FOOT", requestTcpRoute.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteRouteTimeFoot(){
        RequestTcpRoute requestTcpRoute = new RequestTcpRoute();
        String[] args ={ "ROUTE", "STATIONA", "STATIONB", "11:12", "TIME", "FOOT"};
        assertSame("ROUTE;STATIONA;STATIONB;11:12;TIME;FOOT", requestTcpRoute.commandBuilder(args));
    }

}
