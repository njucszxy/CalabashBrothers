package info;


public class Bullet {
    public int damage;
    public double currentRowPosition;
    public double currentColumnPosition;
    public double xSpeed;
    public double ySpeed;
    public Camp camp;
    Bullet(int damage,int startRowPosition,int startColumnPosition,double deltaX,double deltaY,Camp camp)
    {
        this.damage = damage;
        currentRowPosition = startRowPosition;
        currentColumnPosition = startColumnPosition;
        xSpeed = deltaX;
        ySpeed = deltaY;
        this.camp = camp;
    }
}
