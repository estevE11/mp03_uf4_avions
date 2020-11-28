package cat.esteve.atc.utils;

public class Bounds {
    public static boolean ABB(int x0, int y0, int x1, int y1, int w1, int h1) {
        return x0 > x1 && x0 < x1 + w1 && y0 > y1 && y0 < y1 + h1;
    }

    public static boolean AABB() {
        return true;
    }
}
