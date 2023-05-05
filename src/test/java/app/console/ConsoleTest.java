package app.console;

import commands.debug.CommandHelp;
import commands.debug.CommandIndexesList;
import commands.debug.CommandKill;
import commands.debug.CommandSettings;
import commands.tcp.RequestIndexesList;
import commands.tcp.out.RequestTcpRoute;
import commands.tcp.out.RequestTcpSearchStation;
import commands.tcp.out.RequestTcpTimeStation;
import console.Console;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleTest {


    @Test
    public void testCommandSize(){
        assertEquals(Console.getCommandList().size(), 3);
    }

    @Test
    public void testRequestSize(){
        assertEquals(Console.getRequestList().size(), 3);
    }

    @Test
    public void testRequestRoute(){
        assertEquals(Console.getRequestList().get(RequestIndexesList.ROUTE).getClass(), RequestTcpRoute.class);
    }

    @Test
    public void testRequestSearch(){
        assertEquals(Console.getRequestList().get(RequestIndexesList.SEARCH).getClass(), RequestTcpSearchStation.class);
    }

    @Test
    public void testRequestTime(){
        assertEquals(Console.getRequestList().get(RequestIndexesList.TIME).getClass(), RequestTcpTimeStation.class);
    }

    @Test
    public void testCommandKill(){
        assertEquals(Console.getCommandList().get(CommandIndexesList.KILL).getClass(), CommandKill.class);
    }

    @Test
    public void testCommandHelp(){
        assertEquals(Console.getCommandList().get(CommandIndexesList.HELP).getClass(), CommandHelp.class);
    }

    @Test
    public void testCommandDebug(){
        assertEquals(Console.getCommandList().get(CommandIndexesList.DEBUG).getClass(), CommandSettings.class);
    }

}
