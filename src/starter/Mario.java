
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
	Platform[][] platform;
	Platform[] Ground;

	Platform[][] Pipe;

	private static final double Vert_MAX_Velocity = 15, Horiz_MAX_Velocity = 5, walkSpeed = 1, Friction = 1,
			jumpSpeed = 15, Gravity = 1;
	private static final int XAXIS = 100, YAXIS = 600, WIDTH = 50, HEIGHT = 50, THICKNESS = 3;
	private int vertVelocity = 0, horizVelocity = 0;

	int TimerCount = 0, onground = 0;
	boolean Moving = false, movingLeft = false, movingRight = false;
	private static final int PROGRAM_WIDTH = 850;
	private static final int PROGRAM_HEIGHT = 650;

	public GRect Mario;

	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	private GRect Mariotop, Mariobottom, Marioleft, Marioright;

	public Mario() {
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
		Mariotop.move(x, y);
		Mariobottom.move(x, y);
		Marioright.move(x, y);
		Marioleft.move(x, y);
	}

	public boolean collideRight, collideLeft, collideTop, collideBottom;
	public boolean collision, Right, Left, Top, Bottom;

	public void run() {
		InitilizeMario(XAXIS, YAXIS, WIDTH, HEIGHT, THICKNESS);
		add(Mario);
		add(Mariotop);
		add(Mariobottom);
		add(Marioright);
		add(Marioleft);

		platform = new Platform[50][5];
		for (int a = 0; a < platform.length; a++)
			for (int i = 0; i < platform[0].length; i++) {
				platform[a][i] = new Platform();
			}

		Ground = new Platform[10];
		for (int i = 0; i < Ground.length; i++) {
			Ground[i] = new Platform();

		}

		Pipe = new Platform[50][5];
		for (int a = 0; a < Pipe.length; a++)
			for (int i = 0; i < Pipe[0].length; i++) {
				Pipe[a][i] = new Platform();
			}

		for (int a = 0; a < platform.length; a++) {
			for (int i = 0; i < platform[0].length; i++) {
				platform[a][i].InitilizePlatform(a * 200 + 400 + i * 50, 400 - a % 3 * 200, 50, 50, 3);

			}
		}

		for (int i = 0; i < Ground.length; i++) {
			Ground[i].InitilizePlatform(i * 200, 600, 800, 200, 3);
		}

		for (int a = 0; a < Pipe.length; a++) {
			for (int i = 0; i < Pipe[0].length; i++) {
				Pipe[a][i].InitilizePlatform(200, 500, 120, 100, 3);
			}
		}

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

		for (int i = 0; i < Ground.length; i++) {
			Ground[i].getGround().setColor(new Color(212, 212, 212));
			add(Ground[i].getGround());
			add(Ground[i].getBottom());
			add(Ground[i].getTop());
			add(Ground[i].getRight());
			add(Ground[i].getLeft());
		}

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

				horizVelocity = 0;
				moveMario(.1, 0);

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
					for (int a = 0; a < Pipe.length; a++) {
						for (int i = 0; i < Pipe[0].length; i++) {
							Pipe[a][i].movePlatform(-horizVelocity, 0);
						}
					}
					moveMario(0, vertVelocity);
				} else
					moveMario(horizVelocity, vertVelocity);
			}
		} else
			moveMario(horizVelocity, vertVelocity);

		TimerCount++;
		if (TimerCount % 10 == 0) {
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
		System.out.print(vertVelocity);
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
					moveMario(p[i].getGround().getX() + p[i].getGround().getWidth() - Mario.getX(), 0);
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
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			movingRight = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			movingLeft = true;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
			if (collideTop) {
				vertVelocity -= jumpSpeed;
			}
		}
	}

	// activates when any key is released
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			Moving = false;
			movingRight = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			Moving = false;
			movingLeft = false;

		}

	}
}
