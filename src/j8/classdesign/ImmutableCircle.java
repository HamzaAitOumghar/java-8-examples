package j8.classdesign;


// ImmutableCircle is an immutable class – the state of its objects
// cannot be modified once the object is created
public final class ImmutableCircle {
    private final Point center;
    private  final int radius;


    public ImmutableCircle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "center: " + center + " and radius = " + radius;
    }

}
