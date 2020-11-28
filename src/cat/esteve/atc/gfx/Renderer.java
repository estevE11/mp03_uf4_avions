package cat.esteve.atc.gfx;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;

public class Renderer extends Canvas {
	private static final long serialVersionUID = 1L;
    public static int FLIP_H = 1;
    public static int FLIP_v = 0;

    private static final String FONT_EDITOR = "Monospaced";
//    public static final Font FONT = new Font(FONT_EDITOR, Font.PLAIN, 14);

    private Graphics g;
    private BufferStrategy bs;

    public Renderer() {
        this.requestFocus();
        this.setFocusTraversalKeysEnabled(false);
    }

    public boolean start() {
        this.bs = this.getBufferStrategy();
        if(this.bs == null) {
            this.createBufferStrategy(3);
            return false;
        }
        this.g = bs.getDrawGraphics();

        return true;
    }

    public void end() {
        g.dispose();
        bs.show();
    }

    public void clear() {
        g.setColor(Color.black);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    
    public void rect(float x, float y, int w, int h, Color c) {
    	this.g.setColor(c);
    	this.g.fillRect((int) x,  (int) y, w, h);
    }

    public void render(Sprite s, float x, float y, int w, int h) {
        this.g.drawImage(s.getOriginalImage(), (int) x, (int) y, w, h, null);
    }

    public void render(Sprite s, float x, float y, int w, int h, float rad) {
        AffineTransform tx = AffineTransform.getRotateInstance(rad, s.getWidth()/2, s.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        this.g.drawImage(op.filter(s.getOriginalImage(), null), (int) x, (int) y, w, h, null);
    }

    public void renderScene(Sprite s, float x, float y, float z, int w, int h, float rad) {
        int width = (int)(w+z/3);
        int height = (int)(h+z/3);
        AffineTransform tx = AffineTransform.getRotateInstance(rad, s.getWidth()/2, s.getHeight()/2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        this.g.drawImage(op.filter(s.getOriginalImage(), null), (int) x, (int) y, width, height, null);
    }

    public void renderText(String s, float x, float y, int size, Color c) {
        g.setFont(new Font("Arial", Font.PLAIN, size));
        g.setColor(c);
        int h = calcTextHeight(s);
        g.drawString(s, (int)x, (int)y+(int)(h/1.4));
    }

    public int calcTextWidth(String msg, int size) {
        g.setFont(new Font("Arial", Font.PLAIN, size));
        return this.g.getFontMetrics().stringWidth(msg);
    }

    public int calcTextHeight(String msg) {
        return (int)g.getFontMetrics().getLineMetrics(msg, g).getHeight();
    }
    
    public Graphics g() {
    	return this.g;
    }
    public Graphics2D g2d() {
        return (Graphics2D) this.g;
    }
}
