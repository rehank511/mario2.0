package starter;

//Fixed Movement
//GitHub Check 2
// Added mario image
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

public class CustomMario extends GraphicsProgram {
	Platform[][] platform;
	Platform[] Ground;

	Platform[][] Pipe;
	Enemies[] Goomba;

	GCanvas Canvas;
	String[][][] Map;
	LevelCreator L = new LevelCreator();
	private static final double Vert_MAX_Velocity = 15, Horiz_MAX_Velocity = 5, walkSpeed = 1, Friction = 1,
			jumpSpeed = 13, Gravity = 1;
	private int XAXIS = 100, YAXIS = -600, WIDTH = 50, HEIGHT = 50, THICKNESS = 3;
	private int vertVelocity = 0, horizVelocity = 0;

	int TimerCount = 0, onground = 0;
	boolean Moving = false, movingLeft = false, movingRight = false;
	private static final int PROGRAM_WIDTH = 850;
	private static final int PROGRAM_HEIGHT = 650;

	private GRect Mario;
	private GImage MarioImgRight, MarioImgLeft;

	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	private GRect Mariotop, Mariobottom, Marioleft, Marioright;

	public CustomMario() {
		Mario = new GRect(0, 0, 0, 0);
		Mariotop = new GRect(0, 0, 0, 0);
		Mariobottom = new GRect(0, 0, 0, 0);
		Marioleft = new GRect(0, 0, 0, 0);
		Marioright = new GRect(0, 0, 0, 0);
	}

	// jumps
	public void InitilizeMario(int x, int y, int w, int h, int q) {
		Mario.setBounds(x, y, w, h);
		Mariotop.setBounds(x + q, y, w - 2 * q, q);
		Mariobottom.setBounds(x + q, y + h - q, w - 2 * q, q);
		Marioleft.setBounds(x, y + q, q, h - 2 * q);
		Marioright.setBounds(x + w - q, y + q, q, h - 2 * q);
	}

	public void moveMario(double x, double y) {
		Mario.move(x, y);
		MarioImgRight.move(x, y);
		MarioImgLeft.move(x, y);
		Mariotop.move(x, y);
		Mariobottom.move(x, y);
		Marioright.move(x, y);
		Marioleft.move(x, y);
	}

	public boolean collideRight, collideLeft, collideTop, collideBottom;
	public boolean collision, Right, Left, Top, Bottom;

	public void run() {

		InitilizeMario(XAXIS, YAXIS, WIDTH, HEIGHT, THICKNESS);
		GImage background = new GImage("bg.png", 0, 0);
		background.setSize(850, 700);
		add(background);
		GImage ground = new GImage("ground.png", 0, 600);
		ground.setSize(850, 100);
		// add(ground);
		MarioImgRight = new GImage("MarioRight.png", Mario.getX(), Mario.getY() - 1);
		MarioImgRight.setSize(50, 57);
		MarioImgLeft = new GImage("MarioLeft.png", Mario.getX(), Mario.getY() + 1);
		MarioImgLeft.setSize(47, 52);
		add(MarioImgRight);
		
		Map = L.getLevels();
		L.openFile();
		L.readFile(Map);
		L.closeFile();

		Platform[] p = L.LoadLevel(0);
		platform = new Platform[p.length][1];

		for (int a = 0; a < platform.length; a++)
			for (int i = 0; i < platform[0].length; i++) {
				platform[a][i] = p[a];
			}

		for (int a = 0; a < platform.length; a++) {
			for (int i = 0; i < platform[0].length; i++) {
				add(platform[a][i].getPlatImg());
			}
		}

		Ground = L.LoadLevelGround(0);

		for (int i = 0; i < Ground.length; i++) {
			add(Ground[i].getPlatImg());
		}

		Goomba = L.LoadLevelGoomba(0);

		for (int i = 0; i < Goomba.length; i++) {
			add(Goomba[i].getGoombaImg());
			add(Goomba[i].getGoombaImgRight());
			add(Goomba[i].getGoombaImgLeft());
		}

		Timer t = new Timer(10, this);
		t.start();
		addKeyListeners();
	}

	int level = 1;

