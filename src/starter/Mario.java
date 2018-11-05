package starter;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.Timer;

public class Mario extends GraphicsProgram {
	Platform[][] p; //platform
	Platform[] P; //the ground
	Platform[][] Pipe;
	
	
	
	public double thicknessthickness = 5;
	private static final int x = 100, y = 600, w = 50, h = 50, thickness = 3;
	private int vertVelocity = 0, horizVelocity = 0;
	public static final double maxhorizVelocity = 5, maxvertVelocity = 15, walkSpeed = 1, friction = .1, jumpSpeed = 25, gravity = 1;
	boolean onground = false;
	private int PROGRAM_WIDTH = 850;
	private int PROGRAM_HEIGHT = 650;

	public GRect Mario;

	
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	private GRect t, b, l, r,pipe;

	public Mario() {
		Mario = new GRect(0, 0, 0, 0);
		t = new GRect(0, 0, 0, 0);
		b = new GRect(0, 0, 0, 0);
		l = new GRect(0, 0, 0, 0);
		r = new GRect(0, 0, 0, 0);
	
	}
//jumps
	public void InitilizeMario(int x, int y, int w, int h, int thickness) {
		Mario.setBounds(x, y, w, h);
		
		t.setBounds(x + thickness, y, w - 2 * thickness, thickness);
		b.setBounds(x + thickness, y + h - thickness, w - 2 * thickness, thickness);
		l.setBounds(x, y + thickness, thickness, h - 2 * thickness);
		r.setBounds(x + w - thickness, y + thickness, thickness, h - 2 * thickness);
		
	
	}

	public void moveMario(double x, double y) {
		Mario.move(x, y);
		t.move(x, y);
		b.move(x, y);
		r.move(x, y);
		l.move(x, y);
	}

	public boolean collideR, collideL, collideT, collideB;
	public boolean collision, R, L, T, B;

	public void run() {
		
		
		
		InitilizeMario(x, y, w, h, thickness);
		add(Mario);
		add(t);
		add(b);
		add(r);
		add(l);
	
		
	
		
		
		p = new Platform[50][5];
		for (int a = 0; a < p.length; a++)
			for (int i = 0; i < p[0].length; i++) {
				p[a][i] = new Platform();
			}
		
		
		P = new Platform[10];
		for (int i = 0; i < P.length; i++) {
			P[i] = new Platform();
		}
		
	/////
		Pipe = new Platform[50][5];
		for (int a = 0; a < Pipe.length; a++)
			for (int i = 0; i < Pipe[0].length; i++) {
				Pipe[a][i] = new Platform();
			}
		
		
	
		
		for (int a = 0; a < p.length; a++) {
			for (int i = 0; i < p[0].length; i++) {
				p[a][i].InitilizePlatform(a*200 + 400 + i * 50, 400 - a%3 * 200, 50, 50, 3);
			}
		}

		for (int i = 0; i < P.length; i++) {
			P[i].InitilizePlatform(i*1000, 600, 800, 200, 3);
		}
		
		for (int a = 0; a < Pipe.length; a++) {
			for (int i = 0; i < Pipe[0].length; i++) {
				Pipe[a][i].InitilizePlatform(200,500, 120, 100, 3);
			}
		}
		

		for (int a = 0; a < p.length; a++) {
			for (int i = 0; i < p[0].length; i++) {
				p[a][i].F.setColor(new Color(212, 212, 212));
				add(p[a][i].F);
				add(p[a][i].b);
				add(p[a][i].t);
				add(p[a][i].r);
				add(p[a][i].l);
			
			}
		}

		for (int i = 0; i < P.length; i++) {
			P[i].F.setColor(new Color(212, 212, 212));
			add(P[i].F);
			add(P[i].b);
			add(P[i].t);
			add(P[i].r);
			add(P[i].l);
		
		}
		//////
		/*
		for (int i = 0; i < Pipe.length; i++) {
			Pipe[i].F.setColor(new Color(212, 212, 212));
			//add(Pipe[i].F);
			add(Pipe[i].b);
			add(Pipe[i].t);
			add(Pipe[i].l);
			add(Pipe[i].r);
		}
		*/
		for (int a = 0; a < Pipe.length; a++) {
			for (int i = 0; i < Pipe[0].length; i++) {
				Pipe[a][i].F.setColor(new Color(212, 212, 212));
				add(Pipe[a][i].F);
				add(Pipe[a][i].b);
				add(Pipe[a][i].t);
				add(Pipe[a][i].r);
				add(Pipe[a][i].l);
			
			}
		}
	
		
		Timer t = new Timer(1, this);
		t.start();

		addKeyListeners();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (int a = 0; a < p.length; a++) {
			collision(p[a]);
		}
		collision(P);
		////
		for (int a = 0; a < Pipe.length; a++) {
			collision(Pipe[a]);
		}
//		collision(P);
		if (Mario.getX() < 0 || Mario.getX() > 600) {
			if (Mario.getX() < 0) {
				horizVelocity = 0;
				moveMario(.1, 0);
			} else if (Mario.getX() > 600) {
				if (horizVelocity > 0) {
					for (int a = 0; a < p.length; a++) {
						for (int i = 0; i < p[0].length; i++) {
							p[a][i].movePlatform(-horizVelocity, 0);
						}
					}
					for (int i = 0; i < P.length; i++) {
						P[i].movePlatform(-horizVelocity, 0);
					}
					////
					
					//for (int i = 0; i < P.length; i++) {
						//Pipe[i].movePlatform(-horizVelocity, 0);
					}
					for (int a = 0; a < Pipe.length; a++) {
						for (int i = 0; i < Pipe[0].length; i++) {
							Pipe[a][i].movePlatform(-horizVelocity, 0);
						}
						
					}
			
					
					moveMario(0, vertVelocity);
				} else
					moveMario(horizVelocity, vertVelocity);
			}
		 else {
			 moveMario(horizVelocity, vertVelocity);
		 }
		if (vertVelocity < maxvertVelocity) {
			vertVelocity += gravity;
		}
		System.out.print(vertVelocity);
	}

	

