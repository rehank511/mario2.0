package starter;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Mario extends GraphicsProgram implements ActionListener {
	public static final int GROUND = 400;
	private static final int SPEED = 2;
	private static final int MAXACCEL = 30;
	public static final int PROGRAM_HEIGHT = 800;
	public static final int PROGRAM_WIDTH = 1000;
//	public 
	public int numSec = 0;
	
	private int xCoord;
	private int yCoord;
	private int accel=0;
	private GRect mario = new GRect(100,600,100,100);
	private GRect floor = new GRect(0,700,80000,GROUND);
	Timer marioTimer = new Timer(70, this);
	
	
	public void init() {
		setSize(PROGRAM_WIDTH,PROGRAM_HEIGHT);
		requestFocus();
	}
	
	public void actionPerformed(ActionEvent e) {
		   //TODO add what we want to do every two seconds
		
		mario.move(0, -SPEED*accel);
		numSec++;
		accel++;
		
		if(numSec >= 7)
		{ 
			
			mario.move(0, SPEED*accel);
			if(mario.getY()==600)
			{
				accel=0;
				marioTimer.stop();
			}
			
		}
		
		
		
	}

	public void run() {
		add(mario);
		add(floor);
		
		addKeyListeners();
		addActionListeners();
		
	}
	
	public void moveRight()
	{
		while(accel<MAXACCEL)
		{
			accel++;
		}
		mario.move(SPEED*accel, 0);
	}
	
	public void moveLeft()
	{
		while(accel<MAXACCEL)
		{
			accel++;
		}
		mario.move(-SPEED*accel,0);
	}
	
	public void moveUp()
	{
//		for(int i =0; i<4;i++)
//		{
//			mario.move(0, -SPEED*i);
//		}
//		mario.setLocation(mario.getX(), mario.getY()-SPEED*accel);
//		for(int i = 0; i<4;i++)
//		{
//			mario.move(0, SPEED*i);
//		}
		marioTimer.start();
		
	}
	
	public boolean marioOnGround()
	{
		
		if(mario.getY()!=floor.getY())
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moveRight();
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			moveLeft();
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			moveUp();
		}
		
//		else
//		{
//			return;
//		}
	}

	
}
=======
//Jeffrey
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

	public double qq = 5;
	public int x = 100, y = 750, w = 50, h = 50, q = 3;
	public int dh = 0, dw = 0;
	public double maxdw = 5, maxdh = 15, walksd = 1, slowsd = .1, jumpsd = 22, fallsd = 1;
	boolean onground = false;

	public GRect Mario;
	public GRect t, b, l, r;

	public Mario() {
		Mario = new GRect(0, 0, 0, 0);
		t = new GRect(0, 0, 0, 0);
		b = new GRect(0, 0, 0, 0);
		l = new GRect(0, 0, 0, 0);
		r = new GRect(0, 0, 0, 0);
		Mario.setColor(new Color(200, 0, 0));
	}

	public void InitilizeMario(int x, int y, int w, int h, int q) {
		Mario.setBounds(x, y, w, h);
		t.setBounds(x + q, y, w - 2 * q, q);
		b.setBounds(x + q, y + h - q, w - 2 * q, q);
		l.setBounds(x, y + q, q, h - 2 * q);
		r.setBounds(x + w - q, y + q, q, h - 2 * q);
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
		InitilizeMario(x, y, w, h, q);
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

		for (int a = 0; a < p.length; a++) {
			for (int i = 0; i < p[0].length; i++) {
				p[a][i].InitilizePlatform(a*400 + 400 + i * 50, 600 - a%3 * 200, 50, 50, 3);
			}
		}

		for (int i = 0; i < P.length; i++) {
			P[i].InitilizePlatform(i * 1000, 800, 800, 200, 3);
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
					moveMario(0, dh);
				} else
					moveMario(dw, dh);
			}
		} else
			moveMario(dw, dh);
		if (dh < maxdh)
			dh += fallsd;
		System.out.print(dh);
	}

	public void collision(Platform[] p) {
		for (int i = 0; i < p.length; i++) {
			if ((b.getBounds()).intersects(p[i].t.getBounds())) {
				onground = true;
				collideT = true;
				if (dh > 0)
					dh = 0;
				moveMario(0, p[i].F.getY() - Mario.getY() - h);
			} else
				collideT = false;
			if ((t.getBounds()).intersects(p[i].b.getBounds())) {
				collideB = true;
				if (dh < 0)
					dh = 0;
				moveMario(0, p[i].t.getY() - Mario.getY() + p[i].F.getHeight());
			} else
				collideB = false;
			if ((r.getBounds()).intersects(p[i].l.getBounds())) {
				collideL = true;
				if (dw > 0)
					dw = 0;
				moveMario(p[i].F.getX() - Mario.getX() - Mario.getWidth(), 0);
			} else
				collideL = false;
			if ((l.getBounds()).intersects(p[i].r.getBounds())) {
				collideR = true;
				if (dw < 0)
					dw = 0;
				moveMario(p[i].F.getX() + p[i].F.getWidth() - Mario.getX(), 0);
			} else
				collideR = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (dw < maxdw)
				dw += walksd;
			if (dw <= 0)
				dw += walksd * 2;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (dw > -maxdw)
				dw -= walksd;
			if (dw >= 0)
				dw -= walksd * 2;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (dw > 0)
				dw -= walksd;
			if (dw < 0)
				dw += walksd;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
			if (dh == 1 && onground) {
				dh -= jumpsd;
				onground = false;
			}
		}
	}
}
>>>>>>> branch 'master' of https://github.com/comp55-fall18/group-project-cloud9.git
