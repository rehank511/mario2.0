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
	public Platform[][] levelPlatform;
	public Platform[] Ground;
	public Platform[][] Pipe;

	GImage flagImage;
	GImage castleImage;
	public void level1()
	{
		GImage background = new GImage("bg.png", 0, 0);
		background.setSize(850, 600);
		add(background);
		
		GImage ground = new GImage("ground.png", 0, 600);
		ground.setSize(850, 100);
		add(ground);
		
		flagImage = new GImage("flag.png",0,0);
		flagImage.setImage("flag.png");
		flagImage.setSize(25,80);
		flagImage.setBounds(8570, 100, 50, 500);


		castleImage = new GImage("castle.png",0,0);
	
		castleImage.setSize(30,30);
		castleImage.setBounds(8725, 350, 250, 250);
	
		
		
		levelPlatform = new Platform[50][10];
		for (int a = 0; a < levelPlatform.length; a++)
			for (int i = 0; i < levelPlatform[0].length; i++) {
				levelPlatform[a][i] = new Platform();
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

		
		levelPlatform[0][0].InitilizePlatform(400,400,50,50,3);
		levelPlatform[0][1].InitilizePlatform(600,400,50,50,3);
		levelPlatform[0][2].InitilizePlatform(650,400,50,50,3);
		levelPlatform[0][3].InitilizePlatform(700,400,50,50,3);
		levelPlatform[0][4].InitilizePlatform(750,400,50,50,3);
		levelPlatform[0][5].InitilizePlatform(800,400,50,50,3);
		levelPlatform[0][6].InitilizePlatform(850,400,50,50,3);
		levelPlatform[0][7].InitilizePlatform(700,200,50,50,3);
		
		levelPlatform[1][0].InitilizePlatform(3400,400,50,50,3);
		levelPlatform[1][1].InitilizePlatform(3500,200,50,50,3);
		levelPlatform[1][2].InitilizePlatform(3550,200,50,50,3);
		levelPlatform[1][3].InitilizePlatform(3600,200,50,50,3);
		levelPlatform[1][4].InitilizePlatform(3650,200,50,50,3);
		levelPlatform[1][5].InitilizePlatform(3700,200,50,50,3);
		levelPlatform[1][6].InitilizePlatform(3750,200,50,50,3);
		levelPlatform[1][7].InitilizePlatform(3800,200,50,50,3);
		
		levelPlatform[2][0].InitilizePlatform(4000,200,50,50,3);
		levelPlatform[2][1].InitilizePlatform(4100,200,50,50,3);
		levelPlatform[2][2].InitilizePlatform(4150,200,50,50,3);
		levelPlatform[2][3].InitilizePlatform(4200,200,50,50,3);
		levelPlatform[2][4].InitilizePlatform(4250,200,50,50,3);
		levelPlatform[2][5].InitilizePlatform(4250,400,50,50,3);
		
		levelPlatform[3][0].InitilizePlatform(4800,400,50,50,3);
		levelPlatform[3][1].InitilizePlatform(4850,400,50,50,3);
		
		levelPlatform[4][0].InitilizePlatform(5400,400,50,50,3);
		levelPlatform[4][1].InitilizePlatform(5600,400,50,50,3);
		levelPlatform[4][2].InitilizePlatform(5800,400,50,50,3);
		
		
		levelPlatform[5][0].InitilizePlatform(6200,200,50,50,3);
		levelPlatform[5][1].InitilizePlatform(6250,200,50,50,3);
		levelPlatform[5][2].InitilizePlatform(6300,200,50,50,3);
		levelPlatform[5][3].InitilizePlatform(6350,200,50,50,3);
		levelPlatform[5][4].InitilizePlatform(6600,200,50,50,3);
		levelPlatform[5][5].InitilizePlatform(6650,200,50,50,3);
		levelPlatform[5][6].InitilizePlatform(6700,200,50,50,3);
		levelPlatform[5][7].InitilizePlatform(6750,200,50,50,3);
		levelPlatform[5][8].InitilizePlatform(6650,400,50,50,3);
		levelPlatform[5][9].InitilizePlatform(6700,400,50,50,3);
		
		levelPlatform[6][0].InitilizePlatform(6700,400,50,50,3);
		levelPlatform[6][1].InitilizePlatform(6700,400,50,50,3);
		levelPlatform[6][2].InitilizePlatform(7300,400,50,50,3);
		levelPlatform[6][3].InitilizePlatform(7350,400,50,50,3);
		
		levelPlatform[7][0].InitilizePlatform(8000,550,50,50,3);
		levelPlatform[7][1].InitilizePlatform(8050,500,50,50,3);
		levelPlatform[7][2].InitilizePlatform(8100,450,50,50,3);
		levelPlatform[7][3].InitilizePlatform(8150,400,50,50,3);
		levelPlatform[7][4].InitilizePlatform(8200,350,50,50,3);
		levelPlatform[7][5].InitilizePlatform(8250,300,50,50,3);
		levelPlatform[7][6].InitilizePlatform(8300,250,50,50,3);
		levelPlatform[7][7].InitilizePlatform(8350,250,50,50,3);
		
		Ground[0].InitilizePlatform(0, 600,2800, 200, 3);
		Ground[1].InitilizePlatform(3000, 600,800, 200, 3);
		Ground[2].InitilizePlatform(4000, 600,7000, 200, 3);
		
		Pipe[0][0].InitilizePlatform(1000, 540, 60, 60, 3);
		Pipe[0][1].InitilizePlatform(1400, 520, 60, 80, 3);
		Pipe[0][2].InitilizePlatform(1800, 500, 60, 100, 3);
		Pipe[0][3].InitilizePlatform(2200, 480, 60, 120, 3);
		Pipe[0][4].InitilizePlatform(7100, 540, 60, 60, 3);
		Pipe[0][5].InitilizePlatform(7900, 540, 60, 60, 3);
		
		levelPlatform[8][0].InitilizePlatform(8550, 590,5, 5, 3);
	}
	
	
	

	public void run()
	{
		level1();
	
	}



	
	
}


