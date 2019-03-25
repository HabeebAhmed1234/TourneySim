package com.tourney;

import java.util.concurrent.TimeUnit;

/**
 * Created by Habeeb on 2/23/2019.
 */
public class Constants {

    public static final int PLAYERS_PER_TEAM = 5;
    public static final int FEES_PER_PLAYER_DOLLARS = 5;

    // How long a tournament is open for new entries
    public static final long TOURNAMENT_ENTRY_LENGTH_MINUTES = TimeUnit.DAYS.toMinutes(1);

    public static final long GAME_LENGTH_MINS = TimeUnit.HOURS.toMinutes(2);
}