	// is called after every milisecond and moves the mario and the platform
	@Override
	public void actionPerformed(ActionEvent e) {
		if (Mario.getY() > 725) {
			for (int a = 0; a < platform.length; a++) {
				for (int i = 0; i < platform[0].length; i++) {
					remove(platform[a][i].getPlatImg());
				}
			}
			for (int i = 0; i < Ground.length; i++) {
				remove(Ground[i].getPlatImg());
			}
			for (int i = 0; i < Goomba.length; i++) {
				remove(Goomba[i].getGoombaImg());
				remove(Goomba[i].getGoombaImgRight());
				remove(Goomba[i].getGoombaImgLeft());
			}
			moveMario(100 - Mario.getX(), -850);

			Platform[] p = L.LoadLevel(level%(L.levelCount()));
			platform = new Platform[p.length][1];
			for (int a = 0; a < platform.length; a++)
				for (int i = 0; i < platform[0].length; i++) {
					platform[a][i] = p[a];
				}
			for (int a = 0; a < platform.length; a++) {
				for (int i = 0; i < platform[0].length; i++) {
					add(platform[a][i].getPlatImg());
				}
			}
			Ground = L.LoadLevelGround(level%(L.levelCount()));
			for (int i = 0; i < Ground.length; i++) {
				add(Ground[i].getPlatImg());
			}
			Goomba = L.LoadLevelGoomba(level%(L.levelCount()));
			for (int i = 0; i < Goomba.length; i++) {
				add(Goomba[i].getGoombaImg());
			}
			level++;
			System.out.print(level + " " + L.levelCount());
		}

		for (int a = 0; a < platform.length; a++) {
			collision(platform[a]);
		}

		collision(Goomba);
		collision(Ground);
		////
		if (onground > 0)
			collideTop = true;
		onground = 0;

		if (Mario.getX() < 0 || Mario.getX() > 500) {
			// If Mario is on left corner of the screen
			if (Mario.getX() < 0) {
				if (horizVelocity < 0)
					horizVelocity = 0;
				moveMario(1, 0);
				// if Mario is on the right corner on the screen
			} else if (Mario.getX() > 500) {
				if (horizVelocity > 0) {
					// moves the platform ground and pipe to left as Mario moves to the right
					for (int a = 0; a < platform.length; a++) {
						for (int i = 0; i < platform[0].length; i++) {
							platform[a][i].movePlatform(-horizVelocity, 0);

						}
					}
					for (int i = 0; i < Ground.length; i++) {
						Ground[i].movePlatform(-horizVelocity, 0);
					}
					for (int i = 0; i < Goomba.length; i++) {
						Goomba[i].moveGoomba(-horizVelocity, 0);
					}
					moveMario(0, vertVelocity);
				} else
					moveMario(horizVelocity, vertVelocity);
			}
		} else
			moveMario(horizVelocity, vertVelocity);

		for (int i = 0; i < Goomba.length; i++) {
			if (Goomba[i].getGoombaImg().getX() < 850) {
				Goomba[i].setMoving();
			}
			if (Goomba[i].getMoving() > 0)
				if (!Goomba[i].getGoombaDead()) {
					Goomba[i].moveGoomba(0, 5);
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
					if (TimerCount % 500 == 0)
						Goomba[i].changeGoombaDirection();
					if (TimerCount % 10 == 0)
						if (Goomba[i].getGoombaCollideSide() > 0) {
							Goomba[i].changeGoombaDirection();
							Goomba[i].resetCollision();
						}
					if ((TimerCount / 20) % 2 == 0) {
						add(Goomba[i].getGoombaImgRight());
						remove(Goomba[i].getGoombaImgLeft());
					} else {
						add(Goomba[i].getGoombaImgLeft());
						remove(Goomba[i].getGoombaImgRight());
					}
				}
		}

		TimerCount++;
		if (TimerCount % 5 == 0) {
			if (Moving == false) {
				if (horizVelocity > 0)
					horizVelocity -= Friction;
				if (horizVelocity < 0)
					horizVelocity += Friction;
			}
			if (movingLeft == true) {
				if (horizVelocity > -Horiz_MAX_Velocity)
					horizVelocity -= walkSpeed;
				if (horizVelocity >= 0)
					horizVelocity -= walkSpeed;
			}
			if (movingRight == true) {
				if (horizVelocity < Horiz_MAX_Velocity)
					horizVelocity += walkSpeed;
				if (horizVelocity <= 0)
					horizVelocity += walkSpeed;
			}
		}

		if (TimerCount % 2 == 0)
			if (vertVelocity < Vert_MAX_Velocity) {
				vertVelocity += Gravity;
			}
	}

