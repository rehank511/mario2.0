package starter;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Platform extends GraphicsProgram{
	public GRect F;
	public GRect t, b, l, r;
	
	public Platform() {
		F = new GRect(0, 0, 0, 0);
		t = new GRect(0, 0, 0, 0);
		b = new GRect(0, 0, 0, 0);
		l = new GRect(0, 0, 0, 0);
		r = new GRect(0, 0, 0, 0);
		F.setColor(new Color(212, 212, 212));
	}
	
	public void InitilizePlatform(int x, int y, int w, int h, int q){
		F.setBounds(x, y, w, h);
		t.setBounds(x+q, y, w-2*q, h/2);
		b.setBounds(x+q, y+h/2, w-2*q, h/2);
		l.setBounds(x, y+q, q, h-2*q);
		r.setBounds(x+w-q, y+q, q, h-2*q);
	}
	
	public void movePlatform(double x, double y) {
		F.move(x, y);
		t.move(x, y);
		b.move(x, y);
		r.move(x, y);
		l.move(x, y);
	}
	
}
