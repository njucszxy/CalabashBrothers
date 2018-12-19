package battlefield;

import java.util.ArrayList;
import java.util.List;

public class BattleField {
    public List<Land> lands = new ArrayList<Land>();

    public BattleField()
    {
        for(int i = 0;i < 20;i++)
        {
            for(int j = 0;j < 20;j++)
            {
                lands.add(new Land(i,j));
            }
        }
    }
}
