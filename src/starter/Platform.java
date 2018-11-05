
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
	
	public void InitilizePlatform(int x, int y, int w, int h, int thickness){
		F.setBounds(x, y, w, h);
		t.setBounds(x+thickness, y, w-2*thickness, h/2);
		b.setBounds(x+thickness, y+h/2, w-2*thickness, h/2);
		l.setBounds(x, y+thickness, thickness, h-2*thickness);
		r.setBounds(x+w-thickness, y+thickness, thickness, h-2*thickness);
	
	
		
		
		
	}
	
	public void InitilizePipe(int x, int y, int w, int h, int thickness){
		F.setBounds(x, y, w, h);
	
		b.setBounds(700,700,50,50);
		t.setBounds(700,750, 50,50);
		
		l.setBounds(750, 700, 3, 100);
		r.setBounds(750, 700, 3, 100);
	

	}
	
	
	public void movePlatform(double x, double y) {
		F.move(x, y);
		t.move(x, y);
		b.move(x, y);
		r.move(x, y);
		l.move(x, y);
	}
	
	
}
