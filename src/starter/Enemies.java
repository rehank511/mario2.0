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
	private GRect GoombaImg;
	private GImage GoombaImgRight;
	private GImage GoombaImgLeft;
	private boolean goombaDead = false;
	private int goombaCollideSide = 0, goombaCollideBottom = 0;
	private int Moving = 0;
	private int goombaDirection = 0;
	private Enemies[] Goomba;
	
	
	private GRect BBTop, BBBottom, BBLeft, BBRight;
	private GImage BBImg;
	private boolean BBDead = false;
	private int BBCollideSide = 0, BBCollideBottom = 0;
	private int BBMoving = 0;
	private int BBDirection = 0;
	private Enemies[] BB;


	public int getGoombaDirection() {
		return goombaDirection;
	}
	
	public int getBBDirection() {
		return BBDirection;
	}
	
	public void changeGoombaDirection() {
		goombaDirection++;
	}
	
	public void resetCollision() {
		goombaCollideSide = 0;
		goombaCollideBottom = 0;
	}
	
	public void resetBBCollision() {
		BBCollideSide = 0;
		BBCollideBottom = 0;
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
	
	public int getBBCollideSide() {
		return BBCollideSide;
	}

	public int getGoombaCollideBottom() {
		return goombaCollideBottom;
	}
	
	public int getBBCollideBottom() {
		return BBCollideBottom;
	}

	public GRect getGoombaImg() {
		return GoombaImg;
	}
	
//<<<<<<< HEAD
//	public GImage getBBImg() {
//		return BBImg;
//=======
	public GImage getGoombaImgRight() {
		return GoombaImgRight;
	}
	
	public GImage getGoombaImgLeft() {
		return GoombaImgLeft;
//>>>>>>> branch 'master' of https://github.com/comp55-fall18/group-project-cloud9.git
	}

	public GRect getTop() {
		return Top;
	}
	
	public GRect getBBTop() {
		return BBTop;
	}

	public GRect getBottom() {
		return Bottom;
	}
	
	public GRect getBBBottom() {
		return BBBottom;
	}

	public GRect getLeft() {
		return Left;
	}
	
	public GRect getBBLeft() {
		return BBLeft;
	}

	public GRect getRight() {
		return Right;
	}
	
	public GRect getBBRight() {
		return BBRight;
	}

	public boolean getGoombaDead() {
		return goombaDead;
	}
	
	public boolean getBBDead() {
		return BBDead;
	}
//
//<<<<<<< HEAD
//	public void animateGoomba(int i) {
//		if (i % 2 == 0) {
//			GoombaImg.setImage("GoombaRight.png");
//			GoombaImg.setSize(50, 50);
//		} else {
//			GoombaImg.setImage("GoombaLeft.png");
//			GoombaImg.setSize(50, 50);
//		}
//	}
//	
//	public void animateBB(int i) {
//		if (i % 2 == 0) {
//			BBImg.setImage("bullet.png");
//			BBImg.setSize(50, 50);
//		} else {
//			BBImg.setImage("bullet.png");
//			BBImg.setSize(50, 50);
//		}
//	}
//
//=======
//>>>>>>> branch 'master' of https://github.com/comp55-fall18/group-project-cloud9.git
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
	
	public void InitilizeBB(int x, int y, int w, int h, int q) {
		BBImg = new GImage("bullet.png", 0, 0);
		BBImg.setSize(0, 0);
		BBImg.setBounds(x, y, w, h);
		Top.setBounds(x + q, y, w - 2 * q, h / 2);
		Bottom.setBounds(x + q, y + h / 2, w - 2 * q, h / 2);
		Left.setBounds(x, y + q, q, h - 2 * q);
		Right.setBounds(x + w - q, y + q, q, h - 2 * q);
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
	
	public void DeleteBB() {
		BBDead = true;
		BBImg.setSize(50, 15);
		BBImg.move(0, 35);
		Top = new GRect(0, 0, 0, 0);
		Bottom = new GRect(0, 0, 0, 0);
		Left = new GRect(0, 0, 0, 0);
		Right = new GRect(0, 0, 0, 0);
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
	
	public void moveBB(double x, double y) {
		BBImg.move(x, y);
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
	
//	public void collisionBB(Platform[] p) {
//		for (int i = 0; i < p.length; i++) {
//			if ((Bottom.getBounds()).intersects(p[i].getTop().getBounds())) {
//				BBCollideBottom++;
//				moveBB(0, p[i].getGround().getY() - getBBImg().getY() - getBBImg().getHeight());
//			}
//			if ((Right.getBounds()).intersects(p[i].getLeft().getBounds())) {
//				BBCollideSide++;
//				moveBB(p[i].getGround().getX() - getBBImg().getX() - getBBImg().getWidth(), 0);
//			}
//			if ((Left.getBounds()).intersects(p[i].getRight().getBounds())) {
//				BBCollideSide++;
//				moveBB(p[i].getGround().getX() + p[i].getGround().getWidth() - getBBImg().getX(), 0);
//			}
//		}
//	}

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
	
//	public void collisionBB(Enemies[] p, int x) {
//		for (int i = 0; i < p.length; i++) {
//			if (i != x) {
//				if ((Bottom.getBounds()).intersects(p[i].getTop().getBounds())) {
//					BBCollideBottom++;
//					moveBB(0, p[i].getBBImg().getY() - getBBImg().getY() - getBBImg().getHeight());
//				}
//				if ((Right.getBounds()).intersects(p[i].getLeft().getBounds())) {
//					BBCollideSide++;
//					moveBB(p[i].getBBImg().getX() - getBBImg().getX() - getBBImg().getWidth(), 0);
//				}
//				if ((Left.getBounds()).intersects(p[i].getRight().getBounds())) {
//					BBCollideSide++;
//					moveBB(p[i].getBBImg().getX() + p[i].getBBImg().getWidth() - getBBImg().getX(), 0);
//				}
//			}
//		}
//	}
//	
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
	
//	public void BBSpot()
//	{
//		BB = new Enemies[30];
//		for (int i = 0; i < BB.length; i++) {
//			BB[i] = new Enemies();
//		}
//
//		for (int i = 0; i < BB.length; i++) {
//			BB[i].InitilizeBB(i * 400, 0, 50, 50, 3);
//			add(BB[i].getBBImg());
//		}
//
//	}
}
