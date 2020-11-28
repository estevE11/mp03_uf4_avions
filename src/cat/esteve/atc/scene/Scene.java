package cat.esteve.atc.scene;

import java.awt.Color;
import java.util.LinkedList;

import cat.esteve.atc.airports.Airport;
import cat.esteve.atc.airports.USAirport;
import cat.esteve.atc.gfx.Art;
import cat.esteve.atc.gfx.Renderer;
import cat.esteve.atc.gfx.Sprite;
import cat.esteve.atc.main.Main;
import cat.esteve.atc.planes.Plane;
import cat.esteve.atc.planes.PlanePath;
import cat.esteve.atc.utils.Vector3f;

public class Scene {
	private LinkedList<Plane> planes = new LinkedList<Plane>();

	private boolean paused = false;

	public Scene() {

	}

	public void init() {
		Plane p = new Plane(new Vector3f(700, 100, 0), "H2345", "AirBnb", "Adri", 100, 100);
		p.setPath(USAirport.getPath());
		this.addPlane(p);
	}

	public void tick() {
		if(!this.paused) {
			for (Plane p : this.planes) {
				p.tick();
				if(p.isDead()) this.planes.remove(p);
			}
		}
	}
	
	public void render(Renderer renderer) {
        renderer.render(Art.i.background, 0, 0, Main.WIDTH, Main.HEIGHT);
		for (int i = 0; i  < this.planes.size(); i++) {
			Plane p = this.planes.get(i);
			p.render(renderer);
		}
	}

	public void addPlane(Plane p) {
		this.planes.add(p);
	}

	public void addPlane(Plane p, Airport ap) {
		p.setPos(ap.getLaunchPos());

	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
