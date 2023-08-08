package models;

import exceptions.InvalidConstructorException;
import strategies.OrderOneWinningStrategy;
import strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;

    private GameStatus gameStatus;

    int currentPlayersIndex;

    WinningStrategy winningStrategy;







    private Game (GameBuilder game){
        this.board = game.board;
        this.players = game.players;
        this.moves = game.moves;
        this.gameStatus = game.gameStatus;
        this.currentPlayersIndex = game.cuurentPlayersIndex;
        this.winningStrategy = game.winningStrategy;
    }

    public void makeMove(){
        //get i/p from player
        //validate i/p and move
        //make move
        //currplayerIndex ++
        //check for win
        // check for draw

        board.showBoard();
        Player currentPlayer = players.get(currentPlayersIndex);
        List<Integer> pair = currentPlayer.getNextMove(board);
        int row = pair.get(0);
        int col = pair.get(1);

        while (!validateMove(row,col)){
            this.makeMove();
        }
        Cell cell = this.board.getCell(row,col);
        cell.setCellStatus(CellStatus.OCCUPIED);
        cell.setPlayer(currentPlayer);
        Move move = new Move(currentPlayer,cell);
        moves.add(move);
        //take mod so that index does not exceed

        if(checkForWin(move)) {
            gameStatus = GameStatus.ENDED;
            System.out.println(currentPlayer.getName()  +" WON THE GAME");
            return;
        }
        if(checkForDraw()) {
            gameStatus = GameStatus.DRAW;
            return;
        }

        currentPlayersIndex = (currentPlayersIndex+1) % players.size();


    }

    private boolean checkForDraw() {
        return moves.size() == board.getSize() * board.getSize();
    }

    private boolean checkForWin(Move move) {
        return winningStrategy.isWon(move);
    }

    private boolean validateMove(int row, int col) {
        boolean isValid =  row >=0 && col >=0 && row < board.getSize() && col <board.getSize();
        if(!isValid){
            System.out.println("Invalid row colum.");
            return false;
        }

        Cell cell = this.board.getCell(row,col);
        return cell.getCellStatus().equals(CellStatus.AVAILABLE);


    }



//

    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }
    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public int getCurrentPlayersIndex() {
        return currentPlayersIndex;
    }



    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public static class GameBuilder{
        private Board board;
        private List<Player> players;
        private List<Move> moves;

        private GameStatus gameStatus;

        public void setGameStatus(GameStatus gameStatus) {
            this.gameStatus = gameStatus;
        }

        private int cuurentPlayersIndex;
        WinningStrategy winningStrategy;
        public GameBuilder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder setMoves(List<Move> moves) {
            this.moves = moves;
            return this;
        }

        public Game build() throws InvalidConstructorException {
            if(players ==null || players.size() ==0){
                throw  new InvalidConstructorException("Players should not be null or zero");
            }
            this.board = new Board(players.size()+1);
            this.cuurentPlayersIndex = 0;
            this.moves = new ArrayList<>();
            this.gameStatus = GameStatus.IN_PROGRESS;
            this.winningStrategy = new OrderOneWinningStrategy(board.getSize());
            return  new Game(this);
        }
    }

}
