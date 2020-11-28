package cat.esteve.atc.airports;

import cat.esteve.atc.planes.PlanePath;
import cat.esteve.atc.utils.Vector3f;

public class SpainAirport extends Airport{
    public SpainAirport() {
        super("Spain", null, null, 0);
    }

    public static PlanePath getPath() {
        PlanePath pp = new PlanePath();

        pp.addNode(new Vector3f(218, 128, 0));
        pp.addNode(new Vector3f(125, 124, 0));
        pp.addNode(new Vector3f(85, 92, 0));
        pp.addNode(new Vector3f(76, 43, 0));
        pp.addNode(new Vector3f(76, 6, 0));

        return pp;
    }
}
