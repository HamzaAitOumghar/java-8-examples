package j8.classdesign;

//Point is a mutable class

public class Point {

    private int xPos,yPos;


    public Point(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public String toString() {
        return  "x = " + xPos + ", y = " + yPos;
    }


    public int getX() {
        return xPos;
    }

    public void setX(int xPos) {
        this.xPos = xPos;
    }

    public int getY() {
        return yPos;
    }

    public void setY(int yPos) {
        this.yPos = yPos;
    }
}
