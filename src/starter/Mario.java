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
