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
	private Platform[][] platform;
	private Platform[] Ground;
	
	private Platform[][] Pipe;

	
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
	

	public void run()
	{
		level1();
	}



	
	
}



