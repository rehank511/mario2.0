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

public class Level extends GraphicsProgram{

	public static final int PROGRAM_HEIGHT = 650;
	public static final int PROGRAM_WIDTH = 850;
	
	public void init()
	{
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
		requestFocus();

	}
	
	public void addGround()
	{
		GRect rect = new GRect(300, 300, 200, 100);
		add(rect);
	}
	
	public void addPlatform()
	{
//		GRect 
	}
	
	public void run()
	{
		addGround();
	}
	
	
}


