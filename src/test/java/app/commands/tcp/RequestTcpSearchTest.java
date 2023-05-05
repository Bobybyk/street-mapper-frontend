package app.commands.tcp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import commands.tcp.out.RequestTcpSearchStation;

class RequestTcpSearchTest {

    private static final int TIMEOUT_SECONDS = 2000;

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestSearchTooManyArgs() {
        RequestTcpSearchStation searchStation = new RequestTcpSearchStation();
        String[] args = {"test", "test", "test", "11:15", "test", "test", "test", "test"};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> searchStation.commandBuilder(args),
                "Too many args");
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestSearchSearchOk() {
        RequestTcpSearchStation searchStation = new RequestTcpSearchStation();
        String[] args = {"SEARCH", "Bercy", "Bercy"};
        assertEquals("SEARCH;Bercy;Bercy", searchStation.commandBuilder(args));
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestSearchNotOk() {
        RequestTcpSearchStation searchStation = new RequestTcpSearchStation();
        String[] args = {"SEARCH", "Bercy", "Bercy"};
        assertNotEquals("SEARCH;Bastille;Bercy", searchStation.commandBuilder(args));
    }


}