	// Makes the Mario to not be able to move when it touches a platform
	public void collision(Platform[] p) {
		for (int i = 0; i < p.length; i++) {
			collideTop = false;
			collideBottom = false;
			collideLeft = false;
			collideRight = false;
			if ((Mariobottom.getBounds()).intersects(p[i].getTop().getBounds())) {
				onground++;

				collideTop = true;
				if (vertVelocity > 0)
					vertVelocity = 0;
				moveMario(0, p[i].getGround().getY() - Mario.getY() - HEIGHT);
			}
			if ((Mariotop.getBounds()).intersects(p[i].getBottom().getBounds())) {
				collideBottom = true;
				if (vertVelocity < 0)
					vertVelocity = 0;
				moveMario(0, p[i].getTop().getY() - Mario.getY() + p[i].getGround().getHeight());

			}
			if ((Marioright.getBounds()).intersects(p[i].getLeft().getBounds())) {
				collideLeft = true;
				if (horizVelocity > 0)
					horizVelocity = 0;
				moveMario(p[i].getGround().getX() - Mario.getX() - Mario.getWidth(), 0);
			}
			if ((Marioleft.getBounds()).intersects(p[i].getRight().getBounds())) {
				collideRight = true;
				if (horizVelocity < 0)
					horizVelocity = 0;
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
				if ((Mariobottom.getBounds()).intersects(p[i].getTop().getBounds())) {

					collideTop = true;
					if (vertVelocity > 0)
						vertVelocity = 0;
					moveMario(0, p[i].getGoombaImg().getY() - Mario.getY() - HEIGHT);
					vertVelocity -= jumpSpeed / 2;
					p[i].DeleteGoomba();
				}
				if ((Mariotop.getBounds()).intersects(p[i].getBottom().getBounds())) {
					collideBottom = true;
					if (vertVelocity < 0)
						vertVelocity = 0;
					moveMario(0, p[i].getTop().getY() - Mario.getY() + p[i].getGoombaImg().getHeight());
					moveMario(-Mario.getX() + 100, -Mario.getY() - 75);
				}
				if ((Marioright.getBounds()).intersects(p[i].getLeft().getBounds())) {
					collideLeft = true;
					if (horizVelocity > 0)
						horizVelocity = 0;
					moveMario(-Mario.getX() + 100, -Mario.getY() - 75);
				}
				if ((Marioleft.getBounds()).intersects(p[i].getRight().getBounds())) {
					collideRight = true;
					if (horizVelocity < 0)
						horizVelocity = 0;
					moveMario(-Mario.getX() + 100, -Mario.getY() - 75);
				}
			}
		}
	}

	// Makes the Mario to not be able to move when it touches a pipe
	public void collisionPipe(Platform[] pipe) {
		for (int i = 0; i < pipe.length; i++) {
			if ((Mariobottom.getBounds()).intersects(pipe[i].getTop().getBounds())) {
				onground++;
				collideTop = true;
				if (vertVelocity > 0)
					vertVelocity = 0;
			} else
				collideTop = false;
			if ((Marioleft.getBounds()).intersects(pipe[i].getRight().getBounds())) {
				collideBottom = true;
				if (vertVelocity < 0)
					vertVelocity = 0;
			} else
				collideBottom = false;
			if ((Marioright.getBounds()).intersects(pipe[i].getLeft().getBounds())) {
				if (horizVelocity > 0)
					horizVelocity = 0;
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

				vertVelocity -= jumpSpeed;
				onground = 0;

			}
		}
	}

	// activates when any key is released
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Moving = false;
			movingLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Moving = false;
			movingRight = false;
		}

	}
}
