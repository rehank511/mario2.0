package starter;
//commit
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
	//Initiolisation of classes
	Platform[][] platform;
	Platform[] Ground;
	Platform[][] Pipe;
	
	
	//Variables initialisaton
	private static final double Vert_MAX_Velocity = 5, Horiz_MAX_Velocity = 15, walkSpeed = 1, Friction = 1, jumpSpeed = 25, Gravity = 1;
	private static final int XAXIS = 100, YAXIS = 600, WIDTH = 50, HEIGHT = 50, THICKNESS = 3;
	private double qq = 5;
	private double vertVelocity = 0, horizVelocity = 0;
	
	boolean onground = false;
	private int PROGRAM_WIDTH = 850;
	private int PROGRAM_HEIGHT = 650;
	private boolean isWalking = true;
	private boolean stopping = false;
	public boolean collideRight, collideLeft, collideTop, collideBottom;
	public boolean collision, Right, Left, Top, Bottom;

	//Creation of mario class
	public GRect Mario;


	//Making window size
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	//Creating collition squares
	private GRect Mariotop, Mariobottom, Marioleft, Marioright;

	//Mario set to 0
	public Mario() {
		Mario = new GRect(0, 0, 0, 0);
		Mariotop = new GRect(0, 0, 0, 0);
		Mariobottom = new GRect(0, 0, 0, 0);
		Marioleft = new GRect(0, 0, 0, 0);
		Marioright = new GRect(0, 0, 0, 0);
	

	}
	//Mario set to normal location
	public void InitilizeMario(int x, int y, int w, int h, int q) {
		Mario.setBounds(x, y, w, h);
		Mariotop.setBounds(x + q, y, w - 2 * q, q);
		Mariobottom.setBounds(x + q, y + h - q, w - 2 * q, q);
		Marioleft.setBounds(x, y + q, q, h - 2 * q);
		Marioright.setBounds(x + w - q, y + q, q, h - 2 * q);
	}

	//Moving mario
	public void moveMario(double x, double y) {
		Mario.move(x, y);
		Mariotop.move(x, y);
		Mariobottom.move(x, y);
		Marioright.move(x, y);
		Marioleft.move(x, y);
	}

	
	

	//func to run
	public void run() {	
		InitilizeMario(XAXIS, YAXIS, WIDTH, HEIGHT, THICKNESS);
		add(Mario);
		add(Mariotop);
		add(Mariobottom);
		add(Marioright);
		add(Marioleft);
	
		
	
		
		//creating platforms
		platform = new Platform[50][5];
		for (int a = 0; a < platform.length; a++)
			for (int i = 0; i < platform[0].length; i++) {
				platform[a][i] = new Platform();
			}

		//creating ground 
		Ground = new Platform[10];
		for (int i = 0; i < Ground.length; i++) {
			Ground[i] = new Platform();

		}

		//creating pipe
		Pipe = new Platform[50][5];
		for (int a = 0; a < Pipe.length; a++)
			for (int i = 0; i < Pipe[0].length; i++) {
				Pipe[a][i] = new Platform();
			}

		//setting platforms locations
		for (int a = 0; a < platform.length; a++) {
			for (int i = 0; i < platform[0].length; i++) {
				platform[a][i].InitilizePlatform(a*200 + 400 + i * 50, 400 - a%3 * 200, 50, 50, 3);

			}
		}
		
		//setting ground locations
		for (int i = 0; i < Ground.length; i++) {
			Ground[i].InitilizePlatform(i*200, 600, 800, 200, 3);
		}

		//setting pipe locations
		for (int a = 0; a < Pipe.length; a++) {
			for (int i = 0; i < Pipe[0].length; i++) {
				Pipe[a][i].InitilizePlatform(200,500, 120, 100, 3);
			}
		}


		//adding platforms to the map
		for (int a = 0; a < platform.length; a++) {
			for (int i = 0; i < platform[0].length; i++) {
				platform[a][i].getGround().setColor(new Color(212, 212, 212));
				add(platform[a][i].getGround());
				add(platform[a][i].getBottom());
				add(platform[a][i].getTop());
				add(platform[a][i].getRight());
				add(platform[a][i].getLeft());			
			}
		}

		//adding ground to the map
		for (int i = 0; i < Ground.length; i++) {
			Ground[i].getGround().setColor(new Color(212, 212, 212));
			add(Ground[i].getGround());
			add(Ground[i].getBottom());
			add(Ground[i].getTop());
			add(Ground[i].getRight());
			add(Ground[i].getLeft());
		}


		//adding pipes to the map
		for (int a = 0; a < Pipe.length; a++) {
			for (int i = 0; i < Pipe[0].length; i++) {

				Pipe[a][i].getGround().setColor(new Color(212, 212, 212));
				add(Pipe[a][i].getGround());
				add(Pipe[a][i].getBottom());
				add(Pipe[a][i].getTop());
				add(Pipe[a][i].getRight());
				add(Pipe[a][i].getLeft());

			}
		}


		//timer
		Timer t = new Timer(1, this);
		t.start();
		addKeyListeners();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//Checks collision with platforms
		for (int a = 0; a < platform.length; a++) {
			collision(platform[a]);

		}
		
		//checking collision with ground
		collision(Ground);
		
		//checks collision with pipe
		for (int a = 0; a < Pipe.length; a++) 
		{
			collision(Pipe[a]);
		}
		
		//if mario is on x position less than 0 or more than 600
		if (Mario.getX() < 0 || Mario.getX() > 600) 
		{
			if (Mario.getX() < 0) 
			{

				horizVelocity = 0;
				moveMario(.1, 0);

			} 
			else if (Mario.getX() > 600) 
			{
				if (horizVelocity > 0) 
				{
					for (int a = 0; a < platform.length; a++) 
					{
						for (int i = 0; i < platform[0].length; i++) 
						{
							platform[a][i].movePlatform(-horizVelocity*(2.5), 0);
						}
					}

					for (int i = 0; i < Ground.length; i++) 
					{
						Ground[i].movePlatform(-horizVelocity*(2.5), 0);

					}

					for (int a = 0; a < Pipe.length; a++) 
					{
						for (int i = 0; i < Pipe[0].length; i++) 
						{
							Pipe[a][i].movePlatform(-horizVelocity*(2.5), 0);
						}
						
					}
				}
					
					
					moveMario(horizVelocity, vertVelocity);
			} 
			else
					moveMario(horizVelocity, vertVelocity);

		}

		 else 
		 {
			 moveMario(horizVelocity, vertVelocity);
		 }
		if (vertVelocity < Horiz_MAX_Velocity) 
		{
			vertVelocity += Gravity;
		}
//		System.out.print(vertVelocity);
	}



	public void collision(Platform[] p) 
	{
		for (int i = 0; i < p.length; i++) 
		{
			if ((Mariobottom.getBounds()).intersects(p[i].getTop().getBounds())) 
			{
				onground = true;

				collideTop = true;
				if (vertVelocity > 0)
					vertVelocity = 0;
			
				moveMario(0, p[i].getGround().getY() - Mario.getY() - HEIGHT);

			} 
			else

				collideTop = false;
			if ((Mariotop.getBounds()).intersects(p[i].getBottom().getBounds())) 
			{
				collideBottom = true;
				if (vertVelocity < 0)
					vertVelocity = 0;
				moveMario(0, p[i].getTop().getY() - Mario.getY() + p[i].getGround().getHeight());

			} 
			else

				collideBottom = false;
			if ((Marioright.getBounds()).intersects(p[i].getLeft().getBounds())) 
			{
				collideLeft = true;
				if (horizVelocity > 0)
					horizVelocity = 0;
			
		
				moveMario(p[i].getGround().getX() - Mario.getX() - Mario.getWidth(), 0);

			} 
			else

				collideLeft = false;
			if ((Marioleft.getBounds()).intersects(p[i].getRight().getBounds())) 
			{
				collideRight = true;
				if (horizVelocity < 0)
				moveMario(p[i].getGround().getX() + p[i].getGround().getWidth() - Mario.getX(), 0);

			} 
			else
				collideRight = false;
		}
	}


	public void collisionPipe(Platform[] pipe) 
	{
		for (int i = 0; i < pipe.length; i++) 
		{
			if ((Mariobottom.getBounds()).intersects(pipe[i].getTop().getBounds())) 
			{
				onground = true;
				collideTop = true;
				if (vertVelocity > 0)
					vertVelocity = 0;

			} 
			else
				collideTop = false;
			if ((Mariotop.getBounds()).intersects(pipe[i].getBottom().getBounds())) 
			{
				collideBottom = true;
				if (vertVelocity < 0)
					vertVelocity = 0;
			

			} 
			else

				collideBottom = false;
			
			if ((Marioright.getBounds()).intersects(pipe[i].getLeft().getBounds())) 
			{

				onground = true;
				if (horizVelocity > 0)
					horizVelocity = 0;

			}
		}

	}
	//right speed control
	public void moveRight(KeyEvent e)
	{
		if (horizVelocity < Vert_MAX_Velocity)
		{
			if(horizVelocity != Vert_MAX_Velocity)
			{
				horizVelocity++;
				horizVelocity = horizVelocity%Vert_MAX_Velocity;
			}
		}
	}
	//left speed control
	public void moveLeft(KeyEvent e)
	{
		if (horizVelocity > -Vert_MAX_Velocity)
		{
			if(horizVelocity != -Vert_MAX_Velocity)
			{
				horizVelocity--;
				horizVelocity = horizVelocity%(-Vert_MAX_Velocity);
			}
		}
	}
	
	public void moveDown(KeyEvent e)
	{
		if (horizVelocity > 0)
			horizVelocity -= walkSpeed;
		
		if (horizVelocity < 0)
			horizVelocity += walkSpeed;
	}
	
	//up speed control
	public void moveUp(KeyEvent e)
	{
		if (vertVelocity == 1 && onground) 
		{
			vertVelocity -= jumpSpeed;
			onground = false;
		}
	}
	
	public void stopMoving(KeyEvent e)
	{
//		horizVelocity -= walkSpeed;
//		if(horizVelocity == 0)
//		stopping = true;
//		if(stopping)
//			horizVelocity -= Friction;
//		else
//			horizVelocity = 0;
//		
//		if (horizVelocity==0)
//				stopping = false;
		horizVelocity = 0;
	}


	//
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			moveRight(e);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			moveLeft(e);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			moveDown(e);
		} 
		else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) 
		{
			moveUp(e);
		}
	}
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT||e.getKeyCode() == KeyEvent.VK_LEFT)
		stopMoving(e);
	}
}
