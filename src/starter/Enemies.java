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


public class Enemies extends GraphicsProgram {
	//some initials for the enemy
	private static final int color= 54;
	private int range = 5;
	private static final int walkSpeed = 2;
	private boolean isDangerous = true;
	private boolean isDead = false;
	
	//Creating enemy
	private GRect enemy;
	//Creating collition squares
	private GRect enemyTop, enemyBottom, enemyLeft, enemyRight;
	
	//Mario set to 0
	private Enemies() {
		enemy = new GRect(0, 0, 0, 0);
		enemyTop = new GRect(0, 0, 0, 0);
		enemyBottom = new GRect(0, 0, 0, 0);
		enemyLeft = new GRect(0, 0, 0, 0);
		enemyRight = new GRect(0, 0, 0, 0);
	}
	//initializing enemy
	public void InitilizeEnemy(int x, int y, int w, int h, int q) {
		enemy.setBounds(x, y, w, h);
		enemyTop.setBounds(x + q, y, w - 2 * q, q);
		enemyBottom.setBounds(x + q, y + h - q, w - 2 * q, q);
		enemyLeft.setBounds(x, y + q, q, h - 2 * q);
		enemyRight.setBounds(x + w - q, y + q, q, h - 2 * q);
	}
	
	//Moving mario
		public void moveEnemy(double x, double y) {
			enemy.move(x, y);
			enemyTop.move(x, y);
			enemyBottom.move(x, y);
			enemyLeft.move(x, y);
			enemyRight.move(x, y);
		}
		public void damage()
		{
			//if mario touches left or right squer then mario dies
		}
		public void enemyDead()
		{
			//if mario touches top squer then enemy dies
		}
		public void enemyFollows()
		{
			//if mario jumps over the enemy it starts to follow mario with increasing speed
		}
		
	

}
