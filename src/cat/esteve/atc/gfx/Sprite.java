package cat.esteve.atc.gfx;

import cat.esteve.atc.main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {

    private BufferedImage image;
    private int w, h;

    public Sprite(String path) {
        try {
            String p = "/img/" + path;
            System.out.println("> Attempting to load " + p);
            image = ImageIO.read(Main.class.getResourceAsStream(p));
            System.out.println("> " + p + " loaded!");
            this.w = image.getWidth();
            this.h = image.getHeight();
        } catch (IOException e) {
            System.out.println("> " + e.getMessage());
        }

    }

    public void draw(double x, double y, Graphics g) {
        g.drawImage(this.image, (int)x, (int)y, this.w, this.h, null);
    }

    public void draw(float x, float y, int w, int h, Renderer renderer) {
        renderer.render(this, x, y, w, h);
    }

    public int getWidth() {
        return this.w;
    }

    public int getHeight() {
        return this.h;
    }

    public BufferedImage getOriginalImage() {
        return image;
    }
}