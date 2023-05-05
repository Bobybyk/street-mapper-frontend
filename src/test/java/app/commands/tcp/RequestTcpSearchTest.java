package app.commands.tcp;

import commands.tcp.out.RequestTcpSearchStation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

public class RequestTcpSearchTest {


    private static final int TIMEOUT_SECONDS = 2;

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestSearchTooManyArgs(){
        RequestTcpSearchStation searchStation = new RequestTcpSearchStation();
        String[] args ={ "test", "test", "test", "11:15", "test", "test", "test", "test"};
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> searchStation.commandBuilder(args), "Too many args");
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestSearchSearchOk(){
        RequestTcpSearchStation searchStation = new RequestTcpSearchStation();
        String[] args ={ "SEARCH", "Bercy", "Bercy"};
        assertEquals("SEARCH;Bercy;Bercy", searchStation.commandBuilder(args));
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testRequestSearchNotOk(){
        RequestTcpSearchStation searchStation = new RequestTcpSearchStation();
        String[] args ={ "SEARCH", "Bercy", "Bercy"};
        assertNotEquals("SEARCH;Bastille;Bercy", searchStation.commandBuilder(args));
    }


}
