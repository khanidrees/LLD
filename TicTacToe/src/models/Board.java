package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> cells;

    public Board (int size){
        this.cells = new ArrayList<>(size);
        for (int i=0;i<size;i++){
            List row =  new ArrayList<Cell>(size);
            for(int j=0;j<size;j++){
                row.add(new Cell(i,j));
            }
            this.cells.add(row);
        }

    }
    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }


    public int getSize(){
        return cells.size();
    }
    Cell getCell(int row,int col){
        return this.cells.get(row).get(col);

    }

    public void showBoard(){
        for (int i = 0; i < cells.size(); i++) {
            for (int j = 0; j < cells.size(); j++) {
                Cell cell = getCell(i,j);
                if(cell.getCellStatus()==CellStatus.AVAILABLE){
                    System.out.print(" _ ");
                }else {
                    System.out.print(" "+cell.getPlayer().symbol+" ");
                }

            }
            System.out.println("");
        }
    }
}
