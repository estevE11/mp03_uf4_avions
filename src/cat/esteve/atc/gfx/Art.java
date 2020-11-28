package cat.esteve.atc.gfx;

public class Art {
    public static Art i = new Art();

    public Sprite background, plane;

    public void load() {
        background = new Sprite("scene.png");
        plane = new Sprite("plane.png");
    }

}
