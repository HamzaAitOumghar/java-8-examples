package j8.classdesign.advanced;

public class Circle {

    class Point{
        private int xPos;
        private int yPos;

        public Point(int xPos, int yPos) {
            this.xPos = xPos;
            this.yPos = yPos;
        }

        public Point() {
            this(0,0);
        }

        @Override
        public String toString() {
            return "Point(" +
                    "" + xPos +
                    "," + yPos +
                    ')';
        }

    }

    private Point center ;
    private int radius;

    public Circle() {
        center=this.new Point();
        radius=0;
    }
    public Circle(int x, int y, int r) {
        center = this.new Point(x, y);
        radius = r;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
