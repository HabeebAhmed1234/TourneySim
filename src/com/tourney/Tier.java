package com.tourney;

import java.util.*;

public class Tier {

    private final TeamsPool mTeamsPool;

    private List<Team> mIdleTeamsInTier = new ArrayList<>();
    private List<Game> mPendingGames = new ArrayList<>();

    public Tier(TeamsPool teamsPool) {
        mTeamsPool = teamsPool;
    }

    /**
     * 1. Concludes any pending games
     * 2. Starts new games
     * 3. Returns the list of winning teams
     * @param currentTimeMins
     * @return
     */
    public List<Team> onTimeStep(long currentTimeMins) {
        List<Team> winningTeams = endPendingGames(currentTimeMins);
        startNewGames(currentTimeMins);
        return winningTeams;
    }

    /**
     * Iterates over the list of pending games in this tier.
     * 1. returns losing teams to teams pool
     * 2. returns winning teams
     */
    private List<Team> endPendingGames(long currentTimeMins) {
        List<Team> winningTeams = new ArrayList();

        Iterator<Game> gamesIterator = mPendingGames.iterator();
        while (gamesIterator.hasNext()) {
            Game game = gamesIterator.next();
            if (Game.shouldEndGame(game, currentTimeMins)) {
                winningTeams.add(endGame(game));
                gamesIterator.remove();
            }
        }
        return winningTeams;
    }

    /**
     * Ends the game and returns the winning team and returns the losing team back to the team pool
     */
    private Team endGame(Game game) {
        boolean isFirstTeamWinner = ((int)(Math.random() * 2)) == 1;
        final Team winningTeam = isFirstTeamWinner ? game.team1 : game.team2;
        final Team losingTeam = isFirstTeamWinner ? game.team2 : game.team1;
        mTeamsPool.idleTeams.add(losingTeam);
        return winningTeam;
    }

    /**
     * pairs up idle teams in the idle teams list that have a time preference compatibility
     * (the teams can both play for time = [currentTimeMins, currentTimeMins + GAME_LENGTH_MINS])
     */
    private void startNewGames(long currentTimeMins) {
        Set<Team> teamsToPlay = new HashSet<>();

        // Get all the teams that are compatible to play right now
        for (int i = 0 ; i < mPendingGames.size() ; i++) {
            Team team1 = mIdleTeamsInTier.get(i);
            for (int j = (i+1) ; j < mPendingGames.size() ; j++) {
                Team team2 = mIdleTeamsInTier.get(j);
                if (!teamsToPlay.contains(team1) && !teamsToPlay.contains(team2) && team1.timePreferences.intersects(Constants.GAME_LENGTH_MINS, currentTimeMins, team2.timePreferences)) {
                    teamsToPlay.add(team1);
                    teamsToPlay.add(team2);
                }
            }
        }
        if (teamsToPlay.size() % 2 != 0) {
            throw new RuntimeException("teams to play must be a multiple of 2!");
        }
        // Start the games of all compatible teams
        Iterator<Team> teamsToPlayIterator = teamsToPlay.iterator();
        while(teamsToPlayIterator.hasNext()) {
            mPendingGames.add(new Game(teamsToPlayIterator.next(), teamsToPlayIterator.next(), currentTimeMins));
        }

        // Remove playing teams from idle list
        Iterator<Team> idleTeamsIterator = mIdleTeamsInTier.iterator();
        while (idleTeamsIterator.hasNext()) {
            Team idleTeam = idleTeamsIterator.next();
            if (teamsToPlay.contains(idleTeam)) {
                idleTeamsIterator.remove();
            }
        }
    }

    /**
     * use this method to promote teams to this tier
     */
    public void addNewTeams(List<Team> teams) {
        mIdleTeamsInTier.addAll(teams);
    }
}
