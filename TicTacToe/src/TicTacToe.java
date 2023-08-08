import controller.GameController;
import exceptions.InvalidConstructorException;
import models.BotPlayer;
import models.Game;
import models.HumanPlayer;
import models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) throws InvalidConstructorException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Number of Players");
        int num = scanner.nextInt();
        List<Player> players = new ArrayList<>();
        for(int i=0;i<num;i++){
            System.out.println("Enter name of #"+i+1+" player");
            String name = scanner.next();
            System.out.println("Enter symbol of #"+i+1+" Player");
            char symbol = scanner.next().charAt(0);
            players.add(new HumanPlayer(name,symbol));
        }
        if(num == 1){
            System.out.println("Enter name of Bot player");
            String name = scanner.next();
            System.out.println("Enter symbol of Bot Player");
            char symbol = scanner.next().charAt(0);
            System.out.println("Enter Bot Level from 1 to 3(easy to hard)");
            int level = scanner.nextInt();

            players.add(new BotPlayer(name,symbol,level));
        }else{
            System.out.println("Enter Number of Bot");
            int numBot = scanner.nextInt();
            for(int i=0;i<numBot;i++){
                System.out.println("Enter name of #"+i+1+" Bot");
                String name = scanner.next();
                System.out.println("Enter symbol of #"+i+1+" Bot");
                char symbol = scanner.next().charAt(0);
                System.out.println("Enter Bot Level from 1 to 3(easy to hard)");
                int level = scanner.nextInt();
                players.add(new BotPlayer(name,symbol,level));
            }
        }


        GameController gameController = new GameController();

        Game game = gameController.createGame(players);

        while(!gameController.isGameOver(game)){
            gameController.makeMove(game);
        }


    }
}