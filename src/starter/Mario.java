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
	Platform[][] p;
	Platform[] P;
	Platform[][] Pipe;
	
	
	public double qq = 5;
	private int x = 100, y = 600, w = 50, h = 50, q = 3;
	private int dh = 0, dw = 0;
	public double maxdw = 5, maxdh = 15, walkSpeed = 1, slowsd = .1, jumpSpeed = 25, fallsd = 1;
	boolean onground = false;
	private int PROGRAM_WIDTH = 850;
	private int PROGRAM_HEIGHT = 650;

	public GRect Mario;

	
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	private GRect Mariotop, Mariobottom, Marioleft, Marioright, Ground;

	public Mario() {
		Mario = new GRect(0, 0, 0, 0);
		Mariotop = new GRect(0, 0, 0, 0);
		Mariobottom = new GRect(0, 0, 0, 0);
		Marioleft = new GRect(0, 0, 0, 0);
		Marioright = new GRect(0, 0, 0, 0);
	
	}
//jumps
	public void InitilizeMario(int x, int y, int w, int h, int q) {
		Mario.setBounds(x, y, w, h);
		
		Mariotop.setBounds(x + q, y, w - 2 * q, q);
		Mariobottom.setBounds(x + q, y + h - q, w - 2 * q, q);
		Marioleft.setBounds(x, y + q, q, h - 2 * q);
		Marioright.setBounds(x + w - q, y + q, q, h - 2 * q);
		
	
	}

	public void moveMario(double x, double y) {
		Mario.move(x, y);
		Mariotop.move(x, y);
		Mariobottom.move(x, y);
		Marioright.move(x, y);
		Marioleft.move(x, y);
	}

	public boolean collideRight, collideLeft, collideTop, collideBottom;
	public boolean collision, Right, Left, Top, Bottom;

	public void run() {
		
		
		
		InitilizeMario(x, y, w, h, q);
		add(Mario);
		add(Mariotop);
		add(Mariobottom);
		add(Marioright);
		add(Marioleft);
	
		
	
		
		
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
				p[a][i].getGround().setColor(new Color(212, 212, 212));
				add(p[a][i].getGround());
				add(p[a][i].getBottom());
				add(p[a][i].getTop());
				add(p[a][i].getRight());
				add(p[a][i].getLeft());
			
			}
		}

		for (int i = 0; i < P.length; i++) {
			P[i].getGround().setColor(new Color(212, 212, 212));
			add(P[i].getGround());
			add(P[i].getBottom());
			add(P[i].getTop());
			add(P[i].getRight());
			add(P[i].getLeft());
		
		}
		
		for (int a = 0; a < Pipe.length; a++) {
			for (int i = 0; i < Pipe[0].length; i++) {
				Pipe[a][i].getGround().setColor(new Color(212, 212, 212));
				add(Pipe[a][i].getGround());
				add(Pipe[a][i].getBottom());
				add(Pipe[a][i].getTop());
				add(Pipe[a][i].getRight());
				add(Pipe[a][i].getLeft());
			
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
		
		if (Mario.getX() < 0 || Mario.getX() > 600) {
			if (Mario.getX() < 0) {
				dw = 0;
				moveMario(.1, 0);
			} else if (Mario.getX() > 600) {
				if (dw > 0) {
					for (int a = 0; a < p.length; a++) {
						for (int i = 0; i < p[0].length; i++) {
							p[a][i].movePlatform(-dw, 0);
						}
					}
					for (int i = 0; i < P.length; i++) {
						P[i].movePlatform(-dw, 0);
					}
					////
					
					//for (int i = 0; i < P.length; i++) {
						//Pipe[i].movePlatform(-dw, 0);
					}
					for (int a = 0; a < Pipe.length; a++) {
						for (int i = 0; i < Pipe[0].length; i++) {
							Pipe[a][i].movePlatform(-dw, 0);
						}
						
					}
			
					
					moveMario(0, dh);
				} else
					moveMario(dw, dh);
			}
		 else {
			 moveMario(dw, dh);
		 }
		if (dh < maxdh) {
			dh += fallsd;
		}
		System.out.print(dh);
	}

	

	public void collision(Platform[] p) {
		for (int i = 0; i < p.length; i++) {
			if ((Mariobottom.getBounds()).intersects(p[i].getTop().getBounds())) {
				onground = true;
				collideTop = true;
				if (dh > 0)
					dh = 0;
			
				moveMario(0, p[i].getGround().getY() - Mario.getY() - h);
			} else
				collideTop = false;
			if ((Mariotop.getBounds()).intersects(p[i].getBottom().getBounds())) {
				collideBottom = true;
				if (dh < 0)
					dh = 0;
				moveMario(0, p[i].getTop().getY() - Mario.getY() + p[i].getGround().getHeight());
			} else
				collideBottom = false;
			if ((Marioright.getBounds()).intersects(p[i].getLeft().getBounds())) {
				collideLeft = true;
				if (dw > 0)
					dw = 0;
			
		
				moveMario(p[i].getGround().getX() - Mario.getX() - Mario.getWidth(), 0);
			} else
				collideLeft = false;
			if ((Marioleft.getBounds()).intersects(p[i].getRight().getBounds())) {
				collideRight = true;
				if (dw < 0)
				moveMario(p[i].getGround().getX() + p[i].getGround().getWidth() - Mario.getX(), 0);
			} else
				collideRight = false;
		}
	}
		
	
	
	
	
	
	public void collisionPipe(Platform[] pipe) {
		for (int i = 0; i < pipe.length; i++) {
			if ((Mariobottom.getBounds()).intersects(pipe[i].getTop().getBounds())) {
				onground = true;
				collideTop = true;
				if (dh > 0)
					dh = 0;
			
			} else
				collideTop = false;
			if ((Mariotop.getBounds()).intersects(pipe[i].getBottom().getBounds())) {
				collideBottom = true;
				if (dh < 0)
					dh = 0;
			
			} else
				collideBottom = false;
			
			if ((Marioright.getBounds()).intersects(pipe[i].getLeft().getBounds())) {
				onground = true;
				if (dw > 0)
					dw = 0;
		
			}
			}
			
	}
	
	//
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (dw < maxdw)
				dw += walkSpeed;
			if (dw <= 0)
				dw += walkSpeed * 2;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (dw > -maxdw)
				dw -= walkSpeed;
			if (dw >= 0)
				dw -= walkSpeed * 2;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (dw > 0)
				dw -= walkSpeed;
			if (dw < 0)
				dw += walkSpeed;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
			if (dh == 1 && onground) {
				dh -= jumpSpeed;
				onground = false;
			}
		}
	}
}
