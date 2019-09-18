package com.tourney;


import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordinates the set of tiers in a tournament.
 *
 * - Assumes that all teams are present at the start of the tournament
 */
public class TournamentCoordinator {

    public final long startTimeMins;

    private long endTime = -1;
    private final TeamsPool mTeamsPool;
    private final List<Tier> mTierList = new ArrayList<>();

    public TournamentCoordinator(final long startTimeMins, final TeamsPool teamsPool) {
        mTeamsPool = teamsPool;
        this.startTimeMins = startTimeMins;

        // Initialize the tournament by creating the first tier
        Tier firstTier = new Tier(mTeamsPool);
        firstTier.addNewTeams(mTeamsPool.removeTeamsForNewTournament());
        mTierList.add(new Tier(mTeamsPool));
    }

    /**
     * Loop over all tiers
     * 1. Set winners from last tier into this tier
     * 2. deliver timestep to tier
     * 3. If we finish looping through all tiers and there are still winners from the last tier create a new tier
     * 4. If 3 and all tiers except the last tier have no more team conclude the tournament (return true)
     *
     * @return the winning team if the tournament is finished null otherwise
     */
    @Nullable
    public Team onTimeStep(final long currentTimeMins) {
        List<Team> winningTeamsFromLastTier = new ArrayList<>();
        for (Tier tier : mTierList) {
            if (!winningTeamsFromLastTier.isEmpty()) {
                tier.addNewTeams(winningTeamsFromLastTier);
            }
            winningTeamsFromLastTier = tier.onTimeStep(currentTimeMins);
        }
        if (!winningTeamsFromLastTier.isEmpty()) {
            Tier newTier = new Tier(mTeamsPool);
            newTier.addNewTeams(winningTeamsFromLastTier);
            mTierList.add(newTier);
            @Nullable Team firstPlaceTeam = isTournamentFinished();
            if (firstPlaceTeam != null) {
                endTime = currentTimeMins;
            }
            return firstPlaceTeam;
        }
        return null;
    }

    public long getEndTime() {
        return endTime;
    }

    /**
     * Tourney is finished only if each tier is finished and the last tier only has one idle team (winning team)
     * @return the winning team if the tournament is finished null otherwise
     */
    @Nullable
    private Team isTournamentFinished() {
        Tier tier = null;
        for (int i = 0 ; i < mTierList.size() ; i++) {
            tier = mTierList.get(i);
            if (i != (mTierList.size()-1) && !tier.isTierFinished()) {
                return null;
            }
        }
        return tier.hasOneIdleTeam();
    }
}
