package starter;

//Fixed Movement
//GitHub Check 2
// Added mario image
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import starter.Platform;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.Timer;

public class Mario extends GraphicsProgram {
	private Platform[][] platform;
	private Platform[] Ground;
	private Level level = new Level();
	private Platform[][] Pipe;
	private Enemies[] Goomba;
	
	public static int  WIDTH = 50, HEIGHT = 50;

	

	

	public int TimerCount = global.TimerCount, onground = 0;
	boolean Moving = false, movingLeft = false, movingRight = false;
	private static final int PROGRAM_WIDTH = 850;
	private static final int PROGRAM_HEIGHT = 650;
	public boolean collideRight, collideLeft, collideTop, collideBottom;
	public boolean collision, Right, Left, Top, Bottom;

	private GRect Mario;
	private GImage MarioImgRight, MarioImgLeft;

	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	private GRect Mariotop, Mariobottom, Marioleft, Marioright;

	public Mario() {
		Mario = new GRect(0, 0, 0, 0);
		Mariotop = new GRect(0, 0, 0, 0);
		setMariobottom(new GRect(0, 0, 0, 0));
		Marioleft = new GRect(0, 0, 0, 0);
		Marioright = new GRect(0, 0, 0, 0);

	}

	// jumps
	public void InitilizeMario(int x, int y, int w, int h, int q) {
		Mario.setBounds(x, y, w, h);
		Mariotop.setBounds(x + q, y, w - 2 * q, q);
		getMariobottom().setBounds(x + q, y + h - q, w - 2 * q, q);
		Marioleft.setBounds(x, y + q, q, h - 2 * q);
		Marioright.setBounds(x + w - q, y + q, q, h - 2 * q);
	}

	public void moveMario(double x, double y) {
		Mario.move(x, y);
		MarioImgRight.move(x, y);
		MarioImgLeft.move(x, y);
		Mariotop.move(x, y);
		getMariobottom().move(x, y);
		Marioright.move(x, y);
		Marioleft.move(x, y);
	}

//	public boolean global.collideRight, collideLeft, collideTop, collideBottom;
//	public boolean collision, Right, Left, Top, Bottom;

	public void marioGraphics(){
		GImage background = new GImage("bg.png", 0, 0);
		background.setSize(850, 600);
		add(background);
		GImage ground = new GImage("ground.png", 0, 600);
		ground.setSize(850, 100);
		add(ground);
		MarioImgRight = new GImage("MarioRight.png", Mario.getX(), Mario.getY() - 1);
		MarioImgRight.setSize(50, 57);
		MarioImgLeft = new GImage("MarioLeft.png", Mario.getX(), Mario.getY() - 1);
		MarioImgLeft.setSize(50, 57);
		add(MarioImgRight);
	}
	public void run() {
		InitilizeMario(global.XAXIS, global.YAXIS, WIDTH, HEIGHT, global.THICKNESS);
		
		marioGraphics();
		
		level1();
		
		gumbaSpot();

		Timer t = new Timer(10, this);
		t.start();
		addKeyListeners();
	}

