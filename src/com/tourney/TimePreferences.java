package com.tourney;

import java.util.List;

/**
 * Represents a set of time preferences
 */
public class TimePreferences {

    public final List<TimePreference> timePreferences;

    public TimePreferences(List<TimePreference> timePreferences) {
        this.timePreferences = timePreferences;
    }

    /**
     * Returns true if the given timepreferences intersect with this timepreferences for the given current time and game length
     */
    public boolean intersects(long gameLengthMins, long currentTimeMillis, TimePreferences otherTimePreferences) {
        TimePreference targetTimePreference = new TimePreference(currentTimeMillis, currentTimeMillis + gameLengthMins);
        // Now check if both sets of time preferences superset the targetTimePreference
        boolean doesContain = false;
        for (TimePreference preference : timePreferences) {
            if (preference.contains(targetTimePreference)) {
                doesContain = true;
            }
        }
        if (!doesContain) {
            return false;
        }
        for (TimePreference preference : otherTimePreferences.timePreferences) {
            if (preference.contains(targetTimePreference)) {
                return true;
            }
        }
        return false;
    }
}
