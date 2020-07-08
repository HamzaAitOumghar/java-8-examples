package j8.classdesign.advanced;

public class InnerClassTest {

    public static void main(String[] args) {

        Shape.Color color = new Shape.Color(12, 12, 12);
        System.out.println(color);

        Circle circle = new Circle(12, 2, 3);
        Circle.Point pt = circle.new Point();

        System.out.println(circle);
        System.out.println(pt);

        //Local Inner Class :
        Shape.Color localInnerClass = getDescriptiveColor(color);
        System.out.println(localInnerClass);

        //Anonymous Inner Class
        Shape.Color anonInnerClass = getAnonDescriptiveColor(color);
        System.out.println(anonInnerClass);

    }

    public static Shape.Color getDescriptiveColor(final Shape.Color color) {
        class DescriptiveColor extends Shape.Color {
            @Override
            public String toString() {
                return "You selected a color with RGB values " + color;
            }
        }
        return new DescriptiveColor();
    }

    //Anonymous inner class
    public static Shape.Color getAnonDescriptiveColor(final Shape.Color color) {
        return new Shape.Color () {
            @Override
            public String toString() {
                return "You selected a color with RGB values " + color;
            }
        };
    }
}
