package cat.esteve.atc.main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

import cat.esteve.atc.gfx.Art;
import cat.esteve.atc.gfx.Renderer;
import cat.esteve.atc.input.MouseHandler;
import cat.esteve.atc.scene.Scene;
import cat.esteve.atc.utils.Cryptography;

public class Main implements Runnable {
	public static final int WIDTH = 1080, HEIGHT = 720;

	public static int mx = 0, my = 0;
	
	private int ttps = 0, ffps = 0;
	
	private JFrame frame;
	
	private Renderer renderer;
	private Scene scene;
	private MouseHandler mh;
	
	private Thread thread;
	
	private void init() {
		Art.i.load();

		this.scene = new Scene();

		this.mh = new MouseHandler(this.scene);
		this.renderer.addMouseListener(this.mh);
		this.renderer.addMouseMotionListener(this.mh);

		this.scene.init();
	}

	@Override
	public void run() {
        this.init();
        int fps = 0;
        int tps = 0;
        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / 60;
        long lastTimer1 = System.currentTimeMillis();

        while (true) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            while (unprocessed >= 1) {
                tps++;
                this.tick();
                unprocessed -= 1;
            }

            fps++;
            render();

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                this.ffps = fps;
                this.ttps = tps;
                //System.out.println("fps " + fps + ", tps " + tps);
                fps = 0;
                tps = 0;
            }
        }
	}
	
	private void tick() {
		this.scene.tick();
	}
	
	private void render() {
		if(!this.renderer.start()) return;
		this.renderer.clear();
		
		this.scene.render(renderer);
		
		this.renderer.end();
	}

	public void start() {
		this.frame = new JFrame();
		this.frame.setTitle("Traffic Air Controller by Roger Esteve (2n DAM)");
		
		this.frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.renderer = new Renderer();
		this.frame.add(this.renderer);
		
		this.frame.pack();
		
		this.frame.setVisible(true);
		
		this.thread = new Thread(this, "Main thread");
		this.thread.start();
	}
}
