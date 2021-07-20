package starter;

//Fixed Movement

//GitHub Check 2
// Added mario image
//Bugs to fix:
//Bullet should be moving only to the left and in the air, bullet should be moving constantly, immortal mario should kill pillet, normal mario can not kill bullet,
//bullet should not touch any thingand bullet should be of size 70,50
//Bug - make the timer restart when we restart the game so that the values are reset to the defaut values.
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import starter.Platform;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.Timer;

public class Mario extends GraphicsProgram {
<<<<<<< HEAD
	public Level level = new Level();
	public static final String MUSIC_FOLDER = "sounds";

	private Enemies[] Goomba;
	private Enemies[] BB;
	private GImage plat;
	private GImage pipe;
	private GLabel message = new GLabel("Press N");
	private GRect gap = new GRect(2800, 599, 200, 200);
	private GRect gap1 = new GRect(3800, 599, 200, 200);
	private GRect gap2 = new GRect(8350, 599, 600, 200);
	
=======
	private Enemies[] Goomba;

	private Platform[][] platform;
	private Platform[] Ground;
	private Level level;
	private Platform[][] Pipe;

>>>>>>> branch 'master' of https://github.com/comp55-fall18/group-project-cloud9.git

//	private GImage waterR = new GImage("water1.png",2800,479);
//	private GImage waterR1 = new GImage("water1.png",3800,479);
//	private GImage waterR2 = new GImage("water1.png",8350,479);
//	private GImage waterL = new GImage("movingWater.gif",2800,);
	
