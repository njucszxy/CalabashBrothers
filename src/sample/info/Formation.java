package sample.info;

import java.util.List;
import java.util.Random;

public class Formation {
    public Formation() {}
    public static FormationType GetRandomFormationType()
    {
        Random RandomNumber = new Random();
        int ran = Math.abs(RandomNumber.nextInt()) % 7 + 1;
        switch (ran)
        {
            case 1:return FormationType.Fish;
            case 2:return FormationType.Zig;
            case 3:return FormationType.Arrow;
            case 4:return FormationType.Goose;
            case 5:return FormationType.Moon;
            case 6:return FormationType.Square;
            case 7:return FormationType.Wing;
            default:System.out.println("Error with random formation");return FormationType.Snake;
        }
    }
    public static void getSnakeFormation(List<PositionInfo> positionInfos,Camp camp)
    {
        if(camp == Camp.CB) {
            positionInfos.set(0,new PositionInfo(6,5));
            positionInfos.set(1,new PositionInfo(7,5));
            positionInfos.set(2,new PositionInfo(8,5));
            positionInfos.set(3,new PositionInfo(9,5));
            positionInfos.set(4,new PositionInfo(10,5));
            positionInfos.set(5,new PositionInfo(11,5));
            positionInfos.set(6,new PositionInfo(12,5));
            positionInfos.set(7,new PositionInfo(13,5));
        }
        else
        {
            positionInfos.set(8,new PositionInfo(6,14));
            positionInfos.set(9,new PositionInfo(7,14));
            positionInfos.set(10,new PositionInfo(8,14));
            positionInfos.set(11,new PositionInfo(9,14));
            positionInfos.set(12,new PositionInfo(10,14));
            positionInfos.set(13,new PositionInfo(11,14));
            positionInfos.set(14,new PositionInfo(12,14));
            positionInfos.set(15,new PositionInfo(13,14));
        }
    }
    public static void getGooseFormation(List<PositionInfo> positionInfos,Camp camp)
    {
        if(camp == Camp.CB) {
            positionInfos.set(0,new PositionInfo(6,2));
            positionInfos.set(1,new PositionInfo(7,3));
            positionInfos.set(2,new PositionInfo(8,4));
            positionInfos.set(3,new PositionInfo(9,5));
            positionInfos.set(4,new PositionInfo(10,6));
            positionInfos.set(5,new PositionInfo(11,7));
            positionInfos.set(6,new PositionInfo(12,8));
            positionInfos.set(7,new PositionInfo(13,9));
        }
        else
        {
            positionInfos.set(8,new PositionInfo(6,11));
            positionInfos.set(9,new PositionInfo(7,12));
            positionInfos.set(10,new PositionInfo(8,13));
            positionInfos.set(11,new PositionInfo(9,14));
            positionInfos.set(12,new PositionInfo(10,15));
            positionInfos.set(13,new PositionInfo(11,16));
            positionInfos.set(14,new PositionInfo(12,17));
            positionInfos.set(15,new PositionInfo(13,18));
        }
    }
    public static void getWingFormation(List<PositionInfo> positionInfos,Camp camp)
    {
        if(camp == Camp.CB) {
            positionInfos.set(0,new PositionInfo(6,2));
            positionInfos.set(1,new PositionInfo(7,3));
            positionInfos.set(2,new PositionInfo(8,4));
            positionInfos.set(3,new PositionInfo(9,5));
            positionInfos.set(4,new PositionInfo(10,5));
            positionInfos.set(5,new PositionInfo(11,4));
            positionInfos.set(6,new PositionInfo(12,3));
            positionInfos.set(7,new PositionInfo(13,2));
        }
        else
        {
            positionInfos.set(8,new PositionInfo(6,17));
            positionInfos.set(9,new PositionInfo(7,16));
            positionInfos.set(10,new PositionInfo(8,15));
            positionInfos.set(11,new PositionInfo(9,14));
            positionInfos.set(12,new PositionInfo(10,14));
            positionInfos.set(13,new PositionInfo(11,15));
            positionInfos.set(14,new PositionInfo(12,16));
            positionInfos.set(15,new PositionInfo(13,17));
        }
    }
    public static void getZigFormation(List<PositionInfo> positionInfos,Camp camp)
    {
        if(camp == Camp.CB) {
            positionInfos.set(0,new PositionInfo(6,4));
            positionInfos.set(1,new PositionInfo(7,5));
            positionInfos.set(2,new PositionInfo(8,4));
            positionInfos.set(3,new PositionInfo(9,5));
            positionInfos.set(4,new PositionInfo(10,4));
            positionInfos.set(5,new PositionInfo(11,5));
            positionInfos.set(6,new PositionInfo(12,4));
            positionInfos.set(7,new PositionInfo(13,5));
        }
        else
        {
            positionInfos.set(8,new PositionInfo(6,15));
            positionInfos.set(9,new PositionInfo(7,14));
            positionInfos.set(10,new PositionInfo(8,15));
            positionInfos.set(11,new PositionInfo(9,14));
            positionInfos.set(12,new PositionInfo(10,15));
            positionInfos.set(13,new PositionInfo(11,14));
            positionInfos.set(14,new PositionInfo(12,15));
            positionInfos.set(15,new PositionInfo(13,14));
        }
    }
    public static void getFishFormation(List<PositionInfo> positionInfos,Camp camp)
    {
        if(camp == Camp.CB) {
            positionInfos.set(0,new PositionInfo(6,2));
            positionInfos.set(1,new PositionInfo(7,3));
            positionInfos.set(2,new PositionInfo(8,4));
            positionInfos.set(3,new PositionInfo(9,5));
            positionInfos.set(4,new PositionInfo(9,4));
            positionInfos.set(5,new PositionInfo(10,4));
            positionInfos.set(6,new PositionInfo(11,3));
            positionInfos.set(7,new PositionInfo(12,2));
        }
        else
        {
            positionInfos.set(8,new PositionInfo(6,17));
            positionInfos.set(9,new PositionInfo(7,16));
            positionInfos.set(10,new PositionInfo(8,15));
            positionInfos.set(11,new PositionInfo(9,14));
            positionInfos.set(12,new PositionInfo(9,15));
            positionInfos.set(13,new PositionInfo(10,15));
            positionInfos.set(14,new PositionInfo(11,16));
            positionInfos.set(15,new PositionInfo(12,17));
        }
    }
    public static void getSquareFormation(List<PositionInfo> positionInfos,Camp camp)
    {
        if(camp == Camp.CB) {
            positionInfos.set(0,new PositionInfo(9,5));
            positionInfos.set(1,new PositionInfo(8,4));
            positionInfos.set(2,new PositionInfo(10,4));
            positionInfos.set(3,new PositionInfo(7,3));
            positionInfos.set(4,new PositionInfo(11,3));
            positionInfos.set(5,new PositionInfo(8,2));
            positionInfos.set(6,new PositionInfo(10,2));
            positionInfos.set(7,new PositionInfo(9,1));
        }
        else
        {
            positionInfos.set(8,new PositionInfo(9,14));
            positionInfos.set(9,new PositionInfo(8,15));
            positionInfos.set(10,new PositionInfo(10,15));
            positionInfos.set(11,new PositionInfo(7,16));
            positionInfos.set(12,new PositionInfo(11,16));
            positionInfos.set(13,new PositionInfo(8,17));
            positionInfos.set(14,new PositionInfo(10,17));
            positionInfos.set(15,new PositionInfo(9,18));
        }
    }
    public static void getMoonFormation(List<PositionInfo> positionInfos,Camp camp)
    {
        if(camp == Camp.CB) {
            positionInfos.set(0,new PositionInfo(9,6));
            positionInfos.set(1,new PositionInfo(7,3));
            positionInfos.set(2,new PositionInfo(8,4));
            positionInfos.set(3,new PositionInfo(9,5));
            positionInfos.set(4,new PositionInfo(10,5));
            positionInfos.set(5,new PositionInfo(11,4));
            positionInfos.set(6,new PositionInfo(12,3));
            positionInfos.set(7,new PositionInfo(10,6));
        }
        else
        {
            positionInfos.set(8,new PositionInfo(9,13));
            positionInfos.set(9,new PositionInfo(7,16));
            positionInfos.set(10,new PositionInfo(8,15));
            positionInfos.set(11,new PositionInfo(9,14));
            positionInfos.set(12,new PositionInfo(10,14));
            positionInfos.set(13,new PositionInfo(11,15));
            positionInfos.set(14,new PositionInfo(12,16));
            positionInfos.set(15,new PositionInfo(10,13));
        }
    }
    public static void getArrowFormation(List<PositionInfo> positionInfos,Camp camp)
    {
        if(camp == Camp.CB) {
            positionInfos.set(0,new PositionInfo(9,5));
            positionInfos.set(1,new PositionInfo(9,4));
            positionInfos.set(2,new PositionInfo(9,3));
            positionInfos.set(3,new PositionInfo(9,2));
            positionInfos.set(4,new PositionInfo(8,4));
            positionInfos.set(5,new PositionInfo(10,4));
            positionInfos.set(6,new PositionInfo(7,3));
            positionInfos.set(7,new PositionInfo(11,3));
        }
        else
        {
            positionInfos.set(8,new PositionInfo(9,14));
            positionInfos.set(9,new PositionInfo(9,15));
            positionInfos.set(10,new PositionInfo(9,16));
            positionInfos.set(11,new PositionInfo(9,17));
            positionInfos.set(12,new PositionInfo(8,15));
            positionInfos.set(13,new PositionInfo(10,15));
            positionInfos.set(14,new PositionInfo(7,16));
            positionInfos.set(15,new PositionInfo(11,16));
        }
    }
    public static void getRandomFormation(List<PositionInfo> positionInfos,Camp camp)
    {
        final double d = Math.random();
        final int result = (int)(d * 8);
        switch (result)
        {
            case 0:getWingFormation(positionInfos,camp);break;
            case 1:getGooseFormation(positionInfos,camp);break;
            case 2:getZigFormation(positionInfos,camp);break;
            case 3:getSnakeFormation(positionInfos,camp);break;
            case 4:getFishFormation(positionInfos,camp);break;
            case 5:getSquareFormation(positionInfos,camp);break;
            case 6:getMoonFormation(positionInfos,camp);break;
            case 7:getArrowFormation(positionInfos,camp);break;
        }
    }
}
