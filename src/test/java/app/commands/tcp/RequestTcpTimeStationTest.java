package app.commands.tcp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import commands.tcp.out.RequestTcpTimeStation;

class RequestTcpTimeStationTest {

    private static final int TIMEOUT_SECONDS = 2000;

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestNotGoodNumberTime() {
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args = {"test", "test", "dzdz"};
        assertThrows(NumberFormatException.class, () -> timeStation.commandBuilder(args),
                "Not number in arg[3]");
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestTimeTooManyArgs() {
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args = {"test", "test", "test", "11:15", "test", "test", "test", "test"};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> timeStation.commandBuilder(args),
                "Too many args");
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestNotTime() {
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args = {"test", "test", "test"};
        assertThrows(IllegalArgumentException.class, () -> timeStation.commandBuilder(args),
                "Not hours in arg[3]");
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestTimeOk() {
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args = {"TIME", "STATIONA", "11:12"};
        assertEquals("TIME;STATIONA;11:12", timeStation.commandBuilder(args));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestTime() {
        RequestTcpTimeStation timeStation = new RequestTcpTimeStation();
        String[] args = {"TIME", "STATIONA", "11:12"};
        assertNotEquals("ROUTE;STATIONA;STATIONB;11:12;TEMPS", timeStation.commandBuilder(args));
    }

}
