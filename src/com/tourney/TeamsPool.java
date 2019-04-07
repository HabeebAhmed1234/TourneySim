package com.tourney;

import java.util.ArrayList;
import java.util.List;

import static com.tourney.Constants.NUM_TEAMS_PER_TOURNAMENT;

/**
 * Maintains a set of idle teams that are not part of any tournament
 */
public class TeamsPool {

    private List<Team> idleTeams = new ArrayList<>();

    public void addTeam(Team team) {
        idleTeams.add(team);
    }

    public List<Team> removeTeamsForNewTournament() {
        List<Team> teams = idleTeams.subList(0, NUM_TEAMS_PER_TOURNAMENT);
        idleTeams = idleTeams.subList(NUM_TEAMS_PER_TOURNAMENT, idleTeams.size());
        return teams;
    }
}
