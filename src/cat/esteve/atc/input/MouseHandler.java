package cat.esteve.atc.input;

import cat.esteve.atc.main.Main;
import cat.esteve.atc.panes.CreatePlanePane;
import cat.esteve.atc.scene.Scene;
import cat.esteve.atc.utils.Bounds;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener {
    private Scene scene;

    public MouseHandler(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        int mx = mouseEvent.getX();
        int my = mouseEvent.getY();
        System.out.println("pp.addNode(new Vector3f(" + mx + ", " + my + ", 0));");
        if(mouseEvent.getButton() == 0) {
            if(Bounds.ABB(mx, my, 930, 20, 50, 50)) {
                new CreatePlanePane(this.scene);
            }
        }
        new CreatePlanePane(this.scene);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Main.mx = mouseEvent.getX();
        Main.my = mouseEvent.getY();
    }
}
