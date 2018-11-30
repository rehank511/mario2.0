package starter;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Enemies extends GraphicsProgram {
	private GRect Top, Bottom, Left, Right;
	private GRect GoombaImg;
	private GImage GoombaImgRight;
	private GImage GoombaImgLeft;
	private boolean goombaDead = false;
	private int goombaCollideSide = 0, goombaCollideBottom = 0;
	private int Moving = 0;
	private int goombaDirection = 0;

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

	public GRect getGoombaImg() {
		return GoombaImg;
	}
	
	public GImage getGoombaImgRight() {
		return GoombaImgRight;
	}
	
	public GImage getGoombaImgLeft() {
		return GoombaImgLeft;
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

	public Enemies() {
		Top = new GRect(0, 0, 0, 0);
		Bottom = new GRect(0, 0, 0, 0);
		Left = new GRect(0, 0, 0, 0);
		Right = new GRect(0, 0, 0, 0);
		GoombaImg = new GRect(0, 0, 0, 0);
		GoombaImg.setSize(0, 0);
		goombaDead = true;
	}

	public void InitilizeGoomba(int x, int y, int w, int h, int q) {
		GoombaImg = new GRect(0, 0, 0, 0);
		GoombaImg.setSize(0, 0);
		GoombaImg.setBounds(x, y, w, h);
		GoombaImg.setVisible(false);
		Top.setBounds(x + q, y, w - 2 * q, h / 2);
		Bottom.setBounds(x + q, y + h / 2, w - 2 * q, h / 2);
		Left.setBounds(x, y + q, q, h - 2 * q);
		Right.setBounds(x + w - q, y + q, q, h - 2 * q);
		GoombaImgLeft = new GImage("GoombaLeft.png", 0, 0);
		GoombaImgRight= new GImage("GoombaRight.png", 0, 0);
		GoombaImgRight.setBounds(GoombaImg.getX(), GoombaImg.getY(), 50, 50);
		GoombaImgLeft.setBounds(GoombaImg.getX(), GoombaImg.getY(), 50, 50);
		goombaDead = false;
	}

	public void DeleteGoomba() {
		GoombaImg.setSize(50, 15);
		GoombaImg.move(0, 35);
		GoombaImgRight.setSize(50, 15);
		GoombaImgRight.move(0, 35);
		GoombaImgLeft.setSize(50, 15);
		GoombaImgLeft.move(0, 35);
		if (goombaDead)
		Top = new GRect(0, 0, 0, 0);
		Bottom = new GRect(0, 0, 0, 0);
		Left = new GRect(0, 0, 0, 0);
		Right = new GRect(0, 0, 0, 0);
		goombaDead = true;
	}

	public void moveGoomba(double x, double y) {
		GoombaImgRight.move(x, y);
		GoombaImgLeft.move(x, y);
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
				moveGoomba(p[i].getGround().getX() - getGoombaImg().getX() + getGoombaImg().getWidth(), 0);
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
					moveGoomba(p[i].getGoombaImg().getX() - getGoombaImg().getX() + getGoombaImg().getWidth(), 0);
				}
			}
		}
	}
}
