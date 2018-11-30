package starter;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Platform extends GraphicsProgram{
	private GRect Ground;
	private GRect Top, Bottom, Left, Right;
	private GRect Platform;
//	private Level level = new Level();
//	private Mario levelPic = new Mario();

	
	private GImage platImg;
	
	public GImage getPlatImg() {
		return platImg;
	}
	
	public GRect getGround() {
		return Ground;
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
	
	public void changeToGround() {
		platImg.setImage("Brick.png");
		platImg.setSize(50, 50);
	}
	
	public Platform() {
		Ground = new GRect(0, 0, 0, 0);
		Top = new GRect(0, 0, 0, 0);
		Bottom = new GRect(0, 0, 0, 0);
		Left = new GRect(0, 0, 0, 0);
		Right = new GRect(0, 0, 0, 0);
//<<<<<<< HEAD
//		Ground.setColor(new Color(212, 212, 212));	
//=======
		platImg = new GImage("Block.png", 0, 0);
		platImg.setSize(0, 0);
//>>>>>>> branch 'master' of https://github.com/comp55-fall18/group-project-cloud9.git
	}
	
	public void InitilizePlatform(int x, int y, int w, int h, int q){
		platImg = new GImage("Block.png", 0, 0);
		platImg.setSize(0, 0);
		platImg.setBounds(x, y, w, h);
		Ground.setBounds(x, y, w, h);
		Top.setBounds(x+q, y, w-2*q, h/2);
		Bottom.setBounds(x+q, y+h/2, w-2*q, h/2);
		Left.setBounds(x, y+q, q, h-2*q);
//<<<<<<< HEAD
//		Right.setBounds(x+w-q, y+q, q, h-2*q);	
//=======
		Right.setBounds(x+w-q, y+q, q, h-2*q);
//>>>>>>> branch 'master' of https://github.com/comp55-fall18/group-project-cloud9.git
	}
	
	public void InitilizePipe(int x, int y, int w, int h, int q){
		Ground.setBounds(x, y, w, h);
		Top.setBounds(x+q, y, w-2*q, h/2);
		Bottom.setBounds(x+q, y+h/2, w-2*q, h/2);
		Left.setBounds(x, y+q, q, h-2*q);
		Right.setBounds(x+w-q, y+q, q, h-2*q);
	}
	
	public void movePlatform(double x, double y) {
		platImg.move(x,y);
		Ground.move(x, y);
		Top.move(x, y);
		Bottom.move(x, y);
		Right.move(x, y);
		Left.move(x, y);
		
//		levelPic.plat1.move(x, y);
		
//		levelPic.plat1.move(x,y);
	}
	
	public void removePlatform() {
		remove(platImg);
		movePlatform(-999999, 0);
	}
	

	public void run() {
		Platform p = new Platform();
		p.InitilizePlatform(50,50,200,200,3);
		add(p.getPlatImg());
	}
}
//>>>>>>> branch 'master' of https://github.com/comp55-fall18/group-project-cloud9.git
