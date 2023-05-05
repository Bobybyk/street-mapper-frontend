package app.console;

import console.Debug;
import console.DebugList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class DebugTest {

    private static final int TIMEOUT_SECONDS = 2;

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesSize(){
        Assertions.assertEquals(Debug.getDebugTypes().size(), 6);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesStatusSize(){
        Assertions.assertEquals(Debug.getDebugTypeStatus().size(), 6);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesStatusError(){
        Assertions.assertEquals(Debug.getDebugTypeStatus().get(DebugList.ERROR), true);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesStatusInfo(){
        Assertions.assertEquals(Debug.getDebugTypeStatus().get(DebugList.INFO), true);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesStatusWarning(){
        Assertions.assertEquals(Debug.getDebugTypeStatus().get(DebugList.WARNING), true);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesStatusGeneral(){
        Assertions.assertEquals(Debug.getDebugTypeStatus().get(DebugList.GENERAL), true);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesStatusNetWork(){
        Assertions.assertEquals(Debug.getDebugTypeStatus().get(DebugList.NETWORK), true);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesStatusSettings(){
        Assertions.assertEquals(Debug.getDebugTypeStatus().get(DebugList.SETTINGS), true);
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesError(){
        Assertions.assertSame(Debug.getDebugTypes().get("ERROR"), DebugList.ERROR);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesInfo(){
        Assertions.assertSame(Debug.getDebugTypes().get("INFO"),DebugList.INFO);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesWarning(){
        Assertions.assertSame(Debug.getDebugTypes().get("WARNING"), DebugList.WARNING);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesGeneral(){
        Assertions.assertSame(Debug.getDebugTypes().get("GENERAL"), DebugList.GENERAL);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesNetWork(){
        Assertions.assertSame(Debug.getDebugTypes().get("NETWORK"), DebugList.NETWORK);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDebugTypesSettings(){
        Assertions.assertSame(Debug.getDebugTypes().get("SETTINGS"), DebugList.SETTINGS);
    }


}
