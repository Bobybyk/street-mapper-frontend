package app.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import console.DebugList;

class DebugListTest {

    private static final int TIMEOUT_SECONDS = 2000;

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugListError() {
        assertEquals(-1, DebugList.ERROR);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugListNetWork() {
        assertEquals(4, DebugList.NETWORK);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugListInfo() {
        assertEquals(1, DebugList.INFO);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugListWarning() {
        assertEquals(0, DebugList.WARNING);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugListGeneral() {
        assertEquals(2, DebugList.GENERAL);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugListSettings() {
        assertEquals(3, DebugList.SETTINGS);
    }

}
