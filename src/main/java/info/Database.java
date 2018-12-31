package info;

import battlefield.BattleField;
import lives.Creature;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public List<Creature> creatures;
    public BattleField battleField;
    public long timeLine;
    public boolean gameOn;
    public List<MoveInfo> moveInfos;
    public List<Bullet> bullets;

    public Database()
    {
        creatures = null;
        battleField = null;
        timeLine = 0;
        gameOn = false;
        moveInfos = null;
        bullets = new ArrayList<>();
    }
}
