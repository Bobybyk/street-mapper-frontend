package app.console;

import console.DebugList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebugListTest {

    private static final int TIMEOUT_SECONDS = 2;

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugListError(){
        assertEquals(DebugList.ERROR, -1);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugListNetWork(){
        assertEquals(DebugList.NETWORK, 4);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugListInfo(){
        assertEquals(DebugList.INFO, 1);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugListWarning(){
        assertEquals(DebugList.WARNING, 0);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugListGeneral(){
        assertEquals(DebugList.GENERAL, 2);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugListSettings(){
        assertEquals(DebugList.SETTINGS, 3);
    }

}
