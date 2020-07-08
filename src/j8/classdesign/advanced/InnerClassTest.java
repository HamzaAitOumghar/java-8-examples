package j8.classdesign.advanced;

public class InnerClassTest {

    public static void main(String[] args) {

        Shape.Color color = new Shape.Color(12,12,12);
        System.out.println(color);

        Circle circle = new Circle(12,2,3);
        Circle.Point pt = circle.new Point();

        System.out.println(circle);
        System.out.println(pt);


    }
}
