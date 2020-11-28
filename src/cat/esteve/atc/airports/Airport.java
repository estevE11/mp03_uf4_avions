package cat.esteve.atc.airports;

import cat.esteve.atc.planes.PlanePath;
import cat.esteve.atc.utils.Vector3f;

import java.nio.file.Path;

public class Airport {
    protected String name;
    protected Vector3f pos;
    protected Vector3f launchPos;
    protected float launchDir;

    protected Path path;

    public Airport(String name, Vector3f pos, Vector3f launchPos, float launchDir) {
        this.name = name;
        this.pos = pos;
        this.launchPos = launchPos;
        this.launchDir = launchDir;
    }

    public String getName() {
        return this.name;
    }

    public Vector3f getLaunchPos() {
        return launchPos;
    }

    public float getLaunchDir() {
        return launchDir;
    }

    public static PlanePath getPath() { // DEFAULT PATH
        PlanePath pp = new PlanePath();

        pp.addNode(new Vector3f(188, 69, 0));
        pp.addNode(new Vector3f(103, 63, 0));
        pp.addNode(new Vector3f(67, 148, 0));
        pp.addNode(new Vector3f(71, 337, 0));
        pp.addNode(new Vector3f(70, 459, 0));
        pp.addNode(new Vector3f(95, 583, 0));
        pp.addNode(new Vector3f(235, 607, 0));
        pp.addNode(new Vector3f(455, 612, 0));
        pp.addNode(new Vector3f(670, 601, 0));
        pp.addNode(new Vector3f(860, 596, 0));
        pp.addNode(new Vector3f(1015, 565, 0));

        return pp;
    }
}
