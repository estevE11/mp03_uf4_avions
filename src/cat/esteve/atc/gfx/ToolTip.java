package cat.esteve.atc.gfx;

import cat.esteve.atc.planes.Plane;
import cat.esteve.atc.utils.Vector3f;

import java.awt.*;

public class ToolTip {
    private Vector3f pos;
    private String content = "";
    private boolean show = false;

    private Plane parent;

    private int border = 2;
    private int fontSize = 14;

    public ToolTip(Plane parent) {
        this.parent = parent;
        this.pos = new Vector3f();
    }

    public void tick() {
        this.followParent();
    }

    public void render(Renderer renderer) {
        if(!this.show) return;
        int paddingX = 5;
        int paddingY = 5;

        String[] lines = this.content.split("\n");
        int topLen = 0;
        int lineH = renderer.calcTextHeight(lines[0]);
        for(String l : lines) {
            topLen = Math.max(renderer.calcTextWidth(l, this.fontSize), topLen);
        }

        int w = paddingX*2 + topLen;
        int h = paddingY*2 + lineH*lines.length;

        renderer.rect(this.pos.x - border, this.pos.y - border, border*2 + w, border*2 + h, Color.white);
        renderer.rect(this.pos.x, this.pos.y, w, h, Color.black);

        for(int i = 0; i < lines.length; i++) {
            renderer.renderText(lines[i], paddingX + this.pos.x, paddingY + this.pos.y + i * renderer.calcTextHeight(lines[i]), this.fontSize, Color.white);
        }

    }

    public void followParent() {
        this.pos.x = this.parent.getPos().x + Plane.WIDTH + 2;
        this.pos.y = this.parent.getPos().y + 16;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void toggle() {
        this.show = !this.show;
    }

    public void show(boolean s) {
        this.show = s;
    }

}
