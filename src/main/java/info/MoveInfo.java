package info;

public class MoveInfo {
    public PositionInfo oldPosition;
    public PositionInfo newPosition;
    public MoveInfo(int oldRowPosition,int oldColumnPosition,int newRowPosition,int newColumnPosition)
    {
        oldPosition = new PositionInfo(oldRowPosition,oldColumnPosition);
        newPosition = new PositionInfo(newRowPosition,newColumnPosition);
    }
}