	public void collision(Platform[] p) {
		for (int i = 0; i < p.length; i++) {
			if ((b.getBounds()).intersects(p[i].t.getBounds())) {
				onground = true;
				collideT = true;
				if (vertVelocity > 0)
					vertVelocity = 0;
			
				moveMario(0, p[i].F.getY() - Mario.getY() - h);
			} else
				collideT = false;
			if ((t.getBounds()).intersects(p[i].b.getBounds())) {
				collideB = true;
				if (vertVelocity < 0)
					vertVelocity = 0;
				moveMario(0, p[i].t.getY() - Mario.getY() + p[i].F.getHeight());
			} else
				collideB = false;
			if ((r.getBounds()).intersects(p[i].l.getBounds())) {
				collideL = true;
				if (horizVelocity > 0)
					horizVelocity = 0;
			
		
				moveMario(p[i].F.getX() - Mario.getX() - Mario.getWidth(), 0);
			} else
				collideL = false;
			if ((l.getBounds()).intersects(p[i].r.getBounds())) {
				collideR = true;
				if (horizVelocity < 0)
				moveMario(p[i].F.getX() + p[i].F.getWidth() - Mario.getX(), 0);
			} else
				collideR = false;
		}
	}
		
	
	
	
	
	
	public void collisionPipe(Platform[] pipe) {
		for (int i = 0; i < pipe.length; i++) {
			if ((b.getBounds()).intersects(pipe[i].t.getBounds())) {
				onground = true;
				collideT = true;
				if (vertVelocity > 0)
					vertVelocity = 0;
			
			} else
				collideT = false;
			if ((t.getBounds()).intersects(pipe[i].b.getBounds())) {
				collideB = true;
				if (vertVelocity < 0)
					vertVelocity = 0;
			
			} else
				collideB = false;
			
			if ((r.getBounds()).intersects(pipe[i].l.getBounds())) {
				onground = true;
				if (horizVelocity > 0)
					horizVelocity = 0;
		
			}
			}
			
	}
	
	
	//
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (horizVelocity < maxhorizVelocity)
				horizVelocity += walkSpeed;
			if (horizVelocity <= 0)
				horizVelocity += walkSpeed * 2;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (horizVelocity > -maxhorizVelocity)
				horizVelocity -= walkSpeed;
			if (horizVelocity >= 0)
				horizVelocity -= walkSpeed * 2;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (horizVelocity > 0)
				horizVelocity -= walkSpeed;
			if (horizVelocity < 0)
				horizVelocity += walkSpeed;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
			if (vertVelocity == 1 && onground) {
				vertVelocity -= jumpSpeed;
				onground = false;
			}
		}
	}
}
