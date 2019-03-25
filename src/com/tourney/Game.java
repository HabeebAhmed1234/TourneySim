package com.tourney;

public class Game {

    public final Team team1;
    public final Team team2;
    public final long startTimeMins;
    public final long endTimeMins;

    public Game(Team team1, Team team2, long currentTimeMins) {
        this.team1 = team1;
        this.team2 = team2;
        this.startTimeMins = currentTimeMins;
        this.endTimeMins = currentTimeMins + Constants.GAME_LENGTH_MINS;
    }

    public static boolean shouldEndGame(Game game, long currentTimeMins) {
        return game.endTimeMins >= currentTimeMins;
    }
}
