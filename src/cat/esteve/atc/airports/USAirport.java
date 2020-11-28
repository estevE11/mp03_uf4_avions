package cat.esteve.atc.airports;

import cat.esteve.atc.planes.PlanePath;
import cat.esteve.atc.utils.Vector3f;

public class USAirport extends Airport{
    public USAirport() {
        super("United States", null, null, 0);
    }

    public static PlanePath getPath() {
        PlanePath pp = new PlanePath();

        pp.addNode(new Vector3f(201, 153, 0));
        pp.addNode(new Vector3f(151, 173, 0));
        pp.addNode(new Vector3f(155, 228, 0));
        pp.addNode(new Vector3f(161, 279, 0));
        pp.addNode(new Vector3f(186, 335, 0));
        pp.addNode(new Vector3f(301, 339, 0));
        pp.addNode(new Vector3f(407, 331, 0));
        pp.addNode(new Vector3f(540, 327, 0));
        pp.addNode(new Vector3f(685, 315, 0));
        pp.addNode(new Vector3f(892, 322, 0));
        pp.addNode(new Vector3f(1040, 367, 0));

        return pp;
    }
}