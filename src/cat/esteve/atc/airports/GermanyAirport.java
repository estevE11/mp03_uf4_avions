package cat.esteve.atc.airports;

import cat.esteve.atc.planes.PlanePath;
import cat.esteve.atc.utils.Vector3f;

public class GermanyAirport extends Airport{
    public GermanyAirport() {
        super("Germany", null, null, 0);
    }

    public static PlanePath getPath() {
        PlanePath pp = new PlanePath();

        pp.addNode(new Vector3f(207, 108, 0));
        pp.addNode(new Vector3f(155, 123, 0));
        pp.addNode(new Vector3f(137, 149, 0));
        pp.addNode(new Vector3f(132, 186, 0));
        pp.addNode(new Vector3f(127, 256, 0));
        pp.addNode(new Vector3f(127, 317, 0));
        pp.addNode(new Vector3f(127, 412, 0));
        pp.addNode(new Vector3f(139, 495, 0));
        pp.addNode(new Vector3f(174, 556, 0));
        pp.addNode(new Vector3f(256, 593, 0));
        pp.addNode(new Vector3f(360, 578, 0));
        pp.addNode(new Vector3f(380, 511, 0));
        pp.addNode(new Vector3f(368, 440, 0));
        pp.addNode(new Vector3f(309, 391, 0));
        pp.addNode(new Vector3f(179, 383, 0));
        pp.addNode(new Vector3f(78, 387, 0));
        pp.addNode(new Vector3f(13, 389, 0));

        return pp;
    }
}
