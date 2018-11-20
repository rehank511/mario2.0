package starter;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

public class Enemies extends GraphicsProgram {
	private GRect Top, Bottom, Left, Right;
	private GImage GoombaImg;
	private boolean goombaDead = false;
	private int goombaCollideSide = 0, goombaCollideBottom = 0;
	private int Moving = 0;
	private int goombaDirection = 0;
	private Enemies[] Goomba;

	public int getGoombaDirection() {
		return goombaDirection;
	}
	
	public void changeGoombaDirection() {
		goombaDirection++;
	}
	
	public void resetCollision() {
		goombaCollideSide = 0;
		goombaCollideBottom = 0;
	}

	public void setMoving() {
		Moving++;
	}

	public int getMoving() {
		return Moving;
	}

	public int getGoombaCollideSide() {
		return goombaCollideSide;
	}

	public int getGoombaCollideBottom() {
		return goombaCollideBottom;
	}

	public GImage getGoombaImg() {
		return GoombaImg;
	}

	public GRect getTop() {
		return Top;
	}

	public GRect getBottom() {
		return Bottom;
	}

	public GRect getLeft() {
		return Left;
	}

	public GRect getRight() {
		return Right;
	}

	public boolean getGoombaDead() {
		return goombaDead;
	}

	public void animateGoomba(int i) {
		if (i % 2 == 0) {
			GoombaImg.setImage("GoombaRight.png");
			GoombaImg.setSize(50, 50);
		} else {
			GoombaImg.setImage("GoombaLeft.png");
			GoombaImg.setSize(50, 50);
		}
	}

	public Enemies() {
		Top = new GRect(0, 0, 0, 0);
		Bottom = new GRect(0, 0, 0, 0);
		Left = new GRect(0, 0, 0, 0);
		Right = new GRect(0, 0, 0, 0);
	}

	public void InitilizeGoomba(int x, int y, int w, int h, int q) {
		GoombaImg = new GImage("GoombaRight.png", 0, 0);
		GoombaImg.setSize(0, 0);
		GoombaImg.setBounds(x, y, w, h);
		Top.setBounds(x + q, y, w - 2 * q, h / 2);
		Bottom.setBounds(x + q, y + h / 2, w - 2 * q, h / 2);
		Left.setBounds(x, y + q, q, h - 2 * q);
		Right.setBounds(x + w - q, y + q, q, h - 2 * q);
	}

	public void DeleteGoomba() {
		goombaDead = true;
		GoombaImg.setSize(50, 15);
		GoombaImg.move(0, 35);
		Top = new GRect(0, 0, 0, 0);
		Bottom = new GRect(0, 0, 0, 0);
		Left = new GRect(0, 0, 0, 0);
		Right = new GRect(0, 0, 0, 0);
	}

	public void moveGoomba(double x, double y) {
		GoombaImg.move(x, y);
		Top.move(x, y);
		Bottom.move(x, y);
		Right.move(x, y);
		Left.move(x, y);
	}

	public void collisionGoomba(Platform[] p) {
		for (int i = 0; i < p.length; i++) {
			if ((Bottom.getBounds()).intersects(p[i].getTop().getBounds())) {
				goombaCollideBottom++;
				moveGoomba(0, p[i].getGround().getY() - getGoombaImg().getY() - getGoombaImg().getHeight());
			}
			if ((Right.getBounds()).intersects(p[i].getLeft().getBounds())) {
				goombaCollideSide++;
				moveGoomba(p[i].getGround().getX() - getGoombaImg().getX() - getGoombaImg().getWidth(), 0);
			}
			if ((Left.getBounds()).intersects(p[i].getRight().getBounds())) {
				goombaCollideSide++;
				moveGoomba(p[i].getGround().getX() + p[i].getGround().getWidth() - getGoombaImg().getX(), 0);
			}
		}
	}

	public void collisionGoomba(Enemies[] p, int x) {
		for (int i = 0; i < p.length; i++) {
			if (i != x) {
				if ((Bottom.getBounds()).intersects(p[i].getTop().getBounds())) {
					goombaCollideBottom++;
					moveGoomba(0, p[i].getGoombaImg().getY() - getGoombaImg().getY() - getGoombaImg().getHeight());
				}
				if ((Right.getBounds()).intersects(p[i].getLeft().getBounds())) {
					goombaCollideSide++;
					moveGoomba(p[i].getGoombaImg().getX() - getGoombaImg().getX() - getGoombaImg().getWidth(), 0);
				}
				if ((Left.getBounds()).intersects(p[i].getRight().getBounds())) {
					goombaCollideSide++;
					moveGoomba(p[i].getGoombaImg().getX() + p[i].getGoombaImg().getWidth() - getGoombaImg().getX(), 0);
				}
			}
		}
	}
	public void gumbaSpot()
	{
		Goomba = new Enemies[30];
		for (int i = 0; i < Goomba.length; i++) {
			Goomba[i] = new Enemies();
		}

		for (int i = 0; i < Goomba.length; i++) {
			Goomba[i].InitilizeGoomba(i * 400, 0, 50, 50, 3);
			add(Goomba[i].getGoombaImg());
		}

	}
}
