package com.tourney;

/**
 * Created by Habeeb on 2/23/2019.
 */
public class Team {

    public final long id;
    public final long numPlayers;
    public final TimePreferences timePreferences;

    public Team(final long id, final long numPlayers, final TimePreferences timePreferences) {
        this.id = id;
        this.numPlayers = numPlayers;
        this.timePreferences = timePreferences;
    }
}
