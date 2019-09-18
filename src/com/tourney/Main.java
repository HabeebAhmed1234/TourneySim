package com.tourney;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

import static com.tourney.Constants.*;
import static com.tourney.Print.print;

public class Main {

    public static void main(String[] args) {
        TeamsPool teamsPool = TeamsGenerator.generateStartingTeamsPool();
        TournamentCoordinator tournamentCoordinator = new TournamentCoordinator(START_TIME_MINS, teamsPool);

        long minutesElapsed = 0;
        while (minutesElapsed <= TIMEOUT_NUM_MINS) {
            // Run sim step
            @Nullable Team firstPlaceTeam = tournamentCoordinator.onTimeStep(minutesElapsed);
            if (firstPlaceTeam != null) {
                printTourneyEndMessage(minutesElapsed, firstPlaceTeam);
                return;
            }
            minutesElapsed += TIME_INCREMENT_MINS;
        }
        print("Tourney timed out " + minutesElapsed + " mins OR " + TimeUnit.MINUTES.toHours(minutesElapsed) + " hours");
        return;
    }

    private static void printTourneyEndMessage(long minutesElapsed, Team firstPlaceTeam) {
        print("Tourney is over.");
        print("Total elapsed time = " + minutesElapsed + " mins OR " + TimeUnit.MINUTES.toHours(minutesElapsed) + " hours");
        print("Winning team id = " + firstPlaceTeam.id);
    }
}
