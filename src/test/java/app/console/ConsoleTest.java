package app.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import commands.debug.CommandHelp;
import commands.debug.CommandIndexesList;
import commands.debug.CommandKill;
import commands.debug.CommandSettings;
import commands.tcp.RequestIndexesList;
import commands.tcp.out.RequestTcpRoute;
import commands.tcp.out.RequestTcpSearchStation;
import commands.tcp.out.RequestTcpTimeStation;
import console.Console;

class ConsoleTest {
    private static final int TIMEOUT_SECONDS = 2000;

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testCommandSize() {
        assertEquals(3, Console.getCommandList().size());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestSize() {
        assertEquals(3, Console.getRequestList().size());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestRoute() {
        assertEquals(RequestTcpRoute.class,
                Console.getRequestList().get(RequestIndexesList.ROUTE).getClass());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestSearch() {
        assertEquals(RequestTcpSearchStation.class,
                Console.getRequestList().get(RequestIndexesList.SEARCH).getClass());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testRequestTime() {
        assertEquals(RequestTcpTimeStation.class,
                Console.getRequestList().get(RequestIndexesList.TIME).getClass());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testCommandKill() {
        assertEquals(CommandKill.class,
                Console.getCommandList().get(CommandIndexesList.KILL).getClass());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testCommandHelp() {
        assertEquals(CommandHelp.class,
                Console.getCommandList().get(CommandIndexesList.HELP).getClass());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testCommandDebug() {
        assertEquals(CommandSettings.class,
                Console.getCommandList().get(CommandIndexesList.DEBUG).getClass());
    }
}
