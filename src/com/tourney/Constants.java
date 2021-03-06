package com.tourney;

import java.util.concurrent.TimeUnit;

/**
 * Created by Habeeb on 2/23/2019.
 */
public class Constants {

    public static final long START_TIME_MINS = 0;

    private static final long TIMEOUT_NUM_DAYS = 60;
    public static final long TIMEOUT_NUM_MINS = TimeUnit.DAYS.toMinutes(TIMEOUT_NUM_DAYS);
    public static final long TIME_INCREMENT_MINS = 30;

    public static final int PLAYERS_PER_TEAM = 5;
    public static final int FEES_PER_PLAYER_DOLLARS = 5;
    public static final int NUM_TEAMS_PER_TOURNAMENT = 100;
    public static final long GAME_LENGTH_MINS = TimeUnit.HOURS.toMinutes(2);

    // Team generation constants
    //public static final long TEAM_INTERVAL_AVERAGE_START_TIME_IN_DAY_MINS =
}
