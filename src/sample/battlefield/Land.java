package sample.battlefield;

public class Land {
    public int row = -1;
    public int column = -1;
    public boolean isUsed = false;
    public int creatureIndex;

    public Land(){}
    public Land(int row,int column)
    {
        this.row = row;
        this.column = column;
    }

}