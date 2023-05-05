package app.commands.tcp;

import commands.tcp.out.RequestTcpRoute;
import commands.tcp.out.RequestTcpTimeStation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTcpTimeStationTest {


    private static final int TIMEOUT_SECONDS = 2;

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestTimeTooManyArgs(){
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args ={ "test", "test", "test", "11:15", "test", "test", "test", "test"};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> timeStation.commandBuilder(args), "Too many args");
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteNotTime(){
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args ={ "test", "test", "test"};
        assertThrows(IllegalArgumentException.class, () -> timeStation.commandBuilder(args), "Not hours in arg[3]");
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteTimeOk(){
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args ={ "TIME", "STATIONA", "11:12"};
        assertSame("TIME;STATIONA;11:12", timeStation.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestRouteRouteTime(){
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args ={ "TIME", "STATIONA", "11:12"};
        assertNotEquals("ROUTE;STATIONA;STATIONB;11:12;TIME", timeStation.commandBuilder(args));
    }

}
