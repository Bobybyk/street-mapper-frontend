package app.map;

import java.io.Serial;
import java.io.Serializable;

/**
 * Classe représentant un temps
 */
public record Time(int hour, int minute, int second) implements Comparable<Time>, Serializable {

    @Serial
    private static final long serialVersionUID = 4L;

    private static final int HOUR_IN_A_DAY = 3600 * 24;

    /**
     * Créer un nouveau temps
     *
     * @param hour le nombre des heures entre 0 et 23
     * @param minute le nombre des minutes entre 0 et 59
     * @param second le nombre des secondes entre 0 et 59
     * @throws IllegalArgumentException si les valeurs sont incorrectes
     */
    public Time {
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59)
            throw new IllegalArgumentException();
    }

    public Time(int duration) {
        this((duration / 3600) % 24, (duration / 60) % 60, duration % 60);
    }

    public Time(int hour, int minute) {
        this(hour, minute, 0);
    }

    /**
     * Ajoute des secondes au temps et renvoie le nouveau temps.
     *
     * @param second le nombre de secondes à ajouter
     * @return un nouveau Time avec les secondes en plus
     */
    public Time addDuration(int second) {
        int s = this.second + second;
        int m = minute + (s / 60);
        int h = hour + (m / 60);
        return new Time(h % 24, m % 60, s % 60);
    }

    @Override
    public String toString() {
        return hour == 0 ? String.format("%02d:%02d", minute, second)
                : String.format("%02d:%02d:%02d", hour, minute, second);
    }


    /**
     * @return le temps à partir de minuit en secondes
     */
    private int toSeconds() {
        return second + minute * 60 + hour * 3600;
    }

    @Override
    public int compareTo(Time time) {
        return toSeconds() - time.toSeconds();
    }

    /**
     * @param time
     * @return le temps en seconde nécessaire pour atteindre time
     */
    public int durationTo(Time time) {
        int t1 = toSeconds();
        int t2 = time.toSeconds();
        int diff = t2 - t1;
        while (diff < 0) {
            diff += HOUR_IN_A_DAY;
        }
        return diff;
    }
}
