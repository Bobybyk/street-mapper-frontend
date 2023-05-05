package app.data;

import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import data.DataList;
import server.data.DepartureTimes;
import server.data.ErrorServer;
import server.data.Route;
import server.data.SuggestionStations;

/**
 * DataListTest class des test sur DataList
 */
class DataListTest {

    private static final int TIMEOUT_SECONDS = 2000;

    @BeforeEach
    void initEveryone() {
        DataList.setData(null);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDataRoute() {
        Route route = new Route(null);
        DataList.setData(route);
        assertSame(DataList.getData(), route);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDataError() {
        ErrorServer error = new ErrorServer("");
        DataList.setData(error);
        assertSame(DataList.getData(), error);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDataDepartureTimes() {
        DepartureTimes departureTimes = new DepartureTimes(null);
        DataList.setData(departureTimes);
        assertSame(DataList.getData(), departureTimes);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    void testDataSuggestionStations() {
        SuggestionStations suggestionStations = new SuggestionStations(null, null);
        DataList.setData(suggestionStations);
        assertSame(DataList.getData(), suggestionStations);
    }
}
