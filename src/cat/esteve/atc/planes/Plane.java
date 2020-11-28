package cat.esteve.atc.planes;

import cat.esteve.atc.airports.Airport;
import cat.esteve.atc.gfx.Art;
import cat.esteve.atc.gfx.Renderer;
import cat.esteve.atc.gfx.ToolTip;
import cat.esteve.atc.main.Main;
import cat.esteve.atc.utils.Cryptography;
import cat.esteve.atc.utils.Vector3f;

import java.awt.*;

public class Plane {
	public static final int WIDTH = 48;

	protected Vector3f pos;
	protected Vector3f vel;
	protected float acc;
	protected float dir = 0;
	private PlanePath path;

	protected Airport origin;
	
	protected String plate;
	protected String brand;
	protected String model;
	protected boolean engineOn = false;
	protected int maxPassangers;
	protected int currPassangers;
	protected int kerosene;

	protected int throttle = 0;
	protected boolean launch = true;

	protected boolean dead = false;

	private ToolTip tooltip;

	public Plane(String plate, String brand, String model) {
		this.plate = Cryptography.method01_code(plate, 123);
		this.brand = brand;
		this.model = model;
		this.pos = new Vector3f();
		this.vel = new Vector3f();
		this.acc = 0;
		this.kerosene = 0;
		this.maxPassangers = 0;
		this.tooltip = new ToolTip(this);
	}
	
	public Plane(Vector3f pos, String plane, String brand, String model, int maxPassangers, int kerosene) {
		this(plane, brand, model);
		this.maxPassangers = maxPassangers;
		this.kerosene = kerosene;
		this.pos = pos;
	}
	
	public void tick() {
		this.physics();
		this.pos.add(this.vel);
		this.path.update(this.pos);
		this.followTarget();
		if(Main.mx > this.pos.x && Main.mx < this.pos.x + WIDTH && Main.my > this.pos.y && Main.my < this.pos.y + WIDTH) {
			this.tooltip.followParent();
			this.updateTooltipContent();
			this.tooltip.show(true);
		} else {
			this.tooltip.show(false);
		}
		if(this.pos.x < -150 || this.pos.x > Main.WIDTH || this.pos.y < -150 || this.pos.y > Main.HEIGHT) this.dead = true;
	}
	
	public void render(Renderer renderer) {
        renderer.renderScene(Art.i.plane, this.pos.x, this.pos.y, this.pos.z, WIDTH, WIDTH, this.dir);
	    this.tooltip.render(renderer);
	}

	private void physics() {
		if(this.launch) {
			this.throttle++;
			this.acc += (float)this.throttle/10000;
			if(this.acc > 2) this.pos.z += 2;
			if(this.acc > 3) this.launch = false;
		} else {
			this.throttle = 0;
		}
	}

	private void followTarget() {
	    if(this.path.getIdx() == -1) return;
        Vector3f target = this.path.getCurrentTarget();
        this.setDir(target);
        this.updateDir();
    }

    private void setDir(Vector3f target) {
	    float dx = target.x - this.pos.x;
	    float dy = target.y - this.pos.y;
        double angle = Math.atan2(dy, dx);
        this.vel.x = (float)Math.cos(angle)*acc;
        this.vel.y = (float)Math.sin(angle)*acc;
    }

	private void updateDir() {
	    if(this.vel.x == 0 && this.vel.y == 0) return;
        double angle = Math.atan2(this.vel.y, this.vel.x);   //radians
        float target = (float) angle + (float) Math.PI; //round number, avoid decimal fragments

		this.dir = target;
	}

    private void updateTooltipContent() {
		String cont = "Plate: " + this.plate + "\n" +
				"Model: " + this.model + "\n" +
				"Brand: " + this.brand + "\n" +
				"X: " + this.pos.x + " Y: + " + this.pos.y + " Z: " + this.pos.z + "\n";

		this.tooltip.updateContent(cont);
	}

    public void setAirport(Airport ap) {
        this.origin = ap;
    }

    public void setUpLunch() {
        this.setPos(this.origin.getLaunchPos());
        this.setDir(this.origin.getLaunchDir());
    }

    public void setPath(PlanePath pp) {
	    this.path = pp;
    }

	public void setDir(float dir) {
	    this.dir = dir;
    }

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public boolean isEngineOn() {
		return engineOn;
	}

	public void setEngineOn(boolean engineOn) {
		this.engineOn = engineOn;
	}

	public int getMaxPassangers() {
		return maxPassangers;
	}

	public void setMaxPassangers(int maxPassangers) {
		this.maxPassangers = maxPassangers;
	}

	public int getPassangers() {
		return currPassangers;
	}

	public int getKerosene() {
		return kerosene;
	}

	public void setKerosene(int kerosene) {
		this.kerosene = kerosene;
	}

	public void setPassangers(int n) {
		if(n > this.maxPassangers) return;
		this.currPassangers = n;
	}

	public boolean isDead() {return this.dead;}
}
