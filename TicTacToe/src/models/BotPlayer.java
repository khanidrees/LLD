package models;

import strategies.BotPlayingStrategy;

import java.util.ArrayList;
import java.util.List;

public class BotPlayer extends Player implements BotPlayingStrategy {

    BotLevel botLevel;

    public BotPlayer(String name, char symbol,int level) {
        super(name, symbol);
        switch (level){
            case 1:
                setBotLevel(BotLevel.EASY);
                break;
            case 2:
                setBotLevel(BotLevel.MEDIUM);
                break;
            case 3:
                setBotLevel(BotLevel.HARD);
                break;
            default:
                setBotLevel(BotLevel.EASY);
        }

    }

    public BotLevel getBotLevel() {
        return botLevel;
    }

    public void setBotLevel(BotLevel botLevel) {
        this.botLevel = botLevel;
    }

    @Override
    public List<Integer> getNextMove(Board board){
        List<Integer> pair = new ArrayList<>();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {

                if(board.getCell(i,j).getCellStatus()==CellStatus.AVAILABLE){
                    pair.add(i);
                    pair.add(j);
                    return pair;
                }
            }
        }
        return pair;
    }


}
