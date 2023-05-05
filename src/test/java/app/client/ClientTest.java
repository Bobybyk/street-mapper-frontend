package app.client;

import client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class ClientTest {


    private final int TEMPS = 2;
    
    @Test
    @Timeout(TEMPS)
    public void testNotConnected(){
        Client client = new Client(null, 0, null, null, null);
        Assertions.assertFalse(client.isConnected());
    }

}
