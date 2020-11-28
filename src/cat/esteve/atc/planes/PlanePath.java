package cat.esteve.atc.planes;

import cat.esteve.atc.utils.Vector3f;

import java.util.Arrays;
import java.util.LinkedList;

public class PlanePath {
    private LinkedList<Vector3f> nodes = new LinkedList<Vector3f>();
    private int currentTarget = 0;

    public void update(Vector3f pos) {
        if(this.currentTarget == -1) return;
        if(pos.dist2D(this.nodes.get(this.currentTarget)) < 10) {
            this.next();
        }
    }

    private void next() {
        this.currentTarget++;
        if(this.currentTarget >= this.nodes.size()) {
            this.currentTarget = -1;
            return;
        }
    }

    public void addNode(Vector3f pos) {
        this.nodes.add(pos);
    }

    public void addNodes(Vector3f[] nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
    }

    public void reset() {
        this.currentTarget = 0;
    }

    public Vector3f getCurrentTarget() {
        return this.currentTarget < this.nodes.size() ? nodes.get(currentTarget) : null;
    }

    public int getIdx() {
        return this.currentTarget;
    }

    public LinkedList<Vector3f> getNodes() {
        return this.nodes;
    }
}

