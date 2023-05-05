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
    public void testRequestNotGoodNumberTime(){
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args ={ "test", "test", "dzdz"};
        assertThrows(NumberFormatException.class, () -> timeStation.commandBuilder(args), "Not number in arg[3]");
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestTimeTooManyArgs(){
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args ={ "test", "test", "test", "11:15", "test", "test", "test", "test"};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> timeStation.commandBuilder(args), "Too many args");
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestNotTime(){
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args ={ "test", "test", "test"};
        assertThrows(IllegalArgumentException.class, () -> timeStation.commandBuilder(args), "Not hours in arg[3]");
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestTimeOk(){
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args ={ "TIME", "STATIONA", "11:12"};
        assertEquals("TIME;STATIONA;11:12", timeStation.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestTime(){
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args ={ "TIME", "STATIONA", "11:12"};
        assertNotEquals("ROUTE;STATIONA;STATIONB;11:12;TEMPS", timeStation.commandBuilder(args));
    }

}
