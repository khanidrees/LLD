package controller;

import exceptions.InvalidConstructorException;
import models.Game;
import models.GameStatus;
import models.Player;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    public Game createGame(List<Player> players) throws InvalidConstructorException {
        return Game.getBuilder()
                .setPlayers(players)
                .build();
    }

    public void makeMove(Game game){

        game.makeMove();

    }


    public boolean isGameOver(Game game) {
        boolean t = game.getGameStatus() != GameStatus.IN_PROGRESS;
        System.out.println(t);
        System.out.println(game.getGameStatus());
        return game.getGameStatus() != GameStatus.IN_PROGRESS;
    }
}
