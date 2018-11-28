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
	public Level level = new Level();
	public static final String MUSIC_FOLDER = "sounds";

	private Enemies[] Goomba;
	private GImage plat;
	private GImage pipe;
	private GLabel message = new GLabel("Press N");
	private GRect gap = new GRect(2800, 599, 200, 200);
	private GRect gap1 = new GRect(3800, 599, 200, 200);
	private GRect gap2 = new GRect(8350, 599, 600, 200);
	
	private GImage waterR = new GImage("water.gif",2800,479);
	private GImage waterR1 = new GImage("water.gif",3800,479);
	private GImage waterR2 = new GImage("flames.gif",8350,479);
//	private GImage waterL = new GImage("movingWater.gif",2800,);
	
	private GRect mortApple = new GRect(2000,550,100,100);
	private static final String JUMP_SOUND ="jump.mp3";
	private static final String GAME_SOUND ="mario-game.mp3";
	private static final String IMMORTALITY_SOUND ="r2d2.mp3";
	private static final String GAME_OVER_SOUND ="mario-gameover.mp3";
	public static final String lABEL_FONT = "Arial-Bold-22";
	private int gamescore = 0;
	private int gametime = 0;
	private GLabel GameScore;
	private GLabel GameTime;
	
	

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
	private GImage MarioImgRight, MarioImgLeft, Menu;

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
		GImage ground = new GImage("ground.png", 0, 600);
		ground.setSize(850, 100);
		add(ground);
		waterR.setSize(200,200);
		waterR1.setSize(200,200);
		waterR2.setSize(200,600);
		add(waterR);
		add(waterR1);
		add(waterR2);
		GImage background = new GImage("bg.png", 0, 0);
		background.setSize(850, 600);
		add(background);
		
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
		Menu = new GImage("menu.png", 0, 0);
		Menu.setSize(850, 600);
		add(Menu);
		
	}
	
	public void maingame()
	{
		InitilizeMario(global.XAXIS, global.YAXIS, WIDTH, HEIGHT, global.THICKNESS);
		marioGraphics();
		level.level1();
		levelSpawn();
		gumbaSpawn();
		playGameSound();
		//immortality apple graphics (not done)
		mortApple.setColor(Color.RED);
		mortApple.setFillColor(Color.red);
		mortApple.setFilled(true);
		add(level.flagImage);
		add(level.castleImage);
		add(level.castleImage);
		add(mortApple);
		GLabel score = new GLabel("Score");
		score.setLocation(50, 50);
		score.setFont(lABEL_FONT);
		score.setColor(Color.WHITE);
		add(score);
		GLabel time = new GLabel("Time(ms)");
		time.setLocation(740, 50);
		time.setColor(Color.WHITE);
		time.setFont(lABEL_FONT);
		add(time);
		gameTime();
		gameScore();
		t.start();
		addKeyListeners();
		message.setLocation(3700, 235);
		message.setColor(Color.BLACK);
		add(message);
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
		gametime = gametime + 10;		
		GameTime.setLabel(Integer.toString(gametime));
		if(Mario.getY()>650)
		{
			marioDied();
		}

		for (int a = 0; a < level.levelPlatform.length; a++) {
			collision(level.levelPlatform[a]);	
		}


		collision(Goomba);

		collision(level.levelGround);
		////
		if(Mario.getX()>=level.levelPlatform[0][51].getTop().getX())  {


			moveMario(0,2);
			if(Mario.getY()==551 && Mario.getX()<= level.levelPlatform[8][0].getTop().getX()+250) {
				moveMario(2,0);

			}

			return;
		}
		for (int a = 0; a < level.levelPipe.length; a++) {
			collision(level.levelPipe[a]);

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
					message.move(-global.horizVelocity, 0);
					waterR.move(-global.horizVelocity,0);
					waterR1.move(-global.horizVelocity,0);
					waterR2.move(-global.horizVelocity,0);
					gamescore = gamescore + 1;
					GameScore.setLabel(Integer.toString(gamescore));
					for(int i = 0; i < Platimg.size(); i++)
					{
						Platimg.get(i).move(-global.horizVelocity, 0);
					}
					for (int i = 0; i < level.levelGround.length; i++) {
						level.levelGround[i].movePlatform(-global.horizVelocity, 0);
					}
					for (int a = 0; a < level.levelPipe.length; a++) {
						for (int i = 0; i < level.levelPipe[0].length; i++) {
							level.levelPipe[a][i].movePlatform(-global.horizVelocity, 0);
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
		 else
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
					Goomba[i].collisionGoomba(level.levelGround);
					Goomba[i].collisionGoomba(Goomba, i);
					for (int a = 0; a < level.levelPipe.length; a++) {
						Goomba[i].collisionGoomba(level.levelPipe[a]);
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
	}


	public void gameTime()
	{
		GameTime = new GLabel(Integer.toString(gametime));
		GameTime.setLocation(750, 67);
		GameTime.setFont(lABEL_FONT);
		GameTime.setColor(Color.WHITE);
		add(GameTime);
	}

	public void gameScore()
	{
		GameScore = new GLabel(Integer.toString(gamescore));
		GameScore.setLocation(60, 67);
		GameScore.setFont(lABEL_FONT);
		GameScore.setColor(Color.WHITE);
		add(GameScore);
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
		} else if(e.getKeyCode() == KeyEvent.VK_S) {
			remove(Menu);
			maingame();
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

		for (int i = 0; i < level.levelGround.length; i++) {
			level.levelGround[i].getGround().setColor(new Color(212, 212, 212));
			add(level.levelGround[i].getGround());
		}

		for (int a = 0; a < level.levelPipe.length; a++) {
			for (int i = 0; i < level.levelPipe[0].length; i++) {

				level.levelPipe[a][i].getGround().setColor(new Color(212, 212, 212));
				add(level.levelPipe[a][i].getGround());
			}
		}
		pipe = new GImage("pipe.png", level.levelPipe[0][0].getGround().getX(), level.levelPipe[0][0].getGround().getY());
		pipe.setSize(60, 60);
		add(pipe);
		Pipeimg.add(pipe);

		pipe = new GImage("pipe.png", level.levelPipe[0][1].getGround().getX(), level.levelPipe[0][1].getGround().getY());
		pipe.setSize(60, 80);
		add(pipe);
		Pipeimg.add(pipe);

		pipe = new GImage("pipe.png", level.levelPipe[0][2].getGround().getX(), level.levelPipe[0][2].getGround().getY());
		pipe.setSize(60, 100);
		add(pipe);
		Pipeimg.add(pipe);

		pipe = new GImage("pipe.png", level.levelPipe[0][3].getGround().getX(), level.levelPipe[0][3].getGround().getY());
		pipe.setSize(60, 120);
		add(pipe);
		Pipeimg.add(pipe);

		pipe = new GImage("pipe.png", level.levelPipe[0][4].getGround().getX(), level.levelPipe[0][4].getGround().getY());
		pipe.setSize(60, 60);
		add(pipe);
		Pipeimg.add(pipe);

		pipe = new GImage("pipe.png", level.levelPipe[0][5].getGround().getX(), level.levelPipe[0][5].getGround().getY());
		pipe.setSize(60, 60);
		add(pipe);
		Pipeimg.add(pipe);

	}

}