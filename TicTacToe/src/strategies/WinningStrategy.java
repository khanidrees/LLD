package strategies;

import models.Move;

public interface WinningStrategy {
    public boolean isWon(Move move);
}
