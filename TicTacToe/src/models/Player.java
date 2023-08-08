package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    public String name;
    public char symbol;

    public Player(String name, char symbol){
        this.name = name;
        this.symbol = symbol;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public List<Integer> getNextMove(Board board){
        List pair = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter row then colum for your move");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        pair.add(row);
        pair.add(col);
        return pair;
    }
}