	// is called after every milisecond and moves the mario and the platform
	@Override
	public void actionPerformed(ActionEvent e) {

		for (int a = 0; a < platform.length; a++) {
			collision(platform[a]);

		}

		collision(Goomba);
		collision(Ground);
		////
		for (int a = 0; a < Pipe.length; a++) {
			collision(Pipe[a]);
		}

		if (onground > 0)
			collideTop = true;

		if (Mario.getX() < 0 || Mario.getX() > 500) {
			// If Mario is on left corner of the screen
			if (Mario.getX() < 0) {

				if (global.horizVelocity < 0)
					global.horizVelocity = 0;
				moveMario(1, 0);

				// if Mario is on the right corner on the screen
			} else if (Mario.getX() > 500) {
				if (global.horizVelocity > 0) {
					// moves the platform ground and pipe to left as Mario moves to the right
					for (int a = 0; a < platform.length; a++) {
						for (int i = 0; i < platform[0].length; i++) {
							platform[a][i].movePlatform(-global.horizVelocity, 0);

						}
					}
					for (int i = 0; i < Ground.length; i++) {
						Ground[i].movePlatform(-global.horizVelocity, 0);
					}
					for (int a = 0; a < Pipe.length; a++) {
						for (int i = 0; i < Pipe[0].length; i++) {
							Pipe[a][i].movePlatform(-global.horizVelocity, 0);
						}
					}
					for (int i = 0; i < Goomba.length; i++) {
						Goomba[i].moveGoomba(-global.horizVelocity, 0);
					}
					moveMario(0, global.vertVelocity);
				} else
					moveMario(global.horizVelocity, global.vertVelocity);
			}
		} else
			moveMario(global.horizVelocity, global.vertVelocity);

		for (int i = 0; i < Goomba.length; i++) {
			if (Goomba[i].getGoombaImg().getX() < 850) {
				Goomba[i].setMoving();
			}
			if (Goomba[i].getGoombaImg().getX() < -50) {
				Goomba[i].DeleteGoomba();
			}
			if (Goomba[i].getMoving() > 0)
				if (!Goomba[i].getGoombaDead()) {
					Goomba[i].moveGoomba(0, global.Vert_MAX_Velocity);
					if (Goomba[i].getGoombaDirection() % 2 == 0) {
						Goomba[i].moveGoomba(1, 0);
					} else {
						Goomba[i].moveGoomba(-1, 0);
					}
					for (int a = 0; a < platform.length; a++) {
						Goomba[i].collisionGoomba(platform[a]);
					}
					Goomba[i].collisionGoomba(Ground);
					Goomba[i].collisionGoomba(Goomba, i);
					for (int a = 0; a < Pipe.length; a++) {
						Goomba[i].collisionGoomba(Pipe[a]);
					}
					if (TimerCount % 500 == 0)
						Goomba[i].changeGoombaDirection();
					if (Goomba[i].getGoombaCollideSide() > 0)
						Goomba[i].changeGoombaDirection();
					Goomba[i].animateGoomba(TimerCount / 20);
					Goomba[i].resetCollision();
				}
		}

		TimerCount++;
		if (TimerCount % 10 == 0) {
			if (Moving == false) {
				if (global.horizVelocity > 0)
					global.horizVelocity -= global.Friction;
				if (global.horizVelocity < 0)
					global.horizVelocity += global.Friction;
			}
			if (movingLeft == true) {
				if (global.horizVelocity > -global.Horiz_MAX_Velocity)
					global.horizVelocity -= global.walkSpeed;
				if (global.horizVelocity >= 0)
					global.horizVelocity -= global.walkSpeed;
			}
			if (movingRight == true) {
				if (global.horizVelocity < global.Horiz_MAX_Velocity)
					global.horizVelocity += global.walkSpeed;
				if (global.horizVelocity <= 0)
					global.horizVelocity += global.walkSpeed;
			}
		}

		if (TimerCount % 2 == 0)
			if (global.vertVelocity < global.Vert_MAX_Velocity) {
				global.vertVelocity += global.Gravity;
			}
		System.out.print(global.vertVelocity);
	}

	// Makes the Mario to not be able to move when it touches a platform
	public void collision(Platform[] p) {
		for (int i = 0; i < p.length; i++) {
			collideTop = false;
			collideBottom = false;
			collideLeft = false;
			collideRight = false;
			if ((getMariobottom().getBounds()).intersects(p[i].getTop().getBounds())) {
				onground++;

				collideTop = true;
				if (global.vertVelocity > 0)
					global.vertVelocity = 0;
				moveMario(0, p[i].getGround().getY() - Mario.getY() - HEIGHT);
			}
			if ((Mariotop.getBounds()).intersects(p[i].getBottom().getBounds())) {
				collideBottom = true;
				if (global.vertVelocity < 0)
					global.vertVelocity = 0;
				moveMario(0, p[i].getTop().getY() - Mario.getY() + p[i].getGround().getHeight());

			}
			if ((Marioright.getBounds()).intersects(p[i].getLeft().getBounds())) {
				collideLeft = true;
				if (global.horizVelocity > 0)
					global.horizVelocity = 0;
				moveMario(p[i].getGround().getX() - Mario.getX() - Mario.getWidth(), 0);
			}
			if ((Marioleft.getBounds()).intersects(p[i].getRight().getBounds())) {
				collideRight = true;
				if (global.horizVelocity < 0)
					global.horizVelocity = 0;
				moveMario(p[i].getGround().getX() + p[i].getGround().getWidth() - Mario.getX(), 0);
			}
		}
	}

