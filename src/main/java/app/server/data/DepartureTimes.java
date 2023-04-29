package app.server.data;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Class StationTimeTable qui represente les horraires d'une station à partir d'une certaine heure
 */
public class DepartureTimes implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<StationTime> times;

    public DepartureTimes(List<StationTime> times) {
        this.times = new ArrayList<>();
        times.addAll(times);
    }

    public List<StationTime> getTimes() {
        return times;
    }

}
