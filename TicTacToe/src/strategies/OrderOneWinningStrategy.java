package strategies;

import models.Cell;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOneWinningStrategy implements WinningStrategy{
    List<Map<Character, Integer>> rows ;
    List<Map<Character, Integer>> cols ;

    HashMap<Character, Integer> diagonal;
    HashMap<Character, Integer> antidiagonal;

    public OrderOneWinningStrategy(int n){
        rows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rows.add(new HashMap<>());
        }
        cols =new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cols.add(new HashMap<>());
        }
        diagonal = new HashMap<>();
        antidiagonal = new HashMap<>();
    }
    @Override
    public boolean isWon(Move move) {
        Cell cell = move.getCell();
        char symbol = move.getPlayer().getSymbol();
        int row = cell.getRow();
        int col = cell.getCol();
        int n = getRows().size();

        //Add to hashmap
        Map<Character, Integer> map = rows.get(row);
        map.put(symbol, map.getOrDefault(symbol, 0) + 1);

        map = cols.get(col);
        map.put(symbol, map.getOrDefault(symbol, 0) + 1);

        boolean isDaiagonal = row == col;
        boolean isAntiDiagonal = row +col == n-1;

        if(isDaiagonal){
            diagonal.put(symbol, diagonal.getOrDefault(symbol,0)+1);
        }
        if(isAntiDiagonal){
            antidiagonal.put(symbol, antidiagonal.getOrDefault(symbol,0)+1);
        }

        //check if someone wins
        boolean winOnRow = rows.get(row).get(symbol) == n;
        boolean winOnCol = cols.get(col).get(symbol) == n;

        boolean winDiagonal = isDaiagonal && diagonal.get(symbol) == n;
        boolean winAtidiagonal = isAntiDiagonal && antidiagonal.get(symbol) == n;

        return winOnRow || winOnCol || winDiagonal || winAtidiagonal;
    }

    public List<Map<Character, Integer>> getRows() {
        return rows;
    }
}
