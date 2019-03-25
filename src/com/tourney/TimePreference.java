package com.tourney;

/**
 * Represents one continuous block of availability
 */
public class TimePreference {

    public final long startTimeMins;
    public final long endTimeMins;

    public TimePreference(final long startTimeMins, final long endTimeMins) {
        this.startTimeMins = startTimeMins;
        this.endTimeMins = endTimeMins;
    }

    public long length() {
        return endTimeMins - startTimeMins;
    }

    /**
     * returns true if this time preferences contains (inclusively) the given time preference
     * @param other
     * @return
     */
    public boolean contains(TimePreference other) {
        return other.startTimeMins >= startTimeMins && other.endTimeMins <= endTimeMins;
    }
}