	private GRect mortApple = new GRect(2000,550,100,100);
	private static final String JUMP_SOUND ="jump.mp3";
	private static final String GAME_SOUND ="mario-game.mp3";
	private static final String IMMORTALITY_SOUND ="mario-immortality.mp3";
	private static final String GAME_OVER_SOUND ="mario-gameover.mp3";
	private int gamescore = 0;
	private int gametime = 0;
	private GLabel GameScore, GameTime;
	private GButton restart = new GButton("RESTART", 100, 100, 200, 50), exit = new GButton("EXIT", 500, 100, 200, 50);
	
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
//		System.exit(0);
		t.stop();
		remove(MarioImgRight);
		remove(MarioImgLeft);
		GImage Gameover = new GImage("gameover.png", 0, 0);
		Gameover.setSize(850, 650);
		add(Gameover);
		add(exit);
		add(restart);
	}


	public void marioGraphics(){
		GImage ground = new GImage("ground.png", 0, 600);
		ground.setSize(850, 100);
		add(ground);
//		waterR.setSize(200,200);
//		waterR1.setSize(200,200);
//		waterR2.setSize(200,600);
//		add(waterR);
//		add(waterR1);
//		add(waterR2);
		GImage background = new GImage("bg.png", 0, 0);
		background.setSize(850, 600);
		add(background);
		
		MarioImgRight = new GImage("MarioRight.png", Mario.getX(), Mario.getY() - 1);
		MarioImgRight.setSize(50, 57);
		MarioImgLeft = new GImage("MarioLeft.png", Mario.getX(), Mario.getY() - 1);
		MarioImgLeft.setSize(50, 57);
		add(MarioImgRight);
<<<<<<< HEAD
=======
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

		platform[0][0].InitilizePlatform(400, 400, 50, 50, 3);

		platform[0][1].InitilizePlatform(600, 400, 50, 50, 3);
		platform[0][2].InitilizePlatform(650, 400, 50, 50, 3);
		platform[0][3].InitilizePlatform(700, 400, 50, 50, 3);
		platform[0][4].InitilizePlatform(750, 400, 50, 50, 3);
		platform[0][5].InitilizePlatform(800, 400, 50, 50, 3);
		platform[0][6].InitilizePlatform(850, 400, 50, 50, 3);
		platform[0][7].InitilizePlatform(700, 200, 50, 50, 3);

		platform[1][0].InitilizePlatform(3400, 400, 50, 50, 3);
		platform[1][1].InitilizePlatform(3500, 200, 50, 50, 3);
		platform[1][2].InitilizePlatform(3550, 200, 50, 50, 3);
		platform[1][3].InitilizePlatform(3600, 200, 50, 50, 3);
		platform[1][4].InitilizePlatform(3650, 200, 50, 50, 3);
		platform[1][5].InitilizePlatform(3700, 200, 50, 50, 3);
		platform[1][6].InitilizePlatform(3750, 200, 50, 50, 3);
		platform[1][7].InitilizePlatform(3800, 200, 50, 50, 3);

		platform[2][0].InitilizePlatform(4000, 200, 50, 50, 3);
		platform[2][1].InitilizePlatform(4100, 200, 50, 50, 3);
		platform[2][2].InitilizePlatform(4150, 200, 50, 50, 3);
		platform[2][3].InitilizePlatform(4200, 200, 50, 50, 3);
		platform[2][4].InitilizePlatform(4250, 200, 50, 50, 3);
		platform[2][5].InitilizePlatform(4250, 400, 50, 50, 3);

		platform[3][0].InitilizePlatform(4800, 400, 50, 50, 3);
		platform[3][1].InitilizePlatform(4850, 400, 50, 50, 3);

		platform[4][0].InitilizePlatform(5400, 400, 50, 50, 3);
		platform[4][1].InitilizePlatform(5600, 400, 50, 50, 3);
		platform[4][2].InitilizePlatform(5800, 400, 50, 50, 3);

		platform[5][0].InitilizePlatform(6200, 200, 50, 50, 3);
		platform[5][1].InitilizePlatform(6250, 200, 50, 50, 3);
		platform[5][2].InitilizePlatform(6300, 200, 50, 50, 3);
		platform[5][3].InitilizePlatform(6350, 200, 50, 50, 3);

		platform[5][4].InitilizePlatform(6600, 200, 50, 50, 3);
		platform[5][5].InitilizePlatform(6650, 200, 50, 50, 3);
		platform[5][6].InitilizePlatform(6700, 200, 50, 50, 3);
		platform[5][7].InitilizePlatform(6750, 200, 50, 50, 3);

		platform[5][8].InitilizePlatform(6650, 400, 50, 50, 3);
		platform[5][9].InitilizePlatform(6700, 400, 50, 50, 3);

		platform[6][0].InitilizePlatform(6700, 400, 50, 50, 3);
		platform[6][1].InitilizePlatform(6700, 400, 50, 50, 3);
		platform[6][2].InitilizePlatform(7300, 400, 50, 50, 3);
		platform[6][3].InitilizePlatform(7350, 400, 50, 50, 3);

		platform[7][0].InitilizePlatform(8000, 550, 50, 50, 3);
		platform[7][1].InitilizePlatform(8050, 500, 50, 50, 3);
		platform[7][2].InitilizePlatform(8100, 450, 50, 50, 3);
		platform[7][3].InitilizePlatform(8150, 400, 50, 50, 3);
		platform[7][4].InitilizePlatform(8200, 350, 50, 50, 3);
		platform[7][5].InitilizePlatform(8250, 300, 50, 50, 3);
		platform[7][6].InitilizePlatform(8300, 250, 50, 50, 3);
		platform[7][7].InitilizePlatform(8350, 250, 50, 50, 3);

		for (int i = 0; i < Ground.length; i++) {
			// Ground[i].InitilizePlatform(i*200, 600,1800, 200, 3);
		}

		Ground[0].InitilizePlatform(0, 600, 8000, 200, 3);
//		Ground[1].InitilizePlatform(3000, 600,800, 200, 3);
//		Ground[2].InitilizePlatform(4000, 600,7000, 200, 3);

		for (int a = 0; a < Pipe.length; a++) {
			for (int i = 0; i < Pipe[0].length; i++) {

				// Pipe[a][i].InitilizePlatform(a*1000, 500, 120, 100, 3);
			}
		}

		Pipe[0][0].InitilizePlatform(1000, 540, 60, 60, 3);
		Pipe[0][1].InitilizePlatform(1400, 520, 60, 80, 3);
		Pipe[0][2].InitilizePlatform(1800, 500, 60, 100, 3);
		Pipe[0][3].InitilizePlatform(2200, 480, 60, 120, 3);
		Pipe[0][4].InitilizePlatform(7100, 540, 60, 60, 3);
		Pipe[0][5].InitilizePlatform(7900, 540, 60, 60, 3);

		for (int a = 0; a < platform.length; a++) {
			for (int i = 0; i < platform[0].length; i++) {
//				platform[a][i].InitilizePlatform(a * 200 + 400 + i * 50, 400 - a % 3 * 200, 50, 50, 3);

			}
		}

//		for (int i = 0; i < Ground.length; i++) {
//			Ground[i].InitilizePlatform(i * 200, 600, 800, 200, 3);
//		}
//
//		for (int a = 0; a < Pipe.length; a++) {
//			for (int i = 0; i < Pipe[0].length; i++) {
//				Pipe[a][i].InitilizePlatform(200, 500, 120, 100, 3);
//			}
//		}

		for (int a = 0; a < platform.length; a++) {
			for (int i = 0; i < platform[0].length; i++) {
				platform[a][i].getGround().setColor(new Color(212, 212, 212));
				add(platform[a][i].getGround());
//				add(platform[a][i].getBottom());
//				add(platform[a][i].getTop());
//				add(platform[a][i].getRight());
//				add(platform[a][i].getLeft());
			}
		}

		for (int i = 0; i < Ground.length; i++) {
			Ground[i].getGround().setColor(new Color(212, 212, 212));
			add(Ground[i].getGround());
//			add(Ground[i].getBottom());
//			add(Ground[i].getTop());
//			add(Ground[i].getRight());
//			add(Ground[i].getLeft());
		}

		for (int a = 0; a < Pipe.length; a++) {
			for (int i = 0; i < Pipe[0].length; i++) {

				Pipe[a][i].getGround().setColor(new Color(212, 212, 212));
				add(Pipe[a][i].getGround());
//				add(Pipe[a][i].getBottom());
//				add(Pipe[a][i].getTop());
//				add(Pipe[a][i].getRight());
//				add(Pipe[a][i].getLeft());

			}
		}

		Goomba = new Enemies[30];
		for (int i = 0; i < Goomba.length; i++) {
			Goomba[i] = new Enemies();
		}

		for (int i = 0; i < Goomba.length; i++) {
			Goomba[i].InitilizeGoomba(i * 400, 0, 50, 50, 3);
			add(Goomba[i].getGoombaImg());
		}

		Timer t = new Timer(10, this);
		t.start();
		addKeyListeners();
>>>>>>> branch 'master' of https://github.com/comp55-fall18/group-project-cloud9.git
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
		Menu.setSize(850, 650);
		add(Menu);
//		InitilizeMario(global.XAXIS, global.YAXIS, WIDTH, HEIGHT, global.THICKNESS);
//
//
//
//		marioGraphics();
//
//
//		level.level1();
//
//		levelSpawn();
//
//		gumbaSpawn();
//		playGameSound();
//		
//		//immortality apple graphics (not done)
//		mortApple.setColor(Color.RED);
//		mortApple.setFillColor(Color.red);
//		mortApple.setFilled(true);
//		
//		
//		
//		gap.setColor(Color.BLACK);
//		gap1.setColor(Color.BLACK);
//		gap2.setColor(Color.BLACK);
//		gap.setFilled(true);
//		gap1.setFilled(true);
//		gap2.setFilled(true);
//
//		add(level.flagImage);
//		add(level.castleImage);
//		
//
//		
//		
//		add(mortApple);
//		add(gap);
//		add(gap1);
//		add(gap2);
//
//		GLabel score = new GLabel("Score");
//		score.setLocation(50, 50);
//		add(score);
//		GLabel time = new GLabel("Time(ms)");
//		time.setLocation(740, 50);
//		add(time);
//		gameTime();
//		gameScore();
		
//		
		addKeyListeners();
		addMouseListeners();
//		
//
//			message.setLocation(3700, 235);
//			message.setColor(Color.BLACK);
//			add(message);
	}
	
	public void MainGame()
	{
		InitilizeMario(global.XAXIS, global.YAXIS, WIDTH, HEIGHT, global.THICKNESS);



		marioGraphics();


		level.level1();

		levelSpawn();

		gumbaSpawn();
//		BBSpawn();
		playGameSound();
		
		//immortality apple graphics (not done)
		mortApple.setColor(Color.RED);
		mortApple.setFillColor(Color.red);
		mortApple.setFilled(true);
		
		
		
		gap.setColor(Color.BLACK);
		gap1.setColor(Color.BLACK);
		gap2.setColor(Color.BLACK);
		gap.setFilled(true);
		gap1.setFilled(true);
		gap2.setFilled(true);

		add(level.flagImage);
		add(level.castleImage);
		

		
		
		add(mortApple);
		add(gap);
		add(gap1);
		add(gap2);

		GLabel score = new GLabel("Score");
		score.setLocation(50, 50);
		add(score);
		GLabel time = new GLabel("Time(ms)");
		time.setLocation(740, 50);
		add(time);
		gameTime();
		gameScore();
		t.start();
		
//		addKeyListeners();
		

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
//		int count = 1;
//		if(count == 5)
//		{
//			waterR.setImage("water1.png");
//			waterR1.setImage("water1.png");
//			waterR2.setImage("water1.png");
//		}
//		count++;
//		if (count == 50)
//		{
//			waterR.setImage("water2.png");
//			waterR1.setImage("water2.png");
//			waterR2.setImage("water2.png");
//		}
//		if(count == 100)
//		{
//			waterR.setImage("water3.png");
//			waterR1.setImage("water3.png");
//			waterR2.setImage("water3.png");
//		}
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
		collisionBB(BB);

		collision(level.levelGround);
		////
		if(Mario.getX()>=level.levelPlatform[0][51].getTop().getX())  {


			moveMario(0,2);
			System.out.println(Mario.getY());
			if(Mario.getY()==552 && Mario.getX()<= level.levelPlatform[0][51].getTop().getX()+250) {
				moveMario(2,0);
				if(Mario.getX() == level.levelPlatform[0][51].getTop().getX()+250)
				{
					remove(MarioImgRight);
					remove(MarioImgLeft);
					marioDied();
				}
				
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
					gap.move(-global.horizVelocity, 0);
					gap1.move(-global.horizVelocity, 0);
					gap2.move(-global.horizVelocity, 0);
					message.move(-global.horizVelocity, 0);

//					waterR.move(-global.horizVelocity,0);
//					waterR1.move(-global.horizVelocity,0);
//					waterR2.move(-global.horizVelocity,0);
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
					
					for (int i = 0; i < BB.length; i++) {
						BB[i].moveBB(-1.5*global.horizVelocity, 0);
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
					Goomba[i].collisionGoomba(level.levelGround);
					Goomba[i].collisionGoomba(Goomba, i);
					for (int a = 0; a < level.levelPipe.length; a++) {
						Goomba[i].collisionGoomba(level.levelPipe[a]);
					}
					if (TimerCount % 500 == 0)
						Goomba[i].changeGoombaDirection();
					if (Goomba[i].getGoombaCollideSide() > 0)
						Goomba[i].changeGoombaDirection();
//					Goomba[i].animateGoomba(TimerCount / 20);
					Goomba[i].resetCollision();
				}
		}
		
//		for (int i = 0; i < BB.length; i++) {
//			if (BB[i].getBBImg().getX() < 850) {
//				BB[i].setMoving();
//			}
//			if (BB[i].getBBImg().getX() < -50) {
//				BB[i].DeleteBB();
//			}
//			if (BB[i].getMoving() > 0)
//				if (!BB[i].getBBDead()) {
//					BB[i].moveBB(0, global.Vert_MAX_Velocity);
//					if (BB[i].getBBDirection() % 2 == 0) {
//						BB[i].moveBB(-2, 0);
//					} else {
//						BB[i].moveBB(-2, 0);
//					}
//					for (int a = 0; a < level.levelPlatform.length; a++) {
//						BB[i].collisionBB(level.levelPlatform[a]);
//					}
//					BB[i].collisionBB(level.levelGround);
//					BB[i].collisionBB(BB, i);
//					for (int a = 0; a < level.levelPipe.length; a++) {
//						BB[i].collisionBB(level.levelPipe[a]);
//					}
//					BB[i].animateBB(TimerCount / 20);
//					BB[i].resetCollision();
//				}
//		}

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

		
	public void gameTime()
	{
		GameTime = new GLabel(Integer.toString(gametime));
		GameTime.setLocation(750, 63);
		add(GameTime);
	}
	
	public void gameScore()
	{
		GameScore = new GLabel(Integer.toString(gamescore));
		GameScore.setLocation(60, 63);
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
		
	public void collisionBB(Enemies[] p1) {
		for (int i = 0; i < p1.length; i++) {		
			if (p1 != null) {
				collideTop = false;
				collideBottom = false;
				collideLeft = false;
				collideRight = false;
				if ((getMariobottom().getBounds()).intersects(p1[i].getTop().getBounds())) {
					onground++;

					collideTop = true;
					if (global.vertVelocity > 0)
						global.vertVelocity = 0;
//					moveMario(0, p1[i].getBBImg().getY() - Mario.getY() - HEIGHT);
					global.vertVelocity -= global.jumpSpeed / 2;
					p1[i].DeleteBB();
				}
				if ((Mariotop.getBounds()).intersects(p1[i].getBottom().getBounds())) {
					collideBottom = true;
					if (global.vertVelocity < 0)
						global.vertVelocity = 0;
//					moveMario(0, p1[i].getTop().getY() - Mario.getY() + p1[i].getBBImg().getHeight());

				}
				if ((Marioright.getBounds()).intersects(p1[i].getLeft().getBounds())) {
					collideLeft = true;
					if (global.horizVelocity > 0)
						global.horizVelocity = 0;
//					moveMario(p1[i].getBBImg().getX() - Mario.getX() - Mario.getWidth(), 0);
					if(power.immortal==false)
					{
						marioDied();
					}
					else
					{
						p1[i].DeleteBB();
					}
				}
				if ((Marioleft.getBounds()).intersects(p1[i].getRight().getBounds())) {
					collideRight = true;
					if (global.horizVelocity < 0)
						global.horizVelocity = 0;
//					moveMario(p1[i].getBBImg().getX() + p1[i].getBBImg().getWidth() - Mario.getX(), 0);
					if(power.immortal==false)
					{
						marioDied();
					}
					else
					{
						p1[i].DeleteBB();
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
			MainGame();
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
	
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		GObject obj = getElementAt(e.getX(), e.getY());
		if(obj == exit)
		{
			System.exit(0);
		}
		if(obj == restart)
		{
			run();
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
	
//	public void BBSpawn()
//	{
//		BB = new Enemies[15];
//		for (int i = 0; i < BB.length; i++) {
//			BB[i] = new Enemies();
//		}
//
//		for (int i = 0; i < BB.length; i++) {
//			BB[i].InitilizeBB(i * 500, 600, 80, 50, 3);
//			add(BB[i].getBBImg());
//		}
//
//	}

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