	public void collision(Enemies[] p) {
		for (int i = 0; i < p.length; i++) {
			if (p != null) {
				collideTop = false;
				collideBottom = false;
				collideLeft = false;
				collideRight = false;
				if ((getMariobottom().getBounds()).intersects(p[i].getTop().getBounds())) {
					onground++;

					collideTop = true;
					if (global.vertVelocity > 0)
						global.vertVelocity = 0;
					moveMario(0, p[i].getGoombaImg().getY() - Mario.getY() - HEIGHT);
					global.vertVelocity -= global.jumpSpeed / 2;
					p[i].DeleteGoomba();
				}
				if ((Mariotop.getBounds()).intersects(p[i].getBottom().getBounds())) {
					collideBottom = true;
					if (global.vertVelocity < 0)
						global.vertVelocity = 0;
					moveMario(0, p[i].getTop().getY() - Mario.getY() + p[i].getGoombaImg().getHeight());

				}
				if ((Marioright.getBounds()).intersects(p[i].getLeft().getBounds())) {
					collideLeft = true;
					if (global.horizVelocity > 0)
						global.horizVelocity = 0;
					moveMario(p[i].getGoombaImg().getX() - Mario.getX() - Mario.getWidth(), 0);
				}
				if ((Marioleft.getBounds()).intersects(p[i].getRight().getBounds())) {
					collideRight = true;
					if (global.horizVelocity < 0)
						global.horizVelocity = 0;
					moveMario(p[i].getGoombaImg().getX() + p[i].getGoombaImg().getWidth() - Mario.getX(), 0);
				}
			}
		}
	}

	// Makes the Mario to not be able to move when it touches a pipe
	public void collisionPipe(Platform[] pipe) {
		for (int i = 0; i < pipe.length; i++) {
			if ((getMariobottom().getBounds()).intersects(pipe[i].getTop().getBounds())) {
				onground++;
				collideTop = true;
				if (global.vertVelocity > 0)
					global.vertVelocity = 0;
			} else
				collideTop = false;
			if ((Marioleft.getBounds()).intersects(pipe[i].getRight().getBounds())) {
				collideBottom = true;
				if (global.vertVelocity < 0)
					global.vertVelocity = 0;
			} else
				collideBottom = false;
			if ((Marioright.getBounds()).intersects(pipe[i].getLeft().getBounds())) {
				if (global.horizVelocity > 0)
					global.horizVelocity = 0;
			}
		}

	}

