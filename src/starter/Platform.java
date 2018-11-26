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
	private Level level = new Level();
	private Mario levelPic = new Mario();

	
	
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
	

	public Platform() {
		Ground = new GRect(0, 0, 0, 0);
		Top = new GRect(0, 0, 0, 0);
		Bottom = new GRect(0, 0, 0, 0);
		Left = new GRect(0, 0, 0, 0);
		Right = new GRect(0, 0, 0, 0);
		Ground.setColor(new Color(212, 212, 212));	
	}
	
	public void InitilizePlatform(int x, int y, int w, int h, int q){
		Ground.setBounds(x, y, w, h);
		Top.setBounds(x+q, y, w-2*q, h/2);
		Bottom.setBounds(x+q, y+h/2, w-2*q, h/2);
		Left.setBounds(x, y+q, q, h-2*q);
		Right.setBounds(x+w-q, y+q, q, h-2*q);	
	}
	

	public void InitilizePipe(int x, int y, int w, int h, int q){
		Ground.setBounds(x, y, w, h);
		Top.setBounds(x+q, y, w-2*q, h/2);
		Bottom.setBounds(x+q, y+h/2, w-2*q, h/2);
		Left.setBounds(x, y+q, q, h-2*q);
		Right.setBounds(x+w-q, y+q, q, h-2*q);
	}
	
	
	public void movePlatform(double x, double y) {
		Ground.move(x, y);
		Top.move(x, y);
		Bottom.move(x, y);
		Right.move(x, y);
		Left.move(x, y);
		
//		levelPic.plat1.move(x, y);
		
//		levelPic.plat1.move(x,y);
	}
	
	
	
	
}