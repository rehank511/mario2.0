
//Jeffrey
//---------------------------------------------------------------------------------------------------------------------------------------------------------------
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

	public int x = 100, y = 750, w = 50, h = 50, s = 3; // Mario parameters
	public int dh = 0, dw = 0;
	public double maxdw = 5, maxdh = 15, walksd = 1, slowsd = 1, jumpsd = 15, fallsd = 1; // Mario movement speed

	public GRect Mario;
	public GRect t, b, l, r; // t = top, b = bottom, l = left, r = right

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
		InitilizeMario(x, y, w, h, s);
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
				p[a][i].InitilizePlatform(a * 400 + 400 + i * 50, 600 - a % 3 * 200, 50, 50, 3);
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

		Timer t = new Timer(10, this);
		t.start();

		addKeyListeners();
	}

	int i = 0;
	int onground = 0;

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int a = 0; a < p.length; a++) {
			collision(p[a]);
		}
		collision(P);

		System.out.print(onground);
		if (onground > 0)
			collideT = true;

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

		if (i % 10 == 0)
			if (Moving == false) {
				if (dw > 0)
					dw -= slowsd;
				if (dw < 0)
					dw += slowsd;
			}
		if (i % 10 == 0)
		if (MovingLeft == true) {
			if (dw > -maxdw)
				dw -= walksd;
			if (dw >= 0)
				dw -= walksd;
		}
		if (i % 10 == 0)
		if (MovingRight == true) {
			if (dw < maxdw)
				dw += walksd;
			if (dw <= 0)
				dw += walksd;
		}

		i++;
		if (i % 2 == 0)
			if (i % 1 == 0)
				if (dh <= maxdh)
					dh += fallsd;

		onground = 0;
	}

	public void collision(Platform[] p) {
		collideT = false;
		collideB = false;
		collideL = false;
		collideR = false;
		for (int i = 0; i < p.length; i++) {
			if ((b.getBounds()).intersects(p[i].t.getBounds())) {
				collideT = true;
				onground++;
				if (dh > 0)
					dh = 0;
				moveMario(0, p[i].F.getY() - Mario.getY() - h);
			}

			if ((t.getBounds()).intersects(p[i].b.getBounds())) {
				collideB = true;
				if (dh < 0)
					dh = 0;
				moveMario(0, p[i].t.getY() - Mario.getY() + p[i].F.getHeight());
			}
			if ((r.getBounds()).intersects(p[i].l.getBounds())) {
				collideL = true;
				if (dw > 0)
					dw = 0;
				moveMario(p[i].F.getX() - Mario.getX() - Mario.getWidth(), 0);
			}
			if ((l.getBounds()).intersects(p[i].r.getBounds())) {
				collideR = true;
				if (dw < 0)
					dw = 0;
				moveMario(p[i].F.getX() + p[i].F.getWidth() - Mario.getX(), 0);
			}
		}
	}

	boolean Moving = false;
	boolean MovingRight = false;
	boolean MovingLeft = false;

	@Override
	public void keyPressed(KeyEvent e) {
		Moving = true;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			MovingRight = true;
			if (dw < maxdw)
				dw += walksd;
			if (dw <= 0)
				dw += walksd;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			MovingLeft = true;
			if (dw > -maxdw)
				dw -= walksd;
			if (dw >= 0)
				dw -= walksd;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
			if (collideT) {
				dh -= jumpsd;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Moving = false;
			MovingRight = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Moving = false;
			MovingLeft = false;
		}
	}
}
//Jeffrey
//---------------------------------------------------------------------------------------------------------------------------------------------------------------