	// activates when the mentioned key are pressed and moves the mario
	@Override
	public void keyPressed(KeyEvent e) {
		Moving = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			remove(MarioImgRight);
			add(MarioImgLeft);
			movingLeft = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			remove(MarioImgLeft);
			add(MarioImgRight);
			movingRight = true;

		} else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
			if (collideTop) {
				if (onground > 0) {
					global.vertVelocity -= global.jumpSpeed;
				}
			}
		}
	}

	// activates when any key is released
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Moving = false;
			movingLeft = false;
			remove(MarioImgRight);
			add(MarioImgLeft);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Moving = false;
			movingRight = false;
			remove(MarioImgLeft);
			add(MarioImgRight);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_SPACE) {
			onground = 0;
		}

	}

	public GRect getMariobottom() {
		return Mariobottom;
	}

	public void setMariobottom(GRect mariobottom) {
		Mariobottom = mariobottom;
	}
	
	public void level1()
	{
		platform = new Platform[50][10];
		for (int a = 0; a < platform.length; a++)
			for (int i = 0; i < platform[0].length; i++) {
				platform[a][i] = new Platform();
			}
		Ground = new Platform[10];
		for (int i = 0; i < Ground.length; i++) {
			Ground[i] = new Platform();

		}

		Pipe = new Platform[50][10];
		for (int a = 0; a < Pipe.length; a++)
			for (int i = 0; i < Pipe[0].length; i++) {
				Pipe[a][i] = new Platform();
			}
		platform[0][0].InitilizePlatform(400,400,50,50,3);
		platform[0][1].InitilizePlatform(600,400,50,50,3);
		platform[0][2].InitilizePlatform(650,400,50,50,3);
		platform[0][3].InitilizePlatform(700,400,50,50,3);
		platform[0][4].InitilizePlatform(750,400,50,50,3);
		platform[0][5].InitilizePlatform(800,400,50,50,3);
		platform[0][6].InitilizePlatform(850,400,50,50,3);
		platform[0][7].InitilizePlatform(700,200,50,50,3);
		
		platform[1][0].InitilizePlatform(3400,400,50,50,3);
		platform[1][1].InitilizePlatform(3500,200,50,50,3);
		platform[1][2].InitilizePlatform(3550,200,50,50,3);
		platform[1][3].InitilizePlatform(3600,200,50,50,3);
		platform[1][4].InitilizePlatform(3650,200,50,50,3);
		platform[1][5].InitilizePlatform(3700,200,50,50,3);
		platform[1][6].InitilizePlatform(3750,200,50,50,3);
		platform[1][7].InitilizePlatform(3800,200,50,50,3);
		
		platform[2][0].InitilizePlatform(4000,200,50,50,3);
		platform[2][1].InitilizePlatform(4100,200,50,50,3);
		platform[2][2].InitilizePlatform(4150,200,50,50,3);
		platform[2][3].InitilizePlatform(4200,200,50,50,3);
		platform[2][4].InitilizePlatform(4250,200,50,50,3);
		platform[2][5].InitilizePlatform(4250,400,50,50,3);
		
		platform[3][0].InitilizePlatform(4800,400,50,50,3);
		platform[3][1].InitilizePlatform(4850,400,50,50,3);
		
		platform[4][0].InitilizePlatform(5400,400,50,50,3);
		platform[4][1].InitilizePlatform(5600,400,50,50,3);
		platform[4][2].InitilizePlatform(5800,400,50,50,3);
		
		
		platform[5][0].InitilizePlatform(6200,200,50,50,3);
		platform[5][1].InitilizePlatform(6250,200,50,50,3);
		platform[5][2].InitilizePlatform(6300,200,50,50,3);
		platform[5][3].InitilizePlatform(6350,200,50,50,3);
		
		platform[5][4].InitilizePlatform(6600,200,50,50,3);
		platform[5][5].InitilizePlatform(6650,200,50,50,3);
		platform[5][6].InitilizePlatform(6700,200,50,50,3);
		platform[5][7].InitilizePlatform(6750,200,50,50,3);
		
		platform[5][8].InitilizePlatform(6650,400,50,50,3);
		platform[5][9].InitilizePlatform(6700,400,50,50,3);
		
		platform[6][0].InitilizePlatform(6700,400,50,50,3);
		platform[6][1].InitilizePlatform(6700,400,50,50,3);
		platform[6][2].InitilizePlatform(7300,400,50,50,3);
		platform[6][3].InitilizePlatform(7350,400,50,50,3);
		
		platform[7][0].InitilizePlatform(8000,550,50,50,3);
		platform[7][1].InitilizePlatform(8050,500,50,50,3);
		platform[7][2].InitilizePlatform(8100,450,50,50,3);
		platform[7][3].InitilizePlatform(8150,400,50,50,3);
		platform[7][4].InitilizePlatform(8200,350,50,50,3);
		platform[7][5].InitilizePlatform(8250,300,50,50,3);
		platform[7][6].InitilizePlatform(8300,250,50,50,3);
		platform[7][7].InitilizePlatform(8350,250,50,50,3);
		
		Ground[0].InitilizePlatform(0, 600,2800, 200, 3);
		Ground[1].InitilizePlatform(3000, 600,800, 200, 3);
		Ground[2].InitilizePlatform(4000, 600,7000, 200, 3);
		
		Pipe[0][0].InitilizePlatform(1000, 540, 60, 60, 3);
		Pipe[0][1].InitilizePlatform(1400, 520, 60, 80, 3);
		Pipe[0][2].InitilizePlatform(1800, 500, 60, 100, 3);
		Pipe[0][3].InitilizePlatform(2200, 480, 60, 120, 3);
		Pipe[0][4].InitilizePlatform(7100, 540, 60, 60, 3);
		Pipe[0][5].InitilizePlatform(7900, 540, 60, 60, 3);
		
		for (int a = 0; a < platform.length; a++) {
			for (int i = 0; i < platform[0].length; i++) {
				platform[a][i].getGround().setColor(new Color(212, 212, 212));
				add(platform[a][i].getGround());

			}
		}

		for (int i = 0; i < Ground.length; i++) {
			Ground[i].getGround().setColor(new Color(212, 212, 212));
			add(Ground[i].getGround());

		}

		for (int a = 0; a < Pipe.length; a++) {
			for (int i = 0; i < Pipe[0].length; i++) {

				Pipe[a][i].getGround().setColor(new Color(212, 212, 212));
				add(Pipe[a][i].getGround());


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

