package app.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import console.Debug;
import console.DebugList;

class DebugTest {

    private static final int TIMEOUT_SECONDS = 2000;

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesSize() {
        assertEquals(6, Debug.getDebugTypes().size());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesStatusSize() {
        assertEquals(6, Debug.getDebugTypeStatus().size());
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesStatusError() {
        assertTrue(Debug.getDebugTypeStatus().get(DebugList.ERROR));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesStatusInfo() {
        assertTrue(Debug.getDebugTypeStatus().get(DebugList.INFO));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesStatusWarning() {
        assertTrue(Debug.getDebugTypeStatus().get(DebugList.WARNING));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesStatusGeneral() {
        assertTrue(Debug.getDebugTypeStatus().get(DebugList.GENERAL));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesStatusNetWork() {
        assertTrue(Debug.getDebugTypeStatus().get(DebugList.NETWORK));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesStatusSettings() {
        assertTrue(Debug.getDebugTypeStatus().get(DebugList.SETTINGS));
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesError() {
        assertSame(DebugList.ERROR, Debug.getDebugTypes().get("ERROR"));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesInfo() {
        assertSame(DebugList.INFO, Debug.getDebugTypes().get("INFO"));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesWarning() {
        assertSame(DebugList.WARNING, Debug.getDebugTypes().get("WARNING"));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesGeneral() {
        Assertions.assertSame(DebugList.GENERAL, Debug.getDebugTypes().get("GENERAL"));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesNetWork() {
        Assertions.assertSame(DebugList.NETWORK, Debug.getDebugTypes().get("NETWORK"));
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDebugTypesSettings() {
        Assertions.assertSame(DebugList.SETTINGS, Debug.getDebugTypes().get("SETTINGS"));
    }
}
