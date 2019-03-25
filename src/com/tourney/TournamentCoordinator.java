package com.tourney;

import java.util.PriorityQueue;

/**
 * on each timestep this class will
 * 1. find idle teams from the {@link TeamsPool} to play in this tournament if the tournament is still open for entry
 * 2. find pairs of teams to play against one another in each tier
 * 3. play the teams against one another
 * 4. advance the winning teams to the next tier
 * 5. kick the losing teams out and return them to the idle teams pool
 *
 */
public class TournamentCoordinator {

    public final long startTimeMins;

    private long endTime;
    private final TeamsPool mTeamsPool;
    private final PriorityQueue<Team> mTierList = new PriorityQueue();

    public TournamentCoordinator(final long startTimeMins, final TeamsPool teamsPool) {
        mTeamsPool = teamsPool;
        this.startTimeMins = startTimeMins;
    }


    public void onTimeStep(final long currentTimeMins) {

    }

    private void maybeEndTournament() {

    }
}
