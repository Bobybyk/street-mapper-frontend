package app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class ServerTest {
    private static final String HOST = "localhost";
    private static final int PORT = 12345;
    private static final int SLEEP_TIME = 1_000;
    private static final long TIMEOUT = 10;

    @Test
    @Timeout(value = TIMEOUT, unit = TimeUnit.SECONDS)
    public void testLaunchKill() {
        
        Server s = new Server(HOST, PORT);
        s.start();

        try {
            Server.sleep(SLEEP_TIME);
            assertTrue(s.isRunning());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        s.kill();

        try {
            Server.sleep(SLEEP_TIME);
            assertFalse(s.isAlive());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
