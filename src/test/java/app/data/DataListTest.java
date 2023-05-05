package app.data;

import data.DataList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import server.data.DepartureTimes;
import server.data.Route;
import server.data.StationTime;
import server.data.SuggestionStations;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DataListTest class des test sur DataList
 */
public class DataListTest {

    private static final int TIMEOUT_SECONDS = 2;

    @BeforeEach
    public void initEveryone(){
        DataList.setData(null);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDataRoute() {
        Route route = new Route(null);
        DataList.setData(route);
        assertSame(DataList.getData(), route);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDataError() {
        Error error = new Error();
        DataList.setData(error);
        assertSame(DataList.getData(), error);
    }

    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDataDepartureTimes() {
        DepartureTimes departureTimes = new DepartureTimes(null);
        DataList.setData(departureTimes);
        assertSame(DataList.getData(), departureTimes);
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDataSuggestionStations() {
        SuggestionStations suggestionStations = new SuggestionStations(null, null);
        DataList.setData(suggestionStations);
        assertSame(DataList.getData(), suggestionStations);
    }


    @Test
    @Timeout(value = TIMEOUT_SECONDS)
    public void testDataStationTimes() {
        StationTime stationTime = new StationTime(null, null, null);
        DataList.setData(stationTime);
        assertSame(DataList.getData(), stationTime);
    }

}

