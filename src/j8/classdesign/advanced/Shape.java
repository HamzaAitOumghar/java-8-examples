package j8.classdesign.advanced;

abstract public class Shape {

    public static class Color{
        int m_red, m_green,m_blue;
        public Color() {
            this(0,0,0);
        }
        public Color(int m_red, int m_green, int m_blue) {
            this.m_red = m_red;
            this.m_green = m_green;
            this.m_blue = m_blue;
        }

        @Override
        public String toString() {
            return "Color(" +
                    "red=" + m_red +
                    ",green=" + m_green +
                    ",blue=" + m_blue +
                    ')';
        }
    }

}
