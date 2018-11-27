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
	//	private Platform[][] platform;
	//	private Platform[] Ground;
	public Level level = new Level();
	//	private Platform[][] Pipe;
	private Enemies[] Goomba;
	private GImage plat;
	private GImage pipe;

	//Timer
	
	private GLabel score = new GLabel("score");
	

	private GRect gap = new GRect(2800, 599, 200, 200);
	private GRect gap1 = new GRect(3800, 599, 200, 200);
	private GRect gap2 = new GRect(8350, 599, 600, 200);
	private GRect mortApple = new GRect(2000,550,100,100);
	public static final String MUSIC_FOLDER = "sounds";
	private static final String JUMP_SOUND ="jump.mp3";
	private static final String GAME_SOUND ="mario-game.mp3";
	private static final String IMMORTALITY_SOUND ="mario-immortality.mp3";
	private static final String GAME_OVER_SOUND ="mario-gameover.mp3";
	//	String picture = "hello";
	//	private GImage imageIn = new GImage(picture,0,0);
	//	private GImage imageDel = new GImage(picture,0,0);
	powerUps power = new powerUps();
	ArrayList<GImage> Platimg = new ArrayList<GImage>();
	ArrayList<GImage> Pipeimg = new ArrayList<GImage>();
	Timer t = new Timer(10, this);


	//To start immortal mode inside of the game press shift + N and to turn off press shift + F


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

	public void marioDied()
	{
		System.exit(0);
	}


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

	private void playJumpSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, JUMP_SOUND);
	}

	private void playGameSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, GAME_SOUND);
	}

	private void playImmortalitySound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, IMMORTALITY_SOUND);
	}

	public void run() {

		InitilizeMario(global.XAXIS, global.YAXIS, WIDTH, HEIGHT, global.THICKNESS);



		marioGraphics();


		level.level1();

		levelSpawn();

		gumbaSpawn();
		playGameSound();
		gap.setColor(Color.BLACK);
		gap.setFillColor(Color.BLACK);
		gap.setFilled(true);

		gap1.setColor(Color.BLACK);
		gap1.setFillColor(Color.BLACK);
		gap1.setFilled(true);

		gap2.setColor(Color.BLACK);
		gap2.setFillColor(Color.BLACK);
		gap2.setFilled(true);
		//immortality apple graphics (not done)
		mortApple.setColor(Color.RED);
		mortApple.setFillColor(Color.red);
		mortApple.setFilled(true);



		add(level.flagImage);
		add(level.castleImage);

		add(gap);
		add(gap1);
		add(gap2);

		

		t.start();
		
		addKeyListeners();
	}
 

	public void imageIn(GImage imageIn)
	{
		t.stop();
		imageIn.setSize(850, 650);
		add(imageIn);
	}

	public void imageDel(GImage imageDel)
	{
		remove(imageDel);
		t.start();
	}
	//	
	//	public void setPic(String setPicture)
	//	{
	//		picture = setPicture;
	//	}



	// is called after every milisecond and moves the mario and the platform
	@Override
	public void actionPerformed(ActionEvent e) {

		if(Mario.getY()>650)
		{
			marioDied();
		}
		
		for (int a = 0; a < level.levelPlatform.length; a++) {
			collision(level.levelPlatform[a]);	
		}


		collision(Goomba);

		collision(level.Ground);
		////
		if(Mario.getX()>=level.levelPlatform[0][51].getTop().getX())  {


			moveMario(0,2);
			if(Mario.getY()==551 && Mario.getX()<= level.levelPlatform[8][0].getTop().getX()+250) {
				moveMario(2,0);

			}

			return;
		}
		for (int a = 0; a < level.Pipe.length; a++) {
			collision(level.Pipe[a]);

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
					for (int a = 0; a < level.levelPlatform.length; a++) {
						for (int i = 0; i < level.levelPlatform[0].length; i++) {
							level.levelPlatform[a][i].movePlatform(-global.horizVelocity, 0);
						}
					}
					gap.move(-global.horizVelocity, 0);
					gap1.move(-global.horizVelocity, 0);
					gap2.move(-global.horizVelocity, 0);
					for(int i = 0; i < Platimg.size(); i++)
					{
						Platimg.get(i).move(-global.horizVelocity, 0);
					}
					for (int i = 0; i < level.Ground.length; i++) {
						level.Ground[i].movePlatform(-global.horizVelocity, 0);
					}
					for (int a = 0; a < level.Pipe.length; a++) {
						for (int i = 0; i < level.Pipe[0].length; i++) {
							level.Pipe[a][i].movePlatform(-global.horizVelocity, 0);
						}
					}
					for(int i = 0; i < Pipeimg.size(); i++)
					{
						Pipeimg.get(i).move(-global.horizVelocity, 0);
					}
					for (int i = 0; i < Goomba.length; i++) {
						Goomba[i].moveGoomba(-global.horizVelocity, 0);
					}

					level.flagImage.move(-(global.horizVelocity), 0);
					level.castleImage.move(-(global.horizVelocity), 0);


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
					for (int a = 0; a < level.levelPlatform.length; a++) {
						Goomba[i].collisionGoomba(level.levelPlatform[a]);
					}
					Goomba[i].collisionGoomba(level.Ground);
					Goomba[i].collisionGoomba(Goomba, i);
					for (int a = 0; a < level.Pipe.length; a++) {
						Goomba[i].collisionGoomba(level.Pipe[a]);
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
					if(power.immortal==false)
					{
						marioDied();
					}
					else
					{
						p[i].DeleteGoomba();
					}
				}
				if ((Marioleft.getBounds()).intersects(p[i].getRight().getBounds())) {
					collideRight = true;
					if (global.horizVelocity < 0)
						global.horizVelocity = 0;
					moveMario(p[i].getGoombaImg().getX() + p[i].getGoombaImg().getWidth() - Mario.getX(), 0);
					if(power.immortal==false)
					{
						marioDied();
					}
					else
					{
						p[i].DeleteGoomba();
					}
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
					playJumpSound();
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
		if (e.getKeyCode() == KeyEvent.VK_N)
		{
			playImmortalitySound();
			power.mortOn();
			remove(MarioImgRight);
			remove(MarioImgLeft);
			MarioImgRight = new GImage("LuigiRight.png", Mario.getX(), Mario.getY() - 1);
			MarioImgRight.setSize(50, 57);
			MarioImgLeft = new GImage("LuigiLeft.png", Mario.getX(), Mario.getY() - 1);
			MarioImgLeft.setSize(50, 57);
			add(MarioImgRight);
		}
		if (e.getKeyCode() == KeyEvent.VK_F)
		{
			power.mortOff();
			remove(MarioImgRight);
			remove(MarioImgLeft);
			MarioImgRight = new GImage("MarioRight.png", Mario.getX(), Mario.getY() - 1);
			MarioImgRight.setSize(50, 57);
			MarioImgLeft = new GImage("MarioLeft.png", Mario.getX(), Mario.getY() - 1);
			MarioImgLeft.setSize(50, 57);
			add(MarioImgRight);
		}


	}

	public GRect getMariobottom() {
		return Mariobottom;
	}

	public void setMariobottom(GRect mariobottom) {
		Mariobottom = mariobottom;
	}


	public void gumbaSpawn()
	{
		Goomba = new Enemies[29];
		for (int i = 0; i < Goomba.length; i++) {
			Goomba[i] = new Enemies();
		}

		for (int i = 0; i < Goomba.length; i++) {
			Goomba[i].InitilizeGoomba(i * 400, 600, 50, 50, 3);
			add(Goomba[i].getGoombaImg());
		}

	}

	public void levelSpawn()
	{
		for (int a = 0; a < level.levelPlatform.length; a++) {
			for (int i = 0; i < level.levelPlatform[0].length; i++) {
				level.levelPlatform[a][i].getGround().setColor(new Color(212, 212, 212));
				add(level.levelPlatform[a][i].getGround());
			}
		}

		for(int i = 0; i < 51; i++)
		{
			plat = new GImage("Plat1.png", level.levelPlatform[0][i].getGround().getX(), level.levelPlatform[0][i].getGround().getY());
			plat.setSize(50, 50);
			add(plat);
			Platimg.add(plat);
		}

		for (int i = 0; i < level.Ground.length; i++) {
			level.Ground[i].getGround().setColor(new Color(212, 212, 212));
			add(level.Ground[i].getGround());
		}

		for (int a = 0; a < level.Pipe.length; a++) {
			for (int i = 0; i < level.Pipe[0].length; i++) {

				level.Pipe[a][i].getGround().setColor(new Color(212, 212, 212));
				add(level.Pipe[a][i].getGround());
			}
		}
		pipe = new GImage("pipe.png", level.Pipe[0][0].getGround().getX(), level.Pipe[0][0].getGround().getY());
		pipe.setSize(60, 60);
		add(pipe);
		Pipeimg.add(pipe);

		pipe = new GImage("pipe.png", level.Pipe[0][1].getGround().getX(), level.Pipe[0][1].getGround().getY());
		pipe.setSize(60, 80);
		add(pipe);
		Pipeimg.add(pipe);

		pipe = new GImage("pipe.png", level.Pipe[0][2].getGround().getX(), level.Pipe[0][2].getGround().getY());
		pipe.setSize(60, 100);
		add(pipe);
		Pipeimg.add(pipe);

		pipe = new GImage("pipe.png", level.Pipe[0][3].getGround().getX(), level.Pipe[0][3].getGround().getY());
		pipe.setSize(60, 120);
		add(pipe);
		Pipeimg.add(pipe);

		pipe = new GImage("pipe.png", level.Pipe[0][4].getGround().getX(), level.Pipe[0][4].getGround().getY());
		pipe.setSize(60, 60);
		add(pipe);
		Pipeimg.add(pipe);

		pipe = new GImage("pipe.png", level.Pipe[0][5].getGround().getX(), level.Pipe[0][5].getGround().getY());
		pipe.setSize(60, 60);
		add(pipe);
		Pipeimg.add(pipe);

	}

}